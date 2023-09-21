package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TodoTask;




/**
 * Controls the storage and retrieving of saved data on the hard disk.
 */
public class Storage {
    private static final String PARSE_ERROR = "Invalid file format!";
    private String filePath;

    /**
     * Constructor for the Storage controller.
     *
     * @param filePath The filepath of the save file.
     */
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

        if (split.length < 3) {
            throw new DukeException(PARSE_ERROR);
        }
        String taskType = split[1];
        String isDoneStr = split[2];
        String taskDescription = split[3];

        // id is the last item in the list
        int id = Integer.parseInt((split[0]));
        Boolean isDone = false;
        if (Objects.equals(isDoneStr, "1") || Objects.equals(isDoneStr, "0")) {
            isDone = isDoneStr.equals("1");
        }


        Task task;
        switch (taskType) {
        case "T": {
            task = new TodoTask(id, taskDescription);
            break;
        }
        case "D": {
            // get the deadline, which is 4th element
            if (split.length < 4) {
                throw new DukeException(PARSE_ERROR);
            }
            String deadlineStr = split[4];

            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineStr);

            task = new DeadlineTask(id, taskDescription, deadlineDateTime);
            break;
        }
        case "E": {
            // get the start date, which is 4th element
            // get the end date, which is 5th element
            if (split.length < 6) {
                throw new DukeException(PARSE_ERROR);
            }
            String from = split[4];
            LocalDateTime dateTimeStart = LocalDateTime.parse(from);
            String to = split[5];
            LocalDateTime dateTimeEnd = LocalDateTime.parse(to);
            task = new EventTask(id, taskDescription, dateTimeStart, dateTimeEnd);
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
