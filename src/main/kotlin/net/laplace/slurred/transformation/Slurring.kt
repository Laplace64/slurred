package net.laplace.slurred.transformation

class Slurring(
    private val chance: Double = 0.3,
    private val replacements: List<String> = SLURRING_REPLACEMENTS,
): Transformation {

    override fun transform(input: String) = run {
        replacements.firstOrNull {
            input.endsWith(it)
        }?.let {
            if (Math.random() < chance) {
                return input.dropLast(it.length) + it
            } else {
                return input
            }
        } ?: input
    }

    companion object {
        private val SLURRING_REPLACEMENTS = listOf(
            "ing",
            "er",
        )
    }
}