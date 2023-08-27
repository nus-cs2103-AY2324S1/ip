package com.alpha.commands;

import com.alpha.storage.Storage;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;
import java.io.IOException;

public class ByeCommand extends Command {

  public ByeCommand() {
    super();
  }

  public void execute(TaskList taskList, Ui ui, Storage storage) {
    try {
      storage.save(taskList);
      ui.goodbye();
    } catch (IOException e) {
      ui.loadingError();
    }
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
