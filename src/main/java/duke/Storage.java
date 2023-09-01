package duke;

import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.exception.DetailsUnknownException;

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
            StringBuilder tasks = new StringBuilder();
            for (Task task: TaskList.getList()) {
                tasks.append(task.getStatus()).append(" | ").append(task.getTaskType(task)).append("\n");

            }
            writer.write(tasks.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occur while trying to save tasks: " + e.getMessage());
        }
    }

    public void loadTask() throws IOException, DetailsUnknownException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String taskData = scanner.nextLine();
            String[] keywords = taskData.split(" \\|\\| ");

            Task currT = null;
            switch (keywords[0]) {
                case "T":
                    currT = new ToDo(keywords[2]);
                    break;
                case "D":
                    currT = new Deadline(keywords[2], keywords[3]);
                    break;
                case "E":
                    currT = new Event(keywords[2], keywords[3]);
                    break;
                default:
                    System.out.println("Error: Unknown task type. ");
                    break;
            }

            if (keywords[1].equals("1")) {
                currT.markDone();
            }
            TaskList.getList().add(currT);
        }
        scanner.close();
    }
}
