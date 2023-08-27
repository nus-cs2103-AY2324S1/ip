import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Storage {

    private File file;
    private String filePath;

    public Storage() {
        this.filePath = "data/duke.txt";
        this.file = getFile();
    }

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = getFile();
    }

    private File getFile() {
        File file = new File(this.filePath);
        if (file.exists()) {
            return file;
        } 

        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            // printErrorMessage(new DukeException("An unexpected error occurred: " + e.getMessage()));
            // throw new DukeException(e.getMessage());
        }
        return file;
    }

    private static Task addTaskFromStorage(String task) {
        Task newTask = null;
        if (task.startsWith("T")) {
            newTask = ToDo.createToDoFromStorage(task);
        } else if (task.startsWith("D")) {
            newTask = Deadline.createDeadlineFromStorage(task);
        } else if (task.startsWith("E")) {
            newTask = Event.createEventFromStorage(task);
        }

        return newTask;
    }

    public List<Task> readTasks() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String entry = sc.nextLine();
                Task newTask = addTaskFromStorage(entry);
                tasks.add(newTask);
                System.out.println(newTask);
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    public void write(List<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.toStorageString() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void write(Task newTask) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(newTask.toStorageString() + "\n");
            fw.close();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

}
