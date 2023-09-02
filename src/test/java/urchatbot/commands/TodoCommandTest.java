package urchatbot.commands;

import org.junit.jupiter.api.Test;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {

    @Test
    public void execute_AddTodoTask_TaskAddedToTaskList() {
        // Create a TaskList, Ui, and Storage for testing
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/testTodoCommand.txt");

        // Create a TodoCommand and execute it
        TodoCommand todoCommand = new TodoCommand("Test Todo");
        todoCommand.execute(taskList, ui, storage);

        // Check if the task was added to the TaskList
        assertEquals(1, taskList.getSize());

        // Check if the task description matches
        assertEquals("Test Todo", (taskList.getTasks().get(0)).getDescription());

        // Check if the task is marked as not done
        assertEquals(false, taskList.getTasks().get(0).getIsDone());

    }

    // You can add more test cases for different scenarios here
}