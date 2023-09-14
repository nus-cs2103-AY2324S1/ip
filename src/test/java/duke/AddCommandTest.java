package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {
    @Test
    public void test(){
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./src/main/java/data/test.txt");
        new AddCommand("deadline read book /by 2023-09-09").execute(tasks, ui, storage);
        new AddCommand("event party /from 2023-09-01 /to 2023-09-02").execute(tasks, ui, storage);

        assertEquals(2, tasks.total());
    }
}
