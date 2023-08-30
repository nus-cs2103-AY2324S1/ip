package com.mimi.main;

import com.mimi.tasks.Task;

public class Ui {
    private final String LINE = "_________________________________________________\n";
    public void showLoadingError() {
        System.out.println("Failed to load... Please try again!\n" + LINE);

    }

    public void welcomeMessage() {
        System.out.println(
                        LINE
                        + "Hello! I'm Mimi.\n"
                        + "What can I do for you?\n"
                        + LINE
        );
    }

    public void exitMessage() {
        System.out.println(
                "Bye. Hope to see you again soon!\n" + LINE
        );
    }

    public void invalidTaskMessage() {
        System.out.println("☹ OOPS!!! I'm sorry but I don't know what that means :-(\n" + LINE);
    }

    public void separator() {
        System.out.println(LINE);
    }

    public void incompleteMarkCommand() {
        System.out.println("Sorry, you must specify a task number to be marked!\n" + LINE);
    }

    public void incompleteUnmarkCommand() {
        System.out.println("Sorry, you must specify a task number to be unmarked!\n" + LINE);
    }

    public void incompleteDeleteCommand() {
        System.out.println("Sorry, you must specify a task number to be deleted!\n" + LINE);
    }

    public void incompleteDeadlineCommand() {
        System.out.println("Sorry, you must specify a time for when the deadline is due!\n" + LINE);
    }

    public void wrongTimeFormat() {
        System.out.println(
                "OOPS!! Looks like your time format is wrong, make sure to use " +
                        "this format: DD/MM/YYYY HHmm.\nExample is 30/05/2023 2100.\n"
        );
    }

    public void incompleteEventCommand() {
        System.out.println("Sorry, you must specify a start time and an end time for the event!\n");
    }

    public void addTaskMessage(Task task, int size) {
        System.out.println(
                String.format("Got it. I've added this task: %s\nNow you have %d tasks in the list.",
                        task.toString(), size)
        );
    }

    public void listTask(int index, Task task) {
        System.out.println(
                String.format("%d. %s",
                        index, task.toString() + "\n"
                )
        );
    }

    public void markUnmarkDeleteWrongTask() {
        System.out.println("☹ OOPS!!! Such a task does not exist!\n");
    }

    public void taskAlreadyMarkedAsDone() {
        System.out.println("That task has already been marked as done!");
    }

    public void markedDone(Task task) {
        System.out.println(
                String.format(
                        "Nice! I've marked this task as done: %s",
                        task.toString()
                )
        );
    }

    public void taskAlreadyUnmarked() {
        System.out.println("That task has already been marked as not done!");
    }

    public void unmarkedDone(Task task) {
        System.out.println(
                String.format(
                        "Ok, I've marked this task as not done yet: %s",
                        task.toString()
                )
        );
    }

    public void deletedTask(Task task) {
        System.out.println(String.format("Noted. I've removed this task: %s", task.toString()));
    }

    public void unableToLoadFromMemory() {
        System.out.println("Error while initialising: unable to load from memory.");
    }

    public void errorWhenUpdatingFile() {
        System.out.println("Error when updating file.\n" + LINE);
    }

    public void returnSearchTerm(Task task, int index) {
        if (index == 1) {
            System.out.println("Here are the matching tasks in your list:");
        }

        System.out.println(String.format(
                "%d. %s", index, task.toString()));
    }

    public void noSearchResults() {
        System.out.println("OOPS!!! There are no search results for your query.");
    }

    public void incompleteSearchCommand() {
        System.out.println("You must enter a search term!");
    }
}
