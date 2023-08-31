package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Storage {
    //path for file to read/right to
    private final String FILE_PATH;
    public static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter dateTimeInputFormatter
            = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    public Storage(String filePath) {
        this.FILE_PATH = filePath;

    }
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
