package uicomponents;

import java.util.ArrayList;

import crackerpackage.TodoList;
import crackerpackage.tasks.Task;


/**
 * The UI component of the chatbot.
 *
 * @author Anton Tan Hong Zhi
 */
public class Reply {
    private String line = "____________________________________________________________";
    private ArrayList<String> lines;

    /**
     * Creates a Reply for the chatbot.
     */
    public Reply() {
        this.lines = new ArrayList<>();
    }


    /**
     * Prints out the content sandwiched by 2 lines.
     *
     * @param content the string to be printed
     */
    public void echo(String content) {
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }

    /**
     * Prints out all the stored strings in sequential order, sandwiched by 2 lines.
     */
    private void echo() {
        System.out.println(line);
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
        System.out.println(line);
        lines.removeAll(lines);
    }
    private void add(String s) {
        lines.add(s);
    }

    /**
     * Lists out all the tasks in the Todolist
     *
     * @param list a Todolist that stores tasks
     */
    public void iterate(TodoList list) {

        this.add("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            this.add((i + 1) + ". " + list.getTaskString(i));
        }
        echo();
    }


    /**
     * Prints out the reply of a store task operation
     * @param t The task to be stored
     * @param size The size of the list
     */
    public void storeTaskReply(Task t, int size) {

        this.add("Got it. I've added this task:");
        this.add(t.toString());
        this.add("Now you have " + size + " task(s) in the list.");

        echo();
    }


    /**
     * Prints out the reply of a delete task operation.
     *
     * @param t The deleted task
     * @param size The size of the list
     */
    public void deleteTaskReply(Task t, int size) {


        this.add("Got it. I've removed this task:");
        this.add(t.toString());
        this.add("Now you have " + size + " task(s) in the list.");

        echo();
    }



    /**
     * Prints out the reply of a mark/unmark task operation.
     * @param t The task that is modified
     */
    public void modifyTaskReply(Task t) {

        this.add("Operation done. This is the current state of your task:");
        this.add(t.toString());

        echo();
    }

    /**
     * Prints out the reply of a find task operation.
     * @param list The list of filtered tasks
     */
    public void findTaskReply(TodoList list) {
        this.add("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            this.add((i + 1) + ". " + list.getTaskString(i));
        }
        echo();
    }
}

