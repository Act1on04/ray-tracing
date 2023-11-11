import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MatrixTest {
//    Scenario: Constructing and inspecting a 4x4 matrix
//    Given the following matrix M:
//    | 1 | 2 | 3 | 4 |
//    | 5.5 | 6.5 | 7.5 | 8.5 |
//    | 9 | 10 | 11 | 12 |
//    | 13.5 | 14.5 | 15.5 | 16.5 |
//    Then M[0,0] = 1
//    And M[0,3] = 4
//    And M[1,0] = 5.5
//    And M[1,2] = 7.5
//    And M[2,2] = 11
//    And M[3,0] = 13.5
//    And M[3,2] = 15.5
    @Test
    fun fillMatrix4x4() {
        val mtrx = Matrix(4)
        mtrx.fill(1, 2, 3, 4, 5.5, 6.5, 7.5, 8.5, 9, 10, 11, 12, 13.5, 14.5, 15.5, 16.5)

        assertEquals(1.0, mtrx[0, 0], "the mtrx[0, 0] should equal 1.0")
        assertEquals(4.0, mtrx[0, 3], "the mtrx[0, 3] should equal 4.0")
        assertEquals(5.5, mtrx[1, 0], "the mtrx[1, 0] should equal 5.5")
        assertEquals(7.5, mtrx[1, 2], "the mtrx[1, 2] should equal 7.5")
        assertEquals(11.0, mtrx[2, 2], "the mtrx[2, 2] should equal 11.0")
        assertEquals(13.5, mtrx[3, 0], "the mtrx[3, 0] should equal 13.5")
        assertEquals(15.5, mtrx[3, 2], "the mtrx[3, 2] should equal 15.5")
    }

    @Test
    fun identityMatrix4x4() {
        val mtrx = Matrix(4)
        mtrx.setIdentity()

        assertEquals(1.0, mtrx[0, 0], "the mtrx[0, 0] should equal 1.0")
        assertEquals(0.0, mtrx[0, 3], "the mtrx[0, 3] should equal 0.0")
        assertEquals(1.0, mtrx[1, 1], "the mtrx[1, 1] should equal 1.0")
        assertEquals(0.0, mtrx[1, 2], "the mtrx[1, 2] should equal 0.0")
        assertEquals(1.0, mtrx[3, 3], "the mtrx[4, 4] should equal 1.0")
    }

//    Scenario: A 2x2 matrix ought to be representable
//    Given the following matrix M:
//    | -3 | 5 |
//    | 1 | -2 |
//    Then M[0,0] = -3
//    And M[0,1] = 5
//    And M[1,0] = 1
//    And M[1,1] = -2
    @Test
    fun fillMatrix2x2() {
        val mtrx = Matrix(2)
        mtrx.fill(-3, 5, 1, -2)

        assertEquals(-3.0, mtrx[0, 0], "the mtrx[0, 0] should equal -3.0")
        assertEquals(5.0, mtrx[0, 1], "the mtrx[0, 1] should equal 5.0")
        assertEquals(1.0, mtrx[1, 0], "the mtrx[1, 0] should equal 1.0")
        assertEquals(-2.0, mtrx[1, 1], "the mtrx[1, 1] should equal -2.0")
    }

//    Scenario: A 3x3 matrix ought to be representable
//    Given the following matrix M:
//    | -3 | 5 | 0 |
//    | 1 | -2 | -7 |
//    | 0 | 1 | 1 |
//    Then M[0,0] = -3
//    And M[1,1] = -2
//    And M[2,2] = 1
    @Test
    fun fillMatrix3x3() {
        val mtrx = Matrix(3)
        mtrx.fill(-3, 5, 0, 1, -2, -7, 0, 0, 1)

        assertEquals(-3.0, mtrx[0, 0], "the mtrx[0, 0] should equal -3.0")
        assertEquals(-2.0, mtrx[1, 1], "the mtrx[1, 1] should equal -2.0")
        assertEquals(1.0, mtrx[2, 2], "the mtrx[2, 2] should equal 1.0")
    }

//    Scenario: Matrix equality with identical matrices
//    Given the following matrix M:
//    | 1 | 2 | 3 | 4 |
//    | 5 | 6 | 7 | 8 |
//    | 9 | 8 | 7 | 6 |
//    | 5 | 4 | 3 | 2 |
//    And the following matrix B:
//    | 1 | 2 | 3 | 4 |
//    | 5 | 6 | 7 | 8 |
//    | 9 | 8 | 7 | 6 |
//    | 5 | 4 | 3 | 2 |
//    Then M = B
    @Test
    fun equalsMatrix() {
        val matrix1 = Matrix(4).fill(1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2)
        val matrix2 = Matrix(4).fill(1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2)

        assertEquals(true, matrix1 == matrix2, "the matrix1 should equal matrix2")
    }

//    Scenario: Matrix equality with different matrices
//    Given the following matrix M:
//    | 1 | 2 | 3 | 4 |
//    | 5 | 6 | 7 | 8 |
//    | 9 | 8 | 7 | 6 |
//    | 5 | 4 | 3 | 2 |
//    And the following matrix B:
//    | 2 | 3 | 4 | 5 |
//    | 6 | 7 | 8 | 9 |
//    | 8 | 7 | 6 | 5 |
//    | 4 | 3 | 2 | 1 |
//    Then M != B
    @Test
    fun notEqualsMatrix() {
        val matrix1 = Matrix(4).fill(1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2)
        val matrix2 = Matrix(4).fill(2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1)

        assertEquals(false, matrix1 == matrix2, "the matrix1 should not be equal matrix2")
    }




}