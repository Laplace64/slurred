package net.laplace.slurred.parser

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextDecoration


@Suppress("MemberVisibilityCanBePrivate")
class State(
    val text: String,
    val color: Int?,
    val italic: Boolean,
    val bold: Boolean,
    val underlined: Boolean,
    val strikethrough: Boolean,
    val obfuscated: Boolean,
    val inMessage: Boolean,
) {
    private constructor(builder: StateBuilder) : this(
        text = builder.text,
        color = builder.color,
        italic = builder.italic,
        bold = builder.bold,
        underlined = builder.underlined,
        strikethrough = builder.strikethrough,
        obfuscated = builder.obfuscated,
        inMessage = builder.inMessage,
    )

    @OptIn(ExperimentalStdlibApi::class)
    override fun toString(): String {
        return "State(text=\"$text\", color=\"${color?.toHexString()}\", italic=$italic, bold=$bold, underlined=$underlined, strikethrough=$strikethrough, obfuscated=$obfuscated, inMessage=$inMessage)"
    }

    fun toComponent() = text().content(
        text
    ).color {
        color ?: Colors.WHITE.hex
    }.decorations(
        mapOf(
            TextDecoration.ITALIC to italic.set(),
            TextDecoration.BOLD to bold.set(),
            TextDecoration.UNDERLINED to underlined.set(),
            TextDecoration.STRIKETHROUGH to strikethrough.set(),
            TextDecoration.OBFUSCATED to obfuscated.set(),
        )
    )

    class StateBuilder {
        var text: String = ""
        var color: Int? = null
        var italic: Boolean = false
        var bold: Boolean = false
        var underlined: Boolean = false
        var strikethrough: Boolean = false
        var obfuscated: Boolean = false
        var inMessage: Boolean = false

        fun text(text: String) = apply { this.text = text }
        fun color(color: Int?) = apply { this.color = color }
        fun italic(italic: Boolean) = apply { this.italic = italic }
        fun bold(bold: Boolean) = apply { this.bold = bold }
        fun underlined(underlined: Boolean) = apply { this.underlined = underlined }
        fun strikethrough(strikethrough: Boolean) = apply { this.strikethrough = strikethrough }
        fun obfuscated(obfuscated: Boolean) = apply { this.obfuscated = obfuscated }

        fun build() = State(this)
        fun default() = StateBuilder()

        fun clearFormatting() = apply {
            italic = false
            bold = false
            underlined = false
            strikethrough = false
            obfuscated = false
        }
    }

    companion object {
        inline fun build(block: StateBuilder.() -> Unit) = StateBuilder().apply(block).build()
    }
}

fun Boolean.set() = if (this) TextDecoration.State.TRUE else TextDecoration.State.FALSE