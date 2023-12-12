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

}