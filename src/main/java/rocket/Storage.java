package rocket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;
    private String filePath;

    /**
     * Create new storage at the filePath specified
     * @param filePath path to store the file.
     */
    public Storage (String filePath){
        // Get FilePath objects from the filePath string
        this.filePath = filePath;
        Path path = Paths.get(filePath);
        Path parent = path.getParent();
        String directoryPath = parent.toString();

        // Using FilePath objects, make new folder if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Using FilePath objects, make new file if it doesn't exist
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.file = file;
    }

    /**
     * Load from the storage a list of strings that represent tasks
     * @return a list of strings.
     */
    public ArrayList<String> load() {
        ArrayList<String> stringList = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNext()) {
                String taskString = fileScanner.nextLine();
                stringList.add(taskString);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return stringList;
    }

    /**
     * Saves tasklist in to a file
     * @param tasks the tasklist.
     * @throws IOException occurs when there are problems writing to the file
     */
    public void saveTaskList(TaskList tasks) throws IOException{
        writeToFile(String.valueOf(tasks));
    }

    /**
     * Writes a string to a file
     * @param textToAdd the text to be added to the file
     * @throws IOException occurs when there are problems writing to the file
     */
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
