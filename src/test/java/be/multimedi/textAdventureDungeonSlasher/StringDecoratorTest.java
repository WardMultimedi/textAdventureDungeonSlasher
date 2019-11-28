package be.multimedi.textAdventureDungeonSlasher;

import be.multimedi.textAdventureDungeonSlasher.TestTools.SystemInOutTester;
import be.multimedi.textAdventureDungeonSlasher.tools.StringDecorator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringDecoratorTest extends SystemInOutTester {

   @Test
   void printAlert_A() {
      StringDecorator.printAlert("Hello");
      assertEquals(" ## Hello                                    ##\n", getOutput());

      resetStreams();

      StringDecorator.printAlert(null);
      assertEquals("-!!------------------------------------------!!-\n", getOutput());
   }

   @Test
   void printAlert_B() {
      StringDecorator.printAlert("Hello", 8);
      assertEquals(" ## Hello    ##\n", getOutput());

      resetStreams();

      StringDecorator.printAlert(null, 8);
      assertEquals("-!!----------!!-\n", getOutput());
   }
}