import Scene.Companion.saveSceneCanvasFromCamera

fun main() {

    // Settings for task09.
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
    var width = 800
    val height = 400

    saveSceneCanvasFromCamera("task09_1", scene, width, height, fov, position, lookAt, up)

    // settings for task09_2
    scene.getObjects()[0].material.color = Color.fromInt(0x00FF00)
    scene.getObjects()[1].material.color = Color.fromInt(0xFFFF00)
    scene.getObjects()[2].material.color = Color.fromInt(0xFF1493)
    saveSceneCanvasFromCamera("task09_2", scene, width, height, fov, position, lookAt, up)

    // settings for task09_3
    scene.setBackGround(Color.BLACK)
    saveSceneCanvasFromCamera("task09_3", scene, width, height, fov, position, lookAt, up)

    // settings for task09_4-9
    // scene with DirectionalLightSource
    // Возвращаем сферам белый цвет и Фону синий цвет
    scene.getObjects()[0].material.color = Color.WHITE
    scene.getObjects()[1].material.color = Color.WHITE
    scene.getObjects()[2].material.color = Color.WHITE
    scene.setBackGround(Color.fromInt(0x00cdff))
    width = 400

    var intensities = listOf(0.5, 1.0, 10.0)
    for ((index, intensity) in intensities.withIndex()) {
        // scene with DirectionalLightSource
        scene.clearLights()
        scene.addLight(DirectionalLightSource(Vector(10, -10, 10), intensity = intensity))
        saveSceneCanvasFromCamera("task09_${index + 4}", scene, width, height, fov, position, lookAt, up)

        // scene with SpotLightSource
        scene.clearLights()
        scene.addLight(SpotLightSource(
            Point(-10, 10, -10),
            Vector(10, -10, 10),
            30.0,
            10.0,
            intensity = intensity))
        saveSceneCanvasFromCamera("task09_${index + 7}", scene, width, height, fov, position, lookAt, up)
    }

    // settings for task09_10-13
    // scene with PointLightSource и Затухание света (Attenuation = Licht-Abschwächung bei Punktlichtquellen)
    intensities = listOf(0.1, 1.0, 10.0, 100.0)
    for ((index, intensity) in intensities.withIndex()) {
        scene.clearLights()
        scene.addLight(PointLightSource(Point(-10, 10, -10), intensity = intensity))
        saveSceneCanvasFromCamera("task09_${index + 10}", scene, width, height, fov, position, lookAt, up)
    }

    // settings for task09_14
    width = 800
    scene.clearLights()
    scene.addLight(DirectionalLightSource(Vector(0, -10, 10), intensity = 0.7))
    scene.addLight(SpotLightSource(
                Point(-10, 10, -10),
                Point(-1.5, 0.33, -0.75) - Point(-10, 10, -10),
                // Point(-5, 5, -5),
                // Point(-1.5, 0.33, -0.75) - Point(-5, 5, -5),
                // Vector(10, -10, 10),
                10.0,
                5.0,
                Color.BLUE))
    scene.addLight(SpotLightSource(
        Point(10, 10, 10),
        Point(1.5, 0.5, -0.5) - Point(10, 10, 10),
        // Vector(10, -10, 10),
        10.0,
        5.0,
        Color.ORANGE))
    saveSceneCanvasFromCamera("task09_14", scene, width, height, fov, position, lookAt, up)

}
