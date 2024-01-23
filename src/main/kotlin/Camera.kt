import kotlin.math.tan

class Camera(val width: Int, val height: Int, val fov: Double) {

//    init {
//        // Convert fov from degrees to radians
//        fov = Math.toRadians(fov)
//    }

    var transform: Matrix = Matrix.identity(4)

    constructor(width: Int, height: Int, fov: Double, transform: Matrix) : this(width, height, fov) {
        this.transform = transform
    }

    constructor(width: Int, height: Int, fov: Double, position: Point, lookAt: Point, up: Vector) : this(width, height, fov) {
        this.transform = Matrix.viewTransform(position, lookAt, up)
    }

    private val halfView = tan(Math.toRadians(fov) / 2.0)
    private val aspectRatio = width.toDouble() / height.toDouble()

    private val halfHeight = if (aspectRatio < 1) halfView  else halfView / aspectRatio
    private val halfWidth = if (aspectRatio >= 1) halfView else halfView * aspectRatio

    val pixelSize = (halfWidth * 2.0) / width

    fun generateRay(pixelX: Double, pixelY: Double): Ray {
//        Этот метод работает следующим образом:
//        1. Определяет смещение от края полотна до центра пикселя.
//        2. Вычисляет координаты пикселя в мировом пространстве.
//        3. Трансформирует координаты пикселя и начало координат камеры с помощью обратной матрицы трансформации вида.
//        4. Вычисляет направление луча.
//        5. Возвращает луч.

        // Визначити зміщення від краю полотна до центру пікселя
        // Calculate pixel offset from canvas edge to center
        val xOffset = (pixelX + 0.5) * pixelSize
        val yOffset = (pixelY + 0.5) * pixelSize

        // Обчислюємо координати пікселя у світових координатах. Оскільки
        // VPN проходить через центр полотна, то зміщення потрібно відняти від половини
        // від ширини/висоти потрібно відняти
        // Calculate pixel coordinates in world space. Since
        // the VPN passes through the center of the canvas, the offset
        // needs to be subtracted from half the
        // width/height.
        val worldX = halfWidth - xOffset
        val worldY = halfHeight - yOffset

        // Перетворити обчислені координати та початок координат камери з коефіцієнтом
        // Трансформуємо обчислені координати та початок координат камери за допомогою трансформації виду (полотно знаходиться при z=-1)
        // Transform the calculated coordinates and camera origin by the inverse view transform
        val pixel = (transform.inverse() * Point(worldX, worldY, -1.0)).asPoint()
        val origin = (transform.inverse() * Point(0.0, 0.0, 0.0)).asPoint()

        // Обчислити напрямок променя
        // Calculate the direction of the ray
        val direction = (pixel - origin).normalize()

        // Повернути промінь
        // Return the ray
        return Ray(origin, direction)
    }

}

fun main() {

    val camera1 = Camera(800, 600, 60.0)
    println(camera1.transform)

    val camera2 = Camera(800, 600, 60.0, Matrix.identity(4))
    println(camera2.transform)

    val camera3 = Camera(800, 600, 60.0, Point(1, 3, 2), Point(4, -2, 8), Vector(1, 1, 0))
    println(camera3.transform)
}
