package tasket.storage;

import tasket.exception.TasketException;
import tasket.data.TaskList;
import tasket.parser.Parser;
import tasket.task.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    private final File dataFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        dataFile = new File(filePath);
    }

    public ArrayList<Task> load() throws TasketException {
        ArrayList<Task> taskList = new ArrayList<>();
        validateSaveFile();
        boolean isFileCorrupted = false;
        Scanner sc = null;
        try {
            sc = new Scanner(dataFile);
            while (sc.hasNext()) {
                try {
                    taskList.add(Parser.parseSaveString(sc.nextLine()));
                } catch (TasketException e) {
                    isFileCorrupted = true;
                }
            }

        } catch (FileNotFoundException e) {
            throw new TasketException("File not found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        if (isFileCorrupted) {
            rewriteSaveFile(new TaskList(taskList));
        }

        return taskList;
    }

    public void append(String taskSave) throws TasketException {
        FileWriter fw = null;

        try {
            try {
                fw = new FileWriter(dataFile, true);
                fw.write(taskSave + System.lineSeparator());
            } catch (IOException e) {
                throw new TasketException("Error occurred when saving the task. The task will not be saved");
            } finally {
                if (fw != null) {
                    fw.close();
                }
            }
        } catch (IOException e) {
            throw new TasketException("Error occurred while saving the file");
        }
    }

    public void rewriteSaveFile(TaskList taskList) throws TasketException {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            try {
                fw = new FileWriter(dataFile, false);
                bw = new BufferedWriter(fw);

                for (int i = 0; i < taskList.size(); i++) {
                    bw.write(taskList.getTaskSaveString(i) + System.lineSeparator());
                }

            } catch (IOException e) {
                throw new TasketException("Error occurred while saving the file");
            } finally {
                if (fw != null && bw != null) {
                    bw.close();
                    fw.close();
                }
            }

        } catch (IOException e) {
            throw new TasketException("Error occurred while saving the file");
        }
    }

    private void validateSaveFile() throws TasketException {
        try {
            Files.createDirectories(Path.of(filePath.substring(0, filePath.lastIndexOf("/"))));
            if (!dataFile.exists()) {
                Files.createFile(Path.of(filePath));
            }
        } catch (IOException e) {
            throw new TasketException("Unable to retrieve save file. This session will not be saved");
        }
    }
}
