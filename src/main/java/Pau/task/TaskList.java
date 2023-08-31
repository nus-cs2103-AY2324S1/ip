package Pau.task;

import Pau.exception.DeadlineNoEndException;
import Pau.exception.NoDescException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int listSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Prints out a list of the tasks the user has.
     */
    public void checkList() {
        if (this.listSize() == 0) {
            int starEyesEmoji = 0x1F929;
            System.out.println("yay you don't have anything to do" + new String(Character.toChars(starEyesEmoji)));
        } else {
            System.out.println("sian you still have to complete these:");
            for (int i = 0; i < this.listSize(); i++) {
                Task curr = this.taskList.get(i);
                System.out.println((i + 1) + ". " + curr.toString());
            }
        }
    }

    /**
     * Marks a task as done.
     *
     * @param input The task the user wants to mark as done.
     */
    public void markTask(String input) {
        int starEyesEmoji = 0x1F929;
        System.out.println("good job, you've completed a task! You're so productive!" + new String(Character.toChars(starEyesEmoji)));
        String parts[] = input.split(" ");
        int taskNo = Integer.parseInt(parts[1]);
        Task checkedTask = this.taskList.get(taskNo - 1);
        checkedTask.markAsDone();
        System.out.println(checkedTask.toString());
    }

    /**
     * Unmarks a task.
     *
     * @param input The task the user wants to unmark.
     */
    public void unMarkTask(String input) {
        String parts[] = input.split(" ");
        int taskNo = Integer.parseInt(parts[1]);
        Task checkedTask = this.taskList.get(taskNo - 1);
        checkedTask.markAsUndone();
        System.out.println("why are you not going to " + checkedTask.description + "? remember to do it later!");
        System.out.println(checkedTask.toString());
    }

    /**
     * Deletes a Task from the list.
     *
     * @param input The task the user wants to delete.
     */
    public void deleteTask(String input) {
        String parts[] = input.split(" ");
        int taskNo = Integer.parseInt(parts[1]);
        Task checkedTask = this.taskList.get(taskNo - 1);
        this.taskList.remove(checkedTask);
        if (checkedTask.getStatusIcon().equals("X")) {
            System.out.println("good job! you're officially done with this:");
            System.out.println(checkedTask.toString());
        } else {
            System.out.println("not you running away from your responsibilities, i guess you don't have to do this now:");
            System.out.println(checkedTask.toString());
        }
        if (this.listSize() == 0) {
            System.out.println("THERES NOTHING LEFT TO DO!!!!");
        } else {
            System.out.println("but still sucks to be you, you still have " + this.listSize() + " tasks");
        }
    }

    /**
     * Adds a ToDo to the list.
     *
     * @param input The ToDo the user wants to add.
     */
    public void addsToDo(String input) {
        try {
            ToDo item = new ToDo(input.replace("todo ", ""));
            if (item.description.isEmpty()) {
                throw new NoDescException("here's literally how to create a todo: todo [task name]");
            }
            this.taskList.add(item);
            if (input.contains("todo")) {
                System.out.println("todo added: " + item.toString());
                System.out.println("You have this many stuff to complete: " + this.listSize());
            }
        } catch (NoDescException e) {
        }
    }

    /**
     * Adds a Deadline to the list.
     *
     * @param input The Deadline the user wants to add.
     */
    public void addDeadline(String input) {
        try {
            String parts[] = input.split("/by ");
            if (input.replace("deadline", "").isEmpty()) {
                throw new NoDescException("how am i suppose to know what is due...");
            }
            if (!input.contains("/by")) {
                throw new DeadlineNoEndException("here's literally how to create a deadline: deadline [task name] /by [date]");
            }
            Deadline item = new Deadline(parts[0].replace("deadline ", ""), parts[1]);
            this.taskList.add(item);
            if (input.contains("deadline")) {
                System.out.println("deadline added: " + item.toString());
                System.out.println("You have this many stuff to complete: " + this.listSize());
            }
        } catch (NoDescException e) {
        } catch (DeadlineNoEndException e) {
        }
    }

    /**
     * Adds an Event to the list.
     *
     * @param input The Event the user wants to add.
     */
    public void addEvent(String input) {
        try {
            String parts[] = input.split("/from");
            if (parts.length == 1) {
                throw new NoDescException("how am i suppose to know what is going on...");
            }
            String time[] = parts[1].split("/to");
            Event item = new Event(parts[0].replace("event ", ""), time[0], time[1]);
            this.taskList.add(item);
            if (input.contains("event")) {
                System.out.println("event added: " + item.toString());
                System.out.println("You have this many stuff to complete: " + this.listSize());
            }
        } catch (NoDescException e) {
        }
    }

    public void createTask(String taskDetails) {
        Scanner s = new Scanner(taskDetails).useDelimiter(" \\| ");
        String taskType = s.next();
        String status = s.next();
        String description = s.next();
        switch (taskType) {
            case "T":
                this.addsToDo(description);
                this.taskList.get(listSize() - 1).setStatus(status);
                break;
            case "D":
                String wholeDeadline = description + "/by " + s.next();
                this.addDeadline(wholeDeadline);
                this.taskList.get(listSize() - 1).setStatus(status);
                break;
            case "E":
                String wholeEvent = description + "/from" + s.next() + "/to" + s.next();
                this.addEvent(wholeEvent);
                this.taskList.get(listSize() - 1).setStatus(status);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + taskType);
        }
    }

}
