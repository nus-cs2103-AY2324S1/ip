package duke;

import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Handles storage related operations.
 */
public class Storage {


    private File file;

    public Storage(String filepath) {
        try {
            file = new File(filepath);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes a task list to file.
     *
     * @param taskList List of tasks.
     */
    public static void writeToFile(ArrayList<Task> taskList) {
        try {
            File file = new File("./data/duke.txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("./data/duke.txt");
            for (Task task : taskList) {
                fileWriter.write(task + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Loads a list of tasks from file.
     *
     * @return Loaded list of tasks.
     * @throws FileNotFoundException If file containing list of tasks is not found.
     */

    public ArrayList<Task> load() throws FileNotFoundException, UnknownCommandException {
        ArrayList<Task> taskData = new ArrayList<>();

        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            Matcher m = Pattern.compile("\\[(.*?)]").matcher(data);
            m.find();
            String taskType = m.group(1);
            m.find();
            String taskComplete = m.group(1);
            Task newTask = null;
            String[] taskInfoSplit = data.split("]");
            String taskInfo = taskInfoSplit[taskInfoSplit.length - 1].trim();

            switch (taskType) {
            case "D":
                newTask = Deadline.initializeFromStorage(taskInfo);
                break;
            case "T":
                newTask = Todo.initializeFromStorage(taskInfo);
                break;
            case "E":
                newTask = Event.initializeFromStorage(taskInfo);
                break;
            default:
                throw new UnknownCommandException();
            }
            if (Objects.equals(taskComplete, "X")) {
                assert newTask != null;
                newTask.mark();
            }
            taskData.add(newTask);
        }
        myReader.close();
        return taskData;
    }
}
