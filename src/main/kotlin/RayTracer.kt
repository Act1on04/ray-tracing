
class RayTracer(private val scene: Scene, private val camera: Camera) {

    fun render(): Canvas {
        val canvas = Canvas(camera.width, camera.height)

        for (y in 0 until camera.height) {
            for (x in 0 until camera.width) {
                val ray = camera.generateRay(x, y)
                val intersections = scene.traceRay(ray)

                val color = if (intersections.hit() != null) Color.fromInt(0xffa500) else Color.fromInt(0x00cdff)
                canvas.setPixel(x, y, color)
            }
        }

        return canvas
    }
}
