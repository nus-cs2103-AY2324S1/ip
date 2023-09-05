package duke;

import java.io.File;
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
     * @throws InvalidInputException the exception thrown if we receive an InvalidInputException while copying
     */
    public void saveTasks(TaskList lst) throws InvalidInputException {
        try {
            Path folderPath = Paths.get(relativePath);

            if (!Files.exists(folderPath)) {
                //file does not exist
                System.out.println("Data folder does not exist, Creating one now");
                try {
                    Files.createDirectories(folderPath);
                } catch (IOException e) {
                    ui.printException(e.getMessage());
                }
            }

            //folder does exist
            Path filePath = Paths.get(relativePath , "TaskList.txt");
            if (!Files.exists(filePath)) {
                //file does not exist
                System.out.println("File does not exist, Creating one now");
                try {
                    Files.createFile(filePath);
                } catch (IOException e) {
                    ui.printException(e.getMessage());
                }
            }
            //file exists
            File file = filePath.toFile();
            FileWriter writer = new FileWriter(file, false);

            for (int i = 0; i < lst.size(); i++) {
                writer.write(lst.get(i).newFormat() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            ui.printException(e.getMessage());
        }
    }
    /**
     * Loads an existing file into a new TaskList object.
     * @return new TaskList object loaded
     * @throws InvalidInputException an exception thrown whenever input is invalid
     */
    public List<Task> load() throws InvalidInputException {
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
        } catch (IOException e) {
            ui.printException(e.getMessage());
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
