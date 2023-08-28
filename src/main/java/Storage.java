import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(ArrayList<Task> tasks) {
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                File parentDir = file.getParentFile();
                if (parentDir != null) {
                    parentDir.mkdirs();
                }
                file.createNewFile();
            }
            StringBuilder result = new StringBuilder();
            for (Task task : tasks) {
                String str = String.format("%s | %s | %s\n", task.getType(),
                        task.isDone ? 1 : 0, task.getDetails());
                result.append(str);
            }
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(result.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong when saving tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\\s*\\|\\s*");
                String taskType = line[0];
                boolean isDone = line[1].equals("1");
                String description = line[2];
                Task task;
                switch (taskType) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        String by = line[3];
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String from = line[3];
                        String to = line[4];
                        task = new Event(description, from, to);
                        break;
                    default:
                        throw new DukeException("Invalid format read from ./data/duke.txt," +
                                " please ensure data is in correct format");
                }
                if (isDone) {
                    task.isDone = true;
                }
                tasks.add(task);
            }
        } catch (IOException | DukeException e) {
            System.out.println("Something went wrong when loading tasks: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return tasks;
    }
}
