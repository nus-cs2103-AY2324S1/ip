package barbie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import barbie.exceptions.BarbieException;



/**
 * NO LONGER USABLE after Level 10 iteration.
 * Tests the Ui class.
 */
public class TestUi {

    /**
     * Tests if an empty file list will print the prompt to add Tasks.
     */
    @Test
    public void testEmptyFileList() {
        String actual;
        try {
            Ui.listTasks(new ArrayList<>());
        } catch (BarbieException e) {
            actual = e.getMessage();
            String expected = "Barbie Error!!\n"
                    + "Hmm.. your list looks empty. To add items, use the 'todo', "
                    + "'deadline' or 'party' commands!";
            assertEquals(expected, actual);
        }

    }

}
