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

}

fun main() {
    // Пример использования
    val camera1 = Camera(800, 600, 60.0)
    println(camera1.transform)

    val camera2 = Camera(800, 600, 60.0, Matrix.identity(4))
    println(camera2.transform)

    val camera3 = Camera(800, 600, 60.0, Point(1, 3, 2), Point(4, -2, 8), Vector(1, 1, 0))
    println(camera3.transform)
}
