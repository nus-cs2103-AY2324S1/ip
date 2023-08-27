import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String DIRECTORY = "./data";
    private final String FILE_PATH = DIRECTORY + "/duke.txt";
    private File FILE;

    public void initialize() throws DukeException {
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
            throw new DukeException("☹ OOPS!!! Failed to create file.");
        }
    }

    public TaskList readFile() throws DukeException {
        try {
            TaskList tasks = new TaskList();
            Scanner sc = new Scanner(this.FILE);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String taskType = input.substring(1, 2);
                String taskMark = input.substring(4, 5);
                String taskDescription = input.substring(7);
                if (taskType.equals("T")) {
                    Task toDo = new ToDo(taskDescription);
                    if (taskMark.equals("X")) {
                        toDo.markAsDone();
                    }
                    tasks.addTask(toDo);
                } else if (taskType.equals("D")) {
                    String[] split = taskDescription.split(" \\(by: |\\)");
                    Task deadline = new Deadline(split[0], split[1]);
                    if (taskMark.equals("X")) {
                        deadline.markAsDone();
                    }
                    tasks.addTask(deadline);
                } else if (taskType.equals("E")) {
                    String[] split = taskDescription.split(" \\(from: | to: |\\)");
                    Task event = new Event(split[0], split[1], split[2]);
                    if (taskMark.equals("X")) {
                        event.markAsDone();
                    }
                    tasks.addTask(event);
                } else {
                    throw new DukeException("☹ OOPS!!! Failed to load tasks from file.");
                }
            }
            return tasks;
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Invalid date format. Please type dates in the format yyyy-mm-dd");
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Failed to load tasks from file.");
        }
    }

    public void writeToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE);
            for (int i = 0; i < tasks.getCountTasks(); i++) {
                Task task = tasks.getTask(i);
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Failed to write to file.");
        }
    }
}
