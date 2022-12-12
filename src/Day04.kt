fun prepare(input: List<String>): List<Pair<IntRange, IntRange>> {
    return input.map { line ->
        line.split(",")
            .let { (left, right) ->
                Pair(
                    left.split("-").let { (first, last) -> first.toInt()..last.toInt() },
                    right.split("-").let { (first, last) -> first.toInt()..last.toInt() }
                )
            }
    }
}

fun main() {
    val rawInput = readInput("Day04")
    val processedInput = prepare(rawInput)
    println(part1(processedInput))
    println(part2(processedInput))
}

fun part1(processedInput: List<Pair<IntRange, IntRange>>): Int {
    return processedInput.count { it.first inRange it.second || it.second inRange it.first }
}

fun part2(processedInput: List<Pair<IntRange, IntRange>>): Int {
    return processedInput.count { it.first intersect it.second }
}

infix fun IntRange.inRange(other: IntRange): Boolean = other.first in this && other.last in this

infix fun IntRange.intersect(other: IntRange): Boolean =
    first in other || last in other || other.first in this || other.last in this
