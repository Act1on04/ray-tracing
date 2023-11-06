//import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Canvas(val width: Int, val height: Int, val fileName: String = "") {
    // Создаем пустое изображение с заданными шириной и высотой
    private val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    // Устанавливаем пикселю в заданной позиции (x, y) значение цвета
    fun setPixel(x: Int, y: Int, color: Color) {
        if (x !in 0 until width || y !in 0 until height)
            throw IllegalArgumentException("Canvas size: ($width,$height), tried to set point ($x,$y).")
        image.setRGB(x, y, color.toInt())
    }

    // Считываем значение цвета пикселя в заданной позиции (x, y)
    fun getPixel(x: Int, y: Int): Color {
        if (x !in 0 until width || y !in 0 until height)
            throw IllegalArgumentException("Canvas size: ($width,$height), tried to get point ($x,$y).")
        return Color.fromInt(image.getRGB(x, y))
    }

    // Записываем содержимое всего изображения в файл (желательно в стандартном формате)
    fun writeToFile(file: String = "canvas") {
        // Если имя файла не задано, то генерируем случайное
        val out = File("$file.png")
        // Записываем изображение в файл
        ImageIO.write(image, "png", out)
    }

    // Читаем файл изображения в наше внутреннее представление
    fun readFromFile(file: String = "canvas") {
        // Считываем изображение из файла
        val input = ImageIO.read(File("$file.png"))
        // Проверяем, что размеры совпадают
        if (input.width != width || input.height != height) {
            throw IllegalArgumentException("The input image dimensions do not match the canvas dimensions")
        }
        // Копируем пиксели из входного изображения в наше
        for (x in 0 until width) {
            for (y in 0 until height) {
                setPixel(x, y, Color.fromInt(input.getRGB(x, y)))
            }
        }
    }

    // Записываем содержимое всего изображения в файл в формате PPM
    fun writeToPPM(file: String = "canvas") {
        File("$file.ppm").bufferedWriter().use { out ->
            out.write("P3\n")
            out.write("$width $height\n")
            out.write("255\n")

            // Записываем пиксели
            for (y in 0 until height) {
                for (x in 0 until width) {
                    val color = getPixel(x, y)
                    out.write(color.toIntString())
                    out.write("\n")
                }
            }
        }
    }

}

fun main(args:Array<String>) {

    val canvasWithoutName = Canvas(300, 300)
    println("Width: ${canvasWithoutName.width}")
    println("Height: ${canvasWithoutName.height}")
    println("FileName: ${canvasWithoutName.fileName}")

    val namedCanvas = Canvas(300, 300, "image")
    println("Width: ${namedCanvas.width}")
    println("Height: ${namedCanvas.height}")
    println("FileName: ${namedCanvas.fileName}")

    var canvasWithPixel = Canvas(200, 200, "MyImage")
    println("Width: ${canvasWithPixel.width}")
    println("Height: ${canvasWithPixel.height}")
    println("FileName: ${canvasWithPixel.fileName}")

    // заполнил канвас случайными значениями
    for (x in 0 until canvasWithPixel.width)
        for (y in 0 until canvasWithPixel.height)
            canvasWithPixel.setPixel(x, y, Color.randomColor())

    // сохранил в файл MyImage.png
    canvasWithPixel.writeToFile("MyImage")
    // считал из файла MyImage.png
    canvasWithPixel.readFromFile("MyImage")
    canvasWithPixel.writeToFile("MyImage2")
    // сохранил в файл MyImage.ppm
    canvasWithPixel.writeToPPM()


}