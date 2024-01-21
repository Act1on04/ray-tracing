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

    fun prepareHitInfo(ray: Ray ): HitInfo {
        val point = ray.pointAt(t)
        val eyeV = -ray.direction
        val normalV = shape.normalAt(point)
        val reflectV = ray.direction.reflect(normalV)
        return HitInfo(shape, t, point, eyeV, normalV, reflectV)

    }

}
