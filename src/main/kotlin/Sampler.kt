import kotlin.random.Random

const val SAMPLING_COUNT = 9

abstract class  Sampler() {

    var count = SAMPLING_COUNT

    constructor(cnt: Int) : this() {
        this.count = cnt
    }

    abstract fun generateSamples(): List<Pair<Double, Double>>

    // тут получаем правильное количество точек в сетке для разных семплеров
    fun getCountPoint(): Int {
        if (this is OffsetSampler) {
            return count * count
        }
        if (this is RandomSampler) {
            return count
        }
        return 1
    }
}

class NoSampler : Sampler(1) {
    override fun generateSamples(): List<Pair<Double, Double>> {
        return listOf(Pair(0.0, 0.0))
    }
}

// Для OffsetSampler в качестве параметра указываем не количество точек, а размерность сетки (по умолчанию 3х3)
class OffsetSampler(cnt: Int = 3) : Sampler(cnt) {
    override fun generateSamples(): List<Pair<Double, Double>> {
        val step = 1.0 / (count - 1)
        val samples = mutableListOf<Pair<Double, Double>>()

        for (y in 0 until count) {
            for (x in 0 until count) {
                val offsetX = x * step - 0.5
                val offsetY = y * step - 0.5
                samples.add(Pair(offsetX, offsetY))
            }
        }
        return samples
    }
}

class RandomSampler(cnt: Int = SAMPLING_COUNT) : Sampler(cnt) {
    override fun generateSamples(): List<Pair<Double, Double>> {
        return List(count) { Pair(Random.nextDouble(-0.5, 0.5), Random.nextDouble(-0.5, 0.5)) }
    }
}
