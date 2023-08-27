import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void loadTasks(TaskList taskList) throws IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            try {
                String[] parts = line.split(" \\| ");

                switch (parts[0]) {
                case "T":
                    taskList.add(new Todo(parts[2]));
                    break;
                case "D":
                    taskList.add(new Deadline(parts[2], LocalDateTime.parse(parts[3], formatter)));
                    break;
                case "E":
                    taskList.add(new Event(parts[2], LocalDateTime.parse(parts[3], formatter)));
                    break;
                default:
                    break;
                }

                if (parts[1].equals("1")) {
                    taskList.get(taskList.getLength() - 1).markAsDone();
                }

            } catch (Exception e) {
                System.err.println("Error loading task: " + line);
            }
        }
    }

    public void saveTasks(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        File file = new File(filePath);

        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (int i = 0; i < taskList.getLength(); i++) {
            Task task = taskList.get(i);
            String line = task.toFileString();
            fw.write(line + System.lineSeparator());
        }

        fw.close();
    }
}
