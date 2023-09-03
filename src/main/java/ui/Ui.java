package ui;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcomeMessage() {
        String logo = "____________________________________________________________\n"
                + "YO! The name's Bond, James Bond.  \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        out.println(logo);
    }

    public void showLoadingError(){
        out.println("Failed to load.");
    }

    public void listout(TaskList tasks) {
        for (int i = 0; i < tasks.len(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public boolean isThereNext() {
        return this.in.hasNextLine();
    }

    public String readLine () {
        return this.in.nextLine();
    }

    public void showErrorMessage(String error) {
        out.println(error);
    }

    public void exit(TaskList tasks, Storage storage) {
        out.println("Bye. Till the next time.");
        storage.saveTasksToFile(tasks);
        System.exit(0);
    }

    public void showDeleteMessage(Task task, int remain) {
        out.println("REMOVED FOR YOU:)\n" + task.toString() +"\n Now you have:" + remain + " tasks left.");
    }

    public void markMessage(Task task) {
        out.println("you're done. DONEEE");
    }

    public void unmarkMessage(Task task) {
        out.println("Done. Stop being lazy.\n" + task.toString());
    }

    public void eventMessage(Task task) {
        out.println("ADDED event FOR YOU!!!!\n" + task.toString());
    }

    public void DeadlineMessage(Task task) {
        out.println("ADDED deadline FOR YOU!!!!\n" + task.toString());
    }

    public void todoMessage(Task task) {
        out.println("ADDED todo FOR YOU!!!!\n" + task.toString());
    }
}
