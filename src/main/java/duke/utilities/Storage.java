package duke.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks into the tasks.txt file
 */
public class Storage {
    
    /** Variable to store relative file path */
    File file;
    Boolean exists;

    public Storage(String filePath) {
        this.file = new File(filePath);
        try {
            this.exists = !file.createNewFile();
        } catch (IOException e) {
            System.out.println("!ERROR! IOException" + e);
        }
    }

    /**
     * Overwrites existing data in tasks.txt
     * 
     * @param list List of tasks that will overwrite the data in the file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void overwriteTasksData(ArrayList<Task> list) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.flush();
        oos.close();
    }

    /**
     * Loads the data from tasks.txt
     * 
     * @return A list of tasks
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Task> loadTasksData() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Task> list = (ArrayList<Task>) ois.readObject();
        ois.close();
        return list;
    }

    public Boolean fileExists() {
        return this.exists;
    }
}
