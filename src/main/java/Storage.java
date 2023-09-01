import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Storage {
    /**
     * Loads file input and adds respective tasks into chatbot taskList.
     */
    public void loadTasksFromFile(TaskList taskList) throws FileNotFoundException, InvalidFileFormatException,
            IOException, SecurityException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File data = new File("data/tasks.txt");
        if (data.exists()) {
            Scanner dataScanner = new Scanner(data);
            while (dataScanner.hasNextLine()) {
                String task = dataScanner.nextLine();
                if (task.startsWith("[T]")) {
                    String description = task.substring(7);

                    if (description.isBlank()) {
                        throw new InvalidFileFormatException();
                    } else {
                        taskList.add(new Todo(description));
                    }
                } else if (task.startsWith("[D]")) {
                    int by = task.indexOf("(by: ");
                    String description = task.substring(7, by - 1);
                    LocalDate deadline = LocalDate.parse(task.substring(by + 5, task.length() - 1),
                            DateTimeFormatter.ofPattern("MMM d yyyy"));

                    if (description.isBlank()) {
                        throw new InvalidFileFormatException();
                    } else {
                        taskList.add(new Deadline(description, deadline));
                    }
                } else if (task.startsWith("[E]")) {
                    int from = task.indexOf("(from: ");
                    int to = task.indexOf("to: ");
                    String description = task.substring(7, from - 1);
                    LocalDate start = LocalDate.parse(task.substring(from + 7, to - 1),
                            DateTimeFormatter.ofPattern("MMM d yyyy"));
                    LocalDate end = LocalDate.parse(task.substring(to + 4, task.length() - 1),
                            DateTimeFormatter.ofPattern("MMM d yyyy"));

                    if (description.isBlank()) {
                        throw new InvalidFileFormatException();
                    } else {
                        taskList.add(new Event(description, start, end));
                    }
                } else {
                    throw new InvalidFileFormatException();
                }
            }
            dataScanner.close();
        } else {
            data.createNewFile();
        }
    }

    /**
     * Saves current taskList to data file.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.taskRep(i) + "\n");
        }
        fw.close();
    }

}
