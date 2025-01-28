package net.laplace.slurred.transformation

import kotlin.random.Random

class Repetition(
    private val chance: Double = 0.1,
    private val replacements: List<String> = REPETITION_REPLACEMENTS,
): Transformation {
    override fun transform(input: String) = run {
        input.map {
            if (replacements.contains(it.lowercase()) && Math.random() < chance) {
                it.toString() + REPETITION_REPLACEMENTS.random().repeat(Random.nextInt(1, 3))
            } else {
                it.toString()
            }
        }.joinToString("")
    }

    companion object {
        private val REPETITION_REPLACEMENTS = listOf(
            "a",
            "e",
            "i",
            "o",
            "u",
        )
    }
}