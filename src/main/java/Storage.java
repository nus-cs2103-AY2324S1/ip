import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException, DateTimeParseException, DukeException {
        List<Task> tasks = new ArrayList<>();
        File saveFile = new File(Paths.get(CURRENT_DIRECTORY, filePath).toString());
        if (saveFile.exists()) {
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] data = line.split("\\|");
                String taskType = data[0].trim();
                switch (taskType) {
                    case "D":
                        // Deadline
                        if (data.length == 4) {
                            // Ensure deadline date is a Datetime object
                            LocalDateTime localDateTime =
                                    LocalDateTime.parse(data[3].trim(), Parser.DATE_TIME_FORMAT);
                            // Create new task object
                            Task t = new Deadline(data[2].trim(), localDateTime);
                            if (Integer.parseInt(data[1].trim()) == 1) {
                                t.markAsDone();
                            }
                            tasks.add(t);
                        }
                        break;
                    case "E":
                        // Event
                        if (data.length == 5) {
                            // Ensure deadline date is a Datetime object
                            Task t = new Event(data[2].trim(),
                                    LocalDateTime.parse(data[3].trim(), Parser.DATE_TIME_FORMAT),
                                    LocalDateTime.parse(data[4].trim(), Parser.DATE_TIME_FORMAT));
                            if (Integer.parseInt(data[1].trim()) == 1) {
                                t.markAsDone();
                            }
                            tasks.add(t);
                        }
                        break;
                    case "T":
                        // To Do
                        if (data.length == 3) {
                            Task t = new ToDo(data[2].trim());
                            if (Integer.parseInt(data[1].trim()) == 1) {
                                t.markAsDone();
                            }
                            tasks.add(t);
                        }
                        break;
                }
            }
        } else if (saveFile.getParentFile().mkdirs()){
            saveFile.createNewFile();
        } else {
            throw new DukeException("Failed to create directory.");
        }
        return tasks;
    }

    public void write(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(Paths.get(CURRENT_DIRECTORY, filePath).toString());
        for (Task t: tasks) {
            fw.write(t.toSaveDataFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
