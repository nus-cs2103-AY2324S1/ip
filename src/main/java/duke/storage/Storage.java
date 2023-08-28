package duke.storage;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<String> load() throws DukeException {
        File file = new File(this.filePath);
        List<String> lines = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lines.add(line);
            }
            sc.close();
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                throw new DukeException("OOPS!!! I have problems creating the file to save your tasks.");
            }
        }
        return lines;
    }

    public void save(List<String> lines) throws DukeException {
        File file = new File(this.filePath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (String line : lines) {
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! I have problems saving your tasks.");
        }
    }
}
