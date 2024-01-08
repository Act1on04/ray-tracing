abstract class Shape {

    var transform = Matrix.identity(4)
    var material = Material()

    fun intersect(ray: Ray): Intersections =
        localIntersect(ray.transform(this.transform.inverse()))


    abstract fun localIntersect(ray: Ray): Intersections

    fun normalAt(point: Point): Vector {
        // Transformieren des worldPoint in das lokale Koordinatensystems
        // Преобразуем worldPoint в локальную систему координат
        val localPoint = this.transform.inverse() * point
        // Berechne die lokale Normale
        // Вычисляем локальную нормаль
        val localNormal = localNormalAt(localPoint)
        // Transformiere die lokale Normale in das Weltkoordinatensystem
        // Преобразуем локальную нормаль в мировую систему координат
        val worldNormal = (this.transform.inverse().transpose() * localNormal).asVector()
        return worldNormal.normalize()
    }

    abstract fun localNormalAt(point: Tuple): Vector

}