import org.junit.jupiter.api.Assertions
import kotlin.math.abs

class Matrix(private val size: Int) {
    private val data = Array(size) { DoubleArray(size) }

    operator fun get(i: Int, j: Int): Double {
        return data[i][j]
    }

    operator fun set(i: Int, j: Int, value: Double) {
        data[i][j] = value
    }

    fun setIdentity(): Matrix {
        for (i in 0 until size) {
            for (j in 0 until size) {
                this[i, j] = if (i == j) 1.0 else 0.0
            }
        }
        return this
    }

    fun fill(vararg values: Number): Matrix {
        require(values.size == size * size) { "The number of values must match the size of the matrix" }
        for (i in 0 until size) {
            for (j in 0 until size) {
                this[i, j] = values[i * size + j].toDouble()
            }
        }
        return this
    }

    private fun getRow(rowIndex: Int): Tuple {
        require(size == 4) { "For getRow matrices must be 4x4 size" }
        return Tuple(this[rowIndex, 0], this[rowIndex, 1], this[rowIndex, 2], this[rowIndex, 3])
    }

    private fun getColumn(columnIndex: Int): Tuple {
        require(size == 4) { "For getColumn matrices must be 4x4 size" }
        return Tuple(this[0, columnIndex], this[1, columnIndex], this[2, columnIndex], this[3, columnIndex])
    }

    operator fun times(other: Matrix): Matrix {
        require(size == other.size) { "Matrices must be of the same size" }
        val result = Matrix(size)
        for (i in 0 until size)
            for (j in 0 until size)
                result[i, j] = this.getRow(i) * other.getColumn(j)
//                for (k in 0 until size) {
//                    result[i, j] += this[i, k] * other[k, j]
//                }
        return result
    }

    operator fun times(tuple: Tuple): Tuple {
        require(size == 4) { "For matrix multiplication on Vector, the matrix must be 4x4" }
        return Tuple(getRow(0) * tuple, getRow(1) * tuple, getRow(2) * tuple, getRow(3) * tuple)
    }

    operator fun times(scalar: Double): Matrix {
        val result = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                result[i, j] = this[i, j] * scalar
            }
        }
        return result
    }

    fun transpose(): Matrix {
        val result = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                result[i, j] = this[j, i]
            }
        }
        return result
    }

    fun determinant(): Double {
        if (size == 2) {
            return this[0, 0] * this[1, 1] - this[1, 0] * this[0, 1]
        }
        var det = 0.0
        for (i in 0 until size) {
            det += this[0, i] * cofactor(0, i)
        }
        return det
    }

    fun cofactor(row: Int, col: Int): Double {
        return minor(row, col) * if ((row + col) % 2 == 0) 1 else -1
    }

    fun minor(row: Int, col: Int): Double {
        return submatrix(row, col).determinant()
    }

    fun submatrix(row: Int, col: Int): Matrix {
        val result = Matrix(size - 1)
        var r = 0
        for (i in 0 until size) {
            if (i == row) continue
            var c = 0
            for (j in 0 until size) {
                if (j == col) continue
                result[r, c] = this[i, j]
                c++
            }
            r++
        }
        return result
    }

    fun isInvertible(): Boolean {
        return this.determinant() != 0.0
    }

    fun inverse(): Matrix {
        require(size == 4) { "Inverse can only be calculated for 4x4 matrices" }
        val det = determinant()
        require(det != 0.0) { "Matrix is not invertible" }
        val adj = Matrix(size)
            for (i in 0 until size)
                for (j in 0 until size)
                    adj[i, j] = this.cofactor(j, i)
        return adj * (1.0 / det)
    }

//    override fun toString(): String {
//        return data.joinToString("\n") { it.joinToString(" | ", "[ ", " ]") }
//    }

    override fun toString(): String {
        val maxLengths = DoubleArray(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                val length = data[i][j].toString().length
                if (length > maxLengths[j]) {
                    maxLengths[j] = length.toDouble()
                }
            }
        }

        return data.joinToString("\n") { row ->
            row.mapIndexed { index, value ->
                "%${maxLengths[index].toInt()}s".format(value)
            }.joinToString(" | ", "[ ", " ]")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is Matrix) {
            if (size != other.size) return false

            for (i in 0 until size)
                for (j in 0 until size)
                    if (abs(this[i, j] - other[i, j]) > epsilon) return false
            return true
        }
        else
            return false
    }

}


fun main() {

//    val mtrx = Matrix(4)
//    println("1")
//    println(mtrx.toString())
//
//    mtrx.setIdentity()
//    println("2")
//    println(mtrx.toString())
//
//    mtrx.fill(1 , 2 , 3 , 4 , 5.5 , 6.5 , 7.5 , 8.5 , 9 , 10 , 11 , 12 , 13.5 , 14.5 , 15.5 , 16.5 )
//    println("3")
//    println(mtrx.toString())

    val matrix1 = Matrix(3).fill(1.001, 2, 3, 4, 5, 6, 7, 8, 9)
    val matrix2 = Matrix(3).fill(1, 2, 3, 4, 5, 6, 7, 8, 9)

    if (matrix1 == matrix2) {
        println("Matrix equals")
    } else {
        println("Matrix not equals")
    }

    val M = Matrix(4).fill(-5, 2, 6, -8, 1, -5, 1, 8, 7, 7, -6, -7, 1, -3, 7, 4)
    val B = M.inverse()
    val invB = Matrix(4).fill(0.21805, 0.45113, 0.24060, -0.04511, -0.80827, -1.45677, -0.44361, 0.52068,
        -0.07895, -0.22368, -0.05263, 0.19737, -0.52256, -0.81391, -0.30075, 0.30639)

    println(M.determinant())
    println(M.cofactor(2, 3))
    println(-160.0/532.0)
    println(B[3, 2])
    println(M.cofactor(3, 2))
    println(105.0 / 532.0)
    println(B[2, 3])
    println(B)
    println(invB)

}