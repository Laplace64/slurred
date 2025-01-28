package net.laplace.slurred.parser


class Parser(
    private val actionColor: Int,
    private val messageColor: Int = Colors.WHITE.hex,
    private val quotationColor: Int = Colors.WHITE.hex,
) {
    fun parse(text: String): List<State> {
        val states = mutableListOf<State>()

        var builder = State.StateBuilder().default().apply {
            color(actionColor)
        }

        val pat = "(&#[0-9a-fA-F]{6})|(&.)|(\")|(\\s+)|([^&\" ]+)"
            .toRegex(setOf(RegexOption.MULTILINE, RegexOption.IGNORE_CASE))

        val matches = pat.findAll(text)
        for (match in matches) {
            val grp = match.groups[0] ?: continue
            val group = grp.value

            val part = group.trim()

            // check if this is a color code
            if (part.startsWith('&')) {
                if (part.length == 2) {
                    // if reset
                    if (part[1] == 'r') {
                        val last = states.lastOrNull()

                        // decide what color code we should be using right now
                        val color = last?.let {
                            when(it.inMessage) {
                                true -> messageColor
                                false -> actionColor
                            }
                        }

                        builder = builder.apply {
                            clearFormatting()
                            color(color)
                        }
                    } else {
                        val code = part[1]

                        colorCode(code)?.let {
                            builder = builder.color(it.hex)
                        }
                        formatCode(code)?.let {
                            when (it) {
                                Formats.BOLD -> builder.bold(true)
                                Formats.STRIKETHROUGH -> builder.strikethrough(true)
                                Formats.UNDERLINED -> builder.underlined(true)
                                Formats.ITALIC -> builder.italic(true)
                                Formats.OBFUSCATED -> builder.obfuscated(true)
                            }
                        }

                    }
                } else {
                    // custom color code

                    val color = part.substring(2)

                    builder = builder.apply {
                        color(color.toInt(16))
                    }
                }
            } else if (part == "" && group.isNotEmpty()) {
                // whitespaces found, which is why part is empty
                states.addLast(builder.text(" ").build())

            } else if (part.first() == '"') {
                // quote found
                builder = builder.apply {
                    inMessage = !inMessage
                }


                builder = builder.apply {
                    when (inMessage) {
                        true -> color(messageColor)
                        false -> color(actionColor)
                    }

                    clearFormatting()
                }

                states.addLast(State.build {
                    text("\"")
                    color(quotationColor)
                })

            } else if (group.isNotEmpty()) {
                // normal text
                builder = builder.apply {
                    text(group)
                }

                states.addLast(builder.build())
            }
        }

        return states
    }


}