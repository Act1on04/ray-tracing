class Intersections(vararg intersections: Intersection) {
    private var intersections = intersections.sortedBy { it.t }

    val count: Int
        get() = intersections.size

    fun hit(): Intersection? = intersections.filter { it.t > 0.0 }.minByOrNull { it.t }
//    fun hit(): Intersection? = intersections.firstOrNull { intersection -> intersection.t > 0 }

    operator fun get(index: Int): Intersection = intersections[index]


}