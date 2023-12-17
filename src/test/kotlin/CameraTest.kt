import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.tan

class CameraTest {

//    Scenario: Constructing a camera
//    Given hsize is 160
//    And vsize is 120
//    And field_of_view is 90
//    When camera is a Camera(hsize, vsize, field_of_view)
//    Then camera.width = 160
//    And camera.height = 120
//    And camera.field_of_view = 90
//    And camera.transform = identity_matrix
    @Test
    fun `Constructing a camera`() {
        // Given
        val hsize = 160
        val vsize = 120
        val field_of_view = 90.0

        // When
        val camera = Camera(hsize, vsize, field_of_view)

        // Then
        assertEquals(hsize, camera.width, "Camera width should be $hsize")
        assertEquals(vsize, camera.height, "Camera height should be $vsize")
        assertEquals(field_of_view, camera.fov, "Camera field of view should be $field_of_view")
        assertEquals(Matrix.identity(4), camera.transform, "Camera transform should be identity matrix")
    }

//    Scenario: The pixel size for a horizontal canvas
//    Given camera is a Camera(200, 125, 90)
//    Then camera.pixel_size = 0.01
    @Test
    fun `The pixel size for a horizontal canvas`() {
        // Given
        val camera = Camera(200, 125, 90.0)
        // Then
        assertEquals(0.01, camera.pixelSize, 0.00001, "The pixel size should be 0.01")
    }

//    Scenario: The pixel size for a vertical canvas
//    Given camera is a Camera(125, 200, 90)
//    Then camera.pixel_size = 0.01
    @Test
    fun `The pixel size for a vertical canvas`() {
        // Given
        val camera = Camera(125, 200, 90.0)
        // Then
        assertEquals(0.01, camera.pixelSize, 0.00001, "The pixel size should be 0.01")
    }
}