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

//    fun traceRay(ray: Ray): Intersections {
//        val intersections = objects.map { it.intersect(ray) }
//        return intersections
//    }


//    internal fun intersect(ray: Ray): Intersections =
//        shapes.flatMap { it.intersect(ray) }.sortedBy { it.t }
//
//    fun intersect(ray: Ray): Intersections {
//        val xss = objects.map { it.intersect(ray) }
//        return Intersections.combine(xss)
//    }
//
//    fun combine(intersections: List<Intersections>): Intersections {
//        val all = intersections.flatMap { it.intersections.asList() }
//        val sorted = all.sortedBy { it.t }
//        return Intersections(*sorted.toTypedArray())
//    }
//
    fun traceRay(ray: Ray): Intersections {

        // Перебираем все объекты на сцене и вычисляем их пересечения с лучом
        val objectsIntersection = objects.map { it.intersect(ray) }
        val allIntersections = objectsIntersection.flatMap { it.intersections.asList() }
        val sorted = allIntersections.sortedBy { it.t }
        return Intersections(*sorted.toTypedArray())

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