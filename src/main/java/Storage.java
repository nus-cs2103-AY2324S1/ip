import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath = "data/tasks.txt";
    private String fileDirectory = "data";
    private String fileName = "tasks.txt";

    public void saveToFile(String textToAdd) throws DukeException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Saving failed: " + e.getMessage());
        }
    }

    public String loadFromFile() throws DukeException {
        try {
            // check if file exists
            File file = new File(filePath);
            if (!file.exists()) {
                throw new DukeException("File does not exist.");
            }
            String output = "";
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                output += s.nextLine() + "\n";
            }
            return output;
        } catch (FileNotFoundException e) {
            throw new DukeException("Loading failed: " + e.getMessage());
        }
    }

}
