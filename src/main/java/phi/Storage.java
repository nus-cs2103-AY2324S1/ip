package phi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents all storage-related methods (reading from file, checking file paths etc.)
 */
public class Storage {
    File txtFile;
    Path filePath;

    /**
     * Constructor for a new Storage instance.
     * If file / file path does not exist, the .txt file and corresponding directories will be created.
     *
     * @param pathString File path for the saved tasklist.txt file (to be read and written to)
     */
    public Storage(String pathString) {
        this.txtFile = new File(pathString);
        this.filePath = Paths.get(pathString);

        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                System.out.println("No file found, new .txt file created!");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Reads the input of the stored tasklist .txt file and returns a new TaskList object containing the saved information
     *
     * @return TaskList object containing the tasks stored in tasklist.txt
     */
    public TaskList readFromFile() {
        TaskList tasks = new TaskList();
        try {
            Scanner txtScanner = new Scanner(txtFile);
            while (txtScanner.hasNextLine()) {
                tasks.addFromTxt(txtScanner.nextLine());
            }
            txtScanner.close();
            System.out.println("Text file has been scanned!\n" + tasks.listSize() + " items in the list");
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong... There's still no file to be found");
            return new TaskList();
        }
        return tasks;
    }

    /**
     * Writes the output of PHI's current tasklist to the .txt file in storage.
     *
     * @param taskInput PHI's current TaskList object
     */
    public void writeToFile(TaskList taskInput) {
        try {
            FileWriter output = new FileWriter(txtFile);
            output.write(taskInput.outputList());
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
