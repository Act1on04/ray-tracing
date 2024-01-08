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
        assertEquals(light.position, position, "The light.position should be position=Point(0, 0, 0)")
        assertEquals(light.intensity, color, "The light.intensity should be color=Color(1, 1, 1)")
    }

}