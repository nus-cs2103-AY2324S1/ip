package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;


public interface Command {
  void execute(TaskList tasks, Ui ui, Storage storage);
}
