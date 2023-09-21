package duke.stub;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeFileNotFoundException;

public class StorageStub {
    File taskFile = new File("./this/file/is/invalid");

    /**
     * Loads all the lines in the file and stores in a list.
     *
     * @return The list with all the lines.
     */
    public ArrayList<String> loadFromFile() {
        Scanner sc;
        ArrayList<String> linesInFile = new ArrayList<>();
        try {
            sc = new Scanner(taskFile);
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            linesInFile.add(s);
        }
        return linesInFile;
    }
}
