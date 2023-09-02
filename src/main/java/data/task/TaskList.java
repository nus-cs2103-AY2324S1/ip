package data.task;

import data.exception.DukeException;
import parser.Parser.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasks;
    private File file;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(File file) {
        this.tasks = new ArrayList<Task>();
        this.file = file;
    }

    public void open() {
        this.tasks = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String storedTask = scanner.nextLine();
                String[] taskArray = storedTask.split(",");
                Task task;
                if (taskArray[0].startsWith("Todo")) {
                    task = new ToDoTask(taskArray[2]);
                } else if (taskArray[0].startsWith("Deadline")) {
                    task = new DeadlineTask(taskArray[2], taskArray[3]);
                } else {
                    task = new EventTask(taskArray[2], taskArray[3], taskArray[4]);
                }
                if ((taskArray[1]).equals("1")) {
                    task.setDone();
                }
                this.tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when trying to find the file.");
            e.getMessage();
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public void executeCommand(Command command, String input) throws DukeException {
        switch (command) {
        case MARK:
            this.setTaskComplete(input);
            break;
        case UNMARK:
            this.setTaskIncomplete(input);
            break;
        case DELETE:
            this.deleteTask(input);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            this.addTask(command, input);
            break;
        }
    }

    public void addTask(Task task, boolean isMuted) {
        this.list.add(task);
        if (!isMuted) {
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + this.list.size() + " task(s) in the list.");
        }
    }

    public void addTask(Command command, String input) throws DukeException {
        Task taskToAdd;
        String[] inputArr;

        switch (command) {
        case TODO:
            input = input.substring(5);
            taskToAdd = new ToDoTask(input);
            break;
        case DEADLINE:
            input = input.substring(9);
            inputArr = input.split(" /by ");
            taskToAdd = new DeadlineTask(inputArr[0], inputArr[1]);
            break;
        case EVENT:
            input = input.substring(6);
            inputArr = input.split(" /from ");
            String description = inputArr[0];
            inputArr = inputArr[1].split(" /to ");
            taskToAdd = new EventTask(description, inputArr[0], inputArr[1]);
            break;
        default:
            throw new DukeException("No such command found.");
        }
        this.tasks.add(taskToAdd);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskToAdd);
        System.out.println("Now you have " + this.tasks.size() + " task(s) in the list.");
    }

    public void setTaskComplete(int i) {
        Task task = this.tasks.get(i);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void setTaskComplete(String input) {
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
        Task task = this.tasks.get(taskNo);
        task.setDone();
        System.out.println("OK, I've marked this task as done:");
        System.out.println(task);
    }

    public void setTaskIncomplete(int i) {
        Task task = this.tasks.get(i);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void setTaskIncomplete(String input) {
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
        Task task = this.tasks.get(taskNo);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public void deleteTask(int i) {
        Task task = this.tasks.get(i);
        this.tasks.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " task(s) in the list.");
    }

    public void deleteTask(String input) {
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
        Task task = this.tasks.get(taskNo);
        this.tasks.remove(taskNo);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " task(s) in the list.");
    }

    @Override
    public String toString() {
        if (this.list.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(i + 1 + "." + list.get(i) + "\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public void close() {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task task : tasks) {
                writer.write(task.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving your tasks.");
            e.printStackTrace();
        }
    }

    public int getSize() {
        return this.list.size();
    }

    public String find(String input) {
        String toFind = input.substring(5);
        TaskList tasksFound = new TaskList();
        for (Task task : this.list) {
            if (task.toString().contains(toFind)) {
                tasksFound.addTask(task, true);
            }
        }
        if (tasksFound.getSize() == 0) {
            return "No tasks found matching that description.";
        }
        return tasksFound.toString();
    }
}
