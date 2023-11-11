import kotlin.math.abs

class Matrix(private val size: Int) {
    private val data = Array(size) { DoubleArray(size) }

    operator fun get(i: Int, j: Int): Double {
        return data[i][j]
    }

    operator fun set(i: Int, j: Int, value: Double) {
        data[i][j] = value
    }

    fun setIdentity() {
        for (i in 0 until size) {
            for (j in 0 until size) {
                data[i][j] = if (i == j) 1.0 else 0.0
            }
        }
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

    operator fun times(other: Matrix): Matrix {
        require(size == other.size) { "Matrices must be of the same size" }
        val result = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                for (k in 0 until size) {
                    result[i, j] += this[i, k] * other[k, j]
                }
            }
        }
        return result
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

}