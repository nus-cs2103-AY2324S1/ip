package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File file;
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            this.file = new File(filePath);
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File load() {
        return this.file;
    }

    public void rewrite(String newText) {
        try {
            FileWriter fw;
            fw = new FileWriter(filePath);
            fw.write(newText);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
