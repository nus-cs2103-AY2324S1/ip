import exception.InvalidDateTimeException;
import taskClasses.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public TaskList load() {
        try {
            TaskList taskList = new TaskList();
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                try {
                    String[] taskParts = s.nextLine().split("\\|");
                    if (taskParts.length == 3) {
                        taskList.addToDoToList(taskParts[1].trim().contains("1"),
                                taskParts[2].trim());
                    } else if (taskParts.length == 4) {
                        taskList.addDeadlineToList(
                                taskParts[1].trim().contains("1"),
                                taskParts[2].trim(),
                                taskParts[3].trim());
                    } else if (taskParts.length == 5) {
                        taskList.addEventToList(
                                taskParts[1].trim().contains("1"),
                                taskParts[2].trim(),
                                taskParts[3].trim(),
                                taskParts[4].trim());
                    }
                } catch (InvalidDateTimeException error) {
                    continue;
                }
                return taskList;
            }
        } catch (FileNotFoundException e) {
            System.out.println("the file is corrupted, deleting the content");
        }
        return new TaskList();
    }

    public void writeToDB(TaskList taskList) {
        try {
            Path path = Paths.get(this.filePath);
            String content = "";
            for (Task newTask : taskList.getList()) {
                content += newTask.getDBString();
                content += "\n";
            }
            Files.write(path, content.getBytes());
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
