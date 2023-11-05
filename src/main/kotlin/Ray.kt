class Ray(val origin:Point, val direction:Vector) {
    // Конструктор, который создает луч из двух точек
    constructor(origin: Point, target: Point) : this(origin, (target - origin).normalize())

    // Метод, который вычисляет координаты точки на луче для значения параметра t
    fun pointAt(t:Double):Point = origin + direction * t

    fun pointAt(t:Int) = pointAt(t.toDouble())

    override fun toString(): String = "[Ray, origin:$origin, direction:$direction]"
}