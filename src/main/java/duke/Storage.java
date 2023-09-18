package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class that is used to read and write files.
 */
public class Storage {
    private static String relativePath = "C:\\Users\\wenji\\OneDrive\\Desktop\\Y2S1\\"
            + "CS2103T\\CS2103T projects\\ip\\data";
    private Ui ui = new Ui();
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes tasks from TaskList into the file
     * @param lst the TaskList we want to copy from
     */
    public void writeFile(TaskList lst) throws InvalidInputException{
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);

            for (Task task : lst.lst) {
                fileWriter.write(task + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new InvalidInputException("Error while saving tasks to file: " + e.getMessage());
        }
    }
    /**
     * Loads an existing file into a new TaskList object.
     * @return new TaskList object loaded
     * @throws InvalidInputException an exception thrown whenever input is invalid
     */
    public List<Task> readFile() throws IOException {
        List<Task> list = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String info = sc.nextLine();
                String[] taskDetails = info.split("\\|");
                if (taskDetails[0].equals("[T]")) {
                    Task tsk = new ToDo(taskDetails[2]);
                    list.add(tsk);
                    if (taskDetails[1].equals("1")) {
                        list.get(list.size() - 1).setCompleted();
                    }
                } else if (taskDetails[0].equals("[D]")) {
                    list.add(new Deadline(taskDetails[2], taskDetails[3]));
                    if (taskDetails[1].equals("1")) {
                        list.get(list.size() - 1).setCompleted();
                    }
                } else if (taskDetails[0].equals("[E]")) {
                    list.add(new Event(taskDetails[2], taskDetails[3], taskDetails[4]));
                    if (taskDetails[1].equals("1")) {
                        list.get(list.size() - 1).setCompleted();
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("Data folder does not exist, creating now!");
            } else if (new File("data/TaskList.txt").createNewFile()) {
                System.out.println("TaskList.txt file not found, creating now!");
            }
        }
        return list;
    }

    /**
     * Gets relative path
     * @return String instance of relative path
     */
    public static String getRelativePath() {
        return Storage.relativePath;
    }
}
