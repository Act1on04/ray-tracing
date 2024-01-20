class SpotLightSource(private val position: Point,
                      private val direction: Vector,
                      private val totalAngleDegrees: Double,
                      private val fallOffStartDegrees: Double,
                      color: Color = Color.WHITE,
                      intensity: Double = 1.0): LightSource(color, intensity)   {

    fun getPosition(): Point = position
    fun getDirection(): Vector = direction
    fun getTotalAngleDegrees(): Double = totalAngleDegrees
    fun getFallOffStartDegrees(): Double = fallOffStartDegrees

    override fun isDirectional(): Boolean = false

    override fun directionFromPoint(point: Point): Vector = (position - point).normalize()

    override fun directionToPoint(point: Point): Vector = (point - position).normalize()

    override fun distanceFromPoint(point: Point): Double  = (position - point).magnitude()

    override fun colorAtPoint(point: Point): Color {
        // вычисляем направление от положения прожектора до точки поверхности,
        // а затем вычисляем угол между направлением прожектора и этим вектором
        val angle = direction.angleBetween(directionToPoint(point))

        return when {
            // Если этот угол - больше половины от totalAngleDegrees,
            // точка находится за пределами светового конуса и не освещена, поэтому она возвращается черной
            angle > totalAngleDegrees / 2 -> Color.BLACK
            // Если угол меньше половины fallOffStartDegrees,
            // то точка находится в центральной, полностью освещенной области, и мы возвращаем цвет * интенсивность.
            angle < fallOffStartDegrees / 2 -> getColor() * getIntensity()
            else -> {
                // В противном случае точка находится в области fallOff, поэтому освещение должно быть приглушено.
                // Для этого мы вычисляем пропорцию угла между направлением на точку поверхности
                // и внешней стороной светового конуса в углу между началом FallOff и внешней стороной светового конуса.
                // Затем мы ослабляем цвет, который должен быть возвращаемый цвет  ( цвет * интенсивность ).
                // Более четкий эффект получается, если предварительно возвести коэффициент в квадрат
                // (это также физически ближе к реальности).
                val fallOffFactor = (totalAngleDegrees / 2 - angle) / (totalAngleDegrees / 2 - fallOffStartDegrees / 2)
                getColor() * getIntensity() * (fallOffFactor * fallOffFactor)
            }
        }
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is SpotLightSource) {
            return this.position == other.position && this.direction == other.direction
                    && this.totalAngleDegrees == other.totalAngleDegrees
                    && this.fallOffStartDegrees == other.fallOffStartDegrees
                    && this.getColor() == other.getColor() && this.getIntensity() == other.getIntensity()
        }
        return false
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + direction.hashCode()
        result = 31 * result + totalAngleDegrees.hashCode()
        result = 31 * result + fallOffStartDegrees.hashCode()
        return result
    }


}