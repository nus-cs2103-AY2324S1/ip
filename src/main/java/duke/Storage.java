package duke;

import duke.exception.DukeDatabaseException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() throws DukeDatabaseException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(this.filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                loadedTasks.add(readEntry(line));
            }

            return loadedTasks;
        } catch (FileNotFoundException e) {
            this.createFile();
        }

        return new ArrayList<>();
    }

    private void createFile() throws DukeDatabaseException{
        File file = new File(this.filePath);
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeDatabaseException();
        }
    }

    private Task readEntry(String entry) {
        String[] parts = entry.split(" \\| ");
        Task taskToAdd = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        switch (parts[0]) {
        case "T":
            taskToAdd = new Todo(parts[2]);
            break;
        case "D":
            taskToAdd = new Deadline(parts[2], LocalDateTime.parse(parts[3], formatter));
            break;
        case "E":
            taskToAdd = new Event(parts[2], LocalDateTime.parse(parts[3], formatter));
            break;
        default:
            break;
        }

        if (parts[1].equals("1")) {
            taskToAdd.markAsDone();
        }
        return taskToAdd;
    }

    public void saveData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < taskList.getLength(); i++) {
            Task task = taskList.getTask(i);
            String line = task.toFileString();
            fw.write(line + System.lineSeparator());
        }

        fw.close();
    }
}
