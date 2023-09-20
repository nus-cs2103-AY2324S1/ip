package duke;

import gman.Deadline;
import org.junit.jupiter.api.Test;
public class DeadlineTest {

    /**
     * Test the toString method for a deadline task with a given date.
     */
    @Test
    public void testToString() {
        Deadline deadline  = new Deadline(" have more fun", "2019-10-19");
        assert deadline.toString().equals("[D][ ] have more fun (by: Oct 19 2019)");
    }

    /**
     * Tests that the output to the GUI from loading the deadline is the same as when creating a new deadline.
     */
    @Test
    public void testReadFromFile() {
        String textFromFile = "D | O |  Submit CS2100 assignment  | 2023-10-03";
        String[] segments = textFromFile.split(" \\| ");
        Deadline deadlineToTest = Deadline.readFromFile(segments);
        Deadline correctDeadline = new Deadline(" Submit CS2100 assignment ", "2023-10-03");
        assert deadlineToTest.toString().equals(correctDeadline.toString());
    }

    /**
     * Tests the opposite of the readFromFile() method. Tests whether the deadline task correctly writes in the
     * correct format to the .txt file.
     */
    @Test
    public void testStringToWrite() {
        Deadline deadline = new Deadline(" Submit CS2100 assignment ", "2023-10-03");
        assert deadline.stringToWrite().equals("D | O |  Submit CS2100 assignment  | 2023-10-03");
    }
}
