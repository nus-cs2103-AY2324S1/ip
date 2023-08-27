package carbonbot;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
	private final String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Loads the data from the storage.
	 * @return The lines in the file in a List.
	 * @throws IOException The file data could not be fetched from the storage.
	 */
	public List<String> load() throws IOException {
		// Returns an empty list if the file does not exists
		if (!new File(this.filePath).exists()) {
			return new ArrayList<String>();
		}

		return Files.readAllLines(Paths.get(filePath));
	}

	public void write(String data) throws IOException {
		// Create the file, and its directories if it does not already exists
		File file = new File(filePath);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}

		// Writes the data to the file
		FileWriter fw = new FileWriter(file);
		fw.write(data);
		fw.close();
	}
}
