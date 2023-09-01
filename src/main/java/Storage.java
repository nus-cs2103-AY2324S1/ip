import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public void createDataFile() throws IOException {
        File dataFile = new File(this.path);
        File dataDir = new File(dataFile.getParent());
        dataDir.mkdir();

        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error: Data Storage");
        }
    }

    public String generateTaskListString(TaskList stored) {
        int len = stored.getLength();
        String tL = "";
        if (len > 0) {
            for (int i = 1; i < len + 1; i++) {
                tL = tL + stored.getTask(i - 1).toSavedString() + "\n";
            }
        }
        return tL;
    }

    public void update(TaskList storedTasks) throws IOException {
        File dataFile = new File(this.path);
        FileWriter writer = new FileWriter(dataFile);

        String updatedTaskList = generateTaskListString(storedTasks);

        writer.write(updatedTaskList);

        writer.close();
    }

    public ArrayList<Task> loadTaskList() throws FileNotFoundException, DukeException {
        ArrayList<Task> taskList = new ArrayList<>();

        File dataFile = new File(this.path);

        try {
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                String taskStr = sc.nextLine();
                taskList.add(convertStrToTask(taskStr));
            }
        } catch (FileNotFoundException e) {
            return taskList;
        } catch (DukeInvalidTaskStringException e) {
            System.out.println(e);
        }

        return taskList;
    }

    public Task convertStrToTask(String str) throws DukeInvalidTaskStringException {
        String[] strArr = str.split("//");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

        Task t;
        boolean isDone = strArr[1].equals("X");

        switch (str.substring(0, 3)) {
        case "[T]":
            t = new Todo(strArr[2]);
            break;
        case "[D]":
            t = new Deadline(strArr[2], LocalDateTime.parse(strArr[3], dateTimeFormatter));
            break;
        case "[E]":
            t = new Event(strArr[2], LocalDateTime.parse(strArr[3], dateTimeFormatter),
                    LocalDateTime.parse(strArr[4], dateTimeFormatter));
            break;
        default:
            throw new DukeInvalidTaskStringException();
        }

        if (isDone) {
            t.markAsDone();
        }
        return t;
    }
}
