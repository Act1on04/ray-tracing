import kotlin.math.PI
import kotlin.math.abs

fun task06() {

//    TASK 1

    // Тут Матрица трансформации единичная при умножении на луч он не изменится
    savePictureFromTask06("transform_1", Matrix.identity(4))

//    TASK 2
    // Масштабирование на 0,5 в направлении Y
    savePictureFromTask06("transform_2", Matrix.scaling(1.0, 0.5, 1.0))

//    TASK 3
    // Масштабирование на 0,5 в направлении X
    savePictureFromTask06("transform_3", Matrix.scaling(0.5, 1.0, 1.0))

//    TASK 4
    // Поворот навколо осі Z на π/4 та
    // Масштабування на 0.5 в напрямку x
    // как оказалось порядок трансформации играет роль
    // Поэтому менять местами матрицы нельзя
    savePictureFromTask06(
        "transform_4",
        Matrix.rotationZ(PI / 4) * Matrix.scaling(0.5, 1.0, 1.0)
    )

//    TASK 5

    val width = 400
    val height = 400
    val canvas = Canvas(width, height)
    val sphere = Sphere()

    val WALL_Z = 10.0
    val WALL_SIZE = 7.0
    val HALF_SIZE = WALL_SIZE / 2.0
    val PIXEL_SIZE = WALL_SIZE / width

    // Transform Sphere
    sphere.transform = Matrix.rotationZ(PI / 4) * Matrix.scaling(0.5, 1.0, 1.0)

    for (x in 0 until width) {
        val worldX = -HALF_SIZE + PIXEL_SIZE * x
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val worldY = HALF_SIZE - PIXEL_SIZE * y

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(worldX, worldY, WALL_Z) // Точка пикселя
            val origin = Point(0.0, 0.0, -5.0) // Точка наблюдателя
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
    canvas.writeToFile("VilnaTema")

}

fun savePictureFromTask06(fileName: String, transform: Matrix) {
    val width = 400
    val height = 400
    val canvas = Canvas(width, height)
    val sphere = Sphere()

    val WALL_Z = 10.0
    val WALL_SIZE = 7.0
    val HALF_SIZE = WALL_SIZE / 2.0
    val PIXEL_SIZE = WALL_SIZE / width

    // Transform Sphere
    sphere.transform = transform

    for (x in 0 until width) {
        val worldX = -HALF_SIZE + PIXEL_SIZE * x
        for (y in 0 until height) {
            // Рассчитать координаты пикселя в мировой системе координат
            val worldY = HALF_SIZE - PIXEL_SIZE * y

            // Создать луч из точки наблюдателя в направлении пикселя
            val pixel = Point(worldX, worldY, WALL_Z) // Точка пикселя
            val origin = Point(0.0, 0.0, -5.0) // Точка наблюдателя
            val ray = Ray(origin, pixel) // Луч
            val intersections = sphere.intersect(ray)

            if (intersections.count != 0) {
                canvas.setPixel(x, y, Color.fromInt(0xffa500))
            } else {
                canvas.setPixel(x, y, Color.fromInt(0x00cdff))
            }
        }
    }
    // Сохранить холст в файл
    canvas.writeToFile(fileName)

}

fun main() {

    task06()

}