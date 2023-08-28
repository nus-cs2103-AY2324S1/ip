import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Responsible for loading saved task list from/writing updated task list to plaintext file.
 * FORMAT: [Type]/[isDone]/[description]/[time]
 *
 */
public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Loads task list from file.
     *
     * @return A list of saved tasks.
     * @throws FileNotFoundException, DookException
     */
    public ArrayList<Task> load() throws FileNotFoundException, DookException{

        Scanner sc = new Scanner(file);
        ArrayList<Task> result = new ArrayList<>();
        while (sc.hasNext()) {
            result.add(getTaskFromString(sc.nextLine()));
        }
        return result;
    }

    public String save(TaskList taskList) throws DookException{
        String toSave = taskList.getSaveableString();
        try {
            writeToFile(filePath, toSave);
        } catch (IOException e) {
            throw new DookException("File cannot be saved.");
        }
        return "Task list saved!";

    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Parses plaintext and converts it into a Task.
     *
     * @param str
     * @return The converted Task.
     */
    private Task getTaskFromString(String str) throws DookException{
        String[] params = str.split("//");
        String taskCode = params[0];
        boolean isDone = params[1].equals("X");
        switch (taskCode) {
            case "T":
                return new Todo(params[2].trim(), isDone);
            case "D":
                return new Deadline(params[2].trim(), params[3].trim(), isDone);
            case "E":
                return new Event(params[2].trim(), params[3].trim(), params[4].trim(), isDone);
            default:
                throw new DookException("Failed to read from file correctly.");
        }
    }

}
