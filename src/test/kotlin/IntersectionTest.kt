import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class IntersectionTest {

    @Test
    fun `An intersection encapsulates t and object`() {
        val sphere = Sphere()
        val i1 = Intersection(3.5, sphere)

        assertEquals(3.5, i1.t, "The t value of the intersection should be 3.5")
        assertEquals(sphere, i1.shape, "The shape of the intersection should be the sphere")
    }

    @Test
    fun `comparing intersections`() {
        val sphere = Sphere()
        val i1 = Intersection(3.5, sphere)
        val i2 = Intersection(0.3, sphere)

        assertTrue(i1 > i2, "i1 should be greater than i2")
    }

    @Test
    fun `comparing intersections 2`() {
        val sphere = Sphere()
        val i1 = Intersection(3.5, sphere)
        val i2 = Intersection(3.5, sphere)

        assertTrue(i1 == i2, "i1 should be Equal than i2")
    }


}