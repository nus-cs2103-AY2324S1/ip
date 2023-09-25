package ben;

import ben.commands.AddCommand;
import ben.exceptions.EmptyDescriptionException;
import ben.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

public class AddCommandTest {
    @Test
    public void invalidAddCommandTest () {
        try {
            AddCommand addCommand = new AddCommand("deadline email /by");
            addCommand.interpretTask();
        } catch (EmptyDescriptionException | InvalidCommandException | DateTimeParseException e) {
            Assertions.assertEquals("/by cannot be empty", e.getMessage());
        }
    }
}
