fun main() {

    fun prepare(input:String): List<Long> {
        return input.split("\n\n").map { it.split("\n") }.map {
            it.sumOf { item -> item.toLong() }
        }
    }

    fun part1(input: List<Long>): Triple<Long, Long, Long> {
        var (first, second, third) = arrayOf(Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE)

        for(e in input){
            if(e>first){
                third = second
                second = first
                first = e
            }else if (e > second) {
                third = second
                second = e
            }else if (e > third) {
                third = e
            }
        }

        return Triple(first, second, third)
    }

    fun part2(triple: Triple<Long, Long, Long>): Long {
        return triple.first+triple.second+triple.third
    }

    val input = readAsString("Day01")
    val processedInput = prepare(input)
    val triple = part1(processedInput)
    println(triple.first)
    println(part2(triple))
}
