import java.io.File;
import java.io.IOException;

public class Storage {

    private File file;
    public Storage() {
        try {
            File dataDirectory = new File("./data");
            dataDirectory.mkdir();
            File taskFile = new File("./data/duke.txt");
            if (taskFile.createNewFile()) {
                System.out.println("Created new file to store your tasks!");
            } else {
                System.out.println("Existing task file exists. ");
            }
            this.file = taskFile;
        } catch (IOException e) {
            System.out.println("Unable to create file.");
            e.printStackTrace();
        }
    }

    public File getFile() {
        return this.file;
    }
}
