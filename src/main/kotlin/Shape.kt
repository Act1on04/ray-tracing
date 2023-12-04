abstract class Shape {

    var transform = Matrix.identity(4)

    fun intersect(ray: Ray): Intersections =
        localIntersect(ray.transform(transform.inverse()))


    abstract fun localIntersect(ray: Ray): Intersections

    abstract fun normalAt(point: Point): Vector
}