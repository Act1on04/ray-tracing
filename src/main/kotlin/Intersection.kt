import kotlin.math.abs

data class Intersection(val t:Double, val obj:Sphere) {

//    override fun equals(other: Any?): Boolean {
//        if (other is Intersection) {
//            return this.t == other.t && this.obj == other.obj
//        }
//        return false
//    }

    // Реализация оператора сравнения для сценария "comparing intersections"
    operator fun compareTo(other: Intersection): Int {
        return this.t.compareTo(other.t)
    }

}
