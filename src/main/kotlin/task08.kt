import Scene.Companion.saveSceneCanvasFromCamera
import kotlin.math.PI

fun main() {

    // Camera settings
    // Камера в позиции (0,0,-2) смотрит в начало координат с вертикальным углом апертуры 90 градусов и
    // создает квадратное изображение (800 × 800)
    var position = Point(0, 0, -2)
    var lookAt = Point(0, 0, 0)
    var up = Vector(0, 1, 0)
    var fov = 90.0
    var width = 800
    var height = 800

    //    Scene settings
    val scene = Scene()
    scene.addLight(PointLightSource(Point(-10, 10, -10), Color(1, 1, 1), 1.0))
//    scene.setBackGround(Color.BLACK)

    val sphere = Sphere()
    scene.add(sphere)

    // sphere settings for task08_1
    sphere.material.color = Color(1.0, 0.2, 1.0)
    sphere.material.ambient = 0.1
    sphere.material.diffuse = 0.0
    sphere.material.specular = 0.0
    sphere.material.shininess = 200
    saveSceneCanvasFromCamera("task08_1", scene, width, height, fov, position, lookAt, up)

    // sphere settings for task08_2
    sphere.material.color = Color(1.0, 0.2, 1.0)
    sphere.material.ambient = 0.0
    sphere.material.diffuse = 0.9
    sphere.material.specular = 0.0
    sphere.material.shininess = 200
    saveSceneCanvasFromCamera("task08_2", scene, width, height, fov, position, lookAt, up)

    // sphere settings for task08_3
    sphere.material.color = Color(1.0, 0.2, 1.0)
    sphere.material.ambient = 0.0
    sphere.material.diffuse = 0.0
    sphere.material.specular = 0.9
    sphere.material.shininess = 200
    saveSceneCanvasFromCamera("task08_3", scene, width, height, fov, position, lookAt, up)

    // sphere settings for task08_4
    sphere.material.color = Color(1.0, 0.2, 1.0)
    sphere.material.ambient = 0.1
    sphere.material.diffuse = 0.9
    sphere.material.specular = 0.9
    sphere.material.shininess = 200
    saveSceneCanvasFromCamera("task08_4", scene, width, height, fov, position, lookAt, up)

    // settings for task08_5
    val scene2 = Scene.testScene()
    // Камера в позиции (0, 0, -5), направленная вдоль оси z
    position = Point(0.0, 1.5, -5.0)
    lookAt = Point(0, 1, 0)
    up = Vector(0, 1, 0)
    fov = 60.0
    width = 800
    height = 400

    saveCanvasFromCamera("task08_5", scene2, width, height, fov, position, lookAt, up)

    // settings for task08_6
    scene2.getObjects()[0].material.color = Color.fromInt(0x00FF00)
    scene2.getObjects()[1].material.color = Color.fromInt(0xFFFF00)
    scene2.getObjects()[2].material.color = Color.fromInt(0xFF1493)

    saveCanvasFromCamera("task08_6", scene2, width, height, fov, position, lookAt, up)

    // settings for task08_7
    scene2.setBackGround(Color.BLACK)

    saveCanvasFromCamera("task08_7", scene2, width, height, fov, position, lookAt, up)

}
