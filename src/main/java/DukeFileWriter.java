import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DukeFileWriter {
    private File file;
    public DukeFileWriter(String filePath, String fileName) {
        try {
            this.file = new File(filePath + "/" + fileName);
            if (new File(filePath).mkdirs()) {
                System.out.println("Directories created");
            } else {
                System.out.println("Directories exist");
            }
            if (this.file.createNewFile()) {
                System.out.println("File created: " + this.file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(List<Task> data) {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task entry : data) {
                writer.write(entry.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
