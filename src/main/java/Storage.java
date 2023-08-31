import java.io.File;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;

        try {
            getFile(filePath);
        }  catch (DukeException e) {
            throw e;
        }
    }

    private void getFile(String filePath) throws DukeException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();

            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created: " + directory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory.");
                }
            }

            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create file.");
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error creating file: " + e.getMessage());
        }
    }

    public String getFilePath() {
        return this.filePath;
    }
}
