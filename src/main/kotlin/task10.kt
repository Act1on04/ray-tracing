import Scene.Companion.saveSceneCanvasFromCamera
import kotlin.system.measureTimeMillis

fun main() {

    // Settings for task10.
    // Сначала добавьте в сцену с тремя шарами еще один шар,
    // который будет сильно сжат и будет служить полом.
    // Эта сфера находится в начале координат и масштабируется с коэффициентами (10, 0,01, 10)
    val scene = Scene.testScene()
    val sphere = Sphere()
    sphere.transform = Matrix.scaling(10, 0.01, 10)
    scene.add(sphere)
    // Покрасим наши сферы
    scene.getObjects()[0].material.color = Color.fromInt(0x00FF00)
    scene.getObjects()[1].material.color = Color.fromInt(0xFFFF00)
    scene.getObjects()[2].material.color = Color.fromInt(0xFF1493)
    // Цвет фона сделаем чёрным, а не синим. Так лучше смотрится.
    scene.setBackGround(Color.BLACK)
    // Уберём точечный свет и поставим направленный, так как при точечном сильно тускло
    scene.clearLights()
    scene.addLight(DirectionalLightSource(Vector(10, -10, 10), intensity = 1.0))


    // Затем визуализируйте эту сцену с помощью камеры, которая создает изображение размером 800×400.
    // Камера в позиции (0, 0, -5) и смотрит на (0, 1, 0), т.е. направленная вдоль оси z
    val position = Point(0.0, 1.5, -5.0)
    val lookAt = Point(0, 1, 0)
    val up = Vector(0, 1, 0)
    val fov = 60.0
    val width = 800
    val height = 400


    // Settings for task10_1
    // Здесь все материалы имеют отражательную способность 1,0, а глубина рекурсии ограничена 5.
    // Глубину отражения по умолчанию равна 5, так что её не меняем
    // Отражающие свойства объектов равны 1.0
    scene.getObjects()[0].material.reflectance = 1.0
    scene.getObjects()[1].material.reflectance = 1.0
    scene.getObjects()[2].material.reflectance = 1.0
    scene.getObjects()[3].material.reflectance = 1.0
    // Task 10_1
    val time1 = measureTimeMillis {
        saveSceneCanvasFromCamera("task10_1", scene, width, height, fov, position, lookAt, up)
    }
    println("Time for task10_1: %.1f seconds".format(time1 / 1000.0))

    // Settings for task10_2
    // Здесь отражается только земля и левый шар. Глубина рекурсии была ограничена до 1.
    // Установим глубину отражения в единицу
    scene.setDepth(1)
    // Отражающие свойства центрального и правого шара поменяем на 0.0, чтоб не отражали
    scene.getObjects()[0].material.reflectance = 0.0
    scene.getObjects()[1].material.reflectance = 0.0
    val time2 = measureTimeMillis {
        saveSceneCanvasFromCamera("task10_2", scene, width, height, fov, position, lookAt, up)
    }
    println("Time for task10_2: %.1f seconds".format(time2 / 1000.0))

    // Settings for task10_3
    scene.sampler = RandomSampler()
    println("RandomSampler ")
    val time3 = measureTimeMillis {
        saveSceneCanvasFromCamera("task10_3", scene, width, height, fov, position, lookAt, up)
    }
    println("Time for task10_3: %.1f seconds".format(time3 / 1000.0))

    // Settings for task10_4
    scene.sampler = OffsetSampler()
    println("OffsetSampler ")
    val time4 = measureTimeMillis {
        saveSceneCanvasFromCamera("task10_4", scene, width, height, fov, position, lookAt, up)
    }
    println("Time for task10_4: %.1f seconds".format(time4 / 1000.0))

    // Total time
    val totalTime = time1 + time2 + time3 + time4
    println("Total time: %.1f seconds".format(totalTime / 1000.0))

}
