abstract class LightSource(val color: Color = Color.WHITE, val intensity: Double = 1.0) {

    abstract fun isDirectional(): Boolean

    abstract fun directionFromPoint(point: Point): Vector

    abstract fun directionToPoint(point: Point): Vector

    abstract fun distanceFromPoint(point: Point): Double

    abstract fun colorAtPoint(point: Point): Color

}