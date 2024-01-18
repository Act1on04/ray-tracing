class DirectionalLightSource(private val direction: Vector, color: Color = Color.WHITE, intensity: Double = 1.0): LightSource(color, intensity)   {
    fun getDirection(): Vector = direction

    override fun isDirectional(): Boolean = true

    override fun directionFromPoint(point: Point): Vector = -direction.normalize()

    override fun directionToPoint(point: Point): Vector = direction.normalize()

    override fun distanceFromPoint(point: Point): Double = Double.POSITIVE_INFINITY

    override fun colorAtPoint(point: Point): Color = getColor() * getIntensity()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is DirectionalLightSource) {
            return this.direction == other.direction
                    && this.getColor() == other.getColor() && this.getIntensity() == other.getIntensity()
        }
        return false
    }

    override fun hashCode(): Int {
        return direction.hashCode()
    }

}