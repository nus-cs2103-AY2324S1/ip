package chatty.utils;

import chatty.exception.ChattyException;
import chatty.exception.InvalidTaskNumberException;
import chatty.task.Deadline;
import chatty.task.Event;
import chatty.task.TaskList;
import chatty.task.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private File file;

    public Storage(String filePath)  {
        this.filePath = filePath;
        this.file = new File(this.filePath);
        this.file.getParentFile().mkdirs();

        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    public void saveTask(TaskList taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            StringBuilder tasks = new StringBuilder();

            for (int i = 0; i < taskList.listSize(); i++) {
                tasks.append(taskList.showTask(i + 1)).append('\n');
            }
            writer.write(tasks.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured while trying to save tasks: " + e.getMessage());
        }
    }

    public void loadTask(TaskList taskList) throws IOException, ChattyException {
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                String task = scanner.nextLine();
                char taskType = task.charAt(1);

                switch (taskType) {
                    case 'T':
                        loadTodoTask(taskList, task);
                        break;
                    case 'D':
                        loadDeadlineTask(taskList, task);
                        break;
                    case 'E':
                        loadEventTask(taskList, task);
                        break;
                }
            }
        } catch (IOException e) {
            throw new ChattyException("Unable to load chatty.task");
        }
    }

    private void loadTodoTask(TaskList taskList, String task) throws InvalidTaskNumberException {
        String taskDescription = task.substring(7);
        boolean isDone = task.charAt(4) == 'X';
        taskList.addTask(new ToDo(taskDescription));
        if (isDone) {
            taskList.markTask(taskList.listSize(), true);
        }
    }

    private void loadDeadlineTask(TaskList taskList, String task) throws InvalidTaskNumberException {
        int colonIndex = task.indexOf(":");
        String taskDescription = task.substring(7, colonIndex - 4);
        String deadline = task.substring(colonIndex + 4, task.length() - 1);
        boolean isDone = task.charAt(4) == 'X';
        taskList.addTask(new Deadline(taskDescription, deadline));
        if (isDone) {
            taskList.markTask(taskList.listSize(), true);
        }
    }

    private void loadEventTask(TaskList taskList, String task) throws InvalidTaskNumberException {
        int colon1Index = task.indexOf(':');
        int colon2Index = task.indexOf(':', colon1Index + 1);

        String taskDescription = task.substring(7, colon1Index - 6);
        String startDate = task.substring(colon1Index + 2, colon2Index - 3);
        String endDate = task.substring(colon2Index + 2, task.length() - 1);

        boolean isDone = task.charAt(4) == 'X';
        taskList.addTask(new Event(taskDescription, startDate, endDate));
        if (isDone) {
            taskList.markTask(taskList.listSize(), true);
        }
    }
}
