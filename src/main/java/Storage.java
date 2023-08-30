import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load() {
        File file = new File(filePath);

        try {
            Scanner printSC = new Scanner(file);
            while (printSC.hasNextLine()) {
                System.out.println(printSC.nextLine());
            }
            printSC.close();

        } catch (FileNotFoundException e) {
            System.out.println("No file found!");
        }
    }

    public void write(List<Task> taskForce) {
        String toBeSaved = "";
        for (int i = 0; i < taskForce.size(); i++) {
            toBeSaved = toBeSaved + taskForce.get(i) + "\n";
        }

        File path = new File(filePath);

        try {
            FileWriter wr = new FileWriter(path);
            wr.write(toBeSaved);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            System.out.println("This will never occur.");
        }
    }
}