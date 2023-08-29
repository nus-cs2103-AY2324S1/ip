import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    Storage() {

    }

    String readFromFile(String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        return Files.readString(filePath);
    }
}
