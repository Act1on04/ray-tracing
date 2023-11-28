import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class IntersectionsTest {

    @Test
    fun `Aggregating intersections`() {
        val sphere = Sphere()
        val i1 = Intersection(1.0, sphere)
        val i2 = Intersection(2.0, sphere)
        val xs = Intersections(i1, i2)

        assertEquals(2, xs.count, "The count of intersections should be 2")
        assertEquals(1.0, xs[0].t, "The first intersection's t value should be 1.0")
        assertEquals(2.0, xs[1].t, "The second intersection's t value should be 2.0")
    }
}