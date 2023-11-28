class Intersections(vararg intersections: Intersection) {
    private val intersections = intersections.sortedBy { it.t }

    val count: Int
        get() = intersections.size

    operator fun get(index: Int): Intersection {
        return intersections[index]
    }

}