
class RayTracer(private val scene: Scene, private val camera: Camera) {

    fun render(): Canvas {
        val canvas = Canvas(camera.width, camera.height)

        // вообще у нас типа может быть в Сцене много источников цвета,
        // но пока мы работаем только с одним.
        // Но если даже него не будет, то присфаиваем свет как в Дефолтной сцене
        val light = scene.getLights().firstOrNull() ?: PointLightSource(Point(-10, 10, -10), Color(1, 1, 1))

        for (y in 0 until camera.height) {
            for (x in 0 until camera.width) {
                val ray = camera.generateRay(x, y)
                val intersections = scene.traceRay(ray)
                val hit = intersections.hit()

                val color = if (hit != null) {
                    // если есть пересечения, то вычисляем цвет, используя Модель освещения Фонга
                    val position = ray.pointAt(hit.t)
                    val eyev = -ray.direction
                    val normalv = hit.shape.normalAt(position)
                    hit.shape.material.phongLighting(light, position, eyev, normalv)
                }
                else
                    // если нет пересечения, то берём дефолтный цвет фона из Сцены
                    scene.getBackGround()
                canvas.setPixel(x, y, color)
            }
        }

        return canvas
    }
}
