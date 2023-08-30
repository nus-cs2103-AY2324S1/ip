package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
	private File folder;
	private File file;

	public Storage(String filePath) {
		String[] folder = filePath.split("/");
		this.file = new File(filePath);
		this.folder = new File(folder[0]);

		// Directory doesn't exist
		if (!this.folder.isDirectory()) {
			this.createDirectory();
		}

		// File doesn't exist
		if (!this.fileExists()) {
			this.createFile();
		}
	}

	public void createDirectory() {
		this.folder.mkdir();
	}

	public void createFile() {
		try {
			this.file.createNewFile();
		} catch (IOException err) {
			System.out.println(err);
		}
	}

	public boolean fileExists() {
		return this.file.exists();
	}

	public ArrayList<Task> readData() {
		ArrayList<Task> data = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(this.file);
			while (scanner.hasNext()) {
				Task task = this.formatStringToTask(scanner.nextLine());
				if (task != null) {
					data.add(task);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!" + e.getMessage());
		}
		return data;
	}

	public void writeData(ArrayList<Task> tasks) {
		try {
			FileWriter fw = new FileWriter(this.file);
			for (Task task : tasks) {
				fw.write(task.toFile() + "\n");
			}
			fw.close();
		} catch (IOException e) {
			System.out.println("Error writing to file!" + e.getMessage());
		}
	}

	public Task formatStringToTask(String line) {
		String[] split = line.split(" \\| ", 4);

		// Corrupted File
		if (split.length < 3) {
			System.out.println("Error!");
			return null;
		}

		String type = split[0];
		String status = split[1];
		String description = split[2];

		Task task;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

		switch (type) {
			case "T":
				task = new Todo(description, status);
				break;
			case "D":
				task = new Deadline(description, LocalDateTime.parse(split[3], formatter), status);
				break;
			case "E":
				String[] interval = split[3].split(" - ", 2);
				if (interval.length < 2) {
					task = null;
				} else {
					task = new Event(description, LocalDateTime.parse(interval[0], formatter), LocalDateTime.parse(interval[1], formatter), status);
				}
				break;
			default:
				task = null;
		}

		return task;
	}

}
