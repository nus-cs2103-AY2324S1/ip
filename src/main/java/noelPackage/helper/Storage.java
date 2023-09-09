package noelPackage.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    private final String filePath;
    protected boolean hasFileUpdated;

    public Storage(String filePath) {
        hasFileUpdated = false;
        this.filePath = filePath;
    }

    public String loadFile() {
        return checkFile();
    }

    public void updateFile() {
        this.hasFileUpdated = true;
    }

    public String checkFile() {
        Path filePath = Paths.get(this.filePath);
        if (Files.exists(filePath)) {
            try {
                return new String(Files.readAllBytes(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            hasFileUpdated = true;
            System.out.println("File does not exist");
            System.out.println("Creating file now!");
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                System.out.println("File and Directories created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void writeToFile(List<String> task) {
        if (hasFileUpdated) {
            try {
                Files.write(Paths.get(filePath), task);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
