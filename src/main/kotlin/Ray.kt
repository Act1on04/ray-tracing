class Ray(val origin:Point, val direction:Vector) {
    // Конструктор, который создает луч из двух точек
    constructor(origin: Point, target: Point) : this(origin, (target - origin).normalize())

    // Метод, который вычисляет координаты точки на луче для значения параметра t
    fun pointAt(t:Double):Point = origin + direction * t

    fun pointAt(t:Int) = pointAt(t.toDouble())

    override fun toString(): String = "[Ray, origin:$origin, direction:$direction]"
}

fun main(args:Array<String>) {

    val width = 800
    val height = 600
    val canvas = Canvas(width, height)

    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val px = x.toDouble() / width
            val py = y.toDouble() / height
            val pz = 0.0

            // Создать луч из точки наблюдателя в направлении пикселя
            val eye = Point(0.0, 0.0, -1.0) // Точка наблюдателя
            val pixel = Point(px, py, pz) // Точка пикселя
            val ray = Ray(eye, pixel) // Луч

            // Рассчитать цвет для пикселя
            // Здесь вы должны реализовать логику трассировки лучей, которая зависит от вашей сцены и объектов
            // Для тестового изображения мы просто используем вектор направления луча в качестве цвета
            val color = Color(ray.direction.x, ray.direction.y, ray.direction.z)

            // Записать цвет на холст
            canvas.setPixel(x, y, color)
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("1")

    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val px = x.toDouble() / width
            val py = y.toDouble() / height
            val pz = 0.0

            // Создать луч из точки наблюдателя в направлении пикселя
            val eye = Point(0.0, 0.0, -1.0) // Точка наблюдателя
            val pixel = Point(px, py, pz) // Точка пикселя
            val ray = Ray(eye, pixel) // Луч

            // Рассчитать цвет для пикселя
            // Здесь вы должны реализовать логику трассировки лучей, которая зависит от вашей сцены и объектов
            val color = Color(pixel.x, pixel.y, 0.0)

            // Записать цвет на холст
            canvas.setPixel(x, y, color)
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("2")

}