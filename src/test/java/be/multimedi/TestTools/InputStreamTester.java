package be.multimedi.TestTools;

import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream tester
 */
public class InputStreamTester extends InputStream {
   byte[] bytes = null; // buffer
   int index = 0; // index, current(reading) position in buffer
   String nextLine = null; // next/split of input

   /**
    * Setter for the current input/data to process
    *
    * @param text the text to use as input
    */
   public void setInputString(String text) {
      if (text != null) {
         //# split input after newline \n
         int index = text.indexOf("\n");
         if (index >= 0) {
            nextLine = text.substring(index + 1);
            text = text.substring(0, index + 1);
         } else {
            nextLine = null;
         }
         //# add newline if none exists
         if (!text.contains("\n")) text += "\n";
         //# set buffer
         bytes = text.getBytes();
      } else {
         nextLine = null;
         bytes = null;
      }
      //# set index
      index = 0;
   }

   /**
    * Reset input
    */
   public void reset() {
      setInputString(null);
   }

   /**
    * Has every byte been read?
    *
    * @return if everything has been read this returns true, otherwise false
    */
   public boolean isEnded() {
      if (bytes == null) return true;
      return index >= bytes.length && nextLine == null;
   }

   @Override
   /**
    * Read next byte
    * @return next byte in stream or -1(when the stream ended)
    */
   public int read() throws IOException {
      if (bytes != null && index < bytes.length && index >= 0) // are we in the buffer?
         return bytes[index++]; // return buffer at index and continue reading(++)
      setInputString(nextLine); // prepare next line
      return -1; // return end of buffer
   }
}
