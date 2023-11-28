class Intersections(vararg intersections: Intersection) {
    private val intersections = intersections.sortedBy { it.t }

    val count: Int
        get() = intersections.size

    fun hit(): Intersection? = intersections.filter { it.t > 0.0 }.minByOrNull { it.t }

    operator fun get(index: Int): Intersection {
        return intersections[index]
    }


}