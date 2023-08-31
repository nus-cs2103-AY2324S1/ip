import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String DIRECTORY = "./data";
    private final String FILE_PATH = DIRECTORY + "/duke.txt";
    private File FILE;

    public Storage() throws DukeException {
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

    public ArrayList<Task> getStorage() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(this.FILE);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String taskType = input.split(",")[0];
                String taskMark = input.split(",")[1];
                String taskName = input.split(",")[2];
                if (taskType.equals("T")) {
                    tasks.add(new ToDos(taskName));
                } else if (taskType.equals("D")) {
                    tasks.add(new Deadlines(taskName, input.split(",")[3]));
                } else if (taskType.equals("E")) {
                    tasks.add(new Events(taskName, input.split(",")[3],input.split(",")[4]));
                } else {
                    throw new DukeException("☹ OOPS!!! Failed to load tasks from file.");
                }
                if (taskMark == "0") {
                    tasks.get(tasks.size() - 1).changeMarkStatus(true);
                }
            }
            System.out.println(tasks.size());
            return tasks;
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Failed to load tasks from file.");
        }
    }

    public void editStorage (ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fw.write(task.writeString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Failed to write to file.");
        }
    }
}
