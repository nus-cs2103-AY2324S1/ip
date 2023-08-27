import duke.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private final File file;
    public Storage(String filePath) {
        File diskValues = new File(filePath + "/duke.txt");
        this.file = diskValues;
        try {
            File folder = new File(filePath);
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                    throw new IOException();
                }
            }
            if (!diskValues.exists()) {
                if (!diskValues.createNewFile()) {
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            System.out.println("IOException in reading files: " + e.getMessage());
        }
    }
    private Task parseOneLine(String oneTask) throws DukeException {
        Task theTask;
        String taskName;
        char TaskType = oneTask.charAt(1);
        String[] splitTask = oneTask.split(" ");
        if (oneTask.charAt(4) == 'X') {
            taskName = splitTask[1];
        } else {
            taskName = splitTask[2];
        }
        if (TaskType == 'T') {
            theTask = new Todo(taskName);
        } else if (TaskType == 'D') {
            StringBuilder deadline = new StringBuilder();
            String mode = "none";
            for (String command : splitTask) {
                if (Objects.equals(command, "(by:")) {
                    mode = "by";
                    continue;
                }
                if (Objects.equals(mode, "by")) {
                    if (deadline.length() != 0) {
                        deadline.append(" ");
                    }
                    deadline.append(command);
                }
            }
            if (deadline.length() != 0) {
                deadline.deleteCharAt(deadline.length() - 1); // Remove last ).
            }
            theTask = new Deadline(taskName, deadline.toString());
        } else if (TaskType == 'E') {
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            String mode = "none";
            for (String command : splitTask) {
                if (Objects.equals(command, "(from:")) {
                    mode = "from";
                    continue;
                }
                if (Objects.equals(command, "to:")) {
                    mode = "to";
                    continue;
                }
                if (Objects.equals(mode, "from")) {
                    if (from.length() != 0) {
                        from.append(" ");
                    }
                    from.append(command);
                } else if (Objects.equals(mode, "to")) {
                    if (to.length() != 0) {
                        to.append(" ");
                    }
                    to.append(command);
                }
            }
            if (to.length() != 0) {
                to.deleteCharAt(to.length() - 1); // Remove last ).
            }
            theTask = new Event(taskName, from.toString(), to.toString());
        } else {
            throw new DukeException("Input file corrupted.");
        }
        if (oneTask.charAt(4) == 'X') {
            theTask.completeTask();
        }
        return theTask;
    }
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>(100);
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String oneTask = s.nextLine();
                tasks.add(parseOneLine(oneTask));
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in reading files: " + e.getMessage());
        }
        return tasks;
    }

    public static void writeToDisk(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (Task task : tasks) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("IOException in writing to file: " + e.getMessage());
        }
    }
}


