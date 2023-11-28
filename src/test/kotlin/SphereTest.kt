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