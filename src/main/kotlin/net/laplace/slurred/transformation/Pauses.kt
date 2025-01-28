package net.laplace.slurred.transformation

class Pauses(
    private val chance: Double = 0.2,
    private val replacements: List<String> = PAUSE_REPLACEMENTS,
): Transformation {
    override fun transform(input: String): String {
        return if (Math.random() < chance) {
            input + replacements.random()
        } else {
            input
        }
    }

    companion object {
        private val PAUSE_REPLACEMENTS = listOf(
            "...",
            "..",
            ",",
            ", uh",
            ", er",
        )
    }
}