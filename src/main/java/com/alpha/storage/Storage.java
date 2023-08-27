package com.alpha.storage;

import com.alpha.commands.Command;
import com.alpha.enums.MarkEnum;
import com.alpha.exceptions.InvalidTaskException;
import com.alpha.tasks.Deadline;
import com.alpha.tasks.Event;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.ui.SilentUi;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {

  private final File file;

  public Storage(String filePath) throws IOException {
    file = new File(filePath);
    createFile();
  }

  private void createFile() throws IOException {
    file.getParentFile().mkdirs();
    file.createNewFile();
  }

  public TaskList load() throws FileNotFoundException, InvalidTaskException {
    Scanner sc = new Scanner(file);
    TaskList taskList = new TaskList();
    Ui ui = new SilentUi();
    int count = 1;
    while (sc.hasNextLine()) {
      String input = sc.nextLine();
      Command command = Parser.parse(input);
      if (command == null) {
        continue;
      }
      command.execute(taskList, ui, this);
      if (Integer.parseInt(sc.nextLine()) == 1) {
        taskList.markTask(count++);
      }
    }
    return taskList;
  }

  public void save(TaskList taskList) throws IOException {
    FileWriter fw = new FileWriter(file);
    boolean first = true;
    for (Task task : taskList.getTasks()) {
      StringBuilder row = new StringBuilder();
      if (!first) row.append("\n");
      if (first) first = false;
      row.append(task.getTagName());
      row.append(" ");
      row.append(task.getName());
      if (task instanceof Deadline) {
        row.append(" /by ");
        Deadline deadline = (Deadline) task;
        row.append(deadline.getEndToStore());
      } else if (task instanceof Event) {
        row.append(" /from ");
        Event event = (Event) task;
        row.append(event.getStartToStore());
        row.append(" /to ");
        row.append(event.getEndToStore());
      }
      if (task.getMark() == MarkEnum.DONE) {
        row.append("\n1");
      } else {
        row.append("\n0");
      }
      fw.write(row.toString());
    }
    fw.close();
  }
}
