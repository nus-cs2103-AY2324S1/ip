import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Utils {
    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        String filePath = "./data/duke.txt";

        // Ensure the directory exists
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
        }
    }

    public static ArrayList<Task> loadTasks() throws FileNotFoundException {
        String filePath = "./data/duke.txt";
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            return tasks; // return empty list if file doesn't exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                switch (parts[0]) {
                    case "T":
                        Todo todo = new Todo(parts[2]);
                        if (parts[1].equals("1")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        try{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                            LocalDateTime dateTime = LocalDateTime.parse(parts[3], formatter);
                            Deadline deadline = new Deadline(parts[2], dateTime);
                            if (parts[1].equals("1")) {
                                deadline.markAsDone();
                            }
                            tasks.add(deadline);
                        } catch (DateTimeParseException e) {
                            System.out.println("Error parsing date-time from saved data: " + e.getMessage());
                        }
                        break;
                    case "E":
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime dateTimeFrom = LocalDateTime.parse(parts[3], formatter);
                        LocalDateTime dateTimeTo = LocalDateTime.parse(parts[4], formatter);
                        Event event = new Event(parts[2], dateTimeFrom, dateTimeTo);
                        if (parts[1].equals("1")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                }
            }
        }

        return tasks;
    }
}
