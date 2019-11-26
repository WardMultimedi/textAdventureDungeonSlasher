package be.multimedi.TestTools;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * A class to help build test-cases that need to test algorithms using System streams (in, out, err)
 */
public class SystemInOutTester {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final InputStreamTester inContent = new InputStreamTester();// = new ByteArrayInputStream(null);
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;
    private static final InputStream originalIn = System.in;

    /**
     * Set system-streams so they can be used for testing
     */
    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setIn(inContent);
    }

    /**
     * Set Input of the user
     */
    public void setInput(String data) {
        inContent.setInputString(data);
    }

    /**
     * Get Output of the algorithm
     */
    public String getOutput() {
        return outContent.toString();
    }

    /**
     * Get Error of the algorithm
     */
    public String getError() {
        return errContent.toString();
    }

    /**
     * Restore system-streams so they can be used as normal
     */
    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    /**
     * Reset testing Streams so no test-output remains
     */
    @AfterEach
    public void resetStreams() {
        inContent.reset();
        /* //# Reset remaining scanner input?
        //Scanner keyb = new Scanner(System.in);
        while(true){
            try{
                //keyb.nextLine();
                //ConsoleTool.askPressEnterToContinue();
            }catch (Exception e            ){
                break;
            }
        }// */
        outContent.reset();
        errContent.reset();
    }

    public void printStatus(){
        originalOut.println("Input Finished: " + inContent.isEnded());
        originalOut.println("Output: " + getOutput());
        originalErr.println("Errors: " + getError());
    }
}
