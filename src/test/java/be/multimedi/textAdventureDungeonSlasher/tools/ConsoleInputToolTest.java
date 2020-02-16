package be.multimedi.textAdventureDungeonSlasher.tools;

import be.multimedi.textAdventureDungeonSlasher.TestTools.SystemInOutTester;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConsoleInputToolTest extends SystemInOutTester {
    static final String DEFAULT_QUESTION = "q:";

    @Test
    void askPressEnterToContinue() {
        setInput("");
        ConsoleInputTool.askPressEnterToContinue();
        assertEquals("Press enter to continue.", getOutput());
        assertTrue(getError().isBlank());
    }

    @Test
    void askUserYesNoQuestion() {
        //# yes
        setInput("y");
        assertTrue(ConsoleInputTool.askUserYesNoQuestion(DEFAULT_QUESTION));
        assertEquals(DEFAULT_QUESTION, getOutput());
        assertTrue(getError().isBlank());

        resetStreams();
        //# no
        setInput("n");
        assertFalse(ConsoleInputTool.askUserYesNoQuestion(DEFAULT_QUESTION));
        assertEquals(DEFAULT_QUESTION, getOutput());
        assertTrue(getError().isBlank());
    }

    @Test
    void askUserString() {
        setInput("abc");
        assertEquals("abc", ConsoleInputTool.askUserString(DEFAULT_QUESTION));
        assertEquals(DEFAULT_QUESTION, getOutput());
        assertTrue(getError().isBlank());
    }

    @Test
    void askUserInteger() {
        setInput("123");
        assertEquals(123, ConsoleInputTool.askUserInteger(DEFAULT_QUESTION));
        assertEquals(DEFAULT_QUESTION, getOutput());
        assertTrue(getError().isBlank());
    }

    @Test
    void askUserDate() {
        setInput("2000-01-01");
        assertEquals(LocalDate.of(2000,01,01),
                ConsoleInputTool.askUserDate(DEFAULT_QUESTION));
        assertEquals(DEFAULT_QUESTION, getOutput());
        assertTrue(getError().isBlank());
    }

    @Test
    void askUserDateBefore() {
        setInput("2000-01-01");
        assertEquals(LocalDate.of(2000,01,01),
                ConsoleInputTool.askUserDateBefore(DEFAULT_QUESTION, LocalDate.now()));
        assertEquals(DEFAULT_QUESTION, getOutput());
        assertTrue(getError().isBlank());
    }

    @Test
    void askUserDateAfter() {
        setInput("2100-01-01");
        assertEquals(LocalDate.of(2100,01,01),
                ConsoleInputTool.askUserDateAfter(DEFAULT_QUESTION, LocalDate.now()));
        assertEquals(DEFAULT_QUESTION, getOutput());
        assertTrue(getError().isBlank());
    }
}
