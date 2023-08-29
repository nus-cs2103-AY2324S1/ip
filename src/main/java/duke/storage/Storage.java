package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIOException;
import duke.tasks.TaskList;
import duke.tasks.TaskType;
import duke.ui.Ui;

public class Storage {

    private final String path;

    public Storage(String path) throws DukeException {
        this.path = path;
        File file = new File(path);
        if (!file.exists()) {
            Ui.printlns(new String[] { "...No saved tasks found.", "Creating new save file for you..." });
            try {
                file.getParentFile().mkdirs();
                FileWriter writer = new FileWriter(file);
                writer.close();
            } catch (IOException e) {
                throw new DukeIOException("Error creating save file: " + e);
            }
        }
    }

    public void load(TaskList tasklist) throws DukeException {
        try {
            boolean isEmpty = Files.size(Path.of(path)) == 0;
            if (isEmpty) {
                Ui.println("Save file empty, you're good to go.");
                return;
            }

            Ui.println("Found some old tasks, replaying some commands...");
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|\\|");
                TaskType type = TaskType.valueOf(args[0]);
                boolean isDone = args[1].equals("1");
                tasklist.addTask(type, args[2]);
                if (isDone) {
                    tasklist.markTaskDone(tasklist.size());
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeIOException("Error loading tasks from save file: " + e);
        }
    }

    public void save(TaskList tasklist) {
        try {
            String content = "";
            FileWriter fileWriter = new FileWriter(path, false);

            for (int i = 0; i < tasklist.size(); i++) {
                content += tasklist.getTask(i).getSaveFormat();
            }

            fileWriter.write(content);
            fileWriter.close();
            Ui.println("...Successfully saved your tasks!");
        } catch (IOException e) {
            throw new DukeIOException("Error saving tasks to local disk: " + e);
        }
    }

}
