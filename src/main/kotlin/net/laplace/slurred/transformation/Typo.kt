package net.laplace.slurred.transformation

class Typo(
    private val replacements: Map<Char, List<String>> = TYPO_REPLACEMENTS,
    private val chance: Double = 0.05,
): Transformation {
    override fun transform(input: String): String {
        val stemList: MutableList<String> = mutableListOf()
        input.forEach {
            if (Math.random() < chance && replacements.containsKey(it)) {
                val replacement = replacements[it]!!.random()

                if (it.isUpperCase()) {
                    replacement.replaceFirstChar { c -> c.uppercase() }
                }

                stemList.add(replacement)
            } else {
                stemList.add(it.toString())
            }
        }

        return stemList.joinToString("")
    }

    companion object {
        private val TYPO_REPLACEMENTS = mapOf(
            's' to listOf("sh", "z"),
            'c' to listOf("k"),
            't' to listOf("d"),
            'i' to listOf("ee"),
            'o' to listOf("oo"),
        )
    }
}