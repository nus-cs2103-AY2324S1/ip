import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {

    String saveFilePath;

    Storage(String filePath) {
        this.saveFilePath = filePath;
    }

    String readFromFile() throws IOException {
        Path filePath = Path.of(saveFilePath);
        return Files.readString(filePath);
    }

    void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(saveFilePath);
        fw.write(text);
        fw.close();
    }


}
