import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class RayTest {


//    Scenario: Creating and querying a ray
//    Given origin is a Point(1, 2, 3)
//    And direction is a Vector(4, 5, 6)
//    When ray is a Ray(origin, direction)
//    Then ray.origin = origin
//    And ray.direction = direction
    @Test
    fun creatingAndQueryingRay() {
        val ray = Ray(Point(1, 2, 3), Vector(4, 5, 6))

        assertEquals(true, ray.origin == Point(1, 2, 3), "the origin of the ray should equal Point(1, 2, 3)")
        assertEquals(true, ray.direction == Vector(4, 5, 6), "the direction of the ray should equal Vector(4, 5, 6)")
    }


//    Scenario: Creating and querying a ray from two points
//    Given origin is a Point(2, 3, 4)
//    And target is a Point(3, 4, 5)
//    When ray is a Ray(origin, target)
//    Then ray.origin = origin
//    And ray.direction is normalized
    @Test
    fun creatingRayFromTwoPoints() {
        val ray = Ray(Point(1, 2, 3), Point(3, 4, 5))

//        println("ray.direction: ${ray.direction}")
//        println("normalize: ${(Point(1, 2, 3) - Point(3, 4, 5)).normalize()}")

        assertEquals(true, ray.origin == Point(1, 2, 3), "the origin of the ray should equal Point(1, 2, 3)")
        assertEquals(true, ray.direction == (Point(3, 4, 5) - Point(1, 2, 3)).normalize(),
            "the direction of the ray should be normalized")
    }

//    Scenario Outline: Computing a point from a distance
//    Given ray is a Ray(point(2, 3, 4), vector(1, 0, 0))
//    Then pointAt(ray, <distance> ) = Point( <x> , <y> , <z> )
//    Examples:
//    | distance | x | y | z |
//    | 0 | 2 | 3 | 4 |
//    | 1 | 3 | 3 | 4 |
//    | -1 | 1 | 3 | 4 |
//    | 2.5 | 4.5 | 3 | 4 |

    @Test
    fun distancePoint() {
        val ray = Ray(Point(2, 3, 4), Vector(1, 0, 0))

        assertEquals(true, ray.pointAt(0) == Point(2, 3, 4), "the distance point should equal Point(2, 3, 4)")
        assertEquals(true, ray.pointAt(1) == Point(3, 3, 4), "the distance point should equal Point(3, 3, 4)")
        assertEquals(true, ray.pointAt(-1) == Point(1, 3, 4), "the distance point should equal Point(1, 3, 4)")
        assertEquals(true, ray.pointAt(2.5) == Point(4.5, 3.0, 4.0), "the distance point should equal Point(4.5, 3.0, 4.0)")
    }

}