package net.laplace.slurred.transformation

class SwapCase(
    private val chance: Double = 0.1,
): Transformation {
    override fun transform(input: String): String {
        return input.map {
            if (Math.random() < chance) {
                it.swapCase()
            } else {
                it
            }
        }.joinToString("")
    }
}

fun Char.swapCase(): Char {
    return if (this.isUpperCase()) {
        this.lowercaseChar()
    } else {
        this.uppercaseChar()
    }
}