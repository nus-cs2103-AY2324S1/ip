package duke;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String[]> loadData() throws DukeException {
        ArrayList<String[]> stringList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(this.filePath));
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split("\\|");
                for (int i = 0; i < line.length; i++) {
                    line[i] = line[i].strip();
                }
                stringList.add(line);
            }
            sc.close();
            return stringList;
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from data file.");
        }
    }

    public void writeData(String[] inputList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (String t: inputList) {
                fw.write(t.strip()+"\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing tasks to file.");
        }
    }
}
