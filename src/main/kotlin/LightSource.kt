abstract class LightSource(
    private val color: Color = Color.WHITE,
    private val intensity: Double = 1.0)
{
    abstract fun isDirectional(): Boolean

    abstract fun directionFromPoint(point: Point): Vector

    abstract fun directionToPoint(point: Point): Vector

    abstract fun distanceFromPoint(point: Point): Double

    abstract fun colorAtPoint(point: Point): Color

    fun getColor(): Color = color

    fun getIntensity(): Double = intensity

}