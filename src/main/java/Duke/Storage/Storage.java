package Duke.Storage;

import Duke.Exceptions.DukeException;
import Duke.Tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Controls the storage and retrieving of saved data on the hard disk.
 */
public class Storage {
    private static final String PARSE_ERROR = "Invalid file format!";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;


    }

    /**
     * Saves the specified list of tasks.
     *
     * @throws DukeException
     */
    public void saveTasks(TaskList taskList) throws DukeException {
        String serialized = taskList.serialize();

        try {
            // create file if not exists


            // write to file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(serialized);
            fileWriter.close();

        } catch (IOException e) {
            throw new DukeException("Error saving file! \n" + e.getMessage());
        }


    }

    /**
     * Loads the list of tasks that a user has previously entered.
     *
     * @return a list of tasks
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            Path path = Path.of(filePath);
            if (Files.notExists(path)) {
                // create the directory
                System.out.println("Directory not present, creating...");
                Files.createDirectory(path.getParent());
            }
            if (Files.exists(path)) {
                System.out.println("Save file already exists, loading previous data");
                // it exists, so let's read it

                Scanner sc = new Scanner(path);
                ArrayList<Task> tasks = new ArrayList<>();
                while (sc.hasNextLine()) {
                    String inputLine = sc.nextLine();
                    if (inputLine.isEmpty()) {
                        continue;

                    }
                    Task task = parseTask(inputLine);
                    tasks.add(task);


                }
//                System.out.println("✅ Loaded " + listContainer.getSize() + " previous tasks.");
                return tasks;

            } else {

                File saveFile = new File(String.valueOf(path));

                if (saveFile.createNewFile()) {
                    System.out.println("✅ Created save file!");


                } else {
                    System.out.println("⚠\uFE0F Could not create save file!");
                }

                return new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("ERROR");
            return new ArrayList<>();
        }
    }

    /**
     * Parses a task as is encoded in the save file.
     *
     * @param inputLine the string that was encoded
     * @return The task that was parsed successfully
     * @throws IOException
     */
    public static Task parseTask(String inputLine) throws DukeException {
        String[] split = inputLine.split(" \\| ");

        if (split.length < 2) {
            throw new DukeException(PARSE_ERROR);
        }
        String taskType = split[0];
        String isDoneStr = split[1];
        String taskDescription = split[2];
        Boolean isDone = false;
        if (Objects.equals(isDoneStr, "1") || Objects.equals(isDoneStr, "0")) {
            isDone = isDoneStr.equals("1");
        }

        Task task;
        switch (taskType) {
            case "T": {
                task = new TodoTask(taskDescription);
                break;
            }
            case "D": {
                // get the deadline, which is 4th element
                if (split.length < 3) {
                    throw new DukeException(PARSE_ERROR);
                }
                String deadlineStr = split[3];


                LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineStr);

                task = new DeadlineTask(taskDescription, deadlineDateTime);
                break;
            }
            case "E": {
                // get the start date, which is 4th element
                // get the end date, which is 5th element
                if (split.length < 5) {
                    throw new DukeException(PARSE_ERROR);
                }
                String from = split[3];
                LocalDateTime dateTimeStart = LocalDateTime.parse(from);
                String to = split[4];
                LocalDateTime dateTimeEnd = LocalDateTime.parse(to);
                task = new EventTask(taskDescription, dateTimeStart, dateTimeEnd);
                break;
            }
            default:
                throw new DukeException(PARSE_ERROR);
        }

        if (isDone) {
            task.setDone();
        }
        return task;
    }
}
