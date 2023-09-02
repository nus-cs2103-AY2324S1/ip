package bob;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bob.task.Task;

/**
 * Handles loading tasks from, and saving tasks to file
 */
public class Storage {

    private String dataPath;

    public Storage(String dataPath) {
        this.dataPath = dataPath;
    }

    /**
     * If bob.txt exists, read data from it.
     * If data is not in correct form, delete file and create empty file.
     * If bob.txt does not exist, create empty file.
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(dataPath);
        ArrayList<Task> lst = new ArrayList<>();

        if (file.createNewFile()) { // if file do not exist
            // do nothing, as a new file is created. list remains empty
        } else {
            // read from file
            Scanner sc = new Scanner(file);
            try {
                while (sc.hasNext()) {
                    lst.add(Task.parse(sc.nextLine()));
                }
            } catch (IndexOutOfBoundsException e) { // file corrupted
                file.delete();
                file.createNewFile();
            }
        }
        return lst;
    }

    /**
     * Writes a new line to bob.txt for each Task in lst.
     * Called whenever an operation that alters lst is implemented.
     * @throws IOException
     */
    public void write(ArrayList<Task> lst) throws IOException {
        FileWriter fw = new FileWriter(dataPath, false);
        String lstString = "";

        for (Task tsk : lst) {
            lstString += tsk.toTxt() + "\n";
        }

        fw.write(lstString);
        fw.close();
    }
}
