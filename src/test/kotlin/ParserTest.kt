import net.laplace.slurred.parser.Colors
import net.laplace.slurred.parser.Parser
import net.laplace.slurred.parser.colorCode
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class ParserTest {
    @Test
    fun testSimpleText() {
        val states = parser.parse(
            "\"word\" Hello  &oworld &r&lhi "
        )

        val text: String = states.joinToString("") { it.text }

        assertEquals(text, "\"word\" Hello world hi ")
    }

    @Test
    fun testFormatting() {
        val states = parser.parse(
            "\"sentence abc\" &2Hello &#${TEST_COLOR.toString(radix=16)}color &oworld &r&lhi"
        )

        assertEquals(
            states[1].color,
            MESSAGE_COLOR
        )

        assertEquals(
            states[3].color,
            MESSAGE_COLOR
        )

        assertEquals(
            states[6].color,
            colorCode('2')!!.hex
        )

        assertEquals(
            states[8].color,
            TEST_COLOR
        )

        assertEquals(
            states[10].color,
            TEST_COLOR,
        )

        assert(states[10].italic)

        assertEquals(
            states[12].color,
            ACTION_COLOR,
        )
        assert(states[12].bold)
    }

    companion object {
        private const val ACTION_COLOR = 0xFF0000
        private const val MESSAGE_COLOR = 0xFFFFFF
        private const val TEST_COLOR = 0xAABBCC

        val parser = Parser(
            actionColor = ACTION_COLOR,
            messageColor = MESSAGE_COLOR,
        )
    }

    @Test
    fun testComponent() {
        val states = parser.parse(
            "aaaaaa \"word\" aaa"
        )


        val component = states.map { it.toComponent().build() }.joinToString("\n")

        println(states.joinToString("\n"))
        println(component)
    }
}