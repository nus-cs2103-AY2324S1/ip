import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {

    String saveFilePath;

    Storage(String filePath) {
        this.saveFilePath = filePath;
    }

    String getSaveFilePath() {
        return this.saveFilePath;
    }

    String getAbsoluteSaveFilePath() {
        return System.getProperty("user.dir") +
                this.getSaveFilePath().substring(1).replace("/", "\\");
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
