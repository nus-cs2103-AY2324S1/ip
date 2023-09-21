package sisyphus.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import sisyphus.task.Deadline;
import sisyphus.task.Event;
import sisyphus.task.TaskList;
import sisyphus.task.ToDo;


/**
 * Storage class that stores and reads a local data file in the user's computer.
 */
public class Storage {
    private String currentDirectory = System.getProperty("user.dir");
    private final String fileName = "sisyphusData.csv";
    private Path filePath = Path.of(currentDirectory, fileName);


    /**
     * Attempts to read file in csv format and skip all "corrupted" / wrong lines of values.
     * If file does not exist, an empty sisyphus.task.TaskList will be returned and a file will be created.
     *
     * @return TaskList from the saved file
     */
    public TaskList loadData() {
        if (Files.exists(filePath)) {
            try {
                FileReader fileReader = new FileReader(filePath.toString());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                TaskList savedTaskList = new TaskList();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] params = line.split(",");
                    if (params.length == 0) {
                        continue;
                    }
                    String type = params[0];
                    switch (type) {
                    case ("E"): {
                        addEventFromStorage(params, savedTaskList);
                        break;
                    }
                    case ("D"): {
                        addDeadlineFromStorage(params, savedTaskList);
                        break;
                    }
                    case ("T"): {
                        addToDoFromStorage(params, savedTaskList);
                        break;
                    }
                    default: {
                        break;
                    }
                    }
                }
                bufferedReader.close();
                return savedTaskList;
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file: " + e.getMessage());
                return new TaskList();
            }
        } else {
            writeFile(new TaskList());
        }
        return new TaskList();
    }

    /**
     * Takes in a TaskList and writes all tasks in the TaskList to the saved file based on a csv format.
     *
     * @param taskList
     */
    public void writeFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath.toString());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < taskList.getSize(); i++) {
                stringBuilder.append(taskList.getTask(i).toSaveFormat());
                stringBuilder.append('\n');
            }
            assert fileWriter != null : "Ensure that fileWriter is instantiated to be written";
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Parses the event from the params and saves the event into the savedTaskList.
     *
     * @param params
     * @param savedTaskList
     */
    public void addEventFromStorage(String[] params, TaskList savedTaskList) {
        if (params.length == 5) {
            Event event = new Event(params[1], params[2].equals("1"), params[3], params[4]);
            savedTaskList.addTask(event);
        }
    }

    /**
     * Parses the deadline from the params and saves the deadline into the savedTaskList.
     *
     * @param params
     * @param savedTaskList
     */
    public void addDeadlineFromStorage(String[] params, TaskList savedTaskList) {
        if (params.length == 4) {
            LocalDate deadlineDate;
            try {
                deadlineDate = LocalDate.parse(params[3]);
            } catch (DateTimeParseException e) {
                return;
            }
            Deadline deadline = new Deadline(params[1], params[2].equals("1"), deadlineDate);
            savedTaskList.addTask(deadline);
        }
    }

    /**
     * Parses the ToDo from the params and saves the ToDo into the savedTaskList.
     *
     * @param params
     * @param savedTaskList
     */

    public void addToDoFromStorage(String[] params, TaskList savedTaskList) {
        if (params.length == 3) {
            ToDo todo = new ToDo(params[1], params[2].equals("1"));
            savedTaskList.addTask(todo);
        }
    }
}
