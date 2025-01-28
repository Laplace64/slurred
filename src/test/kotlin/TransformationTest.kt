import net.laplace.slurred.transformation.*
import net.laplace.slurred.transformation.transform
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TransformationTest {
    @Test
    fun testTypo() {
        val typo = listOf(Typo(chance=1.0))

        assertEquals(
            typo.transform("Hello!! Hi??"),
            "Helloo!! Hee??"
        )
    }

    @Test
    fun testSwapCase() {
        val swapCase = listOf(SwapCase(chance=1.0))

        assertEquals(
            swapCase.transform("Hello!! Hi??"),
            "hELLO!! hI??"
        )
    }

    @Test
    fun testTypoSwapCase() {
        val transformations = listOf(
            Typo(chance=0.9),
            SwapCase(chance=0.9),
            Slurring(chance=0.9),
            Repetition(chance=0.9),
            Pauses(chance=0.9),
        )

        println(
            transformations.transform("Hello!! Hi?? I am saying hi hello!!!")
        )
    }
}