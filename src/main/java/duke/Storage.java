import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;

    public Storage() throws IOException {
        File f = new File("data.txt");

        if (!f.exists()) {
            f.createNewFile();
        }

        this.file = f;
    }

    /**
     * This method modifies a given ArrayList based on the contents
     * of the given File. This method is used at the start of the program to
     * read the content of a File into an ArrayList.
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
     * given ArrayList. This method is used at any time that the ArrayList
     * is modified, to update the File accordingly.
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
