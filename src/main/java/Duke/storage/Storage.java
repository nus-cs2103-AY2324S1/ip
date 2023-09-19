package Duke.storage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Duke.parser.Parser;
import Duke.tasks.Deadlines;
import Duke.tasks.Events;
import Duke.tasks.Task;
import Duke.tasks.TaskList;
import Duke.tasks.ToDos;
import core.DukeException;

public class Storage {
    public static String filePath;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        try {
            return loadFromFile(filePath);
        } catch (FileNotFoundException e) {
            throw new DukeException("Data file not found. Starting with an empty task list.");
        } catch (Exception e) {
            throw new DukeException("An error occurred while loading the data.");
        }
    }

    public void updateData(TaskList tasks) {
        try {
            saveToFile(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }
    

    public List<Task> loadFromFile(String filename) throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        Parser parser = new Parser();

        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (!isValidFormat(line)) {
                    throw new DukeException("Data file is corrupted. Line not in expected format: " + line);
                }

                Task task = parser.parseTaskFromFile(line);
                tasks.add(task);
            }
            scanner.close();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with an empty task list.");
        }
        return tasks;
    }


    public boolean isValidFormat(String line) {
        return line.matches("^[TDE] \\| [01] \\| .+");
    }


    public void saveToFile(TaskList tasks) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileWriter writer = new FileWriter(filePath)) { // Using try-with-resources for automatic closure of
                                                              // writer
            for (Task task : tasks.getTasks()) {
                writer.write(taskToFileString(task) + "\n"); // Convert each task to its file string representation
            }
        }
    }

    public String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();

        if (task instanceof ToDos) {
            sb.append("T | ");
        } else if (task instanceof Deadlines) {
            sb.append("D | ");
        } else if (task instanceof Events) {
            sb.append("E | ");
        }

        sb.append(task.isCompleted() ? "1" : "0").append(" | ");
        sb.append(task.getDescription());
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (task instanceof Deadlines) {
            sb.append(" | ").append(((Deadlines) task).getDate().format(outputFormatter));
        } else if (task instanceof Events) {
            sb.append(" | ").append(((Events) task).getStartDate().format(outputFormatter))
                    .append(" | ").append(((Events) task).getEndDate().format(outputFormatter));
        }

        return sb.toString();
    }

    public String archiveTasksInNewFile(TaskList tasks) {
        File archiveFile = new File("data/archive.txt");

        try {
            FileWriter fw = new FileWriter(archiveFile, true);
            for (Task task : tasks.getTasks()) {
                fw.write(taskToFileString(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            return "Error archiving tasks " + e.getMessage();
        }
        return "Successfully archived tasks in a new file";
    }

    public String clearFile() {
        try {
            FileWriter fw = new FileWriter("./data", false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            return "Error clearing main tasks for archive action " + e.getMessage();
        }
        return "Successfully cleared data from current file.";
    }
}
