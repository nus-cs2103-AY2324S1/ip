package Duke.Storage;

import Duke.DukeException.DukeException;
import Duke.Task.Deadlines;
import Duke.Task.Events;
import Duke.Task.Task;
import Duke.Task.ToDos;
import Duke.TaskList.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String DIRECTORY = "./data";
    private final String FILE_PATH = DIRECTORY + "/duke.txt";
    private File FILE;

    public void createStorage() throws DukeException {
        try {
            File directory = new File(this.DIRECTORY);
            if (!directory.exists()) {
                directory.mkdir();
            }
            this.FILE = new File(this.FILE_PATH);
            if (!this.FILE.exists()) {
                this.FILE.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Failed to create a file");
        }
    }

    public TaskList getStorage() throws DukeException {
        try {
            TaskList tasks = new TaskList();
            Scanner sc = new Scanner(this.FILE);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String taskType = input.split(",")[0];
                String taskMark = input.split(",")[1];
                String taskName = input.split(",")[2];
                if (taskType.equals("T")) {
                    tasks.addTask(new ToDos(taskName));
                } else if (taskType.equals("D")) {
                    tasks.addTask(new Deadlines(taskName, input.split(",")[3]));
                } else if (taskType.equals("E")) {
                    tasks.addTask(new Events(taskName, input.split(",")[3],input.split(",")[4]));
                } else {
                    throw new DukeException(" OOPS!!! Failed to load tasks from file.");
                }
                if (taskMark == "0") {
                    tasks.getTask(tasks.getNumberOfTask() - 1).changeMarkStatus(true);
                }
            }
            System.out.println(tasks.getNumberOfTask());
            return tasks;
        } catch (IOException e) {
            throw new DukeException(" OOPS!!! Failed to load tasks from file.");
        }
    }

    public void editStorage (TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE);
            for (int i = 0; i < tasks.getNumberOfTask(); i++) {
                Task task = tasks.getTask(i);
                fw.write(task.writeString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(" OOPS!!! Failed to write to file.");
        }
    }
}
