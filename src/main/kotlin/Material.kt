import kotlin.math.pow

class Material(var color: Color = Color.WHITE,
               var ambient: Double = 0.1,
               var diffuse: Double = 0.9,
               var specular: Double = 0.9,
               var shininess: Int = 200,
               var reflectance: Double = 0.0) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is Material) {
            return this.color == other.color && this.ambient == other.ambient &&
            this.diffuse == other.diffuse && this.specular == other.specular && this.shininess == other.shininess
        }
        return false
    }

    fun phongLighting(light: LightSource,
                      point: Point,
                      eyeV: Vector,
                      normalV: Vector,
                      inShadow: Boolean = false): Color  {
        // Компонент ambient описывает общую базовую яркость сцены
        val ambient = color * ambient

        // Из 9-й лабы. Если точка в тени, то диффузную и зеркальную составляющую не учитываем.
        // Сразу возвращаем только рассеянный цвет
        if (inShadow) return ambient

        // find the direction to the light source
        // вектор от точки в которой вычисляем свет к источнику света
        val lightV = light.directionFromPoint(point)
        // light_dot_normal represents the cosine of the angle between the
        // light vector and the normal vector. A negative number means the
        // light is on the other side of the surface.
        val lightDotNormal = lightV * normalV
        // Диффузная составляющая описывает отражение света от матовых поверхностей
        val diffuse = if (lightDotNormal < 0) Color.BLACK else light.colorAtPoint(point) * color * diffuse * lightDotNormal
        // Зеркальный (Спекулярный) компонент описывает отражение на блестящих поверхностях
        val specular = if (lightDotNormal < 0) Color.BLACK else {
            val reflectV = -lightV.reflect(normalV)
            val reflectDotEye = reflectV * eyeV
            if (reflectDotEye < 0) Color.BLACK else light.colorAtPoint(point) * specular * reflectDotEye.pow(shininess)
        }
        return ambient + diffuse + specular
    }

}
