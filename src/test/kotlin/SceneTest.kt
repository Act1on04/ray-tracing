import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SceneTest {

//    Scenario: Creating a Scene
//    Given scene is a Scene()
//    Then scene contains no objects
    @Test
    fun `Creating a Scene`() {
        // Given
        val scene = Scene()

        // Then
        assertTrue(scene.getObjects().isEmpty(), "Scene should be empty")
    }

//    Scenario: Adding unnamed objects to a scene
//    Given scene is a Scene()
//    And sphere is a Sphere()
//    When sphere is added to scene
//    Then scene contains sphere as object
    @Test
    fun `Adding unnamed objects to a scene`() {
        // Given
        val scene = Scene()
        val sphere = Sphere()

        // When
        scene.add(sphere)

        // Then
        assertTrue(scene.getObjects().contains(sphere), "Scene should contain the sphere")
    }

//    Scenario: Intersect a scene with a ray
//    Given scene is the defaultScene()
//    And ray is a Ray(point(0, 0, -5), vector(0, 0, 1))
//    When xs is traceRay(scene, ray)
//    Then xs.count = 4
//    And xs[0].t = 4
//    And xs[1].t = 4.5
//    And xs[2].t = 5.5
//    And xs[3].t = 6
    @Test
    fun `Intersect a scene with a ray`() {
        // Given
        val scene = Scene.defaultScene()
        val ray = Ray(Point(0, 0, -5), Vector(0, 0, 1))

        // When
        val xs = scene.traceRay(ray)

        // Then
        assertEquals(4, xs.count, "The count of intersections should be 4")
        assertEquals(4.0, xs[0].t, "The first intersection's t value should be 4.0")
        assertEquals(4.5, xs[1].t, "The second intersection's t value should be 4.5")
        assertEquals(5.5, xs[2].t, "The third intersection's t value should be 5.5")
        assertEquals(6.0, xs[3].t, "The fourth intersection's t value should be 6.0")
    }


}