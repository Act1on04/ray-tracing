abstract class Shape {
    abstract fun intersect(ray: Ray): Intersections
    abstract fun normalAt(point: Point): Vector
}