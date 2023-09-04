package task;

import exception.KoraException;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks;


    public TaskList() {
        tasks = new ArrayList<>();

    }

    public void addTask(Task task) {
        tasks.add(task);
//        try {
//            saveTask(task);
//            System.out.println("ho");
//        } catch (IOException e) {
//            System.out.println("Couldn't add");
//        }

        //saveTask(task);

    }

    public void addNoSaveTask(Task task) {
        tasks.add(task);

    }

    public int getNextIndex() {
        return tasks.size() + 1;
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }

    public int getLength() {
        return tasks.size();
    }

    /*
    private void createFile(String filePath) throws KoraException {
        File f = new File("./data");
        if (!f.exists()) {
            f.mkdir();
        }

        try {
            File ff = new File(filePath);
            if (!ff.exists()) {
                ff.createNewFile();
            }
        } catch (IOException e){
            throw new KoraException("Unable to create file!");

        }
    }

    private void loadTask() throws KoraException {
        createFile(path);
        File f = new File(path);
        try {
            Scanner s = new Scanner(f);
            String[] array;
            while (s.hasNextLine()) {
                array = s.nextLine().split("/ ");
                tasks.add(checkAndAddTask(array));
            }
        } catch (IOException e) {
            throw new KoraException("Unable to scan!");
        }
    }


    private void saveTask(Task task) {
//        FileWriter fw = new FileWriter(path, true);
//        fw.write(task.saveFormat() + "\n");
//        fw.close();

        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(task.saveFormat() + "\n");
        } catch (IOException e) {
            new KoraException("Couldn't add!");
        }
    }

    private Task checkAndAddTask(String[] array) {
        Task currentTask;
        if (array[0].contains("E")) {
            currentTask = new Event(array[2], array[3], array[4]);
        } else if (array[0].contains("D")) {
            currentTask = new Deadline(array[2], array[3]);
        } else if (array[0].contains("T")) {
            currentTask = new ToDo(array[2]);
        } else {
            System.out.println("Task not valid!");
            return null;
        }

        if (array[1].equals("[X]")) {
            currentTask.setMarked();
        } else {
            currentTask.setUnmarked();
        }

        return currentTask;
    }

*/
//    private String saveTaskFormat(Task task) {
//        String output;
//        output = task.getTaskType() + "|" + task.showMarked() + "|" + task.getDetails();
//        return output;
//    }

    public String saveFormat() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + "\n" + tasks.get(i).saveFormat();
        }
        return output;
    }
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + String.format("%d", i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return output;
    }
}
