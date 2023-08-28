import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    public static void saveData(String[] data, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < data.length; i++) {
            fw.write(data[i] + "\n");
        }
        fw.close();
    }

    public static <T> ArrayList<T> loadData(String filePath) {
        return new ArrayList<T>();
    }
}
