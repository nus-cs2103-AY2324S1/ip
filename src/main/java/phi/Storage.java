package phi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    String filepath;
    File txtFile;
    public Storage(String filepath) {
        this.filepath = filepath;
        this.txtFile = new File(filepath);
    }

    public TaskList readFromFile() {
        TaskList tasks = new TaskList();
        try {
            System.out.println("Attempting to read from text file...");
            Scanner txtScanner = new Scanner(txtFile);
            while (txtScanner.hasNextLine()) {
                tasks.addFromSc(txtScanner.nextLine());
            }
            txtScanner.close();
            System.out.println("Text file has been scanned!\n" + tasks.listSize() + " items in the list");
        } catch (FileNotFoundException e) {
            System.out.println("I can't find it :( Creating new .txt file");
            return new TaskList();
        }
        return tasks;
    }

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
