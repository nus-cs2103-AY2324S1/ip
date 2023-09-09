package duke.storage;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.DeadLine;
import duke.task.Event;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents te computer storage system.
 * Able to write Task to storage text file through writeFile method.
 */
public class Storage {
	private String filePath;
	private ArrayList<Task> taskList;
	public Storage(String filePath) {
		this.filePath = filePath;
		this.taskList = new ArrayList<Task>(100);
	}



	/**
	 * Writes Task into text file.
	 * Creates text file if it does not exist.
	 * @param task Thing to be done.
	 * @throws IOException If unable to write file.
	 */
	public void addToFile(Task task) throws IOException {
		taskList.add(task);
		try {
			FileWriter fw = new FileWriter(filePath, true);
				fw.write(task.writeToFile());
				fw.write("\n");
			fw.close();
		} catch (IOException e) {
			throw new IOException("write fail");
		}

	}

	/**
	 * Reads stored text file line by line.
	 * @return ArrayList of Task.
	 * @throws IOException If unable to read lines in text file.
	 */
	public ArrayList<Task> readFile() throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
			StringBuilder br = new StringBuilder();
			String fileLine;
			while ((fileLine = in.readLine()) != null) {
				// append raw unformatted version
				br.append(fileLine);
				br.append("\n");
				readTask(fileLine);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return taskList;
	}

	// the file stored needs to be reformatted

	/**
	 * Converts each line into the format shown to reader.
	 * @param fileLine Each line in the file.
	 * @throws IOException If unable to read task in file as Deadline[D], Event[E] or ToDos[T].
	 */
	public void readTask(String fileLine) throws IOException {
		String[] str = fileLine.split("\\|");
		for (int i  = 0; i < str.length; i++) {
			String s = str[i].trim();
			str[i] = s;
		}
		if (str.length % 3 == 0 && str.length != 0) {
			StringBuilder br = new StringBuilder();
			String taskType = str[0];
			boolean isDone = Integer.parseInt(str[1]) == 1;
			switch(taskType) {
				case "T":
					ToDo toDo = new ToDo(str[2]);
					if (isDone) {
						toDo.markAsDone();
					}
					taskList.add(toDo);
					break;
				case "D":
					LocalDateTime startTime = LocalDateTime.parse(str[3]);
					DeadLine deadLine = new DeadLine(str[2], startTime);
					if (isDone) {
						deadLine.markAsDone();
					}
					taskList.add(deadLine);
					break;
				case "E":
					LocalDateTime start = LocalDateTime.parse(str[3]);
					LocalDateTime end = LocalDateTime.parse(str[4]);
					Event event = new Event(str[2], start, end);
					if (isDone) {
						event.markAsDone();
					}
					taskList.add(event);
					break;
				default:
					throw new IOException("read fail");
			}
		}
	}


	/**
	 * Deletes tasks from taskList.
	 * @param i Position of tasks to be deleted.
	 */
	public void deleteFromFile(int i) {
		taskList.remove(i - 1);
		try {
			FileWriter fw = new FileWriter(filePath, true);
			for (Task task: taskList) {
				fw.write(task.writeToFile());
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
			System.out.println("Delete fail " + e.getMessage());
		}
	}

	public void updateMarkInFile() {
		try {
			FileWriter fw = new FileWriter(filePath);
			StringBuilder br = new StringBuilder();
			for (Task task: taskList) {
				System.out.println(task.writeToFile());
				br.append(task.writeToFile()).append("\n");
			}
			fw.write(br.toString());
			fw.close();
		} catch (IOException e) {
			System.out.println("Updating fail " + e.getMessage());
		}
	}

}
