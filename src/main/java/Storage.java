import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList load() throws FileNotFoundException{
        File file = new File(filePath);
        ArrayList tasks = new ArrayList();

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] components = line.split(" \\| ");
            Task t;
            if (components[0].startsWith("T")) {
                t = new Todo(components[1], components[2]);
            } else if (components[0].startsWith("D")) {
                t = new Deadline(components[1], components[2], components[3]);
            } else {
                t = new Event(components[1], components[2], components[3], components[4]);
            }
            tasks.add(t);
        }
        return tasks;
    }
}
