import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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

class CameraGenerateRayTest {

//    Scenario: Constructing a ray through the center of the canvas
//    Given camera is a Camera(201, 101, 90)
//    When ray = ray_for_pixel(camera, 100, 50)
//    Then ray.origin = point(0, 0, 0)
//    And ray.direction = vector(0, 0, -1)
    @Test
    fun `Constructing a ray through the center of the canvas`() {
        // Given
        val camera = Camera(201, 101, 90.0)

        // When
        val ray = camera.generateRay(100, 50)

        // Then
        assertEquals(Point(0, 0, 0), ray.origin, "Ray origin should be at Point(0, 0, 0)")
        assertEquals(Vector(0, 0, -1), ray.direction, "Ray direction should be Vector(0, 0, -1)")
    }

//    Scenario: Constructing a ray through a corner of the canvas
//    Given camera is a Camera(201, 101, 90)
//    When ray = ray_for_pixel(camera, 0, 0)
//    Then ray.origin = point(0, 0, 0)
//    And ray.direction = vector(0.665186, 0.332593, -0.668512)
    @Test
    fun `Constructing a ray through a corner of the canvas`() {
        // Given
        val camera = Camera(201, 101, 90.0)
        // When
        val ray = camera.generateRay(0, 0)
        // Then
        assertEquals(Point(0, 0, 0), ray.origin, "Ray origin should be at point(0, 0, 0)")
        assertEquals(Vector(0.665186, 0.332593, -0.668512), ray.direction, "Ray direction should be Vector(0.665186, 0.332593, -0.668512)")
    }

//    Scenario: Constructing a ray when the camera is transformed
//    Given camera is a Camera(201, 101, 90)
//    When camera.transform = rotation_y(0.785398) * translation(0, -2, 5)
//    And ray = ray_for_pixel(camera, 100, 50)
//    Then ray.origin = point(0, 2, -5)
//    And ray.direction = vector(0.707106, 0, -0.707106)
    @Test
    fun `Constructing a ray when the camera is transformed`() {
        // Given
        val camera = Camera(201, 101, 90.0)
        camera.transform = Matrix.rotationY(0.785398) * Matrix.translation(0, -2, 5)
        // When
        val ray = camera.generateRay(100, 50)
        // Then
        assertEquals(Point(0, 2, -5), ray.origin, "Ray origin should be at point(0, 2, -5)")
        assertEquals(Vector(0.707106, 0.0, -0.707106), ray.direction, "Ray direction should be Vector(0.707106, 0.0, -0.707106)")
    }
}
