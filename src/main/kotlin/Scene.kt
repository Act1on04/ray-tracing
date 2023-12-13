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