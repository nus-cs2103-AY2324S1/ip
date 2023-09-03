import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class Storage {

    /**
     * This method modifies a given file based on the contents of the
     * given ArrayList. This method is used at any time that the ArrayList
     * is modified, to update the File accordingly.
     */
    public static void taskListToFile(File f, ArrayList<Task> taskList)
            throws IOException {

        FileWriter fw = new FileWriter(f, false);
        for (Task t : taskList) {
            String fileString = t.fileToString();
            fw.write(fileString);
            fw.write("\n");
        }
        fw.close();

    }


    /**
     * This method modifies a given ArrayList based on the contents
     * of the given File. This method is used at the start of the program to
     * read the content of a File into an ArrayList.
     */
    public static void fileToTaskList(File f, ArrayList<Task> taskList)
        throws IOException {

        Scanner scf = new Scanner(f);
        while (scf.hasNext()) {
            String s = scf.nextLine();
            String[] sArray = s.split("\\|");
            String typeOfTask = sArray[0];

            switch (typeOfTask) {
                case "T":
                    boolean isTodoDone = sArray[1].equals("1") ? true : false;
                    String todoName = sArray[2];
                    taskList.add(new ToDo(isTodoDone, todoName));
                    break;
                case "E":
                    boolean isEventDone = sArray[1].equals("1") ? true : false;
                    String eventName = sArray[2];
                    taskList.add(new Event(isEventDone, eventName));
                    break;
                case "D":
                    boolean isDeadlineDone = sArray[1].equals("1") ? true : false;
                    String deadlineName = sArray[2];
                    taskList.add(new Deadline(isDeadlineDone, deadlineName));
                    break;

            }

        }
        scf.close();

    }

}
