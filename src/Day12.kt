fun main() {
    val input = readInput("Day12")
    lateinit var start: Pair<Int, Int> // starting indexes
    lateinit var end: Pair<Int, Int> // target indexes
    val allA: MutableList<Pair<Int, Int>> = mutableListOf() // all positions with minimum elevation
    val grid = buildList {
        input.forEach {
            add(it.toCharArray())
        }
    }.toMutableList()

    grid.forEachIndexed { i, row ->
        row.forEachIndexed { j, value ->
            when (value) {
                'S' -> {
                    start = i to j
                    allA.add(i to j)
                    grid[i][j] = 'a' // let's mark S as 'a' so that while traversing we don't have to do any special handling for 'S' on + -
                }

                'E' -> {
                    end = i to j
                    grid[i][j] = 'z' // AS E is max, and z is also max, replace E with z, so that we don't need special handling for E when handling operations + - and store the location of E as end
                }

                'a' -> {
                    allA.add(i to j) // store all minimum points to solve for part 2
                }
            }
        }
    }

    fun traverse(grid: MutableList<CharArray>, start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
        val moves = listOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)
        val q = ArrayDeque<Pair<Pair<Int, Int>, Int>>()
        q.add(start to 0)
        val visited = mutableSetOf<Pair<Int, Int>>()
        while (q.iterator().hasNext()) {
            val (position, distance) = q.removeFirst()
            if (position == end) {
                return distance
            }
            if (!visited.contains(position)) {
                visited.add(position)
                val (x, y) = position
                moves.forEach { (dx, dy) ->
                    if (x + dx in 0 until grid.size && (y + dy) in 0 until grid[0].size && (grid[x + dx][y + dy].code - grid[x][y].code) <= 1) {
                        q.add(element = ((x + dx) to (y + dy)) to distance + 1)
                    }
                }
            }
        }

        return Int.MAX_VALUE
    }

    println(traverse(grid, start, end))

    val allStart = buildList {
            allA.forEach {
                add(traverse(grid, it, end))
            }
        }.min()

    print(allStart)
}