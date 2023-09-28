package phi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void handleInvalidInput() {
        String expected = "SIKE I can't process that!"
                + " Try again or say \"help\" to see a list of all available commands";
        assertEquals(expected, new Parser(new TaskList()).handle("invalid"));
    }

    @Test
    public void helpInput() {
        String expected = "Here's a list of commands:\n"
                + "1. list (prints out a list of all tasks)\n"
                + "2. todo [TASK] (creates a to-do with body \"TASK\")\n"
                + "3. deadline [TASK] /by [yyyy-MM-dd] (creates a corresponding deadline with body \"TASK\")\n"
                + "4. event [TASK] /from [yyyy-MM-dd] /to [yyyy-MM-dd]"
                + " (creates a corresponding event with body \"TASK\")\n"
                + "5. mark [X] (marks task X in the list)\n"
                + "6. unmark [X] (unmarks task X in the list)\n"
                + "7. delete [X] (removes task X from the list)\n"
                + "8. find [KEYWORD] (searches for tasks that contain corresponding KEYWORD)\n"
                + "9. bye (exits the program)";
        assertEquals(expected, new Parser(new TaskList()).handle("help"));
    }
}
