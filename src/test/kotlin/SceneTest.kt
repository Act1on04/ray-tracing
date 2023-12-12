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

}