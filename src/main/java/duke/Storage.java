package duke;

import duke.Task;
import duke.TaskList;
import duke.ToDo;

import java.io.*;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTaskToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task: TaskList.getList()) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occur while trying to save tasks: " + e.getMessage());
        }
    }

    public void loadTask() throws IOException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String taskData = scanner.nextLine();
            String[] keywords = taskData.split(" / ");

            String taskType= keywords[0];
            boolean isDone = "1".equals(keywords[1]);
            String taskDescription = keywords[2];
            String taskDetails = keywords[3];

            Task task = null;
            switch (taskType) {
                case "T":
                    task = new ToDo(taskDescription);
                    break;
                case "D":
                    task = new Deadline(taskDescription, taskDetails);
                    break;
                case "E":
                    String[] eventDetails = taskDetails.split(" /from ");
                    if (eventDetails.length != 2) {
                        System.out.println("Error: Incomplete event details.");
                        continue;
                    }
                    String startTime = eventDetails[1].split(" /to ")[0];
                    String endTime = eventDetails[1].split(" /to ")[1];
                    task = new Event(taskDescription, startTime, endTime);
                    break;
                default:
                    System.out.println("Error: Unknown task type. ");
                    break;
            }

            if (task != null) {
                if (isDone) {
                    task.markDone();
                }
                TaskList.getList().add(task);
            }
        }
        scanner.close();
    }
}
