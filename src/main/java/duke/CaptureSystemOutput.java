package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CaptureSystemOutput {
    public static void main(String[] args) {
        String capturedOutput = captureOutput(() -> {
            // Your program logic here
            System.out.println("Hello, World!");
        });

        // Print the captured output
        System.out.println("Captured Output:");
        System.out.println(capturedOutput);
    }

    public static String captureOutput(Runnable runnable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream originalOut = System.out;
        System.setOut(printStream);

        runnable.run(); // Execute the code that generates output

        System.setOut(originalOut);

        return outputStream.toString();
    }
}

