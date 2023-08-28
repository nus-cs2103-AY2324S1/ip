import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

	private File file;
	private File folder;


	public Storage(String filePath) {
		try {
			String[] split = filePath.split("/");
			this.file = new File(filePath);
			this.folder = new File(split[0]);
			if (!this.folder.isDirectory()) {
				this.folder.mkdir();
			}
			if (!this.file.exists()) {
				this.file.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}

	/**
	 * Writes the tasks to the file
	 *
	 * @param tasks
	 */
	public void writeToFile(String tasks) {
		try {
			FileWriter fw = new FileWriter(this.file);
			fw.write(tasks);
			fw.close();
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}

	public void writeToFile(TaskList tasks) {
		try {
			FileWriter fw = new FileWriter(this.file);
			for (int i = 0; i < tasks.length(); i++) {
				Task currTask = tasks.get(i);
				fw.write(currTask.toString() + "\n");
			}
			fw.close();
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}

	public void appendToFile(String tasks) {
		try {
			FileWriter fw = new FileWriter(this.file, true);
			fw.write(tasks);
			fw.close();
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}
}
