package com.alpha.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alpha.tasks.TaskList;

class UnmarkCommandTest {

    @Test
    void testExecute() {
        String taskName = "read book";
        TaskList taskList = new TaskList();
        ToDoCommand toDoCommand = new ToDoCommand(taskName, taskList);
        toDoCommand.execute();
        MarkCommand markCommand = new MarkCommand(1, taskList);
        markCommand.execute();
        UnmarkCommand unmarkCommand = new UnmarkCommand(1, taskList);
        String expectedResult = String.format("Nice! I've marked this task as done:\n [T][ ] %s", taskName);

        String result = unmarkCommand.execute();

        assertEquals(expectedResult, result);
    }
}
