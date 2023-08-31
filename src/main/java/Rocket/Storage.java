package Rocket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private File file;
    private String filePath;

    public Storage (String filePath){
        this.filePath = filePath;
        Path path = Paths.get(filePath);
        String directoryPath = path.getFileName().toString();

        // Make new folder if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Make new file if it doesn't exist
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

    /**
     * Appends a string to a file
     * @param textToAppend the text to be appended to the file
     * @throws IOException occurs when there are problems writing to the file
     */
    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
