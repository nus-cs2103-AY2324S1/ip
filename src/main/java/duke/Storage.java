package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage class which takes care of the reading
 * and writing of the list of tasks for Duke
 */
public class Storage {
    //path for file to read/right to
    private final String FILE_PATH;
    public static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter dateTimeInputFormatter
            = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    /**
     * Constructs a Storage instance with the specified filePath
     * @param filePath The file path to read and write from
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;

    }

    /**
     * Writes the tasks in the task list to a file as specified
     * @param taskList The list of tasks to write
     * @throws IOException Throws IOException when the writing is unsuccessful
     */
    public void writeTasksToFile(ArrayList<Task> taskList) throws IOException {
        //check if file path exists already or not
        String fileDirectory = "./" + FILE_PATH.split("/",2)[0];
        File dir = new File(fileDirectory);
        if (!dir.exists()) {
            dir.mkdir();
        }
        FileWriter fw =  new FileWriter(FILE_PATH);
        for (Task task : taskList) {
            fw.write(task.writeFileFormat() + "\n");
        }
        fw.close();
    }

    /**
     * Reads saved tasks from file path
     * @return Returns an arraylist of tasks
     * @throws FileNotFoundException Throws FileNotFoundException if the file path does not exist
     */
    public ArrayList<Task> readTasksFromFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> savedTasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task task = Task.readFromFile(line);
            if (task != null) {
                savedTasks.add(task);
            }
        }
        return savedTasks;
    }
}
