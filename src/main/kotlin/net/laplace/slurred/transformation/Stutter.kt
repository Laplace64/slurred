package net.laplace.slurred.transformation

class Stutter(
    private val chance: Double = 0.1,
): Transformation {
    override fun transform(input: String): String {
        return if (input[0].isLetter() && Math.random() < chance) {
            val firstChar = input[0]
            "$firstChar-${input.drop(1)}"
        } else {
            input
        }
    }
}