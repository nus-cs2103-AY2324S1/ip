package duke;

import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Storage {
    protected String fileName = "userData.txt";
    protected File file;

    public Storage(String filePath) {
        this.file = new File(filePath, fileName);
        //Making a new dir if the specified one does not exit
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public void write(String inputs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(inputs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    return line;
                }
            }
            System.out.println("Keyword not found, please try again!");
            return "ERROR_KeyNotFound";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR_Exception";
        }
    }
}
