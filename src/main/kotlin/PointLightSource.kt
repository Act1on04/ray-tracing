class PointLightSource( val position: Point, color: Color, intensity: Double): LightSource(color, intensity) {
    override fun isDirectional(): Boolean = false

    override fun directionFromPoint(point: Point): Vector = (position - point).normalize()

    override fun directionToPoint(point: Point): Vector = (point - position).normalize()

    override fun distanceFromPoint(point: Point): Double  = (position - point).magnitude()

    override fun colorAtPoint(point: Point): Color = color * intensity

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is PointLightSource) {
            return this.position == other.position && this.intensity == other.intensity
        }
        return false
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

}
