package ui;


import java.util.ArrayList;
import tasks.Task;

public class Ui {

    String LINE = "~~*~~*~~*~~*~~*~~*~~*~~*~~*~~\n";

    /**
     * Prints out greeting for CHADbot.
     */
    public void chadGreet() {
        System.out.println(LINE);
        System.out.println("Yo! This is CHADbot\n");
        System.out.println("Need sum help?\n");
        System.out.println(LINE);
    }

    public String displayChadGreet() {
        return LINE + "Yo! This is CHADbot\n Need sum help?\n" + LINE ;
    }


    /**
     * Prints out goodbye message for CHADbot.
     */
    public void chadBye() {
        System.out.println(LINE);
        System.out.println("Cya l8r~\n");
        System.out.println(LINE);
    }

    public String displayChadBye() {
        return LINE + "Cya l8r~\n" + LINE;
    }

    /**
     * Prints out a string for CHADbot.
     *
     * @param input string for CHADbot to print
     */
    public void chadOutput(String input) {
        System.out.println(LINE);
        System.out.println(input + "\n");
        System.out.println(LINE);
    }

    public String displayChadOutput(String input) {
        return LINE + input + "\n" + LINE;
    }

    /**
     * Prints out a task after adding it to the list.
     *
     * @param input name of task added to the list
     */
    public void chadAddListOutput(String input){
        System.out.println(LINE);
        System.out.println(input + " has been added to yo list!\n");
        System.out.println(LINE);
    }

    public String displayChadAddListOutput(String input){
        return LINE + input + "  has been added to yo list!\n" + LINE;
    }

    /**
     * Prints out the whole list of tasks
     */
    public void chadListTask(ArrayList<Task> task) {
        if (task.size() == 0) {
            System.out.println("Your task list is EMPTY!");
        } else {
            System.out.println(LINE);
            System.out.println("Your outstanding tasks are...");
            for (int i = 0; i < task.size(); i++) {
                System.out.println("Task " + (i + 1) + ") "
                        + task.get(i));
            }
            System.out.println("\n" + "Get to work NOW!\n");
            System.out.println(LINE);
        }
    }

    public String displaychadListTask(ArrayList<Task> task) {
        if (task.size() == 0) {
            return "Your task list is EMPTY!";
        } else {
            String s = "";

            for (int i = 0; i < task.size(); i++) {
                s = "Task " + (i + 1) + ") " + task.get(i).toString() + "\n";
            }

            return LINE + "Your outstanding tasks are...\n" + s + "Get to work NOW!\n" + LINE;
        }
    }

    /**
     * Prints out the exception if faced by CHADbot.
     *
     * @param input exception message to be printed
     */
    public void chadExceptionOutput(String input){
        System.out.println(LINE);
        System.out.println(input + "\n");
        System.out.println(LINE);
    }

    /**
     * Prints out the respective messages after removing a task.
     *
     * @param input name of task that has been removed
     * @param size size of list after removing the task
     */
    public void chadRemoveOutput(String input, int size){
        System.out.println("Okay! I have removed this task :\n" + input);

        if (size == 0){
            System.out.println("Your list is currently empty! Good job :)");
        } else {
            System.out.println("Your list is currently " + size + " long... Get back to work!");
        }
    }

    public String displayChadRemoveOutput(String input, int size){

        String s;

        if (size == 0){
            s = "Your list is currently empty! Good job :)";
        } else {
            s = "Your list is currently " + size + " long... Get back to work!";
        }

        return "Okay! I have removed this task :\n" + input + "\n" + s;

    }

    /**
     * Prints out when a task is marked
     *
     * @param task name of task that has been removed
     * @param mark mark of completion status of task
     */
    public void chadMarkTaskOutput(String task, String mark){
        System.out.println(LINE);
        System.out.println("Good job! " + task + " fulfilled!");
        System.out.println(task + " [" + mark + "]\n");
        System.out.println(LINE);
    }

    public String displayChadMarkTaskOutput(String task, String mark){
        return LINE + "Good job! " + task + " fulfilled!" + "\n" + task + " [" + mark + "]\n" + LINE;
    }

    /**
     * Prints out when a task is unmarked
     *
     * @param task name of task that has been removed
     * @param mark mark of completion status of task
     */
    public void chadUnmarkTaskOutput(String task, String mark){
        System.out.println(LINE);
        System.out.println("Boooo! " + task + " is not done!");
        System.out.println(task + " [" + mark + "]\n");
        System.out.println(LINE);
    }

    public String displayChadUnmarkTaskOutput(String task, String mark){
        return  LINE + "\n" + "Boooo! " + task + " is not done!" + task + " [" + mark + "]\n" + LINE;
    }


    public void chadMatchNotFoundOutput(){
        System.out.println(LINE);
        System.out.println("There are no matching tasks!");
        System.out.println(LINE);
    }

    public String displayChadMatchNotFoundOutput(){
        return LINE + "There are no matching tasks!" + LINE;
    }

    public void chadMatchFoundOutput(ArrayList<Task> task){
        System.out.println(LINE);
        System.out.println("Here are the matching tasks:");

        for (int i = 0; i < task.size(); i ++) {
            System.out.println(task.get(i).toString());
        }

        System.out.println("Total of " + task.size() + " matched tasks");
        System.out.println(LINE);

    }

    public String displayChadMatchFoundOutput(ArrayList<Task> task){

        String s = "";

        for (int i = 0; i < task.size(); i ++) {
            s = s + task.get(i).toString() + "\n";
        }
        return LINE + "Here are the matching tasks:" + s + "Total of " + task.size() + " matched tasks" + LINE;
    }

}
