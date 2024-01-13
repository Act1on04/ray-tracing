import Scene.Companion.saveSceneCanvasFromCamera
import kotlin.math.PI

fun main() {

    // settings for task09.
    // Сначала добавьте в сцену с тремя шарами еще один шар,
    // который будет сильно сжат и будет служить полом.
    // Эта сфера находится в начале координат и масштабируется с коэффициентами (10, 0,01, 10)
    val scene = Scene.testScene()
    val sphere = Sphere()
    sphere.transform = Matrix.scaling(10, 0.01, 10)
    scene.add(sphere)

    // Затем визуализируйте эту сцену с помощью камеры, которая создает изображение размером 800×400.
    // Камера в позиции (0, 0, -5) и смотрит на (0, 1, 0), т.е. направленная вдоль оси z
    val position = Point(0.0, 1.5, -5.0)
    val lookAt = Point(0, 1, 0)
    val up = Vector(0, 1, 0)
    val fov = 60.0
    val width = 800
    val height = 400

    saveCanvasFromCamera("task09_1", scene, width, height, fov, position, lookAt, up)

    // settings for task09_2
    scene.getObjects()[0].material.color = Color.fromInt(0x00FF00)
    scene.getObjects()[1].material.color = Color.fromInt(0xFFFF00)
    scene.getObjects()[2].material.color = Color.fromInt(0xFF1493)

    saveCanvasFromCamera("task09_2", scene, width, height, fov, position, lookAt, up)

    // settings for task09_3
    scene.setBackGround(Color.BLACK)

    saveCanvasFromCamera("task09_3", scene, width, height, fov, position, lookAt, up)

}
