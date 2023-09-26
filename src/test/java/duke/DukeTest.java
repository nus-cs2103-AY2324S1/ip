package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DukeTest {
    private final Duke botInstance = new Duke();

    @Test
    public void command_wrongArgument_errorMessageOutput() {
        String markError = "You need to provide a valid number:\n       eg. mark/unmark 1";
        String unmarkError = "You need to provide a valid number:\n       eg. mark/unmark 1";
        String deleteError = "You need to provide a valid number:\n       eg. delete 2";
        String todoError = "Wrong format, make sure your command is in the format:\n       todo [DESCRIPTION]";
        String deadlineNoSplit = "Wrong format, make sure your command is in the format:\n"
                + "      deadline [DESCRIPTION] /by [dd.mm.yyyy tttt]";
        String eventNoSplit = "Wrong format, make sure your command is in the format:\n"
                + "      event [DESCRIPTION] /from [dd.mm.yyyy tttt] /to [dd.mm.yyyy tttt]";

        Assertions.assertEquals(markError, botInstance.getResponse("mark"));
        Assertions.assertEquals(unmarkError, botInstance.getResponse("unmark four"));
        Assertions.assertEquals(deleteError, botInstance.getResponse("delete 5 5"));
        Assertions.assertEquals(todoError, botInstance.getResponse("todo"));
        Assertions.assertEquals(deadlineNoSplit, botInstance.getResponse("deadline thing /until wow"));
        Assertions.assertEquals(eventNoSplit, botInstance.getResponse("event describe then /till now"));
    }
}
