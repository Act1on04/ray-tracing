import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LightSourceTest {
//    Scenario: A point light has a position and intensity
//    Given color is a Color(1, 1, 1)
//    And position is a Point(0, 0, 0)
//    When light is point_light(position, color)
//    Then light.position = position
//    And light.color = color
    @Test
    fun `A point light has a position and intensity`() {
        // Given
        val color = Color(1, 1, 1)
        val position = Point(0, 0, 0)
        // When
        val light = PointLightSource(position, color)
        // Then
        assertEquals(light.getPosition(), position, "The light.position should be position=Point(0, 0, 0)")
        assertEquals(light.getColor(), color, "The light.intensity should be color=Color(1, 1, 1)")
    }
}

class PointLightSourceTest {

    // Scenario: PointLight with standard parameters
    // Given light is a PointLight(point(0, 0, 0))
    // And p is a Point(0, 1, 0)
    // Then isDirectional(light) = false
    // And distanceFromPoint(light, p) = 1.0
    // And directionToPoint(light, p) = Vector(0, 1, 0)
    // And directionFromPoint(light, p) = Vector(0, -1, 0)
    // And colorAtPoint(light, p) = Color(1, 1, 1)
    @Test
    fun `PointLight with standard parameters`() {
        // Given
        val light = PointLightSource(Point(0, 0, 0))
        val p = Point(0,1,0)
        // Then
        assertEquals(false, light.isDirectional(), "The light.isDirectional() should be False")
        assertEquals(1.0, light.distanceFromPoint(p), "The light.distanceFromPoint(p) should be 1.0")
        assertEquals(Vector(0, 1, 0), light.directionToPoint(p), "The light.directionToPoint(p) should be Vector(0, 1, 0)")
        assertEquals(Vector(0, -1, 0), light.directionFromPoint(p), "The light.directionFromPoint(p) should be Vector(0, -1, 0)")
        assertEquals(Color(1, 1, 1), light.colorAtPoint(p), "The light.colorAtPoint(p) should be color=Color(1, 1, 1)")
    }

    // Scenario: PointLight with custom parameters
    // Given light is a PointLight(point(0, 0, 0), color(0.2, 0.1, 0.3), 3.0)
    // And p is a Point(0, 1, 0)
    // Then isDirectional(light) = false
    // And distanceFromPoint(light, p) = 1.0
    // And directionToPoint(light, p) = Vector(0, 1, 0)
    // And directionFromPoint(light, p) = Vector(0, -1, 0)
    // And colorAtPoint(light, p) = Color(0.6, 0.3, 0.9)
    @Test
    fun `PointLight with custom parameters`() {
        // Given
        val light = PointLightSource(Point(0, 0, 0), Color(0.2, 0.1, 0.3), 3.0)
        val p = Point(0,1,0)
        // Then
        assertEquals(false, light.isDirectional(), "The light.isDirectional() should be False")
        assertEquals(1.0, light.distanceFromPoint(p), "The light.distanceFromPoint(p) should be 1.0")
        assertEquals(Vector(0, 1, 0), light.directionToPoint(p), "The light.directionToPoint(p) should be Vector(0, 1, 0)")
        assertEquals(Vector(0, -1, 0), light.directionFromPoint(p), "The light.directionFromPoint(p) should be Vector(0, -1, 0)")
        assertEquals(Color(0.6, 0.3, 0.9), light.colorAtPoint(p), "The light.colorAtPoint(p) should be color=Color(0.6, 0.3, 0.9)")
    }

}
