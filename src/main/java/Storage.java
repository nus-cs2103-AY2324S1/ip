import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String FILEPATH = "data/tasks.txt";

    public void saveStringToFile(String textToAdd) throws DukeException {
        try {
            File file = new File(FILEPATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("File created: " + file.getName());
            } else if (file.length() > 0) {
                throw new DukeException("Clear the file before saving by using clearfile.");
            }
            FileWriter fw = new FileWriter(FILEPATH, true);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Saving failed: " + e.getMessage());
        }
    }

    public String loadStringFromFile() throws DukeException {
        try {
            // check if file exists
            File file = new File(FILEPATH);
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

    public void clearFile() throws DukeException {
        try {
            File file = new File(FILEPATH);
            if (!file.exists()) {
                throw new DukeException("File does not exist.");
            } 
            FileWriter fw = new FileWriter(FILEPATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Clearing failed: " + e.getMessage());
        }
    }


}
