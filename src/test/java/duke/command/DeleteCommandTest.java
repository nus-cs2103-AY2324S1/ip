package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
public class DeleteCommandTest {
    @Test
    public void testInvalidDeleteIndex() {
        try {
            Task t = new Todo("todo crying");
            ArrayList<Task> al = new ArrayList<>();
            al.add(t);
            TaskList task = new TaskList(al);
            Ui ui = new Ui();
            Storage storage = new Storage("./");
            Command delete = new DeleteCommand(4);
            Exception except = assertThrows(DukeException.class, () -> {
                delete.execute(task, ui, storage);
            });
            String expectedmessage = "MEEEOOWWWWWW!!!! Cannot delete this task!";
            String actlmessage = except.toString(); //get the full message

            assertEquals(expectedmessage, actlmessage);



        } catch (DukeException e) {
            System.out.println("Shld not happen");
        }

    }

}
