import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.abs

class CanvasTest {

//    Scenario: Creating a Canvas without name
//    Given canvas is a Canvas(300, 300)
//    Then the width of the canvas is 300
//    And the height of the canvas is 300
//    And the fileName is empty
    @Test
    fun canvasWithoutName() {
        val canvas = Canvas(300, 300)

        assertEquals(300, canvas.width, "the width of the canvas should equal 300")
        assertEquals(300, canvas.height, "the height of the canvas should equal 300")
        assertEquals("", canvas.fileName, "the fileName of the canvas should be empty")
    }

//    Scenario: Creating a named Canvas
//    Given canvas is a Canvas(300, 300, "image")
//    Then the width of the canvas is 300
//    And the height of the canvas is 300
//    And the fileName is "image"
    @Test
    fun canvasWithName() {
        val canvas = Canvas(300, 300, "image")

        assertEquals(300, canvas.width, "the width of the canvas should equal 300")
        assertEquals(300, canvas.height, "the height of the canvas should equal 300")
        assertEquals("image", canvas.fileName, "the fileName of the canvas should equal \"image\"")
    }


//    Scenario: Setting a Pixel in the Canvas
//    Given canvas is a Canvas(300, 300)
//    When the pixel (30, 30) is set to Color(0.5, 0.5, 0.5)
//    Then the pixel (30, 30) is Color(0.5, 0.5, 0.5)
    @Test
    fun setPixelToCanvas() {
        val canvas = Canvas(300, 300)

        canvas.setPixel(30, 30, Color(0.5, 0.5, 0.5))

        assertEquals(true, canvas.getPixel(30,30) == Color(0.5, 0.5, 0.5),
            "the Color of the pixel (30, 30) should equal Color(0.5, 0.5, 0.5)")
    }

}