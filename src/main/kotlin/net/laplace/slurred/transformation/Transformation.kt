package net.laplace.slurred.transformation

interface Transformation {
    fun transform(input: String): String
}

fun List<Transformation>.transform(input: String) = input.split("\\s+".toRegex()).joinToString(" ") {
    val pat = "^(\\w*)(\\W*)$".toRegex()
    val match = pat.matchEntire(it)

    val stem = match?.groups?.get(1)?.value ?: it
    val suffix = match?.groups?.get(2)?.value ?: ""

    val transformedStem = this.fold(stem) { acc, transformation -> transformation.transform(acc) }

    transformedStem + suffix
}