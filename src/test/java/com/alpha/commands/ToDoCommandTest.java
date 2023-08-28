package com.alpha.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alpha.enums.MarkEnum;
import com.alpha.enums.TagEnum;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;

public class ToDoCommandTest {

    @Test
    public void testExecute() {
        String task = "todo";
        String taskName = "read book";
        String userInput = task + " " + taskName;
        ToDoCommand command = new ToDoCommand(userInput);
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        command.execute(taskList, ui, null);

        assertEquals(1, taskList.getSize());
        assertEquals(taskName, taskList.getTasks().get(0).getName());
        assertEquals(TagEnum.TODO, taskList.getTasks().get(0).getTag());
        assertEquals(MarkEnum.NOTDONE, taskList.getTasks().get(0).getMark());
    }
}
