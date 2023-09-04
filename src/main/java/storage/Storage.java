package storage;

import exception.KoraException;
import task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String DEFAULT_PATH = "./data/savedtask.txt";

    private String path;
    public Storage(String filePath) {
        path = filePath;
    }

    public void createFile() throws KoraException {
        File f = new File("./data");
        if (!f.exists()) {
            f.mkdir();
        }

        try {
            File ff = new File(path);
            if (!ff.exists()) {
                ff.createNewFile();
            }
        } catch (IOException e){
            throw new KoraException("Unable to create file!");

        }
    }

    public void loadTask(TaskList taskList) throws KoraException {
        createFile();
        File f = new File(path);
        try {
            Scanner s = new Scanner(f);
            String[] array;
            while (s.hasNextLine()) {
                array = s.nextLine().split("/ ");
                taskList.addNoSaveTask(checkTask(array));
            }
        } catch (IOException e) {
            throw new KoraException("Unable to scan!");
        }
    }


    public void saveTask(TaskList taskList) throws KoraException {
//        FileWriter fw = new FileWriter(path, true);
//        fw.write(task.saveFormat() + "\n");
//        fw.close();

        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(taskList.saveFormat());
        } catch (IOException e) {
            throw new KoraException("Couldn't add!");
        }
    }

    public Task checkTask(String[] array) throws KoraException {
        Task currentTask;
        if (array[0].contains("E")) {
            currentTask = new Event(array[2], array[3], array[4]);
        } else if (array[0].contains("D")) {
            currentTask = new Deadline(array[2], array[3]);
        } else if (array[0].contains("T")) {
            currentTask = new ToDo(array[2]);
        } else {
            throw new KoraException("Task not valid!");
        }

        if (array[1].equals("[X]")) {
            currentTask.setMarked();
        } else {
            currentTask.setUnmarked();
        }

        return currentTask;
    }
}
