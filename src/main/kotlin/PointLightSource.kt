class PointLightSource(private val position: Point, color: Color = Color.WHITE, intensity: Double = 1.0): LightSource(color, intensity) {

    fun getPosition(): Point = position

    override fun isDirectional(): Boolean = false

    override fun directionFromPoint(point: Point): Vector = (position - point).normalize()

    override fun directionToPoint(point: Point): Vector = (point - position).normalize()

    override fun distanceFromPoint(point: Point): Double  = (position - point).magnitude()

    override fun colorAtPoint(point: Point): Color = getColor() * getIntensity()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is PointLightSource) {
            return this.position == other.position && this.getIntensity() == other.getIntensity()
        }
        return false
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

}
