import kotlin.math.abs

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
            val pixel = Point(0.01 * worldX, 0.01 * worldY, 0.0) // Точка пикселя
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
            val pixel = Point(0.01 * worldX, 0.01 * worldY, 0.0) // Точка пикселя
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
            val pixel = Point(0.01 * worldX, 0.01 * worldY, 0.0) // Точка пикселя
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
            val pixel = Point(0.01 * worldX, 0.01 * worldY, 0.0) // Точка пикселя
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