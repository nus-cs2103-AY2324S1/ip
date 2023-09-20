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
//		this.taskList = new ArrayList<Task>(100);
//		this.archiveList = new ArrayList<>(100);
	}

	/**
	 * Get Remaining tasks
	 *
	 * @param isMain Whether accessing Main or Archive file
	 * @return String representing remaining task
	 */
	public String getMainRemaining(boolean isMain) {
		return isMain ? Integer.toString(taskList.getRemaining()) : Integer.toString(archiveList.getRemaining());
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


	/**
	 * Converts each line into the format shown to reader.
	 *
	 * @param fileLine Each line in the file.
	 * @param isMain Whether file access is Main or Archive file.
	 * @throws IOException If unable to read task in file as Deadline[D], Event[E] or ToDos[T].
	 */
	public void readTaskMain(String fileLine, boolean isMain) throws IOException {
		String[] str = fileLine.split("\\|");
		Task temporarytTask;
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
					temporarytTask = toDo;
					break;
				case "D":
					LocalDateTime startTime = LocalDateTime.parse(str[3]);
					DeadLine deadLine = new DeadLine(str[2], startTime);
					if (isDone) {
						deadLine.markAsDone();
					}
					temporarytTask = deadLine;
					break;
				case "E":
					LocalDateTime start = LocalDateTime.parse(str[3]);
					LocalDateTime end = LocalDateTime.parse(str[4]);
					Event event = new Event(str[2], start, end);
					if (isDone) {
						event.markAsDone();
					}
					temporarytTask = event;
					break;
				default:
					throw new IOException("read fail");
			}
			if (isMain) {
				taskList.add(temporarytTask);
			} else {
				archiveList.add(temporarytTask);
			}
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
			FileWriter fw = new FileWriter(filePathReference, true);
			for (Task task: taskList.getTaskList()) {
				fw.write(task.writeToFile());
				fw.write("\n");
			}
			fw.close();
			return taskDeleted;
		} catch (IOException | PositionException e) {
			throw new DukeException(e.getMessage());
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
