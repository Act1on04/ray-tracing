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

class IntersectionsHitTest {

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


//    Scenario: The hit, when all intersections have positive t
//    Given sphere is a Sphere()
//    And i1 is an Intersection(1, sphere)
//    And i2 is an Intersection(2, sphere)
//    And xs is Intersections(i2, i1)
//    When i is hit(xs)
//    Then i = i1
    @Test
    fun `The hit, when all intersections have positive t`() {
        // Given
        val sphere = Sphere()
        val i1 = Intersection(1.0, sphere)
        val i2 = Intersection(2.0, sphere)
        val xs = Intersections(i2, i1)

        // When
        val i = xs.hit()

        // Then
        assertEquals(i, i1, "The hit of intersections should be 'i1'")
    }


//    Scenario: The hit, when some intersections have negative t
//    Given sphere is a Sphere()
//    And i1 is an Intersection(-1, sphere)
//    And i2 is an Intersection(1, sphere)
//    And xs is Intersections(i2, i1)
//    When i is hit(xs)
//    Then i = i2
    @Test
    fun `The hit, when some intersections have negative t`() {
        // Given
        val sphere = Sphere()
        val i1 = Intersection(-1.0, sphere)
        val i2 = Intersection(1.0, sphere)
        val xs = Intersections(i2, i1)

        // When
        val i = xs.hit()

        // Then
        assertEquals(i, i2, "The hit of intersections should be 'i2'")
    }

//    Scenario: The hit, when all intersections have negative t
//    Given sphere is a Sphere()
//    And i1 is an Intersection(-2, sphere)
//    And i2 is an Intersection(-1, sphere)
//    And xs is Intersections(i2, i1)
//    When i is hit(xs)
//    Then i is nothing
    @Test
    fun `The hit, when all intersections have negative t`() {
        // Given
        val sphere = Sphere()
        val i1 = Intersection(-2.0, sphere)
        val i2 = Intersection(-1.0, sphere)
        val xs = Intersections(i2, i1)

        // When
        val i = xs.hit()

        // Then
        assertEquals(i, null, "The hit of intersections should be 'null'")
    }



//    Scenario: The hit is the lowest non-negative intersection
//    Given sphere is a Sphere()
//    And i1 is an Intersection(5, sphere)
//    And i2 is an Intersection(7, sphere)
//    And i3 is an Intersection(-3, sphere)
//    And i4 is an Intersection(2, sphere)
//    And xs is Intersections(i1, i2, i3, i4)
//    When i is hit(xs)
//    Then i = i4
    @Test
    fun `The hit is the lowest non-negative intersection`() {
        // Given
        val sphere = Sphere()
        val i1 = Intersection(5.0, sphere)
        val i2 = Intersection(7.0, sphere)
        val i3 = Intersection(-3.0, sphere)
        val i4 = Intersection(2.0, sphere)
        val xs = Intersections(i1, i2, i3, i4)

        // When
        val i = xs.hit()

        // Then
        assertEquals(i, i4, "The hit of intersections should be 'i4'")
    }

}

