package duke.utilities;

import java.util.Scanner;

import duke.tasks.Task;
import duke.utilities.TaskList;

/**
 * UI class that prints all messages that is shown to the user.
 */
public class Ui {
    protected static Scanner sc;
    protected String br = "____________________________________________________________\n";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the message hello when program starts.
     */
    public String printHello() {
        String output = br
                        + "My name is Jose Mourinho, I am the special one.\n"
                        + "I am a task planning bot that will record your tasks.\n"
                        + "If you require help, type \"help\"\n"
                        + br;
        System.out.println(output);
        return output;
    }

    /**
     * Prints the message goodbye when program ends.
     */
    public String printGoodBye() {
        String[] famousQuotes = { "Football is a game about feelings and intelligence.",
            "I enjoy the work, I enjoy every minute of my professional life.",
            "I hate to speak about individuals. Players don't win you trophies, teams win trophies, "
                    + "squads win trophies.",
            "Money does not guarantee success",
            "I need my time to be lonely",
            "I am Jose Mourinho and I don't change. I arrive with all my qualities and my defects.",
            "Look at the way teams play against Arsenal. They don't believe they can win. They don't believe.",
            "Well, uh, I don't know another way than to work at the top level, which is what I do every day.",
            "The only thing I would like is to have more control of the game in terms of possession.",
            "But I think it's more normal for my team to have no success than it is to "
                    + "win two consecutive European cups.",
            "So I know all about the ups and downs of football, I know that one day I will be sacked.",
            "Please do not call me arrogant, but I am European champion and I think I am the special one.",
            "Why have Chelsea suffered so much since I left? Because I left",
            "Young players are like melons. Only when you open and taste the melon are you "
                    + "100% sure that the melon is good.",
            "If the club decide to sack me because of bad results that’s part of the game. "
                    + "If it happens I will be a millionaire "
                    + "and get another club a couple of months later",
            "I have top players and, I’m sorry, we have a top manager."};
        String output = br
                + famousQuotes[(int) Math.floor(Math.random() * famousQuotes.length)]
                + "\n"
                + br;
        System.out.println(output);
        return output;
    }

    /**
     * Prints the message of the task to be deleted.
     *
     * @param taskList The tasklist that the task will be added to.
     * @param index The index of task to be deleted.
     */
    public String printDeleteTask(TaskList taskList, int index) {
        String output = "There is no excuse for you to be lazy. "
                + "You should work hard and do your tasks:" + taskList.getTask(index);
        System.out.println(output);
        return output;
    }

    /**
     * Prints the message of the task to be added.
     *
     * @param taskList The tasklist that the task will be added to.
     * @param task The task to be added.
     */
    public String printAddTask(TaskList taskList, Task task) {
        String output = br + "You must train hard and complete your task:\n" + task
                + "\nYou now have " + taskList.size() + " tasks to complete.\n";
        System.out.println(output);
        return output;
    }

    /**
     * Prints the message of marking a task as completed.
     *
     * @param taskList The list of tasks.
     * @param index The index of task to be marked.
     */
    public String printMarkedDone(TaskList taskList, int index) {
        String output = "I have noticed you have been working hard,"
                + "Good job on completing your task:\n" + taskList.getTask(index).toString();
        System.out.println(output);
        return output;
    }

    /**
     * Prints the message of marking a task as incomplete.
     *
     * @param taskList The list of tasks.
     * @param index The index of task to be marked.
     */
    public String printMarkedUnDone(TaskList taskList, int index) {
        String output = "You want to skip training? Go and run 10 rounds around the field:\n"
                + taskList.getTask(index).toString();
        System.out.println(output);
        return output;
    }

    /**
     * Prints out the list of tasks.
     *
     * @param taskList The taskList of tasks.
     */
    public String printList(TaskList taskList) {
        if (taskList.getTaskList().isEmpty()) {
            String output = br + "Hard work beats talent. Your list is empty. You should train more.\n" + br;
            System.out.println(output);
            return output;
        }

        String output = br + "If you want to be the best in the world, work hard and follow me:\n";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("%d. %s \n", i + 1, taskList.getTask(i).toString());
        }
        output += br;
        System.out.println(output);
        return output;
    }

    /**
     * Prints out error received.
     *
     * @param message The error received from try catch.
     */
    public String printError(String message) {
        String output = br + message + "\n" + br;
        System.out.println(output);
        return output;
    }

    /**
     * Prints special error when time to parse is invalid.
     */
    public String printInvalidTimeError() {
        String output = br + "\n Your timing is wrong. This is unacceptable \n" + br;
        System.out.println(output);
        return output;
    }

    /**
     * Prints the tasks that match the input.
     *
     * @param tasks The taskList that contains all tasks.
     * @param input The input keyword to filter with.
     */
    public String findFilteredTasks(TaskList tasks, String input) {
        String output = br;
        TaskList filteredList = tasks.filter(input);
        if (filteredList.size() == 0) {
            output += "There are no tasks with that word.\n" + br;
            System.out.println(output);
            return output;
        }
        output += "These are your matching tasks:\n";
        for (int i = 0; i < filteredList.size(); i++) {
            output += String.format("%d. %s\n", i + 1, filteredList.getTask(i).toString());
        }
        output += br;
        System.out.println(output);
        return output;
    }
}
