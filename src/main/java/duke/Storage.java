package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//The duke.Storage class is in charge of writing and reading the tasklists
//from the duke.Duke.txt file
public class Storage{

    public void saveTasks(String filePath, TaskList tasks) throws FileNotFoundException{
        try {
            createDirectoryIfNotExists(filePath);

            FileWriter fileWriter = new FileWriter(filePath);

            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                String taskData = formatTaskData(task);
                fileWriter.write(taskData);
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close(); // Don't forget to close the FileWriter when done.
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
    String formatTaskData(Task task) {
        // Customize this method based on your Task class structure.
        String str = task.saveTaskString();
        return str;
    }
    public TaskList loadTasks(String filePath) {
        TaskList taskList = new TaskList();
        try {
            createDirectoryIfNotExists(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] components = line.split("\\|");
                String taskType = components[0];
                boolean isDone = components[1].equals("1");
                String description = components[2];
                switch (taskType) {
                    case "T":
                        taskList.addTask(new ToDo(description, isDone));
                        break;

                    case "D":
                        LocalDateTime by = LocalDateTime.parse(components[3]);
                        taskList.addTask(new Deadline(description, isDone, by));
                        break;

                    case "E":
                        LocalDateTime from = LocalDateTime.parse(components[3]);
                        LocalDateTime to = LocalDateTime.parse(components[4]);
                        taskList.addTask(new Event(description, isDone, from, to));
                        break;
                }
            }
            reader.close(); // Close the reader when done.
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return taskList;
    }

    private void createDirectoryIfNotExists(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(filePath);
        Path parentDir = path.getParent();

        if (parentDir != null) {
            if (!Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        }
    }
}



