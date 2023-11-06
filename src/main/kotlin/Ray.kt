import kotlin.math.abs
import kotlin.math.sqrt

class Ray(val origin:Point, val direction:Vector) {
    // Конструктор, который создает луч из двух точек
    constructor(origin: Point, target: Point) : this(origin, (target - origin).normalize())

    // Метод, который вычисляет координаты точки на луче для значения параметра t
    fun pointAt(t:Double):Point = origin + direction * t

    fun pointAt(t:Int) = pointAt(t.toDouble())

    override fun toString(): String = "[Ray, origin:$origin, direction:$direction]"
}

fun main(args:Array<String>) {

    val width = 400
    val height = 400
    val canvas = Canvas(width, height)


    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат

//            function ray_for_pixel(camera, px, py)
//                # the offset from the edge of the canvas to the pixel's center
//                xoffset ← (px + 0.5) * camera.pixel_size
//                yoffset ← (py + 0.5) * camera.pixel_size
//
//                # the untransformed coordinates of the pixel in world space.
//                # (remember that the camera looks toward -z, so +x is to the *left*.)
//                world_x ← camera.half_width - xoffset
//                world_y ← camera.half_height - yoffset
//
//                # using the camera matrix, transform the canvas point and the origin,
//                # and then compute the ray's direction vector.
//                # (remember that the canvas is at z=-1)
//                pixel ← inverse(camera.transform) * point(world_x, world_y, -1)
//                origin ← inverse(camera.transform) * point(0, 0, 0)
//                direction ← normalize(pixel - origin)
//                return ray(origin, direction)
//            end function

            val world_x = (width / 2) - (x + 0.5)
            val world_y = (height / 2) - (y + 0.5)
//            println("x = $x, y=$y")
//            println("px = $px, py=$py")

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(world_x, world_y, 0.0) // Точка пикселя
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
            val px = x - width / 2 + 0.5
            val py = y - height / 2 + 0.5

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(px, py, 0.0) // Точка пикселя
            val origin = Point(0.0, 0.0, -100.0) // Точка наблюдателя
            val ray = Ray(origin, pixel) // Луч

            // Рассчитать цвет для пикселя
            // Здесь вы должны реализовать логику трассировки лучей, которая зависит от вашей сцены и объектов
            // die x-und y- Koordinaten des Pixels (z ist 0) (dieser Wert wird manchmal auch als Launch ID bezeichnet.)
            // координаты x и y пикселя (z равно 0) (это значение иногда называют идентификатором запуска).
            val color = Color(pixel.x, pixel.y, 0.0)

            // Записать цвет на холст
            canvas.setPixel(x, y, color)
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("2")

    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val px = x - width / 2 + 0.5
            val py = y - height / 2 + 0.5

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(px, py, 0.0) // Точка пикселя
            val origin = Point(0.0, 0.0, -100.0) // Точка наблюдателя
            val ray = Ray(origin, pixel) // Луч

            // Рассчитать цвет для пикселя
            // Здесь вы должны реализовать логику трассировки лучей, которая зависит от вашей сцены и объектов
            // die Länge des Vektors vom Auge zum Pixel
            // В качестве цвета длина вектора от глаза до пикселя
            val lenDirection = (pixel- origin).magnitude() / 255.0
            val len2Direction = ray.direction.magnitude() / 255.0
            val color = Color(lenDirection, lenDirection, lenDirection)

            // Записать цвет на холст
            canvas.setPixel(x, y, color)
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("3")

}