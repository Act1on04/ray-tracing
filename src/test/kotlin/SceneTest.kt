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

class SceneLightTest {

    //    Scenario: Creating a Scene with Lights
    //    Given scene is a Scene()
    //    Then scene contains no objects
    //    And scene contains no lights
    @Test
    fun `Creating a Scene with Lights`() {
        // Given
        val scene = Scene()
        // Then
        assertTrue(scene.getObjects().isEmpty(), "An empty scene must not have any objects")
        assertTrue(scene.getLights().isEmpty(), "An empty scene must not have any lights")
    }


    //    Scenario: Adding lights to a scene
    //    Given scene is a Scene()
    //    And light is point_light(point(-10, 10, -10), color(1, 1, 1))
    //    When light is added to scene
    //    Then scene contains light
    @Test
    fun `Adding lights to a scene`() {
        // Given
        val scene = Scene()
        val light = PointLightSource(Point(-10, 10, -10), Color(1, 1, 1))
        // When
        scene.addLight(light)
        // Then
        assertTrue(scene.getLights().isNotEmpty(), "Scene must have lights")
    }

}

class SceneMaterialTest {

//     Scenario: The default world
//     Given light is point_light(point(-10, 10, -10), color(1, 1, 1))
//     And s1 is a Sphere() with:
//     | material.color | (0.8, 1.0, 0.6) |
//     | material.diffuse | 0.7 |
//     | material.specular | 0.2 |
//     And s2 is a Sphere() with:
//     | transform | scaling(0.5, 0.5, 0.5) |
//     When scene is default_scene()
//     Then scene.light = light
//     And scene contains s1
//     And scene contains s2
    @Test
    fun `The default world`() {
        // Given
        val light = PointLightSource(Point(-10, 10, -10), Color(1, 1, 1))
        val s1 = Sphere()
        s1.material.color = Color(0.8, 1.0, 0.6)
        s1.material.diffuse = 0.7
        s1.material.specular = 0.2
        val s2 = Sphere()
        s2.transform = Matrix.scaling(0.5)

        // When
        val scene = Scene.defaultScene()
        // Then
        assertEquals(light, scene.getLights().first(), "The light should be added to the scene")
        assertEquals(s1.material, scene.getObjects()[0].material, "The material of s1 should be set correctly")
        assertEquals(s2.transform, scene.getObjects()[1].transform, "The transform of s2 should be set correctly")
    }

}

class SceneShadowTest {
    // Scenario: There is no shadow when nothing is collinear with point and light
    // Given scene is the defaultScene()
    // And point is a Point(0, 10, 0)
    // Then isShadowed(scene, point) is false
    @Test
    fun `There is no shadow when nothing is collinear with point and light`() {
        // Given
        val scene = Scene.defaultScene()
        val point = Point(0, 10, 0)
        // Then
        assertEquals(false, scene.isShadowed(point, PointLightSource(Point(-10, 10, -10))), "The scene.isShadowed(Point(0, 10, 0)) should be False")
    }

    // Scenario: The shadow when an object is between the point and the light
    // Given scene is the defaultScene()
    // And point is a Point(10, -10, 10)
    // Then isShadowed(scene, point) is true
    @Test
    fun `The shadow when an object is between the point and the light`() {
        // Given
        val scene = Scene.defaultScene()
        val point = Point(10, -10, 10)
        // Then
        assertEquals(true, scene.isShadowed(point, PointLightSource(Point(-10, 10, -10))), "The scene.isShadowed(Point(10, -10, 10)) should be True")
    }

    // Scenario: There is no shadow when an object is behind the light
    // Given scene is the defaultScene()
    // And point is a Point(-20, 20, -20)
    // Then isShadowed(scene, point) is false
    @Test
    fun `There is no shadow when an object is behind the light`() {
        // Given
        val scene = Scene.defaultScene()
        val point = Point(-20, 20, -20)
        // Then
        assertEquals(false, scene.isShadowed(point, PointLightSource(Point(-10, 10, -10))), "The scene.isShadowed(Point(-20, 20, -20)) should be False")
    }

    // Scenario: There is no shadow when an object is behind the point
    // Given scene is the defaultScene()
    // And point is a Point(-2, 2, -2)
    // Then isShadowed(scene, point) is false
    @Test
    fun `There is no shadow when an object is behind the point`() {
        // Given
        val scene = Scene.defaultScene()
        val point = Point(-2, 2, -2)
        // Then
        assertEquals(false, scene.isShadowed(point, PointLightSource(Point(-10, 10, -10))), "The scene.isShadowed(Point(-2, 2, -2)) should be False")
    }

}