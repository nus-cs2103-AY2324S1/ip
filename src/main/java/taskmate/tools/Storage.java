package taskmate.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {

    String saveFilePath;

    public Storage(String filePath) {
        this.saveFilePath = filePath;
    }

    public String getSaveFilePath() {
        return this.saveFilePath;
    }

    public String getAbsoluteSaveFilePath() {
        return System.getProperty("user.dir") +
                this.getSaveFilePath().substring(1).replace("/", "\\");
    }

    public String readFromFile() throws IOException {
        Path filePath = Path.of(saveFilePath);
        return Files.readString(filePath);
    }

    public void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(saveFilePath);
        fw.write(text);
        fw.close();
    }


}
