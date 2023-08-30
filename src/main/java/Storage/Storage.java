package Storage;

import Exception.KevinException;

import java.io.File;
import java.io.IOException;

public class Storage {
    public static final String FILE_FOLDER_NAME = "data";

    public void createFile(String fileLocation) throws KevinException {
        try {
            File fileFolder = new File(FILE_FOLDER_NAME);
            if (!fileFolder.isDirectory()) {
                fileFolder.mkdir();
            }

            File dataFile = new File(fileLocation);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (Exception err) {
            throw new KevinException("Fail to make new file.");
        }
    }
}
