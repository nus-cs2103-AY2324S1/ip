package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class deals with interactions with the user
 */
public class Ui {
    private static final String NAME = "Zac";
    /**
     * Prints out all the tasks in the list
     * @param filePath the file where the lists of Tasks are stored. It is hardcoded to be "data/duke.txt"
     * @throws FileNotFoundException if the file at this filePath is not found (though I'm not sure
     *     when this will happen)
     */
    public static String printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        StringBuilder string = new StringBuilder();
        string.append("Hi, I am " + NAME + "!\nHere are the tasks in your list:\n");
        while (s.hasNext()) {
            string.append(s.nextLine() + "\n");
        }
        return string.toString();
    }
}
