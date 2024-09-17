package chatbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.Scanner;

/**
 * Handles the loading and writing of text files.
 */
public class Storage {
    /** The string representation of the directory to text file */
    private String filePath;
    /** The string representation of the directory*/
    private String dataFolderPath;

    /**
     * Constructor for Storage. Initialises the filepath.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.dataFolderPath = "./data";
    }
    /** 
     * Checks if the file path exists and
     * prints the text document if it exists.
     * @throws FileNotFoundException If file path is invalid.
     */
    public String load() {
        String outputStr = "";
        try {
            File file = new File(filePath);
            Scanner printSC = new Scanner(file);
            while (printSC.hasNextLine()) {
                outputStr += printSC.nextLine() + "\n";
            }
            printSC.close();

        } catch (FileNotFoundException e) {
            outputStr = "No file found!";
        } 
        return outputStr;
    }
    /** 
     * Creates a new file in the specified path and
     * writes to the file.
     * @throws IOException If the input/output is interrupted.
     */
    public void write(List<Task> taskForce) {
        File path = new File(filePath);
        File dataFolder = new File(dataFolderPath);

        try {
            if (!path.exists()) {
                dataFolder.mkdir();
            }
            
            String toBeSaved = "";
            Scanner printSC = new Scanner(path);
            while (printSC.hasNextLine()) {
                toBeSaved += printSC.nextLine() + "\n";
            }
            printSC.close();

            for (int i = 0; i < taskForce.size(); i++) {
                toBeSaved = toBeSaved + taskForce.get(i) + "\n";
            }

            FileWriter wr = new FileWriter(path);
            wr.write(toBeSaved);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            System.out.println("This may occur.");
        }
    }
}