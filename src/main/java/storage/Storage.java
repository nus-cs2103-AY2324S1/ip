package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import exceptions.DukeInvalidDateException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import ui.Ui;

public class Storage {
    private List<Task> tasks;

    public Storage() {
        this.tasks = new ArrayList<>();

        try {
            File dataFolder = new File("./data");

            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File dataFile = new File("./data/data.txt");

            if (!dataFile.exists()) {
                dataFolder.createNewFile();
            } else {
                this.readTasksFromData();
            }
        } catch (IOException error) {
            Ui.printLines("Something went wrong when loading tasks :(");
        }
    }

    private void readTasksFromData() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File dataFile = new File("./data/data.txt");
        Scanner reader = new Scanner(dataFile);
        while (reader.hasNextLine()) {
            String[] currentTask = reader.nextLine().split(Pattern.quote("|"), 2);
            String taskType = currentTask[0];
            String[] taskInfo = currentTask[1].split(Pattern.quote("|"), 0);

            Task task;

            try {
                switch (taskType) {
                    case "T":
                        task = new ToDo(taskInfo[1]);
                        break;
                    case "D":
                        task = new Deadline(taskInfo[1], taskInfo[2]);
                        break;
                    case "E":
                        task = new Event(taskInfo[1], taskInfo[2], taskInfo[3]);
                        break;
                    default:
                        continue;
                }
            } catch (DukeInvalidDateException error) {
                Ui.printLines("Something went wrong when loading tasks :(");
                break;
            }


            if (taskInfo[0].equals("1")) {
                task.markAsDone();
            } else {
                task.markAsNotDone();
            }

            tasks.add(task);
        }

        this.tasks = tasks;
        reader.close();
    }


    private void writeTasks() {
        try {
            FileWriter dataWriter = new FileWriter("./data/data.txt");
            for (Task task : this.tasks) {
                dataWriter.write(task.toDataRepresentation() + "\n");
            }
            dataWriter.close();
        } catch (IOException error) {
            Ui.printLines("Something went wrong when updating tasks :(");
        }
    }

    public void addTask (Task newTask) {
        this.tasks.add(newTask);
        this.writeTasks();

        Ui.printLines("Got it. I've added this task:",
                "\t " + newTask,
                "Now you have " + this.tasks.size() + " tasks in your list. Good luck!");
    }

    public boolean checkIndexValidity(int taskIndex) {
        return taskIndex > 0 && taskIndex <= this.tasks.size();
    }

    public int getTasksSize() {
        return this.tasks.size();
    }

    public void markTask (int taskIndex) {
        Task task = this.tasks.get(taskIndex - 1);
        task.markAsDone();
        this.writeTasks();

        Ui.printLines("Nice job! I've marked this task as done:",
                "\t " + task);
    }

    public void unmarkTask (int taskIndex) {
        Task task = this.tasks.get(taskIndex - 1);
        task.markAsNotDone();
        this.writeTasks();

        Ui.printLines("What happened? I've marked this task as not done yet:",
                "\t " + task);
    }

    public void deleteTask (int taskIndex) {
        Task deletedTask = this.tasks.get(taskIndex - 1);

        this.tasks.remove(taskIndex - 1);
        this.writeTasks();

        Ui.printLines("Noted. I've removed this task:",
                deletedTask.toString(),
                "Now you have " + this.tasks.size() + " tasks in your list. Good luck!");
    }

    @Override
    public String toString() {
        String tasksList;

        if (this.getTasksSize() > 0) {
            tasksList = "Here are the tasks in your list:\n";

            for (int i = 0; i < this.tasks.size(); i++) {
                tasksList += ("\t " + (i + 1) + "." + this.tasks.get(i) + "\n");
            }

            tasksList += ("\t Keep up the good work!");
        } else {
            tasksList = "You currently have no tasks :)";
        }

        return tasksList;
    }
}
