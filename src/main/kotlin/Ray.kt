import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

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

fun main(args:Array<String>) {

    val width = 800
    val height = 600
    val canvas = Canvas(width, height)


    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val worldX = (width / 2) - (x + 0.5)
            val worldY = (height / 2) - (y + 0.5)

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(worldX, worldY, 0.0) // Точка пикселя
            val origin = Point(0.0, 0.0, -100.0) // Точка наблюдателя

            val ray = Ray(origin, pixel) // Луч

            // Рассчитать цвет для пикселя
            // Здесь вы должны реализовать логику трассировки лучей, которая зависит от вашей сцены и объектов
            // den Richtungsvektor des zum Pixel erzeugten Strahls
            // используем вектор направления луча в качестве цвета
            val color = Color(abs(ray.direction.x) , abs(ray.direction.y), abs(ray.direction.z))

            // Записать цвет на холст
            canvas.setPixel(x, y, color)
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("1")

    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
//            val worldX = (width / 2) - (x + 0.5)
//            val worldY = (height / 2) - (y + 0.5)

            // Создать луч из точки наблюдателя в направлении пикселя
//            val pixel = Point(worldX, worldY, 0.0) // Точка пикселя
//            val origin = Point(0.0, 0.0, -100.0) // Точка наблюдателя
//            val ray = Ray(origin, pixel) // Луч

            // Рассчитать цвет для пикселя
            // Здесь вы должны реализовать логику трассировки лучей, которая зависит от вашей сцены и объектов
            // die x-und y- Koordinaten des Pixels (z ist 0) (dieser Wert wird manchmal auch als Launch ID bezeichnet.)
            // координаты x и y Canvas (z равно 0) (это значение иногда называют идентификатором запуска).
            // Тут цвет будет расходиться от левого верхнего угла, так как там начало координат Canvas
            val color = Color(x.toDouble() / width, y.toDouble() / height, 0.0)
            // координаты x и y pixel (z равно 0).
            // Тут цвет будет расходиться от центра, так как начало координат в центре
//            val color = Color(abs(pixel.x.toDouble() / width), abs(pixel.y.toDouble() / height), 0.0)

            // Записать цвет на холст
            canvas.setPixel(x, y, color)
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("2")

    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val worldX = (width / 2) - (x + 0.5)
            val worldY = (height / 2) - (y + 0.5)

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(worldX, worldY, 0.0) // Точка пикселя
            val origin = Point(0.0, 0.0, -100.0) // Точка наблюдателя
//            val ray = Ray(origin, pixel) // Луч

            // Рассчитать цвет для пикселя
            // Здесь вы должны реализовать логику трассировки лучей, которая зависит от вашей сцены и объектов
            // die Länge des Vektors vom Auge zum Pixel
            // В качестве цвета длина вектора от глаза до пикселя
            val lenDirection = ( (pixel - origin).magnitude() / sqrt((width / 2.0).pow(2) + (height / 2.0).pow(2)) ).coerceIn(0.0, 1.0)
            val color = Color(lenDirection, lenDirection, lenDirection)

            // Записать цвет на холст
            canvas.setPixel(x, y, color)
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("3")

}