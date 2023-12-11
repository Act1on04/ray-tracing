
class Ray(val origin:Point, val direction:Vector) {
    // Конструктор, который создает луч из двух точек
    constructor(origin: Point, target: Point) : this(origin, (target - origin).normalize())

    // Метод, который вычисляет координаты точки на луче для значения параметра t
    fun pointAt(t:Double):Point = origin + direction * t

    fun pointAt(t:Int) = pointAt(t.toDouble())

    override fun toString(): String = "[Ray, origin:$origin, direction:$direction]"

    fun transform(m:Matrix):Ray {
        val newOrigin = m * origin
        val newDir = m * direction

        return Ray(Point(newOrigin.x, newOrigin.y, newOrigin.z), Vector(newDir.x, newDir.y, newDir.z))
    }

}

fun main() {

}