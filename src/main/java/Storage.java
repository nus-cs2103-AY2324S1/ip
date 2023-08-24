
import java.io.*;
import java.util.ArrayList;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList load() throws IOException {
        try {
            FileInputStream file = new FileInputStream(filepath);
            ObjectInputStream output = new ObjectInputStream(file);
            ArrayList taskList = (ArrayList) output.readObject();
            output.close();
            return taskList;
        } catch (Exception error) {
            ArrayList taskList = new ArrayList();
            return taskList;
        }
    }

    public void save(ArrayList taskList) throws IOException{
        FileOutputStream file = new FileOutputStream(filepath);
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(taskList);
        output.close();
    }
}
