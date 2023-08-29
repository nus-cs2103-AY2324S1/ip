package services.tasklist;

import services.tasklist.tasks.Deadline;
import services.tasklist.tasks.Event;
import services.tasklist.tasks.Task;
import services.tasklist.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String DATA_FILE_PATH = "src/main/resources/jarvislist.txt";
    private File dataFile;

    public Storage() throws IOException {
        initialize();
    }

    public void initialize() throws IOException {
        File file = new File(DATA_FILE_PATH);
        file.createNewFile();
        dataFile = file;
    }

    public void save(List<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile);
        for (Task task : tasks) {
            fileWriter.write(task.encode() + "\n");
        }
        fileWriter.close();
    }

    public List<Task> load() throws IOException {
        Scanner scanner = new Scanner(dataFile);
        List<Task> taskList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String encodedTask = scanner.nextLine();
            Task task;
            String[] varargs = encodedTask.split(" \\| ");
            switch (encodedTask.charAt(0)) {
            case 'T':
                task = new Todo(varargs[2]);
                break;
            case 'D':
                task = new Deadline(varargs[2], varargs[3]);
                break;
            case 'E':
                task = new Event(varargs[2], varargs[3], varargs[4]);
                break;
            default:
                return null;
            }

            if (varargs[1].equals("1")) {
                task.setDone();
            }
            taskList.add(task);
        }
        scanner.close();
        return taskList;
    }
}
