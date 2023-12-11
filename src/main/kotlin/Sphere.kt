import kotlin.math.sqrt

class Sphere  : Shape() {
    override fun localIntersect(ray: Ray): Intersections {
        val sphereToRay = ray.origin - Point(0, 0, 0)
        val a = ray.direction.dot(ray.direction)
        val b = 2 * ray.direction.dot(sphereToRay)
        val c = sphereToRay.dot(sphereToRay) - 1

        val discriminant = b * b - 4 * a * c

        if (discriminant < 0) {
            return Intersections()
        }

        val t1 = (-b - sqrt(discriminant)) / (2 * a)
        val t2 = (-b + sqrt(discriminant)) / (2 * a)

        return Intersections(
            Intersection(t1, this),
            Intersection(t2, this)
        )
    }

    override fun normalAt(point: Point): Vector {
        return (point - Point(0, 0, 0)).normalize()
    }
}

fun main() {


}