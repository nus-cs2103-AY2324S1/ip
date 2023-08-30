package Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String path;
    private File tempFile;

    public Storage(String path) {
        this.path = path;
        this.tempFile = null;
    }


    public List<String> readFile() {
        List<String> output = new ArrayList<>();
        File f = new File(path);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String task = s.nextLine().trim();
                output.add(task);
            }
            return output;
        } catch (IOException e) {
            return output;
        }

    }
    private void writeToFile(FileWriter fw, String tasks) throws IOException {
        fw.write(tasks);
        fw.close();
    }

    public String saveToDisk(List<String> tasks) {
        try {
            FileWriter fw = new FileWriter(path);
            String input = "";
            for (int i = 0; i < tasks.size(); i++) {
                input = input + tasks.get(i) + "\n";
            }
            writeToFile(fw, input);
            return "Tasks saved successfully";
        } catch (IOException e) {
            return "Tasks could not be saved";
        }

    }

}
