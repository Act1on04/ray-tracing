import max
import kotlin.math.*
import kotlin.random.Random

const val epsilon = 0.0005

private fun closeEnough(value:Double, other:Double) =
    abs(value - other) < epsilon

fun min(p1: Point, p2: Point): Point {
    return Point(
        minOf(p1.x, p2.x),
        minOf(p1.y, p2.y),
        minOf(p1.z, p2.z),
    )
}

fun max(p1: Point, p2: Point): Point {
    return Point(
        maxOf(p1.x, p2.x),
        maxOf(p1.y, p2.y),
        maxOf(p1.z, p2.z),
    )
}

open class Tuple(val x:Double, val y:Double, val z:Double, val w:Double) {

    constructor(x:Int, y:Int, z:Int, w:Int) : this(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

    override fun toString(): String = "[Tuple x:$x, y:$y, z:$z, w:$w]"

    operator fun plus(other:Tuple):Tuple =
        Tuple(x + other.x, y + other.y, z + other.z, w + other.w)

    operator fun minus(other:Tuple):Tuple =
        Tuple(x - other.x, y - other.y, z - other.z, w - other.w)

    open operator fun unaryMinus():Tuple = Tuple(-x, -y, -z, -w)

    open operator fun times(scalar:Double):Tuple = Tuple(x * scalar, y * scalar, z * scalar, w * scalar)

    open operator fun times(other:Tuple):Double = x * other.x + y * other.y + z * other.z + w * other.w

    open operator fun div(scalar:Double):Tuple = Tuple(x / scalar, y / scalar, z / scalar, w / scalar)

    override fun equals(other: Any?): Boolean {
        if (other is Tuple) {
            return closeEnough(x, other.x) && closeEnough(y, other.y) && closeEnough(z, other.z) && closeEnough(w, other.w)
        }
        return false
    }

    fun asPoint() = Point(x, y, z)

    fun asVector() = Vector(x, y, z)

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        result = 31 * result + w.hashCode()
        return result
    }

}

class Point(x:Double, y:Double, z:Double) : Tuple(x, y, z, 1.0) {

    constructor(x:Int, y:Int, z:Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    operator fun minus(other:Point):Vector =
        Vector(x - other.x, y - other.y, z - other.z)

    operator fun plus(other:Vector):Point =
        Point(x + other.x, y + other.y, z + other.z)

    operator fun minus(other:Vector):Point =
        Point(x - other.x, y - other.y, z - other.z)

}

class Vector(x:Double, y:Double, z:Double) : Tuple(x, y, z, 0.0) {

    constructor(x:Int, y:Int, z:Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    operator fun plus(other:Vector):Vector =
        Vector(x + other.x, y + other.y, z + other.z)

    operator fun minus(other:Vector):Vector =
        Vector(x - other.x, y - other.y, z - other.z)

    override operator fun unaryMinus():Vector = Vector(-x, -y, -z)

    override operator fun times(scalar:Double):Vector = Vector(x * scalar, y * scalar, z * scalar)

    override operator fun div(scalar:Double):Vector = Vector(x / scalar, y / scalar, z / scalar)

    fun magnitude():Double = sqrt(x*x + y*y + z*z)

    fun normalize():Vector {
        val mag = this.magnitude()

        return Vector(x / mag, y / mag, z / mag)
    }

    fun dot(other:Vector):Double = x * other.x + y * other.y + z * other.z

    fun cross(other:Vector):Vector = Vector(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x
    )

    fun reflect(other:Vector):Vector {
        // Отраженный вектор рассчитывается по следующему правилу, где падающий вектор обозначается - e, нормаль – n,
        // а отраженный вектор – r
        // r = e − 2*(e*n)*n
        return this - other * 2.0 * (this * other)
    }

}

data class Color(val red:Double, val green:Double, val blue:Double) {

    constructor(red:Int, green:Int, blue:Int) : this(red.toDouble(), green.toDouble(), blue.toDouble())

    // Когда писал 8-ю лабу про материалы и освещение, это вдуг стало не актуально
    //  в тестах к Материалам и Освещённости было - Then result = color(1.9, 1.9, 1.9)
    // комментирую. ХЗ, что получится
//    init {
//        require(red in 0.0..1.0) { "Red component must be between 0.0 and 1.0" }
//        require(green in 0.0..1.0) { "Green component must be between 0.0 and 1.0" }
//        require(blue in 0.0..1.0) { "Blue component must be between 0.0 and 1.0" }
//    }

    operator fun plus(other:Color):Color =
        Color(red + other.red, green + other.green, blue + other.blue)

    operator fun minus(other:Color):Color =
        Color(red - other.red, green - other.green, blue - other.blue)

    operator fun times(scalar:Double):Color =
        Color(red * scalar, green * scalar, blue * scalar)

    operator fun times(other:Color):Color =
        Color(red * other.red, green * other.green, blue * other.blue)

    private fun clamp(value:Int):Int =
        when {
            value > 255 -> 255
            value < 0   -> 0
            else        -> value
        }

    private fun doubleToInt(c:Double):Int = clamp((c * 255).roundToInt())

    fun toIntString():String = "${doubleToInt(red)} ${doubleToInt(green)} ${doubleToInt(blue)}"

    fun toInt(): Int {
        // Преобразуем наши дробные значения в целые от 0 до 255
        val red = doubleToInt(red)
        val green = doubleToInt(green)
        val blue = doubleToInt(blue)
        // Собираем их в одно целое число, используя сдвиги и побитовое или
        return (red shl 16) or (green shl 8) or blue
    }

    override fun equals(other: Any?): Boolean {
        if (other is Color) {
            return closeEnough(red, other.red) && closeEnough(green, other.green) && closeEnough(blue, other.blue)
        }
        return false
    }

    companion object {
        // Сопоставляем имена для некоторых цветов
        val BLACK = Color(0.0, 0.0, 0.0)
        val RED = Color(1.0, 0.0, 0.0)
        val GREEN = Color(0.0, 1.0, 0.0)
        val BLUE = Color(0.0, 0.0, 1.0)
        val WHITE = Color(1.0, 1.0, 1.0)

        // Создаём Color со случайными значениями
        fun randomColor(): Color {
            val random = Random.Default
            val randomRed = random.nextDouble()
            val randomGreen = random.nextDouble()
            val randomBlue = random.nextDouble()
            return Color(randomRed, randomGreen, randomBlue)
        }

        fun fromInt(rgb: Int): Color {
//            val r = ((rgb and 0xff0000) shr 16).toDouble() / 0xff
//            val g = ((rgb and 0x00ff00) shr 8).toDouble() / 0xff
//            val b = ((rgb and 0x0000ff)).toDouble() / 0xff
//            return Color(r, g, b)

//            Извлекаем красный, зеленый и синий компоненты из целого числа
            val red = (rgb shr 16) and 0xFF
            val green = (rgb shr 8) and 0xFF
            val blue = rgb and 0xFF
            // Преобразуем их в дробные значения от 0 до 1
            val r = red / 255.0
            val g = green / 255.0
            val b = blue / 255.0
            // Возвращаем новый экземпляр нашего класса цвета
            return Color(r, g, b)
        }

    }

}

fun main() {

    val color2 = Color(300.toDouble() / 800, 500.toDouble() / 600, 0.0)
    println(color2)
    val color = Color((300 / 800).toDouble(), (500 / 600).toDouble(), 0.toDouble())
    println(color)

    val color3 = Color(1.1, 0.5, 0.0)
    println(color3)

}