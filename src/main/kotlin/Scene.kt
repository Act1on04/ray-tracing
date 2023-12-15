class Scene {

    private val objects: MutableList<Shape> = mutableListOf()

    // Метод для добавления объекта на сцену
    // Add object to Scene
    fun add(obj: Shape) {
        objects.add(obj)
    }

    fun getObjects(): List<Shape> {
        return objects
    }

    fun traceRay(ray: Ray): Intersections {
        val objectsIntersection = this.getObjects().flatMap { it.intersect(ray).toList()}
        return Intersections(*objectsIntersection.toTypedArray())
    }


    // Статический метод для создания тестовой сцены
    companion object {
        fun defaultScene(): Scene {
            val scene = Scene()

            // Добавляем нетрансформированную сферу
            val sphere1 = Sphere()
            scene.add(sphere1)

            // Добавляем уменьшенную вдвое сферу в начале координат
            val sphere2 = Sphere()
            sphere2.transform = Matrix.scaling(0.5)
            scene.add(sphere2)

            return scene
        }
    }

}

fun main() {

    val scene = Scene.defaultScene()
    val ray = Ray(Point(0, 0, -5), Point(0, 0, 1))

    // Вариант 1
    var intersections = Intersections()
    for (obj in scene.getObjects())
        intersections = intersections.merge(obj.intersect(ray))
    println("V1: - with FOR")
    println(" - already sorted")
    println(intersections.toList())

    // Вариант 2
    val objectsIntersection = scene.getObjects().flatMap { it.intersect(ray).toList()}
    println("V2: - with flatMap")
    println(" - its List and not sorted yet")
    println(objectsIntersection)
    println(" - its Intersections() and sorted")
    println(Intersections(*objectsIntersection.toTypedArray()).toList())

    // Вариант 3 уже с функцией traceRay(), написанной по Варианту 2
    println("V3: with func traceRay()")
    println(" - already sorted")
    println(Scene.defaultScene().traceRay(ray).toList())

}