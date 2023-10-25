package duke.storage;
import duke.exception.DukeException;
import duke.exception.PositionException;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.DeadLine;
import duke.task.Event;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents te computer storage system.
 * Able to write Task to storage text file through writeFile method.
 */
public class Storage {
	private String filePathMain;
	private String filePathArchive;

	private TaskList taskList;
	private TaskList archiveList;

	/**
	 * Represent file storage for users tasks
	 * @param filePathMain Main file path, where task are stored
	 * @param filePathArchive Archive file path, where task are archived into
	 */
	public Storage(String filePathMain, String filePathArchive) {
		this.filePathMain = filePathMain;
		this.filePathArchive = filePathArchive;
		this.taskList = new TaskList();
		this.archiveList = new TaskList();
	}

	/**
	 * Get Remaining tasks
	 *
	 * @param isMain Whether accessing Main or Archive file
	 * @return String representing remaining task
	 */
	public String getMainRemaining(boolean isMain) throws DukeException{
		try {
			return isMain ? Integer.toString(taskList.getRemaining()) : Integer.toString(archiveList.getRemaining());
		} catch (IndexOutOfBoundsException e) {
			throw new DukeException("Invalid index");
		}
	}
	public TaskList getArchiveList() {
		return this.archiveList;
	}
	/**
	 * Initialise new fileWriter without append to delete its contents
	 */
	public void clearFile() {
		taskList.clearTasks();
		try {
			FileWriter fw = new FileWriter(filePathMain);
			fw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	/**
	 * Writes Task into text file.
	 * Creates text file if it does not exist.
	 *
	 * @param task Thing to be done.
	 * @param isMain Whether file access is Main or Archive file.
	 */
	public void addToFileMain(Task task, TaskList taskList, boolean isMain) {
		String filePathReference = isMain ? filePathMain : filePathArchive;
		if (isMain) {
			taskList.add(task);
		} else {
			archiveList.add(task);
		}
		try {
			FileWriter fw = new FileWriter(filePathReference, true);
			fw.write(task.writeToFile());
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			System.out.println("write fail");
		}

	}

	/**
	 * Reads stored text file line by line.
	 * Initialises both text files within storage.
	 *
	 * @return ArrayList of Task.
	 * @throws IOException If unable to read lines in text file.
	 */
	public TaskList loadFiles() throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(filePathMain))) {
			StringBuilder br = new StringBuilder();
			String fileLine;
			while ((fileLine = in.readLine()) != null) {
				br.append(fileLine);
				br.append("\n");
				readTaskMain(fileLine, true);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		try (BufferedReader in = new BufferedReader(new FileReader(filePathArchive))) {
			StringBuilder br = new StringBuilder();
			String fileLine;
			while ((fileLine = in.readLine()) != null) {
				// append raw unformatted version
				br.append(fileLine);
				br.append("\n");
				readTaskMain(fileLine, false);
				// edit this as well
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return taskList;
	}

	public void removeWhiteSpace(String[] stringArray) {
		for (int i  = 0; i < stringArray.length; i++) {
			String s = stringArray[i].trim();
			stringArray[i] = s;
		}
	}

	/**
	 * Converts each line into the format shown to reader.
	 *
	 * @param fileLine Each line in the file.
	 * @param isMain Whether file access is Main or Archive file.
	 * @throws IOException If unable to read task in file as Deadline[D], Event[E] or ToDos[T].
	 */
	public void readTaskMain(String fileLine, boolean isMain) throws IOException {
		String[] str = fileLine.split("\\|");
		removeWhiteSpace(str);
		boolean isValid = str.length == 3 || str.length == 4 || str.length == 5;
		if (!isValid){
			throw new IOException("Can't read from file");
		}
		Task taskReference;
		String taskDescription = str[2];
		StringBuilder br = new StringBuilder();
		String taskType = str[0];
		LocalDateTime startTime;
		LocalDateTime endTime;

		boolean isDone = Integer.parseInt(str[1]) == 1;
		switch(taskType) {
			case "T":
				taskReference = new ToDo(taskDescription);
				break;
			case "D":
				try {
					startTime = LocalDateTime.parse(str[3]);
				} catch (DateTimeParseException e) {
					throw new IOException("Cannot parse file");
				}
				taskReference = new DeadLine(taskDescription, startTime);
				break;
			case "E":
				try {
					startTime = LocalDateTime.parse(str[3]);
					endTime = LocalDateTime.parse(str[4]);
				} catch (DateTimeParseException e) {
					throw new IOException("Cannot parse file");
				}
				taskReference = new Event(taskDescription, startTime, endTime);
				break;
			default:
				throw new IOException("read fail");
		}

		if (isDone) {
			taskReference.markAsDone();
		}
		if (isMain) {
			taskList.add(taskReference);
		} else {
			archiveList.add(taskReference);
		}
	}


	/**
	 * Deletes tasks from taskList.
	 *
	 * @param i Position of tasks to be deleted.
	 * @param isMain Whether file access is Main or Archive file.
	 */
	public Task deleteFromMainFile(int i, boolean isMain) throws DukeException {
		String filePathReference = isMain ? filePathMain : filePathArchive;
		TaskList taskListReference = isMain ? taskList : archiveList;

		try {
			Task taskDeleted = taskListReference.remove(i);
			// don't want to append
			FileWriter fw = new FileWriter(filePathReference);
			for (Task task: taskListReference.getTaskList()) {
				fw.write(task.writeToFile());
				fw.write("\n");
			}
			fw.close();
			return taskDeleted;
		} catch (IOException | IndexOutOfBoundsException | PositionException e) {
			throw new DukeException("Invalid position");
		}
	}

	public void updateMainStorage(boolean isMain) {
		String filePathReference = isMain ? filePathMain : filePathArchive;
		TaskList taskListReference = isMain ? taskList : archiveList;
		try {
			FileWriter fw = new FileWriter(filePathReference);
			StringBuilder br = new StringBuilder();
			for (Task task: taskListReference.getTaskList()) {
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
