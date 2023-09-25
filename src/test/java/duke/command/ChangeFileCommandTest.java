package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChangeFileCommandTest {
    @Test
    public void isExit_shouldBeFalse() {
        Command command = new ChangeFileCommand("y1");
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeTrue() {
        Command command = new ChangeFileCommand("y1");
        Assertions.assertTrue(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeFalse() {
        Command command = new ChangeFileCommand("y1");
        Assertions.assertFalse(command.isSetCommand());
    }

    @Test
    public void filePath_shouldBeFormatted() {
        Command command = new ChangeFileCommand("test");
        String expected = "./data/test.txt";
        Assertions.assertEquals(expected, command.getFilePath());
    }
}
