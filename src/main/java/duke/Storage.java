package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * This class handles FileIO related operations for the Main class.
 */
public class Storage {
    private static final String DATA_LOCATION = "data.txt";
    private File file;

    /**
     * Creates a Storage object with a new file at "data.txt" if it does not already exist.
     *
     * @throws IOException
     */
    public Storage() throws IOException {
        File f = new File(DATA_LOCATION);

        if (!f.exists()) {
            f.createNewFile();
        }

        assert(f.exists());
        this.file = f;
    }

    /**
     * Creates a Storage object with a given file.
     * Useful for Unit Testing.
     *
     * @param f The file to be encapsulated by the Storage object.
     */
    public Storage(File f) {
        this.file = f;
    }

    /**
     * This method modifies a given TaskList object based on the contents
     * of the given File. This method is used at the start of the program to
     * read the content of a File into an ArrayList.
     *
     * @param tl The input TaskList object.
     * @throws IOException
     */
    public void fileToTaskList(TaskList tl)
            throws IOException {

        Scanner scf = new Scanner(this.file);
        while (scf.hasNext()) {
            String s = scf.nextLine();
            String[] sArray = s.split("\\|");
            String typeOfTask = sArray[0];
            boolean isDone = sArray[1].equals("1") ? true : false;

            assert(typeOfTask instanceof String);
            switch (typeOfTask) {
            case "T":
                String todoName = sArray[2];
                tl.add(new ToDo(isDone, todoName));
                break;

            case "E":
                String eventName = sArray[2];
                tl.add(new Event(isDone, eventName));
                break;

            case "D":
                String deadlineName = sArray[2];
                tl.add(new Deadline(isDone, deadlineName));
                break;

            default:
                break;

            }

        }
        scf.close();

    }

    /**
     * This method modifies a given file based on the contents of the
     * given TaskList object. This method is used at any time that the
     * encapsulated List is modified, to update the File accordingly.
     *
     * @param tl The input TaskList object.
     * @throws IOException
     */
    public void taskListToFile(TaskList tl)
            throws IOException {

        FileWriter fw = new FileWriter(this.file, false);
        for (Task t : tl.getTaskList()) {
            String fileString = t.fileToString();
            fw.write(fileString);
            fw.write("\n");
        }
        fw.close();

    }


    public File getFile() {
        return this.file;
    }



}
