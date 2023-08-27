package com.alpha.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alpha.enums.MarkEnum;
import com.alpha.enums.TagEnum;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;
import org.junit.jupiter.api.Test;

public class DeadlineCommandTest {
  @Test
  public void testExecute() {
    String task = "deadline";
    String taskName = "read book";
    String deadlineEnd = "2023-07-22 23:44";
    String userInput = task + " " + taskName + " /by " + deadlineEnd;
    DeadlineCommand command = new DeadlineCommand(userInput);
    TaskList taskList = new TaskList();
    Ui ui = new Ui();

    command.execute(taskList, ui, null);

    assertEquals(1, taskList.getSize());
    assertEquals(taskName, taskList.getTasks().get(0).getName());
    assertEquals(TagEnum.DEADLINE, taskList.getTasks().get(0).getTag());
    assertEquals(MarkEnum.NOTDONE, taskList.getTasks().get(0).getMark());
  }
}
