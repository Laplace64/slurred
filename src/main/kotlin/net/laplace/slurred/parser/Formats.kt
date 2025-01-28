package net.laplace.slurred.parser

enum class Formats(
    val code: Char,
) {
    BOLD('l'),
    STRIKETHROUGH('m'),
    UNDERLINED('n'),
    ITALIC('o'),
    OBFUSCATED('k'),
}

fun formatCode(code: Char): Formats? {
    return Formats.entries.firstOrNull { it.code == code }
}
