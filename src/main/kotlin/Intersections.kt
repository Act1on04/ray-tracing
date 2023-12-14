class Intersections(vararg intersect: Intersection) {
    private val intersections = intersect.sortedBy { it.t }

    val count: Int
        get() = intersections.size

    fun hit(): Intersection? = intersections.filter { it.t > 0.0 }.minByOrNull { it.t }
//    fun hit(): Intersection? = intersections.firstOrNull { intersection -> intersection.t > 0 }

    operator fun get(index: Int): Intersection = intersections[index]

    fun merge(other: Intersections): Intersections {
        val mergedIntersections = mutableListOf<Intersection>()
        mergedIntersections.addAll(intersections)
        mergedIntersections.addAll(other.intersections)
        return Intersections(*mergedIntersections.toTypedArray())
    }

}
