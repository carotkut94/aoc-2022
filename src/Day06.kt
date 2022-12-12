fun main() {

    val input = readAsString("Day06")

    println(solve(input, 4))
    println(solve(input, 14))
}

fun solve(input:String, size:Int):Int{
    input.windowed(size).forEachIndexed { index, item ->
        if(item.toSet().size == size) return index+size
    }
    return -1
}
