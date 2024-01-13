import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
class MaterialTest {
    //    Background:
    //    Given material is a Material()
    //    And position is a Point(0, 0, 0)
    val material = Material()
    val position = Point(0, 0, 0)

    //    Scenario: Lighting with the eye between the light and the surface
    //    Given eyev is a Vector(0, 0, -1)
    //    And normalv is a Vector(0, 0, -1)
    //    And light is point_light(point(0, 0, -10), color(1, 1, 1))
    //    When result is phongLighting(material, light, position, eyev, normalv)
    //    Then result = color(1.9, 1.9, 1.9)
    @Test
    fun `Lighting with the eye between the light and the surface`() {
        // Given
        val eyeV = Vector(0, 0, -1)
        val normalV = Vector(0, 0, -1)
        val light = PointLightSource(Point(0, 0, -10), Color(1, 1, 1))
        // When
        val  result = material.phongLighting(light, position, eyeV, normalV)
        // Then
        assertEquals(Color(1.9, 1.9, 1.9), result, "The result should be Color(1.9, 1.9, 1.9)")
    }

    //    Scenario: Lighting with the eye between light and surface, eye offset 45 deg
    //    Given eyev is a Vector(0, 0.707106, -0.707106)
    //    And normalv is a Vector(0, 0, -1)
    //    And light is point_light(point(0, 0, -10), color(1, 1, 1))
    //    When result is phongLighting(material, light, position, eyev, normalv)
    //    Then result = color(1.0, 1.0, 1.0)
    @Test
    fun `Lighting with the eye between light and surface, eye offset 45 deg`() {
        // Given
        val eyeV = Vector(0.0, 0.707106, -0.707106)
        val normalV = Vector(0, 0, -1)
        val light = PointLightSource(Point(0, 0, -10), Color(1, 1, 1))
        // When
        val  result = material.phongLighting(light, position, eyeV, normalV)
        // Then
        assertEquals(Color(1.0, 1.0, 1.0), result, "The result should be Color(1.0, 1.0, 1.0)")
    }

    //    Scenario: Lighting with eye opposite surface, light offset 45 deg
    //    Given eyev is a Vector(0, 0, -1)
    //    And normalv is a Vector(0, 0, -1)
    //    And light is point_light(point(0, 10, -10), color(1, 1, 1))
    //    When result is phongLighting(material, light, position, eyev, normalv)
    //    Then result = color(0.736396, 0.736396, 0.736396)
    @Test
    fun `Lighting with eye opposite surface, light offset 45 deg`() {
        // Given
        val eyeV = Vector(0, 0, -1)
        val normalV = Vector(0, 0, -1)
        val light = PointLightSource(Point(0, 10, -10), Color(1, 1, 1))
        // When
        val  result = material.phongLighting(light, position, eyeV, normalV)
        // Then
        assertEquals(Color(0.736396, 0.736396, 0.736396), result, "The result should be Color(0.736396, 0.736396, 0.736396)")
    }

    //    Scenario: Lighting with eye in the path of the reflection vector
    //    Given eyev is a Vector(0, -0.707106, -0.707106)
    //    And normalv is a Vector(0, 0, -1)
    //    And light is point_light(point(0, 10, -10), color(1, 1, 1))
    //    When result is phongLighting(material, light, position, eyev, normalv)
    //    Then result = color(1.636197, 1.636197, 1.636197)
    @Test
    fun `Lighting with eye in the path of the reflection vector`() {
        // Given
        val eyeV = Vector(0.0, -0.707106, -0.707106)
        val normalV = Vector(0, 0, -1)
        val light = PointLightSource(Point(0, 10, -10), Color(1, 1, 1))
        // When
        val  result = material.phongLighting(light, position, eyeV, normalV)
        // Then
        assertEquals(Color(1.636197, 1.636197, 1.636197), result, "The result should be Color(1.636197, 1.636197, 1.636197)")
    }

    //    Scenario: Lighting with the light behind the surface
    //    Given eyev is a Vector(0, 0, -1)
    //    And normalv is a Vector(0, 0, -1)
    //    And light is point_light(point(0, 0, 10), color(1, 1, 1))
    //    When result is phongLighting(material, light, position, eyev, normalv)
    //    Then result = color(0.1, 0.1, 0.1)
    @Test
    fun `Lighting with the light behind the surface`() {
        // Given
        val eyeV = Vector(0, 0, -1)
        val normalV = Vector(0, 0, -1)
        val light = PointLightSource(Point(0, 0, 10), Color(1, 1, 1))
        // When
        val  result = material.phongLighting(light, position, eyeV, normalV)
        // Then
        assertEquals(Color(0.1, 0.1, 0.1), result, "The result should be Color(0.1, 0.1, 0.1)")
    }

    // Scenario: Lighting with the surface in shadow
    // Given eyev is a Vector(0, 0, -1)
    // And normalv is a Vector(0, 0, -1)
    // And light is point_light(point(0, 0, -10), color(1, 1, 1))
    // And isShadow is true
    // When result is phongLighting(material, light, position, eyev, normalv, isShadow)
    // Then result = color(0.1, 0.1, 0.1)
    @Test
    fun `Lighting with the surface in shadow`() {
        // Given
        val eyeV = Vector(0, 0, -1)
        val normalV = Vector(0, 0, -1)
        val light = PointLightSource(Point(0, 0, -10), Color(1, 1, 1))
        val isShadow = true
        // When
        val  result = material.phongLighting(light, position, eyeV, normalV, isShadow)
        // Then
        assertEquals(Color(0.1, 0.1, 0.1), result, "The result should be Color(0.1, 0.1, 0.1)")
    }


}

