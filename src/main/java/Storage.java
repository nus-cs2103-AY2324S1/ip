import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                throw new DukeException("\u2639 OOPS!!! No file found, creating a new file!");
            }
        } catch (IOException e) {
            throw new DukeException("\u2639 OOPS!!! An error occurred while creating the file.");
        }

        // Loading the serialised object
        try {
            FileInputStream fileIn = new FileInputStream(this.filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            @SuppressWarnings("unchecked")
            ArrayList<Task> loadedList = (ArrayList<Task>) objectIn.readObject();
            return loadedList;
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException("\u2639 OOPS!!! An error occurred while reading data.");
        }
    }

    public void write(TaskList tasks) throws DukeException {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException("\u2639 OOPS!!! Something went wrong when saving data");
        }
    }
}
