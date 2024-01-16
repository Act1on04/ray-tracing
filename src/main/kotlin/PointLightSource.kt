class PointLightSource(color: Color, intensity: Double, val position: Point): LightSource(color, intensity) {
    override fun isDirectional(): Boolean = false

    override fun directionFromPoint(point: Point): Vector {
        TODO("Not yet implemented")
    }

    override fun directionToPoint(point: Point): Vector {
        TODO("Not yet implemented")
    }

    override fun distanceFromPoint(point: Point): Double {
        TODO("Not yet implemented")
    }

    override fun colorAtPoint(point: Point): Color {
        TODO("Not yet implemented")
    }

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
