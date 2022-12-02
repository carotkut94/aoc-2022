fun main() {

    fun prepare(input: String): List<String> {
        return input.split("\n")
    }

    fun part1(input: List<String>): Int {
        var total = 0
        input.forEach {
            val (move1, move2) = it.split(" ")
            val v1 = getValue(move1)
            val v2 = getValue(move2)
            total += if (v1 == v2) {
                3 + v2
            } else if (v2 - v1 == 2) {
                v2
            } else if (v1 - v2 == 2 || v2 > v1) {
                v2 + 6
            } else {
                v2
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val l = input.map {
            when (it) {
                "A X" -> 3
                "A Y" -> 4
                "A Z" -> 8
                "B X" -> 1
                "B Y" -> 5
                "B Z" -> 9
                "C X" -> 2
                "C Y" -> 6
                else -> 7
            }
        }
        return l.sum()
    }

    val input = readAsString("Day02")
    val i = prepare(input)
    println(part1(i))
    println(part2(i))
}

fun getValue(face: String): Int {
    return when (face) {
        "X", "A" -> 1
        "Y", "B" -> 2
        else -> 3
    }
}