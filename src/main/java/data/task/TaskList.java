package data.task;

import data.exception.DukeException;
import parser.Parser.Command;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> list;
    private File file;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(File file) {
        this.list = new ArrayList<Task>();
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
                this.list.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.println(e);
        }
        this.file = file;
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

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " task(s) in the list.");
    }

    public void addTask(Command command, String input) throws DukeException {
        Task taskToAdd = null;
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
        }
        this.list.add(taskToAdd);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskToAdd);
        System.out.println("Now you have " + this.list.size() + " task(s) in the list.");
    }

    public void setTaskComplete(int i) {
        Task task = this.list.get(i);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void setTaskComplete(String input) {
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
        Task task = this.list.get(taskNo);
        task.setDone();
        System.out.println("OK, I've marked this task as done:");
        System.out.println(task);
    }

    public void setTaskIncomplete(int i) {
        Task task = this.list.get(i);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void setTaskIncomplete(String input) {
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
        Task task = this.list.get(taskNo);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public Task getTask(int i) {
        return this.list.get(i);
    }

    public void deleteTask(int i) {
        Task task = this.list.get(i);
        this.list.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " task(s) in the list.");
    }

    public void deleteTask(String input) {
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
        Task task = this.list.get(taskNo);
        this.list.remove(taskNo);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " task(s) in the list.");
    }

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    public void close() {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task task : list) {
                writer.write(task.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving your tasks.");
            e.printStackTrace();
        }
    }
}
