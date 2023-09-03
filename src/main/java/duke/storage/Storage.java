package duke.storage;
import duke.task.Task;
import duke.task.ToDos;
import duke.task.DeadLine;
import duke.task.Event;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
	private String filePath;
	private ArrayList<Task> taskList;
	public Storage(String filePath) {
		this.filePath = filePath;
		this.taskList = new ArrayList<Task>(100);
	}


	// take in new one else double write
	public void writeFile(Task task) throws IOException {

		try {
			FileWriter fw = new FileWriter(filePath, true);
				fw.write(task.writeToFile());
				fw.write("\n");
			fw.close();
		} catch (IOException e) {
			throw new IOException("write fail");
		}

	}


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
	public void readTask(String fileLine) throws IOException {
		String[] str = fileLine.split("\\|");
		for (int i  = 0; i < str.length; i++) {
			String s = str[i].trim();
			str[i] = s;
		}
		StringBuilder br = new StringBuilder();
		String taskType = str[0];
		String isDone = str[1];
		switch(taskType) {
			case "T":
				taskList.add(new ToDos(str[2]));
				break;
			case "D":
				LocalDateTime startTime = LocalDateTime.parse(str[3]);
				taskList.add(new DeadLine(str[2], startTime));
				// t.add(new Deadline(str[2], str[3]));
				break;
			case "E":
				LocalDateTime start = LocalDateTime.parse(str[3]);
				LocalDateTime end = LocalDateTime.parse(str[4]);
				taskList.add(new Event(str[2], start, end));
				break;
			default:
				throw new IOException("read fail");
		}
	}
	public void deleteTask(int i) {
		taskList.remove(i);
	}

}
