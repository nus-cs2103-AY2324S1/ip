package com.mimi.main;

import com.mimi.tasks.Task;

/**
 * A class that is responsible for the user interface.
 * @author Yuheng
 */
public class Ui {
    private static final String LINE = "_________________________________________________\n";

    /**
     * Displays a message when there is a loading error.
     */
    public void showLoadingError() {
        System.out.println("Failed to load... Please try again!\n" + LINE);
    }

    /**
     * Displays a welcome message when the program starts up.
     */
    public void welcomeMessage() {
        System.out.println(
                        LINE
                        + "Hello! I'm Mimi.\n"
                        + "What can I do for you?\n"
                        + LINE
        );
    }

    /**
     * Displays a message when the user exits.
     */
    public void exitMessage() {
        System.out.println(
                "Bye. Hope to see you again soon!\n" + LINE
        );
    }

    /**
     * Displays a message when the task given is invalid.
     */
    public void invalidTaskMessage() {
        System.out.println("☹ OOPS!!! I'm sorry but I don't know what that means :-(\n" + LINE);
    }

    /**
     * Displays a simple line to distinguish the next command.
     */
    public void separator() {
        System.out.println(LINE);
    }

    /**
     * Displays a message when the user gives a mark command without specifying the task number.
     */
    public void incompleteMarkCommand() {
        System.out.println("Sorry, you must specify a task number to be marked!\n" + LINE);
    }

    /**
     * Displays a message when the user gives an un-mark command without specifying the task number.
     */
    public void incompleteUnmarkCommand() {
        System.out.println("Sorry, you must specify a task number to be unmarked!\n" + LINE);
    }

    /**
     * Displays a message when the user gives a delete command without specifying the task number.
     */
    public void incompleteDeleteCommand() {
        System.out.println("Sorry, you must specify a task number to be deleted!\n" + LINE);
    }

    /**
     * Displays a message when the user gives a deadline command without specifying the
     * deadline time.
     */
    public void incompleteDeadlineCommand() {
        System.out.println("Sorry, you must specify a time for when the deadline is due!\n" + LINE);
    }

    /**
     * Displays a message when the user gives the wrong time format in the input.
     */
    public void wrongTimeFormat() {
        System.out.println(
                "OOPS!! Looks like your time format is wrong, make sure to use "
                        + "this format: DD/MM/YYYY HHmm.\nExample is 30/05/2023 2100.\n"
        );
    }

    /**
     * Displays a message when the user gives an event command without specifying the
     * start or end time.
     */
    public void incompleteEventCommand() {
        System.out.println("Sorry, you must specify a start time and an end time for the event!\n");
    }

    /**
     * Displays a confirmation message when a task is added successfully.
     * @param task The task added to the storage.
     * @param size The size of the storage containing previous tasks.
     */
    public void addTaskMessage(Task task, int size) {
        System.out.println(
                String.format("Got it. I've added this task: %s\nNow you have %d tasks in the list.",
                        task.toString(), size)
        );
    }

    /**
     * Displays every task previously stored.
     * @param index the index of the task in the storage.
     * @param task the task to be displayed.
     */
    public void listTask(int index, Task task) {
        System.out.println(
                String.format("%d. %s",
                        index, task.toString() + "\n"
                )
        );
    }

    /**
     * Displays a message when the user tries to access a task in storage that does not
     * yet exist.
     */
    public void markUnmarkDeleteWrongTask() {
        System.out.println("☹ OOPS!!! Such a task does not exist!\n");
    }

    /**
     * Displays a message when the user tries to mark a task that has already been completed.
     */
    public void taskAlreadyMarkedAsDone() {
        System.out.println("That task has already been marked as done!");
    }

    /**
     * Displays a message to confirm that a task is marked as done successfully.
     * @param task the task to be marked as done.
     */
    public void markTask(Task task) {
        System.out.println(
                String.format(
                        "Nice! I've marked this task as done: %s",
                        task.toString()
                )
        );
    }

    /**
     * Displays a message when the user tries to un-mark a task that is not complete.
     */
    public void taskAlreadyUnmarked() {
        System.out.println("That task has already been marked as not done!");
    }

    /**
     * Displays a message when the user successfully marks a task as not done.
     * @param task the task to be un-marked.
     */
    public void unmarkTask(Task task) {
        System.out.println(
                String.format(
                        "Ok, I've marked this task as not done yet: %s",
                        task.toString()
                )
        );
    }

    /**
     * Displays a message when the user successfully deletes a task.
     * @param task the task to be deleted.
     */
    public void deleteTask(Task task) {
        System.out.println(String.format("Noted. I've removed this task: %s", task.toString()));
    }

    /**
     * Displays a message when there is an error loading data from the hard disk.
     */
    public void unableToLoadFromMemory() {
        System.out.println("Error while initialising: unable to load from memory.");
    }

    /**
     * Displays a message when there is an error writing data into the hard disk.
     */
    public void errorWhenUpdatingFile() {
        System.out.println("Error when updating file.\n" + LINE);
    }

    /**
     * Displays the task that matches the description given by the user.
     * @param task the task displayed
     * @param index the count of the task number being displayed.
     */
    public void returnSearchTerm(Task task, int index) {
        if (index == 1) {
            System.out.println("Here are the matching tasks in your list:");
        }

        System.out.println(String.format(
                "%d. %s", index, task.toString()));
    }

    /**
     * Displays a message when there are no results that matches the user's query.
     */
    public void noSearchResults() {
        System.out.println("OOPS!!! There are no search results for your query.");
    }

    /**
     * Displays a message when the user tries to search for tasks without
     * giving a search term.
     */
    public void incompleteSearchCommand() {
        System.out.println("You must enter a search term!");
    }
}
