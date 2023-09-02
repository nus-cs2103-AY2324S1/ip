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

/**
 *  The Storage class is responsible for handling operations with saved task data in a text file for the Duke Program.
 *  It is capable of reading / loading and writing to the file.
 */
public class Storage {

	private File data;

	/**
	 * Constructs a new instance of the Ui class.
	 * Initialises an internal File object to read / write Task data.
	 * @param filePath The path location of the data text file.
	 */
	public Storage(String filePath) {
		this.data = new File(filePath);
	}

	/**
	 * Reads the data from the text file and converts them to their corresponding task objects.
	 * The tasks are added to an ArrayList to be returned.
	 *
	 * @return An ArrayList of Task objects which were originally stored in the text file.
	 * @throws DukeException If the file specified cannot be found.
	 */
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

	/**
	 * Takes a FunnyList of tasks and writes each task to the specified data source.
	 *
	 * @param taskList The FunnyList containing the tasks to be written.
	 * @throws DukeException If there is an issue writing the data.
	 */
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
