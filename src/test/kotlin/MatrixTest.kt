import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.abs

class MatrixCreateTest {
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

}

class MatrixEqualityTest {

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
class MatrixMultiplyTest {

//    Scenario: Multiplying two matrices
//    Given the following matrix M:
//    | 1 | 2 | 3 | 4 |
//    | 5 | 6 | 7 | 8 |
//    | 9 | 8 | 7 | 6 |
//    | 5 | 4 | 3 | 2 |
//    And the following matrix B:
//    | -2 | 1 | 2 | 3 |
//    | 3 | 2 | 1 | -1 |
//    | 4 | 3 | 6 | 5 |
//    | 1 | 2 | 7 | 8 |
//    Then M * B is the following matrix:
//    | 20| 22 | 50 | 48 |
//    | 44| 54 | 114 | 108 |
//    | 40| 58 | 110 | 102 |
//    | 16| 26 | 46 | 42 |
    @Test
    fun multiplyingTwoMatrices() {
        val M = Matrix(4).fill(1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2)
        val B = Matrix(4).fill(-2, 1, 2, 3, 3, 2, 1, -1, 4, 3, 6, 5, 1, 2, 7, 8)
        val result = Matrix(4).fill(20, 22, 50, 48, 44, 54, 114, 108, 40, 58, 110, 102, 16, 26, 46, 42)

        assertEquals(true, result == M * B, "the M x B should equal Matrix(20, 22, 50, 48, 44, 54, 114, 108, 40, 58, 110, 102, 16, 26, 46, 42)")
    }

//    Scenario: Multiplying a matrix with the identity matrix
//    Given M is the matrix identity(4)
//    And the following matrix B:
//    | 20 | 22 | 50 | 48 |
//    | 44 | 54 | 114 | 108 |
//    | 40 | 58 | 110 | 102 |
//    | 16 | 26 | 46 | 42 |
//    Then M * B = B
//    And B * M = B
    @Test
    fun notEqualsMatrix() {
        val M = Matrix(4).setIdentity()
        val B = Matrix(4).fill(20, 22, 50, 48, 44, 54, 114, 108, 40, 58, 110, 102, 16, 26, 46, 42)

        assertEquals(true, M * B == B, "the M x B should equal B")
        assertEquals(true, B * M == B, "the B x M should equal B")
    }

//    Scenario: A matrix multiplied by a vector
//    Given the following matrix M:
//    | 1 | 2 | 3 | 4 |
//    | 2 | 4 | 4 | 2 |
//    | 8 | 6 | 4 | 1 |
//    | 0 | 0 | 0 | 1 |
//    And v is a Vector(1, 2, 3)
//    Then M * v = Vector(14, 22, 32)
    @Test
    fun matrixMultipliedVector() {
        val m = Matrix(4).fill(1, 2, 3, 4, 2, 4, 4, 2, 8, 6, 4, 1, 0, 0, 0, 1)
        val v = Vector(1, 2, 3)

        assertEquals(Vector(14, 22, 32), m * v, "the result should equal Vector(14, 22, 32)")
    }


    //    Scenario: Multiplying the identity matrix by a vector
//    Given v is a Vector(1, 2, 3)
//    Then identity_matrix * v = v
    @Test
    fun  identityMultipliedVector() {
        val m = Matrix(4).setIdentity()
        val v = Vector(1, 2, 3)
        assertEquals(Vector(1, 2, 3), m * v, "the result should equal Vector(1, 2, 3)")
    }

    //    Scenario: A matrix multiplied by a point
//    Given the following matrix M:
//    | 1 | 2 | 3 | 4 |
//    | 2 | 4 | 4 | 2 |
//    | 8 | 6 | 4 | 1 |
//    | 0 | 0 | 0 | 1 |
//    And p is a Point(1, 2, 3)
//    Then M * p = Point(18, 24, 33)
    @Test
    fun matrixMultipliedPoint() {
        val m = Matrix(4).fill(1, 2, 3, 4, 2, 4, 4, 2, 8, 6, 4, 1, 0, 0, 0, 1)
        val p = Point(1, 2, 3)

        assertEquals(Point(18, 24, 33), m * p, "the result should equal Point(18, 24, 33)")
    }


//    Scenario: Multiplying the identity matrix by a point
//    Given p is a Point(1, 2, 3)
//    Then identity_matrix * p = p
    @Test
    fun identityMultipliedPoint() {
        val identityMatrix = Matrix(4).setIdentity()
        val p = Point(1, 2, 3)

        assertEquals(Point(1, 2, 3), identityMatrix * p, "the result should equal Point(1, 2, 3)")
    }

}

class MatrixTransposingTest {
    //    Scenario: Transposing a matrix
//    Given the following matrix M:
//    | 0 | 9 | 3 | 0 |
//    | 9 | 8 | 0 | 8 |
//    | 1 | 8 | 5 | 3 |
//    | 0 | 0 | 5 | 8 |
//    Then transpose(M) is the following matrix:
//    | 0 | 9 | 1 | 0 |
//    | 9 | 8 | 8 | 0 |
//    | 3 | 0 | 5 | 5 |
//    | 0 | 8 | 3 | 8 |
    @Test
    fun transposingMatrix() {
        val M = Matrix(4).fill(0, 9, 3, 0, 9, 8, 0, 8, 1, 8, 5, 3, 0, 0, 5, 8)
        val transposeM = Matrix(4).fill(0, 9, 1, 0, 9, 8, 8, 0, 3, 0, 5, 5, 0, 8, 3, 8)
        assertEquals(transposeM, M.transpose(), "the result should equal \n$transposeM")
    }

    //    Scenario: Transposing the identity matrix
//    Given M is transpose(identity_matrix)
//    Then M = identity_matrix
    @Test
    fun transposingIdentity() {
        val identityMatrix = Matrix(4).setIdentity()
        assertEquals(
            true,
            identityMatrix == identityMatrix.transpose(),
            "the result of Transposing the identity matrix should equal identity matrix"
        )
    }

}
class MatrixDeterminantTest {
    
//    Scenario: Calculating the determinant of a 2x2 matrix
//    Given the following matrix M:
//    | 1 | 5 |
//    | -3 | 2 |
//    Then determinant(M) = 17
    @Test
    fun  determinant2x2Matrix() {
        val M = Matrix(2).fill(1, 5, -3, 2)

        assertEquals(17.0, M.determinant(), "the result should equal 17.0")
    }

//    Scenario: A submatrix of a 3x3 matrix is a 2x2 matrix
//    Given the following matrix M:
//    | 1 | 5 | 0 |
//    | -3 | 2 | 7 |
//    | 0 | 6 | -3 |
//    Then submatrix(M, 0, 2) is the following matrix:
//    | -3 | 2 |
//    | 0 | 6 |
    @Test
    fun  submatrix3x3Matrix() {
        val M = Matrix(3).fill(1, 5, 0, -3, 2, 7, 0, 6, -3)
        val subMatrix = Matrix(2).fill(-3, 2, 0, 6)

        assertEquals(true, M.submatrix(0, 2) == subMatrix, "the result should equal \n$subMatrix")
    }

//    Scenario: A submatrix of a 4x4 matrix is a 3x3 matrix
//    Given the following matrix M:
//    | -6 | 1 | 1 | 6 |
//    | -8 | 5 | 8 | 6 |
//    | -1 | 0 | 8 | 2 |
//    | -7 | 1 | -1 | 1 |
//    Then submatrix(M, 2, 1) is the following matrix:
//    | -6 | 1 | 6 |
//    | -8 | 8 | 6 |
//    | -7 | -1 | 1 |
    @Test
    fun  submatrix4x4Matrix() {
        val M = Matrix(4).fill(-6, 1, 1, 6, -8, 5, 8, 6, -1, 0, 8, 2, -7, 1, -1, 1)
        val subMatrix = Matrix(3).fill(-6, 1, 6, -8, 8, 6, -7, -1, 1)

        assertEquals(true, M.submatrix(2, 1) == subMatrix, "the result should equal \n$subMatrix")
    }

//    Scenario: Calculating a minor of a 3x3 matrix
//    Given the following matrix M:
//    | 3 | 5 | 0 |
//    | 2 | -1 | -7 |
//    | 6 | -1 | 5 |
//    And B is submatrix(M, 1, 0)
//    Then determinant(B) = 25
//    And minor(M, 1, 0) = 25
    @Test
    fun  minor3x3Matrix() {
        val M = Matrix(3).fill(3, 5, 0, 2, -1, -7, 6, -1, 5)

        assertEquals(25.0, M.submatrix(1, 0).determinant(), "the result should equal 25.0")
        assertEquals(25.0, M.minor(1, 0), "the result should equal 25.0")
    }

//    Scenario: Calculating a cofactor of a 3x3 matrix
//    Given the following matrix M:
//    | 3 | 5 | 0 |
//    | 2 | -1 | -7 |
//    | 6 | -1 | 5 |
//    Then minor(M, 0, 0) = -12
//    And cofactor(M, 0, 0) = -12
//    And minor(M, 1, 0) = 25
//    And cofactor(M, 1, 0) = -25
    @Test
    fun  cofactor3x3Matrix() {
        val M = Matrix(3).fill(3, 5, 0, 2, -1, -7, 6, -1, 5)

        assertEquals(-12.0, M.minor(0, 0), "the result should equal -12.0")
        assertEquals(-12.0, M.cofactor(0, 0), "the result should equal -12.0")
        assertEquals(25.0, M.minor(1, 0), "the result should equal 25.0")
        assertEquals(-25.0, M.cofactor(1, 0), "the result should equal -25.0")
    }

//    Scenario: Calculating the determinant of a 3x3 matrix
//    Given the following matrix M:
//    | 1 | 2 | 6 |
//    | -5 | 8 | -4 |
//    | 2 | 6 | 4 |
//    Then cofactor(M, 0, 0) = 56
//    And cofactor(M, 0, 1) = 12
//    And cofactor(M, 0, 2) = -46
//    And determinant(M) = -196
    @Test
    fun  determinant3x3Matrix() {
        val M = Matrix(3).fill(1, 2, 6, -5, 8, -4, 2, 6, 4)

        assertEquals(56.0, M.cofactor(0, 0), "the result should equal 56.0")
        assertEquals(12.0, M.cofactor(0, 1), "the result should equal 12.0")
        assertEquals(-46.0, M.cofactor(0, 2), "the result should equal -46.0")
        assertEquals(-196.0, M.determinant(), "the result should equal -196.0")
    }

//    Scenario: Calculating the determinant of a 4x4 matrix
//    Given the following matrix M:
//    | -2 | -8 | 3 | 5 |
//    | -3 | 1 | 7 | 3 |
//    | 1 | 2 | -9 | 6 |
//    | -6 | 7 | 7 | -9 |
//    Then cofactor(M, 0, 0) = 690
//    And cofactor(M, 0, 1) = 447
//    And cofactor(M, 0, 2) = 210
//    And cofactor(M, 0, 3) = 51
//    And determinant(M) = -4071
    @Test
    fun  determinant4x4Matrix() {
        val M = Matrix(4).fill(-2, -8, 3, 5, -3, 1, 7, 3, 1, 2, -9, 6, -6, 7, 7, -9)

        assertEquals(690.0, M.cofactor(0, 0), "the result should equal 690.0")
        assertEquals(447.0, M.cofactor(0, 1), "the result should equal 447.0")
        assertEquals(210.0, M.cofactor(0, 2), "the result should equal 210.0")
        assertEquals(51.0, M.cofactor(0, 3), "the result should equal 51.0")
        assertEquals(-4071.0, M.determinant(), "the result should equal -4071.0")
    }

//    Scenario: Testing an invertible matrix for invertibility
//    Given the following matrix M:
//    | 6 | 4 | 4 | 4 |
//    | 5 | 5 | 7 | 6 |
//    | 4 | -9 | 3 | -7 |
//    | 9 | 1 | 7 | -6 |
//    Then determinant(M) = -2120
//    And M is invertible
    @Test
    fun  invertibleMatrix() {
        val M = Matrix(4).fill(6, 4, 4, 4, 5, 5, 7, 6, 4, -9, 3, -7, 9, 1, 7, -6)

        assertEquals(-2120.0, M.determinant(), "the result should equal -2120.0")
        assertEquals(true, M.isInvertible(), "the result should equal True")
    }
    
//    Scenario: Testing a noninvertible matrix for invertibility
//    Given the following matrix M:
//    | -4 | 2 | -2 | -3 |
//    | 9 | 6 | 2 | 6 |
//    | 0 | -5 | 1 | -5 |
//    | 0 | 0 | 0 | 0 |
//    Then determinant(M) = 0
//    And M is not invertible
    @Test
    fun  notInvertibleMatrix() {
        val M = Matrix(4).fill(-4, 2, -2, -3, 9, 6, 2, 6, 0, -5, 1, -5, 0, 0, 0, 0)

        assertEquals(0.0, M.determinant(), "the result should equal 0.0")
        assertEquals(false, M.isInvertible(), "the result should equal True")
    }

//    Scenario: Calculating the inverse of a matrix
//    Given the following matrix M:
//    | -5 | 2 | 6 | -8 |
//    | 1 | -5 | 1 | 8 |
//    | 7 | 7 | -6 | -7 |
//    | 1 | -3 | 7 | 4 |
//    And B is inverse(M)
//    Then determinant(M) = 532
//    And cofactor(M, 2, 3) = -160
//    And B[3,2] = -160/532
//    And cofactor(M, 3, 2) = 105
//    And B[2,3] = 105/532
//    And B is the following matrix:
//    | 0.21805 | 0.45113 | 0.24060 | -0.04511 |
//    | -0.80827 | -1.45677 | -0.44361 | 0.52068 |
//    | -0.07895 | -0.22368 | -0.05263 | 0.19737 |
//    | -0.52256 | -0.81391 | -0.30075 | 0.30639 |
    @Test
    fun  inverseMatrix() {
        val M = Matrix(4).fill(-5, 2, 6, -8, 1, -5, 1, 8, 7, 7, -6, -7, 1, -3, 7, 4)
        val B = M.inverse()
        val invB = Matrix(4).fill(0.21805, 0.45113, 0.24060, -0.04511, -0.80827, -1.45677, -0.44361, 0.52068,
            -0.07895, -0.22368, -0.05263, 0.19737, -0.52256, -0.81391, -0.30075, 0.30639)

        assertEquals(532.0, M.determinant(), "the result should equal 532.0")
        assertEquals(-160.0, M.cofactor(2, 3), "the result should equal -160.0")
        assertEquals(-160.0/532.0, B[3, 2], "the B[3, 2] should equal -160/532")
        assertEquals(105.0, M.cofactor(3, 2), "the result should equal 105.0")
        assertEquals(105.0/532.0, B[2, 3] , "the B[3, 2] should equal 105/532")
        assertEquals(true, B == invB, "the result should equal \n$invB")
    }

//    Scenario: Calculating the inverse of another matrix
//    Given the following matrix M:
//    | 8 | -5 | 9 | 2 |
//    | 7 | 5 | 6 | 1 |
//    | -6 | 0 | 9 | 6 |
//    | -3 | 0 | -9 | -4 |
//    Then inverse(M) is the following matrix:
//    | -0.15385 | -0.15385 | -0.28205 | -0.53846 |
//    | -0.07692 | 0.12308 | 0.02564 | 0.03077 |
//    | 0.35897 | 0.35897 | 0.43590 | 0.92308 |
//    | -0.69231 | -0.69231 | -0.76923 | -1.92308 |
    @Test
    fun  inverseMatrix2() {
        val M = Matrix(4).fill(8, -5, 9, 2, 7, 5, 6, 1, -6, 0, 9, 6, -3, 0, -9, -4)
        val B = M.inverse()
        val invB = Matrix(4).fill(-0.15385, -0.15385, -0.28205, -0.53846, -0.07692, 0.12308, 0.02564, 0.03077,
            0.35897, 0.35897, 0.43590, 0.92308, -0.69231, -0.69231, -0.76923, -1.92308)

        assertEquals(true, B == invB, "the result should equal \n$invB")
    }

//    Scenario: Calculating the inverse of a third matrix
//    Given the following matrix M:
//    | 9 | 3 | 0 | 9 |
//    | -5 | -2 | -6 | -3 |
//    | -4 | 9 | 6 | 4 |
//    | -7 | 6 | 6 | 2 |
//    Then inverse(M) is the following matrix:
//    | -0.04074 | -0.07778 | 0.14444 | -0.22222 |
//    | -0.07778 | 0.03333 | 0.36667 | -0.33333 |
//    | -0.02901 | -0.14630 | -0.10926 | 0.12963 |
//    | 0.17778 | 0.06667 | -0.26667 | 0.33333 |
    @Test
    fun  inverseMatrix3() {
        val M = Matrix(4).fill(9, 3, 0, 9, -5, -2, -6, -3, -4, 9, 6, 4, -7, 6, 6, 2)
        val B = M.inverse()
        val invB = Matrix(4).fill(-0.04074, -0.07778, 0.14444, -0.22222, -0.07778, 0.03333, 0.36667, -0.33333,
            -0.02901, -0.14630, -0.10926, 0.12963, 0.17778, 0.06667, -0.26667, 0.33333)

        assertEquals(true, B == invB, "the result should equal \n$invB")
    }

//    Scenario: Multiplying a product by its inverse
//    Given the following matrix M:
//    | 3 | -9 | 7 | 3 |
//    | 3 | -8 | 2 | -9 |
//    | -4 | 4 | 4 | 1 |
//    | -6 | 5 | -1 | 1 |
//    And the following matrix B:
//    | 8 | 2 | 2 | 2 |
//    | 3 | -1 | 7 | 0 |
//    | 7 | 0 | 5 | 4 |
//    | 6 | -2 | 0 | 5 |
//    And C is M * B
//    Then C * inverse(B) = M
    @Test
    fun  productInverseMatrix() {
        val M = Matrix(4).fill(3, -9, 7, 3, 3, -8, 2, -9, -4, 4, 4, 1, -6, 5, -1, 1)
        val B = Matrix(4).fill(8, 2, 2, 2, 3, -1, 7, 0, 7, 0, 5, 4, 6, -2, 0, 5)
        val C = M * B

        assertEquals(true, C * B.inverse() == M, "the result should equal True")
    }

}

class MatrixTransformationTest {

//    Scenario: Multiplying by a translation matrix
//    Given transform is translation(5.0, -3.0, 2.0)
//    And p is a Point(-3, 4, 5)
//    Then transform * p = point(2, 1, 7)
    @Test
    fun `Multiplying by a translation matrix`() {
        // Given
        val transform = Matrix.translation(5.0, -3.0, 2.0)
        val p = Point(-3, 4, 5)
        // Then
        assertEquals(Point(2, 1, 7), transform * p, "The result should be point(2, 1, 7)")
    }

//    Scenario: Multiplying by the inverse of a translation matrix
//    Given transform is translation(5, -3, 2)
//    And inv is inverse(transform)
//    And p is a Point(-3, 4, 5)
//    Then inv * p = point(-8, 7, 3.0)
    @Test
    fun `Multiplying by the inverse of a translation matrix`() {
        // Given
        val transform = Matrix.translation(5.0, -3.0, 2.0)
        val inv = transform.inverse()
        val p = Point(-3, 4, 5)
        // Then
        assertEquals(Point(-8, 7, 3), inv * p, "The result should be point(-8, 7, 3)")
    }

//    Scenario: Translation does not affect vectors
//    Given transform is translation(5, -3, 2)
//    And v is a Vector(-3, 4, 5)
//    Then transform * v = v
    @Test
    fun `Translation does not affect vectors`() {
        // Given
        val transform = Matrix.translation(5.0, -3.0, 2.0)
        val v = Vector(-3, 4, 5)
        // Then
        assertEquals(v, transform * v, "The result should be Vector(-3, 4, 5)")
    }

//    Scenario: A scaling matrix applied to a point
//    Given transform is scaling(2, 3, 4)
//    And p is a Point(-4, 6, 8)
//    Then transform * p = point(-8, 18, 32)
    @Test
    fun `A scaling matrix applied to a point`() {
        // Given
        val transform = Matrix.scaling(2.0, 3.0, 4.0)
        val p = Point(-4, 6, 8)
        // Then
        assertEquals(Point(-8, 18, 32), transform * p, "The result should be point(-8, 18, 32)")
    }

//    Scenario: A scaling matrix applied to a vector
//    Given transform is scaling(2, 3, 4)
//    And v is a Vector(-4, 6, 8)
//    Then transform * v = vector(-8, 18, 32)
    @Test
    fun `A scaling matrix applied to a vector`() {
        // Given
        val transform = Matrix.scaling(2.0, 3.0, 4.0)
        val v = Vector(-4, 6, 8)
        // Then
        assertEquals(Vector(-8, 18, 32), transform * v, "The result should be vector(-8, 18, 32)")
    }

//    Scenario: Multiplying by the inverse of a scaling matrix
//    Given transform is scaling(2, 3, 4)
//    And inv is inverse(transform)
//    And v is a Vector(-4, 6, 8)
//    Then inv * v = vector(-2, 2, 2)
    @Test
    fun `Multiplying by the inverse of a scaling matrix`() {
        // Given
        val transform = Matrix.scaling(2.0, 3.0, 4.0)
        val inv = transform.inverse()
        val v = Vector(-4, 6, 8)
        // Then
        assertEquals(Vector(-2, 2, 2), inv * v, "The result should be vector(-2, 2, 2)")
    }

//    Scenario: Reflection is scaling by a negative value
//    Given transform is scaling(-1, 1, 1)
//    And p is a Point(2, 3, 4)
//    Then transform * p = point(-2, 3, 4)
    @Test
    fun `Reflection is scaling by a negative value`() {
        // Given
        val transform = Matrix.scaling(-1.0, 1.0, 1.0)
        val p = Point(2, 3, 4)
        // Then
        assertEquals(Point(-2, 3, 4), transform * p, "The result should be point(-2, 3, 4)")
    }

//    Scenario: Rotating a point around the x axis
//    Given p is a Point(0, 1, 0)
//    And half_quarter is rotation_x(0.7853981)
//    And full_quarter is rotation_x(1.5707963)
//    Then half_quarter * p = point(0, 0.707106, 0.707106)
//    And full_quarter * p = point(0, 0, 1)
    @Test
    fun `Rotating a point around the x axis`() {
        // Given
        val p = Point(0, 1, 0)
        val half_quarter = Matrix.rotationX(0.7853981)
        val full_quarter = Matrix.rotationX(1.5707963)
        // When
        val result1 = half_quarter * p
        val result2 = full_quarter * p
        // Then
        assertEquals(Point(0.0, 0.707106, 0.707106), result1, "The result should be point(0, 0.707106, 0.707106)")
        assertEquals(Point(0, 0, 1), result2, "The result should be point(0, 0, 1)")
    }

//    Scenario: The inverse of an x-rotation rotates in the opposite direction
//    Given p is a Point(0, 1, 0)
//    And half_quarter is rotation_x(0.7853981)
//    And inv is inverse(half_quarter)
//    Then inv * p = point(0, 0.707106, -0.707106)
    @Test
    fun `The inverse of an x-rotation rotates in the opposite direction`() {
        // Given
        val p = Point(0, 1, 0)
        val half_quarter = Matrix.rotationX(0.7853981)
        val inv = half_quarter.inverse()
        // Then
        assertEquals(Point(0.0, 0.707106, -0.707106), inv * p, "The result should be point(0, 0.707106, -0.707106)")
    }

//    Scenario: Rotating a point around the y axis
//    Given p is a Point(0, 0, 1)
//    And half_quarter is rotation_y(0.7853981)
//    And full_quarter is rotation_y(1.5707963)
//    Then half_quarter * p = point(0.707106, 0, 0.707106)
//    And full_quarter * p = point(1, 0, 0)
    @Test
    fun `Rotating a point around the y axis`() {
        // Given
        val p = Point(0, 0, 1)
        val half_quarter = Matrix.rotationY(0.7853981)
        val full_quarter = Matrix.rotationY(1.5707963)
        // When
        val result1 = half_quarter * p
        val result2 = full_quarter * p
        // Then
        assertEquals(Point(0.707106, 0.0, 0.707106), result1, "The result should be point(0.707106, 0, 0.707106)")
        assertEquals(Point(1, 0, 0), result2, "The result should be point(1, 0, 0)")
    }

//    Scenario: Rotating a point around the z axis
//    Given p is a Point(0, 1, 0)
//    And half_quarter is rotation_z(0.7853981)
//    And full_quarter is rotation_z(1.5707963)
//    Then half_quarter * p = point(-0.707106, 0.707106, 0)
//    And full_quarter * p = point(-1, 0, 0)
    @Test
    fun `Rotating a point around the z axis`() {
        // Given
        val p = Point(0, 1, 0)
        val half_quarter = Matrix.rotationZ(0.7853981)
        val full_quarter = Matrix.rotationZ(1.5707963)
        // When
        val result1 = half_quarter * p
        val result2 = full_quarter * p
        // Then
        assertEquals(Point(-0.707106, 0.707106, 0.0), result1, "The result should be point(-0.707106, 0.707106, 0)")
        assertEquals(Point(-1, 0, 0), result2, "The result should be point(-1, 0, 0)")
    }

//    Scenario: Individual transformations are applied in sequence
//    Given p is a Point(1, 0, 1)
//    And A is rotation_x(1.5707963)
//    And B is scaling(5, 5, 5)
//    And C is translation(10, 5, 7)
//    # apply rotation first
//    When p2 is A * p
//    Then p2 = point(1, -1, 0)
//    # then apply scaling
//    When p3 is B * p2
//    Then p3 = point(5, -5, 0)
//    # then apply translation
//    When p4 is C * p3
//    Then p4 = point(15, 0, 7)
    @Test
    fun `Individual transformations are applied in sequence`() {
        // Given
        val p = Point(1, 0, 1)
        val A = Matrix.rotationX(1.5707963)
        val B = Matrix.scaling(5, 5, 5)
        val C = Matrix.translation(10, 5, 7)
        // When
        val p2 = A * p
        val p3 = B * p2
        val p4 = C * p3
        // Then
        assertEquals(Point(1, -1, 0), p2, "The result should be point(1, -1, 0)")
        assertEquals(Point(5, -5, 0), p3, "The result should be point(5, -5, 0)")
        assertEquals(Point(15, 0, 7), p4, "The result should be point(15, 0, 7)")
    }

//    Scenario: Chained transformations must be applied in reverse order
//    Given p is a Point(1, 0, 1)
//    And A is rotation_x(1.5707963)
//    And B is scaling(5, 5, 5)
//    And C is translation(10, 5, 7)
//    When transform is C * B * A
//    Then transform * p = point(15, 0, 7)
    @Test
    fun `Chained transformations must be applied in reverse order`() {
        // Given
        val p = Point(1, 0, 1)
        val A = Matrix.rotationX(1.5707963)
        val B = Matrix.scaling(5, 5, 5)
        val C = Matrix.translation(10, 5, 7)
        // When
        val transform = C * B * A
        // Then
        assertEquals(Point(15, 0, 7), transform * p, "The result should be point(15, 0, 7)")
    }

}