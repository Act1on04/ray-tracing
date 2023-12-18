
fun saveCanvasFromCamera(fileName: String, scene: Scene, width: Int, height: Int, fov: Double, position: Point, lookAt: Point, up: Vector) {

    val camera = Camera(width, height, fov, position, lookAt, up)

    // RayTracer erstellen und rendern
    val rayTracer = RayTracer(scene, camera)

    // Canvas in Datei schreiben
    val canvas = rayTracer.render()
    canvas.writeToFile(fileName)

}


fun main() {
//    Scene settings
    var scene = Scene()
    val sphere = Sphere()
//    Сфера радиусом 0,5 лежит в точке (0,0,0).
    sphere.transform = Matrix.scaling(0.5)
    scene.add(sphere)

    // Camera settings for task07_1
    var position = Point(0, 0, -10)
    var lookAt = Point(0, 0, 0)
    var up = Vector(0, 1, 0)
    var fov = 7.7
    var width = 800
    var height = 600

    saveCanvasFromCamera("task07_1", scene, width, height, fov, position, lookAt, up)

    // Camera settings for task07_2
    position = Point(0, 0, -10)
    lookAt = Point(1, 1, 0)
    up = Vector(0, 1, 0)
    fov = 11.4
    width = 600
    height = 600

    saveCanvasFromCamera("task07_2", scene, width, height, fov, position, lookAt, up)

    // Camera settings for task07_3
    position = Point(10, 10, -10)
    lookAt = Point(0, 0, 0)
    up = Vector(0, 1, 0)
    fov = 2.7
    width = 600
    height = 600

    saveCanvasFromCamera("task07_3", scene, width, height, fov, position, lookAt, up)

//  settings for task07_4
    scene = Scene.testScene()
    // Камера в позиции (0, 0, -5), направленная вдоль оси z
    position = Point(0.0, 1.5, -5.0)
    lookAt = Point(0, 1, 0)
    up = Vector(0, 1, 0)
    fov = 60.0
    width = 800
    height = 400

    saveCanvasFromCamera("task07_4", scene, width, height, fov, position, lookAt, up)


//  settings for task07_5
    scene = Scene()
    val radius = 0.4
    for (x in -3..3) {
        for (y in -3..3) {
            val sphere = Sphere()
            sphere.transform = Matrix.translation(x.toDouble(), y.toDouble(), 0.0) * Matrix.scaling(radius)
            scene.add(sphere)
        }
    }

    position = Point(0.0, 0.0, -5.0)
    lookAt = Point(0.0, 0.0, 0.0)
    up = Vector(0.0, 1.0, 0.0)
    fov = 90.0
    width = 400
    height = 400

    saveCanvasFromCamera("task07_5", scene, width, height, fov, position, lookAt, up)

}
