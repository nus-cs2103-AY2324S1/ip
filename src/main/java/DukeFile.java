import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeFile {
    private final String DIRECTORY = "./data";
    private final String FILE_PATH = DIRECTORY + "/duke.txt";
    private File FILE;

    public DukeFile() throws DukeException {
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

    public ArrayList<Task> readFile() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
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
                    tasks.add(toDo);
                } else if (taskType.equals("D")) {
                    String[] split = taskDescription.split(" \\(by: |\\)");
                    Task deadline = new Deadline(split[0], split[1]);
                    if (taskMark.equals("X")) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                } else if (taskType.equals("E")) {
                    String[] split = taskDescription.split(" \\(from: | to: |\\)");
                    Task event = new Event(split[0], split[1], split[2]);
                    if (taskMark.equals("X")) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                } else {
                    throw new DukeException("☹ OOPS!!! Failed to load tasks from file.");
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Failed to load tasks from file.");
        }
    }

    public void writeToFile(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Failed to write to file.");
        }
    }
}
