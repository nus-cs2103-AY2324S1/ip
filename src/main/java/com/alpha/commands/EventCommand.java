package com.alpha.commands;

import com.alpha.exceptions.InvalidTaskException.InvalidEventException;
import com.alpha.storage.Storage;
import com.alpha.tasks.Event;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;

public class EventCommand extends Command {

  public EventCommand(String args) {
    super(args);
  }

  public void execute(TaskList taskList, Ui ui, Storage storage) {
    try {
      Task task = new Event(Parser.getEventName(getArgs()), Parser.getEventStart(getArgs()), Parser.getEventEnd(getArgs()));
      taskList.addTask(task);
      ui.addTask(task, taskList);
    } catch (InvalidEventException e) {
      System.out.println(e.getMessage());
    }
  }
}
