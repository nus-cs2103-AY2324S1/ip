package duke;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class handles FileIO related operations for the Main class.
 */
public class Storage {

    public File file;

    /**
     * Creates a Storage object with a new file at "data.txt" if it does not already exist.
     *
     * @throws IOException
     */
    public Storage() throws IOException {
        File f = new File("data.txt");

        if (!f.exists()) {
            f.createNewFile();
        }

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

            switch (typeOfTask) {
            case "T":
                boolean isTodoDone = sArray[1].equals("1") ? true : false;
                String todoName = sArray[2];
                tl.add(new ToDo(isTodoDone, todoName));
                break;

            case "E":
                boolean isEventDone = sArray[1].equals("1") ? true : false;
                String eventName = sArray[2];
                tl.add(new Event(isEventDone, eventName));
                break;

            case "D":
                boolean isDeadlineDone = sArray[1].equals("1") ? true : false;
                String deadlineName = sArray[2];
                tl.add(new Deadline(isDeadlineDone, deadlineName));
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
        for (Task t : tl.taskList) {
            String fileString = t.fileToString();
            fw.write(fileString);
            fw.write("\n");
        }
        fw.close();

    }



}
