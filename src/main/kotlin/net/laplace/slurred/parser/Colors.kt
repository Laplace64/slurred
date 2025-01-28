package net.laplace.slurred.parser

enum class Colors(
    val hex: Int,
    val code: Char,
) {
    BLACK(0x000000, '0'),
    DARK_BLUE(0x0000AA, '1'),
    DARK_GREEN(0x00AA00, '2'),
    DARK_AQUA(0x00AAAA, '3'),
    DARK_RED(0xAA0000, '4'),
    DARK_PURPLE(0xAA00AA, '5'),
    GOLD(0xFFAA00, '6'),
    GRAY(0xAAAAAA, '7'),
    DARK_GRAY(0x555555, '8'),
    BLUE(0x5555FF, '9'),
    GREEN(0x55FF55, 'a'),
    AQUA(0x55FFFF, 'b'),
    RED(0xFF5555, 'c'),
    LIGHT_PURPLE(0xFF55FF, 'd'),
    YELLOW(0xFFFF55, 'e'),
    WHITE(0xFFFFFF, 'f'),
}

fun colorCode(code: Char): Colors? {
    return Colors.entries.firstOrNull { it.code == code }
}