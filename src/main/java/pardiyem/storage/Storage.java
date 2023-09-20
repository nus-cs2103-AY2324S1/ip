package pardiyem.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import pardiyem.task.Deadline;
import pardiyem.task.Event;
import pardiyem.task.Task;
import pardiyem.task.TaskList;
import pardiyem.task.Todo;


public class Storage {
    static final String DEFAULT_PATH = "./data/storagefile.txt";
    private static final String NEWLINE = "\n";
    private static final String CORRUPT_MSG = "Whoops, this save file may be corrupted";
    private static final int STATUS_DESC_INDEX = 4;
    private static final int TASK_PREFIX_LENGTH = 7;
    private static final int FROM_ARG_PREFIX_LENGTH = 6;
    private static final int TO_ARG_PREFIX_LENGTH = 4;
    private static final int BY_ARG_PREFIX_LENGTH = 4;
    private final File f;

    /**
     * A constructor for the Storage class. Initialises the default data directory and file.
     */
    public Storage() throws IOException {
        f = new File(DEFAULT_PATH);
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    /**
     * Saves the given TaskList object into a data file by calling toString() on each member
     * of the list and writing it to the file, each separated by a newline.
     *
     * @param tl the TaskList object to be saved into the data file
     * @throws IOException if the writing process fails
     */
    public void save(TaskList tl) throws IOException {
        FileWriter fw = new FileWriter(f);
        StringBuilder toWrite = new StringBuilder();
        for (int i = 0; i < tl.size(); i++) {
            if (i != 0) {
                toWrite.append(NEWLINE);
            }
            toWrite.append(tl.getTask(i).toString());
        }
        fw.write(toWrite.toString());
        fw.close();
    }

    /**
     * Loads a TaskList object by reading a data file,
     * calling the parseLine method on each line and
     * adding the outputted Task object to the list
     *
     * @throws IOException if the reading process fails
     */
    public TaskList load() throws IOException{
        ArrayList<Task> out = new ArrayList<Task>();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line = br.readLine();

        while (line != null) {
            out.add(parseLine(line));
            line = br.readLine();
        }

        br.close();

        return new TaskList(out);
    }

    private Task parseLine(String line) throws IllegalArgumentException {
        boolean isDone = (line.charAt(STATUS_DESC_INDEX) == 'X');

        switch (line.charAt(1)) {
        case 'T':
            return new Todo(line.substring(TASK_PREFIX_LENGTH), isDone);
        case 'E':
            int indFrom = line.indexOf("from:");
            int indTo = line.indexOf("to:");
            return new Event(line.substring(TASK_PREFIX_LENGTH, indFrom - 2),
                    line.substring(indFrom + FROM_ARG_PREFIX_LENGTH, indTo - 1),
                    line.substring(indTo + TO_ARG_PREFIX_LENGTH, line.length() - 1),
                    isDone);
        case 'D':
            int indBy = line.indexOf("by:");
            return new Deadline(line.substring(TASK_PREFIX_LENGTH, indBy - 2),
                    line.substring(indBy + BY_ARG_PREFIX_LENGTH, line.length() - 1),
                    isDone);
        default:
            throw new IllegalArgumentException(CORRUPT_MSG);
        }
    }
}
