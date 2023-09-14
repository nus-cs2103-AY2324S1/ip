//package Duke;
//
//import Duke.GUI.Ui;
//import Duke.Tasks.*;
//import Duke.Exceptions.*;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//public class ParserTest {
//
//    public void testIncorrectInput() {
//        Ui ui = new Ui();
//
//        TaskList tasks = new TaskList("empty");
//
//        boolean correctness = false;
//
//        try {
//            tasks.enter("nonsense");
//        } catch (IncompleteInput e) {
//            correctness = true;
//        } catch (InvalidInput e) {
//
//        } finally {
//            assertEquals(true, correctness);
//        }
//    }
//}
