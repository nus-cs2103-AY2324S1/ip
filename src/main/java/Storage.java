import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void saveData(ArrayList<String> data, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < data.size(); i++) {
            fw.write(data.get(i) + "\n");
        }
        fw.close();
    }

    public static ArrayList<String> loadData(String filePath) throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String line = s.nextLine();
            data.add(line);
        }

        return data;
    }
}
