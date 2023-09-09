package taskmate.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        try {
            Files.createDirectories(Paths.get(getDirectoryPath(saveFilePath)));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        FileWriter fw = new FileWriter(saveFilePath);
        fw.write(text);
        fw.close();
    }

    static String getDirectoryPath(String filePath) {
        Path path = Paths.get(filePath);
        Path directoryPath = path.getParent();

        if (directoryPath != null) {
            return directoryPath.toString();
        } else {
            return null;
        }
    }


}
