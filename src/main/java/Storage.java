import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/duke.txt";
    private final Ui ui;

    public Storage(Ui ui) {
        this.ui = ui;
    }

    private void ensureDirectoryExists() {
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void saveTasks(List<Task> tasks) {
        try {
            ensureDirectoryExists();
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            ui.printIndented("Error saving tasks to file.");
        }
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            ensureDirectoryExists();
            File f = new File(FILE_PATH);
            if (!f.exists()) return tasks;
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String taskData = s.nextLine();
                Task task = parseFileTask(taskData);
                tasks.add(task);
            }
            s.close();
        } catch (IOException e) {
            ui.printIndented("Error loading tasks from file.");
        } catch (DukeException e) {
            ui.printIndented("Data file is corrupted: " + e.getMessage());
        }
        return tasks;
    }

    private Task parseFileTask(String taskData) throws DukeException {
        String[] parts = taskData.split(" \\| ");
        switch (parts[0]) {
            case "T":
                ToDo todo = new ToDo(parts[2]);
                if (parts[1].equals("1")) todo.markAsDone();
                return todo;
            case "D":
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) deadline.markAsDone();
                return deadline;
            case "E":
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) event.markAsDone();
                return event;
            default:
                throw new DukeException("Unknown task type: " + parts[0]);
        }
    }
}
