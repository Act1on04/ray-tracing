import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class TupleTest {

    @Test
    fun a_plus_b() {
        val a = Tuple(3.0, -2.0, 5.0, 1.0)
        val b = Tuple(-2.0, 3.0, 1.0, 0.0)

        val sum = a + b

        assertEquals(1.0, sum.x, "x should equal 1.0")
        assertEquals(1.0, sum.y, "y should equal 1.0")
        assertEquals(6.0, sum.z, "z should equal 6.0")
        assertEquals(1.0, sum.w, "w should equal 1.0")

    }
    @Test
    fun a_minus_b() {
        val a = Tuple(3.0, -2.0, 5.0, 1.0)
        val b = Tuple(-2.0, 3.0, 1.0, 0.0)

        val minus = a - b

        assertEquals(5.0, minus.x, "x should equal 5.0")
        assertEquals(-5.0, minus.y, "y should equal -5.0")
        assertEquals(4.0, minus.z, "z should equal 4.0")
        assertEquals(1.0, minus.w, "w should equal 1.0")

    }
    @Test
    fun a_unaryMinus() {
        val a = Tuple(3.0, -2.0, 5.0, 1.0)

        val minusA = -a

        assertEquals(-3.0, minusA.x, "x should equal -3.0")
        assertEquals(2.0, minusA.y, "y should equal 2.0")
        assertEquals(-5.0, minusA.z, "z should equal -5.0")
        assertEquals(-1.0, minusA.w, "w should equal -1.0")

    }

}

class PointTest {

    @Test
    fun min_a_b() {
        val a = Point(1.0, 2.0, 6.0)
        val b = Point(4.0, 5.0, 3.0)

        val min = min(a, b)

//        println(min)

        assertEquals(Point(1.0, 2.0, 3.0), min, "min should equal Point(1.0, 2.0, 3.0)")

    }

    @Test
    fun max_a_b() {
        val a = Point(1.0, 2.0, 6.0)
        val b = Point(4.0, 5.0, 3.0)

        val max = max(a, b)

//        println(max)

        assertEquals(Point(4.0, 5.0, 6.0), max, "min should equal Point(4.0, 5.0, 6.0)")

    }


}

class VectorTest {

    @Test
    fun a_equals_b() {
        val a = Vector(3.0, -2.0, 5.0)
        val b = Vector(3.0, -2.0, 5.0)

        assertEquals(true, a == b, "a should equal b")

    }

//    Scenario: Reflecting a vector approaching at 45 degrees
//    Given vector1 is a Vector(1, -1, 0)
//    And vector2 is a Vector(0, 1, 0)
//    When refl is reflect(v1, v2)
//    Then refl = Vector(1, 1, 0)
    @Test
    fun `Reflecting a vector approaching at 45 degrees`() {
        // Given
        val vector1 = Vector(1, -1, 0)
        val vector2 = Vector(0, 1, 0)
        // When
        val refl = vector1.reflect(vector2)
        // Then
        assertEquals(Vector(1, 1, 0), refl, "The refl should be Vector(1, 1, 0)")
    }

    //    Scenario: Reflecting a vector off a slanted surface
    //    Given vector1 is a Vector(0, -1, 0)
    //    And vector2 is a Vector(0.707011, 0.707011, 0)
    //    When refl is reflect(v1, v2)
    //    Then refl = Vector(1, 0, 0)
    @Test
    fun `Reflecting a vector off a slanted surface`() {

        // Given
        val vector1 = Vector(0, -1, 0)
        val vector2 = Vector(0.707011, 0.707011, 0.0)
        // When
        val refl = vector1.reflect(vector2)
        // Then
        assertEquals(Vector(1, 0, 0), refl, "The refl should be Vector(1, 0, 0)")
    }

}