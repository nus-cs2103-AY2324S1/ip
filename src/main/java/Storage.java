import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

	public void writeToFile(TaskList tasks) {
		try {
			FileWriter fw = new FileWriter(this.file);
			for (int i = 0; i < tasks.length(); i++) {
				Task currTask = tasks.get(i);
				fw.write(currTask.toFile() + "\n");
			}
			fw.close();
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}

	public TaskList readFromFile() {
		TaskList tasks = new TaskList();
		try {
			Scanner s = new Scanner(this.file);
			while (s.hasNext()) {
				String currentLine = s.nextLine();
				String[] splitted = currentLine.split(" \\| ");
				String taskType = splitted[0];
				String isDone = splitted[1];
				String description = splitted[2];
				Task currTask = createTask(taskType, description, splitted);
				if (isDone.equals("1")) {
					currTask.mark();
				}
				tasks.addToList(currTask);
			}
		} catch (FileNotFoundException | DukeException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
		return tasks;
	}

	private Task createTask(String taskType, String description, String[] splitted) throws DukeException {
		Task task;
		switch (taskType) {
			case "T":
				task = new ToDo(description);
				break;
			case "D":
				task = new Deadline(description, splitted[3]);
				break;
			case "E":
				task = new Event(description, splitted[3], splitted[4]);
				break;
			default:
				throw new DukeException("Invalid task type: " + taskType);
		}
		return task;
	}
}
