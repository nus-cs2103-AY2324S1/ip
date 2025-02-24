package duke;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class to store the task result and load result.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for storage.
     * @param filePath the relative filepath from the project root
     */
    public Storage(String filePath) {
        //fix hard coded part later
        this.filePath = filePath;
    }

    /**
     * Read from file and return an array list of task.
     *
     * @return array list of task
     */
    public ArrayList<Task> loadTask() throws DukeException {
        //print error message if cannot loadTask
        //assume that all file path are ./data/duke.txt for now, change it later
        //if anything happen to the path or to the file, just return a new ArrayList and continue, no exception printed
        Path path = Paths.get("./data/duke.txt");
        ArrayList<Task> loadList = new ArrayList<>();

        if (!Files.exists(path)) {
            return loadList;
        }

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parsed_str = line.split("\\s\\|\\s");
                if (parsed_str[0].equals("T")) {
                    loadTodo(loadList, parsed_str);
                } else if (parsed_str[0].equals("D")) {
                    loadDeadline(loadList, parsed_str);
                } else if (parsed_str[0].equals("E")) {
                    loadEvent(loadList, parsed_str);
                }
            }
        } catch (Exception e) {
            //if anything happen, just return a new one
            throw new DukeException("Error occured in loading", e);
        }
        return loadList;
    }

    private static void loadEvent(ArrayList<Task> loadList, String[] parsedStr) {
        Boolean done = parsedStr[1].equals("1") ? true : false;
        String description = parsedStr[2];
        String fromDate = parsedStr[3];
        String toDate = parsedStr[4];
        Event curr = new Event(description, fromDate, toDate);
        if (done) {
            curr.markAsDone();
        }
        loadList.add(curr);
    }

    private static void loadDeadline(ArrayList<Task> loadList, String[] parsedStr) {
        Boolean isDone = parsedStr [1].equals("1") ? true : false;
        String description = parsedStr [2];
        String dateString = parsedStr [3];
        Deadline deadline = new Deadline(description, dateString);
        if (isDone) {
            deadline.markAsDone();
        }
        loadList.add(deadline);
    }

    private static void loadTodo(ArrayList<Task> loadList, String[] parsedStr) {
        int size = parsedStr.length;
        Boolean isDone = parsedStr[1].equals("1") ? true : false;
        String description = parsedStr[2];
        Todo todo = new Todo(description);
        if (isDone) {
            todo.markAsDone();
        }
        loadList.add(todo);
    }

    /**
     * Store the task in a file.
     * @param tasks An array list of tasks.
     */
    public void store(TaskList tasks) {
        Path path = Paths.get("./data");
        if (Files.notExists(path)) {
            //if the directory not exist, create one instantly
            //duke.txt will be created automatically dateString default
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                System.out.println("Error occured in creating directory");
            }
        }
        try (FileWriter filewriter = new FileWriter(this.filePath)) {
            int size = tasks.getSize();
            for (int i = 0; i < size; i++) {
                Task curr = tasks.getTask(i);
                filewriter.write(curr.stringInFile() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error occurred in writing the file");
        }
    }
}
