import java.io.*;
import java.util.ArrayList;
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error while creating file: " + e.getMessage());
            }
        }
    }

    public ArrayList<Task> load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            in = new ObjectInputStream(fileIn);
            // Suppress warning as deserialised object is of ArrayList<Task>
            @SuppressWarnings("unchecked")
            ArrayList<Task> list = (ArrayList<Task>) in.readObject();
            return list;
        } catch (EOFException e) {
            return new ArrayList<Task>();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public void saveToFile(ArrayList<Task> list) throws IOException{
        ObjectOutputStream out = null;
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
