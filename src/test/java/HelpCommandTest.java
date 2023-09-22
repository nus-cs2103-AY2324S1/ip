import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.Ui;
import duke.command.HelpCommand;
import duke.task.Todo;

public class HelpCommandTest {
    @Test
    public void executeHelpMessageDialogSuccess() {
        HelpCommand helpCommand = new HelpCommand(0);
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();
        String successfulMessage = "What can I help you with ?" + "\n"
                + "1. I  seem to not be able to enter my task into the list." + "\n"
                + "2. I seem to not be able to enter my deadline/event task into the list." + "\n"
                + "3. I seem to be unable to delete my task from the list." + "\n"
                + "Please enter Help with your chosen option.";

        assertEquals(successfulMessage, helpCommand.execute(taskList, ui));
    }

    @Test
    public void executeHelpAnswerSuccess() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();

        assertEquals("All tasks have to be denoted with either a todo/deadline/event at the start. "
                        + "E.g. to add a todo event, please type todo return book." ,
                new HelpCommand(1).execute(taskList, ui));
        assertEquals("All deadline and event dates are in the format yyyy-MM-dd ha where PM and AM are in caps"
                + "E.g. 2023-09-01 2PM", new HelpCommand(2).execute(taskList, ui));
        assertEquals("The number you provide must be greater than zero and within the total size "
                + "of your task list.", new HelpCommand(3).execute(taskList, ui));
    }

    @Test
    public void executeInvalidHelpOptionErrorMessageShown() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();

        assertEquals("I'm sorry, the option you have opted for is not available.",
                new HelpCommand(4).execute(taskList, ui));

    }
}
