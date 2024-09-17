package benben;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Storage that stores, reads and rite the local file
 */
public class Storage {
    private final String filePath;
    private final String directory = "data";

    private File file;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the file path of the local file
     */
    public Storage(String filePath) {
        File fileDir = new File(directory);
        fileDir.mkdir();
        this.file = new File(directory, filePath);
        this.filePath = file.getPath();
    }

    /**
     * Writes the data from the task list to the local file
     *
     * @param tasks the task list
     */
    public void write(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            StringBuilder content = new StringBuilder("");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                content.append(t.getLog());
            }
            fw.write(content.toString());
            fw.close();
        } catch (IOException e) {
            throw new BenBenException("Failed to write to file!" + e.getMessage());
        }
    }

    /**
     * Loads array list of tasks from the local file.
     *
     * @return the array list of the tasks
     * @throws BenBenException the ben ben exception
     */
    public ArrayList<Task> load() throws BenBenException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            TaskList taskList = new TaskList();
            if (!file.exists()) {
                boolean isCreated = file.createNewFile();
                assert isCreated == true;
                return list;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String ln = sc.nextLine();
                String[] strSplit = Parser.getArrayFromFile(ln);
                Task task = Parser.parseFromFile(strSplit);
                list.add(task);
            }
            sc.close();
            return list;
        } catch (FileNotFoundException e) {
            throw new BenBenException("The local file is not found in the directory");
        } catch (IOException e) {
            throw new BenBenException("IOException found!" + e.getMessage());
        } catch (NullPointerException e) {
            throw new BenBenException("The local file does not exits");
        }
    }
}
