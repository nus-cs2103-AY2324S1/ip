package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading tasks to/from a file.
 */
public class Storage {
    private final String saveLocation;
    private final Ui ui;

    public Storage(String saveLocation, Ui ui) {
        this.saveLocation = saveLocation;
        this.ui = ui;
    }

    /**
     * Saves the tasks from the provided TaskList to the specified file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     */
    public void saveTasks(TaskList taskList) {
        File saveFile = new File(saveLocation);
        saveFile.mkdirs();
        if (saveFile.exists()) {
            boolean deleteFileSuccess = saveFile.delete();
            if (!deleteFileSuccess) {
                System.out.println("Failed to delete previous save file!");
            }
        }
        try {
            boolean createNewFileSuccess = saveFile.createNewFile();
            if (!createNewFileSuccess) {
                System.out.println("Failed to create save file!");
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(saveLocation, true));
                for (Task task : taskList.getTasks()) {
                    writer.append(task.saveString()).append("\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            ui.showSaveTasksError(e);
        }
    }

    /**
     * Loads saved tasks from the file and returns them as a list.
     *
     * @return A List of loaded Task objects.
     * @throws DukeLoadTasksException If an error occurs while loading tasks.
     * @throws DukeNoExistingTasksException If the save file does not exist.
     */
    public List<Task> loadSavedTasks() throws DukeLoadTasksException {
        File saveFile = new File(saveLocation);
        if (saveFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(saveLocation));
                String line;
                List<Task> tempTaskList = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    String[] datum = line.split("\\|");
                    try {
                        switch (datum[0]) {
                            case "T":
                                tempTaskList.add(new Todo(datum[2]));
                                break;
                            case "D":
                                try {
                                    tempTaskList.add(new Deadline(datum[2], datum[3]));
                                } catch (DukeInvalidDateException e) {
                                    break;
                                }
                                break;
                            case "E":
                                try {
                                    tempTaskList.add(new Event(datum[2], datum[3], datum[4]));
                                } catch (DukeInvalidDateException e) {
                                    break;
                                }
                                break;
                            default:
                                System.out.println("Illegal task type, skipping");
                                break;
                        }
                        if (datum[1].equals("X")) {
                            tempTaskList.get(tempTaskList.size() - 1).setIsCompleted(true);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Illegal line in save file, skipping");
                    }
                }
                reader.close();
                return tempTaskList;
            } catch (IOException e) {
                throw new DukeLoadTasksException("IOException occurred" + e.getMessage());
            }
        } else {
            throw new DukeNoExistingTasksException();
        }
    }
}
