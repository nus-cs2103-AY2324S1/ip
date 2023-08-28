package friday;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Storage {

    private File storage;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        String directoryPath = filePath.substring(0, filePath.lastIndexOf('/'));
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        this.storage = new File(filePath);
    }

    public void saveFile(String taskList) throws IOException {
        FileWriter file = new FileWriter(filePath);
        file.write(taskList);
        file.close();
    }
}

