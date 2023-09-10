package buddy.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import buddy.Deadline;
import buddy.Event;
import buddy.Task;
import buddy.TaskList;
import buddy.Todo;


public class Storage {
    private String filePath = "./data/tasks.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }
//    public TaskList readFile() { // load tasks
//        try {
//            File file = new File(filePath);
//            if (file.exists()) {
//                BufferedReader reader = new BufferedReader(new FileReader(file));
//                String line;
//                TaskList taskList = new TaskList();
//                while ((line = reader.readLine()) != null) {
//                    taskList = parseTask(line);
//                }
//                reader.close();
//                return taskList;
//            }
//        } catch (IOException | BuddyException e) {
//            System.out.println("Error loading tasks from file: " + e.getMessage());
//        }
//        return new TaskList();
//    }

    public ArrayList<Task> readFile() {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        if (!file.exists()) {
            return taskList;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                taskList.addAll(parseTask(line).getAllTasks());
            }
        } catch (FileNotFoundException | BuddyException e) {
            throw new RuntimeException(e);
        }
        return taskList;

    }

    public void writeToFile(ArrayList<Task> tasks) { // save tasks
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fileWriter.write(task.toSaveFileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private TaskList parseTask(String line) throws BuddyException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        TaskList tasks = new TaskList();

        switch (taskType) {
        case "T":
            tasks.addTask(new Todo(description, isDone));
            break;

        case "D":
            String by = parts[3];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadlineBy = LocalDate.parse(by, formatter);
            tasks.addTask(new Deadline(description, deadlineBy, isDone));
            break;

        case "E":
            String start = parts[3];
            String end = parts[4];
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(start, formatter);
            LocalDate endDate = LocalDate.parse(end, formatter);
            tasks.addTask(new Event(description, startDate, endDate, isDone));
            break;

        default:
            throw new BuddyException("Unexpected value: " + taskType);
        }

        return tasks;
    }
}
