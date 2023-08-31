import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DevyBotException;
import exceptions.UnknownCommandException;
import devybot.Ui;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);

    }

    private ArrayList<Task> loadTasksFromFile() throws DevyBotException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                String[] taskParts = taskString.split(" \\| ");

                String taskType = taskParts[0].trim();
                String taskStatus = taskParts[1].trim();
                String taskDescription = taskParts[2].trim();
                Task loadedTask;

                switch (taskType) {
                    case "T":
                        loadedTask = new TodoTask(taskDescription);
                        if (taskStatus.equals("1")) {
                            loadedTask.markTask();
                        }
                        break;
                    case "D":
                        String taskBy = taskParts[3].trim();
                        if (taskBy.contains(" ")) {
                            // Contains time, parse as LocalDateTime
                            LocalDateTime dateTime = LocalDateTime.parse(taskBy,
                                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                            loadedTask = new DeadlineTask(taskDescription, dateTime);
                        } else {
                            // No time, parse as LocalDate
                            LocalDate date = LocalDate.parse(taskBy, DateTimeFormatter.ofPattern("d/M/yyyy"));
                            loadedTask = new DeadlineTask(taskDescription, date);
                        }
                        if (taskStatus.equals("1")) {
                            loadedTask.markTask();
                        }
                        break;
                    case "E":
                        String taskFrom = taskParts[3].trim();

                        LocalDateTime fromDateTime = LocalDateTime.parse(taskFrom,
                                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                        String taskTo = taskParts[4].trim();

                        LocalDateTime toDateTime = LocalDateTime.parse(taskTo,
                                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                        loadedTask = new EventTask(taskDescription, fromDateTime, toDateTime);
                        if (taskStatus.equals("1")) {
                            loadedTask.markTask();
                        }
                        break;
                    default:
                        throw new UnknownCommandException();

                }
                taskList.add(loadedTask);

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            Ui.showMessage("No existing tasks file found. Starting with an empty task list.");
        }
        return taskList;
    }

    private void saveTasksToFile(ArrayList<Task> taskList) {
        try {
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                dataDir.mkdir(); // Create the directory if it doesn't exist
            }
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : taskList) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.showMessage("An error occurred while saving tasks.");
        }
    }
}
