//package Duke;
//
//import Duke.GUI.Ui;
//import org.junit.jupiter.api.Test;
//import Duke.Tasks.*;
//import Duke.Exceptions.*;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class UiTest {
//
//    @Test
//    public void testPrintList() {
//        Ui ui = new Ui();
//
//        TaskList tasks = new TaskList("empty");
//
//        try {
//            tasks.enter("todo nothing");
//            tasks.enter("deadline nothing /by tonight");
//
//        } catch (IncompleteInput e) {
//            System.out.println("Won't happen");
//        } catch (InvalidInput e) {
//            System.out.println("Won't happen");
//        } finally {
//            // Capture the output of the printList method
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            PrintStream originalOut = System.out;
//            System.setOut(new PrintStream(outputStream));
//
//            ui.printList(tasks);
//
//            // Restore the original System.out
//            System.setOut(originalOut);
//
//            // Convert the captured output to a string
//            String printedOutput = outputStream.toString().trim();
//
//            String expectedOutput = "_______________ \n\n" +
//                    "What a terrible day to be alive. \n" +
//                    "1 - [T] | [ ] | nothing \n" + "2 - [D] | [ ] | nothing  | tonight \n \n" +
//                    "_______________";
//
//            assertEquals(expectedOutput, printedOutput);
//        }
//
//    }
//}