class PointLightSource(position: Point, intensity: Color = Color.WHITE): LightSource(position, intensity) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is PointLightSource) {
            return this.position == other.position && this.intensity == other.intensity
        }
        return false
    }

}
