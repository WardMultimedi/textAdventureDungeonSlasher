package be.multimedi.textAdventureDungeonSlasher;

import be.multimedi.textAdventureDungeonSlasher.TestTools.SystemInOutTester;
import be.multimedi.textAdventureDungeonSlasher.tools.ConsoleInputTool;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConsoleInputToolTest extends SystemInOutTester {

   @Test
   void askPressEnterToContinue() {
      setInput("");
      ConsoleInputTool.askPressEnterToContinue();
      assertEquals("Press enter to continue.", getOutput());
   }

    /*@Test
    void askPressEnterToContinue_tryCatch() {
        try {
            setInput("");
            ConsoleTool.askPressEnterToContinue();
        } catch (Exception e) {
            printStatus();
            throw e;
        }
        assertEquals("Press enter to continue.", getOutput());
    }*/

   @Test
   void askUserInputString_1() {
      //normal input: minimum characters of 0 or lower, equals no minimum
      setInput("testString");
      String result = ConsoleInputTool.askUserInputString("question:", 0);
      assertEquals("question:", getOutput());
      assertEquals("testString", result);
      assertTrue(getError().isBlank());
   }

   @Test
   void askUserInputString_2() {
      //wrong input: below minimum characters
      setInput("\ntestString2");
      String result = ConsoleInputTool.askUserInputString("question:", 1);
      assertEquals("question:question:", getOutput());
      assertEquals("testString2", result);
      assertTrue(getError().length() > 0);
   }

   @Test
   void askUserInputInteger_A_1() {
      //normal input
      setInput("1");
      int result = ConsoleInputTool.askUserInputInteger("question:");
      assertEquals("question:", getOutput());
      assertEquals(1, result);
      assertTrue(getError().isBlank());
   }

   @Test
   void askUserInputInteger_A_2() {
      //wrong input: invalid number format
      setInput("a1\n2");
      int result = ConsoleInputTool.askUserInputInteger("question:");
      assertEquals("question:", getOutput());
      assertEquals(0, result);
      assertTrue(getError().length() > 0);
   }

   @Test
   void askUserInputInteger_B_1() {
      //good input
      setInput("3");
      int result = ConsoleInputTool.askUserInputInteger("question:", 1);
      assertEquals("question:", getOutput());
      assertEquals(3, result);
      assertTrue(getError().isBlank());
   }

   @Test
   void askUserInputInteger_B_2() {
      //wrong input: too small
      setInput("3\n4");
      int result = ConsoleInputTool.askUserInputInteger("question:", 4);
      assertEquals("question:question:", getOutput());
      assertEquals(4, result);
      assertTrue(getError().length() > 0);
   }

   @Test
   void askUserInputInteger_B_3() {
      //wrong input: invalid number format
      setInput("a3\n5");
      int result = ConsoleInputTool.askUserInputInteger("question:", 5);
      assertEquals("question:question:", getOutput());
      assertEquals(5, result);
      assertTrue(getError().length() > 0);
   }

   @Test
   void askUserInputInteger_C_1() {
      //good input
      setInput("7");
      int result = ConsoleInputTool.askUserInputInteger("question:", 1, 10);
      assertEquals("question:", getOutput());
      assertEquals(7, result);
      assertTrue(getError().isBlank());
   }

   @Test
   void askUserInputInteger_C_2() {
      //wrong input: too small
      setInput("8\n9");
      int result = ConsoleInputTool.askUserInputInteger("question:", 9, 10);
      assertEquals("question:question:", getOutput());
      assertEquals(9, result);
      assertTrue(getError().length() > 0);
   }

   @Test
   void askUserInputInteger_C_3() {
      //wrong input: too high
      setInput("99\n10");
      int result = ConsoleInputTool.askUserInputInteger("question:", 5, 10);
      assertEquals("question:question:", getOutput());
      assertEquals(10, result);
      assertTrue(getError().length() > 0);
   }

   @Test
   void askUserInputInteger_C_4() {
      //wrong input: invalid number format
      setInput("a3\n11");
      int result = ConsoleInputTool.askUserInputInteger("question:", 5, 15);
      assertEquals("question:question:", getOutput());
      assertEquals(11, result);
      assertTrue(getError().length() > 0);
   }

   @Test
   void askUserInputDate() {
      setInput("1984-03-09");
      LocalDate result = ConsoleInputTool.askUserInputDate("question:");
      assertEquals("question:", getOutput());
      assertEquals(LocalDate.of(1984, 3, 9), result);
      assertTrue(getError().isBlank());
   }

   @Test
   void askUserInputDateBefore() {
      setInput("1984-03-09");
      LocalDate result = ConsoleInputTool.askUserInputDateBefore("question:", LocalDate.now());
      assertEquals("question:", getOutput());
      assertEquals(LocalDate.of(1984, 3, 9), result);
      assertTrue(getError().isBlank());
   }

   @Test
   void askUserInputDateBetween() {
      setInput("1984-03-09");
      LocalDate result = ConsoleInputTool.askUserInputDateBetween("question:", LocalDate.EPOCH, LocalDate.now());
      assertEquals("question:", getOutput());
      assertEquals(LocalDate.of(1984, 3, 9), result);
      assertTrue(getError().isBlank());
   }

   @Test
   void askUserYesNoQuestion(){
      setInput("y");
      boolean result = ConsoleInputTool.askUserYesNoQuestion("question:");
      assertEquals("question:", getOutput());
      assertEquals(true, result);
      assertTrue(getError().isBlank());

      resetStreams();

      setInput("n");
      result = ConsoleInputTool.askUserYesNoQuestion("question:");
      assertEquals("question:", getOutput());
      assertEquals(false, result);
      assertTrue(getError().isBlank());
   }
}