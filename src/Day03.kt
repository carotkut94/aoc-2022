fun main() {

    val input = readInput("Day03")
    val map = createMap()
    println(part1(input, map))
    println(part2(input, map))
}

fun part1(input:List<String>, map:Map<Char,Int>):Int{
    return input
        .map { it.chunked(it.length / 2) }
        .sumOf { item -> map[item.map { it.toSet() }.reduce { left, right -> left intersect right}.first()]?:0 }
}

fun part2(input:List<String>, map:Map<Char,Int>):Int{
    return input.chunked(3).sumOf { s ->
        val s1 = s[0].toSet()
        val s2 = s[1].toSet()
        val s3 = s[2].toSet()
        val c = (s1 intersect s2 intersect s3).single()
        val p = map[c]
        p?:0
    }
}

fun createMap(): Map<Char, Int> {
    return buildMap {
        for (i in 1..26) {
            putIfAbsent('a' + i - 1, i)
            putIfAbsent('A' + i - 1, i + 26)
        }
    }
}
