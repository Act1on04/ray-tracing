import kotlin.math.abs

data class Intersection(val t: Double, val shape: Sphere) {

//    override fun equals(other: Any?): Boolean {
//        if (other is Intersection) {
//            return this.t == other.t && this.shape == other.shape
//        }
//        return false
//    }

    // Реализация оператора сравнения для сценария "comparing intersections"
    operator fun compareTo(other: Intersection): Int {
        return this.t.compareTo(other.t)
    }

}
