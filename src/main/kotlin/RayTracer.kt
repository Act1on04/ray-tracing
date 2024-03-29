class RayTracer(private val scene: Scene, private val camera: Camera) {

    fun render(): Canvas {
        val canvas = Canvas(camera.width, camera.height)

        // Вообще у нас типа может быть в Сцене много источников цвета,
        // но пока мы работаем только с одним.
        // Но если даже него не будет, то присваиваем свет как в Дефолтной сцене
//        val light = scene.getLights().firstOrNull() ?: PointLightSource(Point(-10, 10, -10), Color(1, 1, 1), 1.0)

        for (y in 0 until camera.height) {
            for (x in 0 until camera.width) {
                val ray = camera.generateRay(x.toDouble(), y.toDouble())
                // Убираем это всё по требованию Лабы 10 и заменяем на вызов Scene.colorAt(ray)
                // val intersections = scene.traceRay(ray)
                // val hit = intersections.hit()
                //
                // val color = if (hit != null) {
                //     // если есть пересечения, то вычисляем цвет, используя Модель освещения Фонга
                //     // в 9-й лабе всё это засунули в метод shadeHit() класса Сцена.
                //     // Поэтому комментируем, то что делали в 8-й лабе
                //     // val position = ray.pointAt(hit.t)
                //     // val eyev = -ray.direction
                //     // val normalv = hit.shape.normalAt(position)
                //     // hit.shape.material.phongLighting(light, position, eyev, normalv)
                //
                //     val hitInfo = hit.prepareHitInfo(ray)
                //     scene.shadeHit(hitInfo)
                //
                // }
                // else
                //     // если нет пересечения, то берём дефолтный цвет фона из Сцены
                //     scene.getBackGround()
                val color = scene.colorAt(ray)
                canvas.setPixel(x, y, color)
            }
        }

        return canvas
    }

    fun render(sampler: Sampler): Canvas {
        val canvas = Canvas(camera.width, camera.height)
        val offsets = sampler.generateSamples()
        val countPoint = sampler.getCountPoint()

        for (y in 0 until camera.height) {
            for (x in 0 until camera.width) {
                var superSamplingColor = Color(0.0, 0.0, 0.0)
                for (offset in offsets) {
                    val ray = camera.generateRay(x + offset.first, y + offset.second)
                    val color = scene.colorAt(ray)
                    superSamplingColor += color
                }

                val pixelColor = superSamplingColor / countPoint
                canvas.setPixel(x, y, pixelColor)
            }
        }

        return canvas
    }


}
