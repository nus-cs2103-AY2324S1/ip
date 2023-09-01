package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.list.FunnyList;
import duke.exception.DukeException;

public class Storage {

	private File data;
	public Storage(String filePath) {
		this.data = new File(filePath);
	}

	public ArrayList<Task> load() throws DukeException {
		try {
			Scanner s = new Scanner(this.data);
			ArrayList<Task> taskList = new ArrayList<>();
			while (s.hasNext()) {
				String[] row  = s.nextLine().split("\\|");
				switch (row.length) {
					case 3:
						taskList.add(new ToDo(row[2], row[1].equals("1")));
						break;
					case 4:
						taskList.add(new Deadline(row[2], row[1].equals("1"), row[3]));
						break;
					case 5:
						taskList.add(new Event(row[2], row[1].equals("1"), row[3], row[4]));
						break;
					default:
						break;
				}
			}
			return taskList;
		} catch (FileNotFoundException e) {
			throw new DukeException("No tasklist record was found");
		}
	}

	public void write(FunnyList taskList) throws DukeException {
		try {
			FileWriter fw = new FileWriter(this.data);
			for (int i = 0; i < taskList.size(); i++) {
				taskList.get(i).writeToFile(fw);
				fw.write(System.lineSeparator());
			}
			fw.close();
		} catch (IOException e) {
			throw new DukeException("Something went wrong: " + e.getMessage());
		}
	}
}
