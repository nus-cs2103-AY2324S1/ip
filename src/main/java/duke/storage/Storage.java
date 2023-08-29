package duke.storage;

import duke.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void editData(TaskList taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
            for (Task t : taskList) {
                writer.write(t.toSave() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public TaskList loadData() throws DukeException {
        TaskList loadedTask = new TaskList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currLine;
            while ((currLine = reader.readLine()) != null && !currLine.isEmpty()) {
                String[] parseCurr = currLine.split(" ", 2);
                String completed = parseCurr[0];
                String command = parseCurr[1];
                Command c = Parser.parse(command);
                c.loadTask(loadedTask);
                if (completed.equals("1")) {
                    loadedTask.get(loadedTask.size() - 1).taskDone();
                }
            }
            return loadedTask;
        } catch (FileNotFoundException e) {
            File newFile = new File(filePath);
            return loadedTask;
        } catch (IOException e) {
            throw new DukeException("OOPS!! Unable to read saved data safely.");
        }
    }
}
