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
	private String filePathMain;
	private String filePathArchive;

	private ArrayList<Task> taskList;
	private ArrayList<Task> archiveList;
	public Storage(String filePathMain, String filePathArchive) {
		this.filePathMain = filePathMain;
		this.filePathArchive = filePathArchive;
		this.taskList = new ArrayList<Task>(100);
		this.archiveList = new ArrayList<>(100);
	}

	public String getMainRemaining(boolean isMain) {
		return isMain ? Integer.toString(taskList.size() - 1) : Integer.toString(archiveList.size() - 1);
	}
	public ArrayList<Task> getArchiveList() {
		return this.archiveList;
	}
	/**
	 * Initialise new filewriter without append to delete its contents
	 */
	public void clearFile() {
		this.taskList = new ArrayList<>(100);
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
	 * @param task   Thing to be done.
	 * @param isMain
	 * @throws IOException If unable to write file.
	 */
	public void addToFileMain(Task task, boolean isMain) {
		String filePathReference = isMain ? filePathMain : filePathArchive;
		if (isMain) {
			taskList.add(task);
		} else {
			archiveList.add(task);
		}
		try {
			// appends just that one task thats why true for append
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
	 * Initialises both text files within storage!!!
	 * @return ArrayList of Task.
	 * @throws IOException If unable to read lines in text file.
	 */
	public ArrayList<Task> loadFiles() throws IOException {
//		String filePathReference = isMain ? filePathMain : filePathArchive;
		try (BufferedReader in = new BufferedReader(new FileReader(filePathMain))) {
			StringBuilder br = new StringBuilder();
			String fileLine;
			while ((fileLine = in.readLine()) != null) {
				// append raw unformatted version
				br.append(fileLine);
				br.append("\n");
				readTaskMain(fileLine, true);
				// edit this as well
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
		// for the sake of returning it for Duke main function reference
		return taskList;
	}

	// the file stored needs to be reformatted

	/**
	 * Converts each line into the format shown to reader.
	 *
	 * @param fileLine Each line in the file.
	 * @param isMain
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
//					taskList.add(toDo);
					break;
				case "D":
					LocalDateTime startTime = LocalDateTime.parse(str[3]);
					DeadLine deadLine = new DeadLine(str[2], startTime);
					if (isDone) {
						deadLine.markAsDone();
					}
					temporarytTask = deadLine;
//					taskList.add(deadLine);
					break;
				case "E":
					LocalDateTime start = LocalDateTime.parse(str[3]);
					LocalDateTime end = LocalDateTime.parse(str[4]);
					Event event = new Event(str[2], start, end);
					if (isDone) {
						event.markAsDone();
					}
					temporarytTask = event;
//					taskList.add(event);
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
	 * @param i      Position of tasks to be deleted.
	 * @param isMain
	 */
	public Task deleteFromMainFile(int i, boolean isMain) {
		String filePathReference = isMain ? filePathMain : filePathArchive;
		ArrayList<Task> taskListReference = isMain ? taskList : archiveList;
		//check
		Task taskDeleted = taskListReference.remove(i);
		try {
			FileWriter fw = new FileWriter(filePathReference, true);
			for (Task task: taskList) {
				fw.write(task.writeToFile());
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
			System.out.println("Delete fail " + e.getMessage());
		}
		return taskDeleted;
	}

	public void updateMainStorage(boolean isMain) {
		String filePathReference = isMain ? filePathMain : filePathArchive;
		ArrayList<Task> taskListReference = isMain ? taskList : archiveList;
		try {
			FileWriter fw = new FileWriter(filePathReference);
			StringBuilder br = new StringBuilder();
			for (Task task: taskListReference) {
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
