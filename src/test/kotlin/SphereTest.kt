import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SphereTest {

    @Test
    fun `intersect a ray with a sphere at two points`() {
        // Given
        val ray = Ray(Point(0, 0, -5), Vector(0, 0, 1))
        val sphere = Sphere()

        // When
        val xs = sphere.intersect(ray)

        // Then
        assertEquals(2, xs.count, "The count of intersections should be 2")
        assertEquals(4.0, xs[0].t, "The first intersection's t value should be 4.0")
        assertEquals(6.0, xs[1].t, "The second intersection's t value should be 6.0")
    }

    @Test
    fun `intersect sets the object on the intersection`() {
        // Given
        val ray = Ray(Point(0, 0, -5), Vector(0, 0, 1))
        val sphere = Sphere()

        // When
        val xs = sphere.intersect(ray)

        // Then
        assertEquals(2, xs.count, "The count of intersections should be 2")
        assertEquals(sphere, xs[0].shape, "The first intersection's shape should be the sphere")
        assertEquals(sphere, xs[1].shape, "The second intersection's shape should be the sphere")
    }

    @Test
    fun `a ray intersects a sphere at a tangent`() {
        // Given
        val ray = Ray(Point(0, 1, -5), Vector(0, 0, 1))
        val sphere = Sphere()

        // When
        val xs = sphere.intersect(ray)

        // Then
        assertEquals(2, xs.count, "The count of intersections should be 2")
        assertEquals(5.0, xs[0].t, "The first intersection's t value should be 5.0")
        assertEquals(5.0, xs[1].t, "The second intersection's t value should be 5.0")
    }

    @Test
    fun `a ray misses a sphere`() {
        // Given
        val ray = Ray(Point(0, 2, -5), Vector(0, 0, 1))
        val sphere = Sphere()

        // When
        val xs = sphere.intersect(ray)

        // Then
        assertEquals(0, xs.count, "The count of intersections should be 0")
    }

    @Test
    fun `a ray originates inside a sphere`() {
        // Given
        val ray = Ray(Point(0, 0, 0), Vector(0, 0, 1))
        val sphere = Sphere()

        // When
        val xs = sphere.intersect(ray)

        // Then
        assertEquals(2, xs.count, "The count of intersections should be 2")
        assertEquals(-1.0, xs[0].t, "The first intersection's t value should be -1.0")
        assertEquals(1.0, xs[1].t, "The second intersection's t value should be 1.0")
    }

    @Test
    fun `a sphere is behind a ray`() {
        // Given
        val ray = Ray(Point(0, 0, 5), Vector(0, 0, 1))
        val sphere = Sphere()

        // When
        val xs = sphere.intersect(ray)

        // Then
        assertEquals(2, xs.count, "The count of intersections should be 2")
        assertEquals(-6.0, xs[0].t, "The first intersection's t value should be -6.0")
        assertEquals(-4.0, xs[1].t, "The second intersection's t value should be -4.0")
    }

}

class SphereTransformTest {

//    Scenario: A sphere's default transformation
//    Given sphere is a Sphere()
//    Then sphere.transform = identity_matrix
    @Test
    fun `A sphere's default transformation`() {
        // Given
        val sphere = Sphere()

        // Then
        assertEquals(Matrix.identity(4), sphere.transform, "The default transformation should be the identity matrix")
    }

//    Scenario: Changing a sphere's transformation
//    Given sphere is a Sphere()
//    And transform is translation(2, 3, 4)
//    When set_transform(sphere, transform)
//    Then sphere.transform = transform
    @Test
    fun `Changing a sphere's transformation`() {
        // Given
        val sphere = Sphere()
        val transform = Matrix.translation(2, 3, 4)
        // When
        sphere.transform = transform
        // Then
        assertEquals(transform, sphere.transform, "The sphere's transformation should be the given transform")
    }

//    Scenario: Intersecting a scaled sphere with a ray
//    Given ray is a Ray(point(0, 0, -5), vector(0, 0, 1))
//    And sphere is a Sphere()
//    And transform is scaling(2, 2, 2)
//    When set_transform(sphere, transform)
//    And xs is intersect(sphere, ray)
//    Then xs.count = 2
//    And xs[0].t = 3
//    And xs[1].t = 7
    @Test
    fun `Intersecting a scaled sphere with a ray`() {
        // Given
        val ray = Ray(Point(0, 0, -5), Vector(0, 0, 1))
        val sphere = Sphere()
        val transform = Matrix.scaling(2, 2, 2)
        // When
        sphere.transform = transform
        val xs = sphere.intersect(ray)

        // Then
        assertEquals(2, xs.count, "The count of intersections should be 2")
        assertEquals(3.0, xs[0].t, "The first intersection's t value should be 3.0")
        assertEquals(7.0, xs[1].t, "The second intersection's t value should be 7.0")
    }

//    Scenario: Intersecting a translated sphere with a ray
//    Given ray is a Ray(point(0, 0, -5), vector(0, 0, 1))
//    And sphere is a Sphere()
//    And transform is translation(5, 0, 0)
//    When set_transform(sphere, transform)
//    And xs is intersect(sphere, ray)
//    Then xs.count = 0
    @Test
    fun `Intersecting a translated sphere with a ray`() {
        // Given
        val ray = Ray(Point(0, 0, -5), Vector(0, 0, 1))
        val sphere = Sphere()
        val transform = Matrix.translation(5, 0, 0)
        // When
        sphere.transform = transform
        val xs = sphere.intersect(ray)
        // Then
        assertEquals(0, xs.count, "The count of intersections should be 0")
    }

}