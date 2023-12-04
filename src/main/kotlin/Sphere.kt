import kotlin.math.abs
import kotlin.math.sqrt

class Sphere  : Shape() {
    override fun intersect(ray: Ray): Intersections {
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

    val width = 800
    val height = 600
    val canvas = Canvas(width, height)
    val sphere = Sphere()

//    TASK 1
    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val worldX = (width / 2) - (x + 0.5)
            val worldY = (height / 2) - (y + 0.5)

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(0.01 * worldX,0.01 * worldY, 0.0) // Точка пикселя
            val origin = Point(0.0, 0.0, -10.0) // Точка наблюдателя
            val ray = Ray(origin, pixel) // Луч
//            val intersections = sphere.intersect(ray)

//            if (intersections.count != 0) {
                val r = abs(ray.direction.x)
                val g = abs(ray.direction.y)
                val b = 0.0
                canvas.setPixel(x, y, Color(r, g, b))
//            } else {
//                canvas.setPixel(x, y, Color.fromInt(0x00cdff))
//            }
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("sphere1")


//    TASK 2
    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val worldX = (width / 2) - (x + 0.5)
            val worldY = (height / 2) - (y + 0.5)

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(0.01 * worldX,0.01 * worldY, 0.0) // Точка пикселя
            val origin = Point(0.0, 0.0, -10.0) // Точка наблюдателя
            val ray = Ray(origin, pixel) // Луч
            val intersections = sphere.intersect(ray)
//            println("intersection: ${intersection[0]}, ${intersection[1]}")

            if (intersections.count != 0) {
                canvas.setPixel(x, y, Color.fromInt(0xffa500))
            } else {
                canvas.setPixel(x, y, Color.fromInt(0x00cdff))
            }
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("sphere2")

//    TASK 3
    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val worldX = (width / 2) - (x + 0.5)
            val worldY = (height / 2) - (y + 0.5)

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(0.01 * worldX,0.01 * worldY, 0.0) // Точка пикселя
            val origin = Point(0.0, 0.0, -10.0) // Точка наблюдателя
            val ray = Ray(origin, pixel) // Луч
            val intersections = sphere.intersect(ray)

            if (intersections.count != 0) {
//                var hit = intersections.hit()
//                val t = hit!!.t - 9.0
                val t = intersections[0].t - 9.0
                canvas.setPixel(x, y, Color(t, t, t))
            } else {
                canvas.setPixel(x, y, Color.fromInt(0x00cdff))
//                canvas.setPixel(x, y, Color.WHITE)
            }
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("sphere3")

//    TASK 4
    for (x in 0 until width) {
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val worldX = (width / 2) - (x + 0.5)
            val worldY = (height / 2) - (y + 0.5)

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(0.01 * worldX,0.01 * worldY, 0.0) // Точка пикселя
            val origin = Point(0.0, 0.0, -10.0) // Точка наблюдателя
            val ray = Ray(origin, pixel) // Луч
            val intersections = sphere.intersect(ray)

            if (intersections.count != 0) {
                val vector = sphere.normalAt(ray.pointAt(intersections[0].t))
                val r = abs(vector.x)
                val g = abs(vector.y)
                val b = abs(vector.z)
                canvas.setPixel(x, y, Color(r, g, b))
            } else {
                canvas.setPixel(x, y, Color.fromInt(0x00cdff))
            }
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile("sphere4")




}