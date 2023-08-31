package adam;

import java.util.Scanner;

import adam.tasks.Task;

public class Ui {
    protected Scanner scaner = new Scanner(System.in);

    public String readInput() {
        return scaner.nextLine();
    }

    public void welcome() {
        System.out.println("What's up I am Adam.Adam\n" + "so like what do you want?");
    }

    public void getAmount(int size) {
        System.out.println(String.format("%d adam.tasks in this list, stop procrasinating them!!!", size));
    }

    public void delete(Task curr, int size) {
        System.out.println("I have removed the Tasks.Task, so just make up your mind next time:");
        System.out.println(curr.toString());
        getAmount(size);
    }

    public void addTodo(Task curr, int size) {
        System.out.println("I added this todo to the list of endless work you have:");
        System.out.println(curr.toString());
        getAmount(size);
    }

    public void addEvent(Task curr, int size) {
        System.out.println("I added this event to your list, congrats on having a life outside of work:");
        System.out.println(curr.toString());
        getAmount(size);
    }

    public void addDeadline(Task curr, int size) {
        System.out.println("I have added this deadline to the list, good " +
                "luck on remembering it one day before deadline:");
        System.out.println(curr.toString());
        getAmount(size);
    }
    public void list() {
        System.out.println("Here are the amount of hard labor you have in your lists:");
    }

    public void bye() {
        System.out.println("Bye. Hope we don't see each other too often");
    }
    public void mark() {
        System.out.println("Congrats on getting one step closer to achieving true happines," +
                " I have marked this task as complete");
    }
    public void unmark() {
        System.out.println("turns out you still got more work to do, I have unmarked it");
    }

    public void displayError(String message) {
        System.out.println(message);
    }
}
