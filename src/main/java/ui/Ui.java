package ui;


public class Ui {
    String line = "~~*~~*~~*~~*~~*~~*~~*~~*~~*~~\n";

    /**
     * Prints out greeting for CHADbot.
     */
    public void chadGreet() {
        System.out.println(line);
        System.out.println("Yo! This is CHADbot\n");
        System.out.println("Need sum help?\n");
        System.out.println(line);
    }

    /**
     * Prints out goodbye message for CHADbot.
     */
    public void chadBye() {
        System.out.println(line);
        System.out.println("Cya l8r~\n");
        System.out.println(line);
    }

    /**
     * Prints out a string for CHADbot.
     *
     * @param input string for CHADbot to print
     */
    public void chadOutput(String input) {
        System.out.println(line);
        System.out.println(input + "\n");
        System.out.println(line);
    }

    /**
     * Prints out a task after adding it to the list.
     *
     * @param input name of task added to the list
     */
    public void chadAddListOutput(String input){
        System.out.println(line);
        System.out.println(input + " has been added to yo list!\n");
        System.out.println(line);
    }

    /**
     * Prints out the exception if faced by CHADbot.
     *
     * @param input exception message to be printed
     */
    public void chadExceptionOutput(String input){
        System.out.println(line);
        System.out.println(input + "\n");
        System.out.println(line);
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

    /**
     * Prints out when a task is marked
     *
     * @param task name of task that has been removed
     * @param mark mark of completion status of task
     */
    public void chadMarkTaskOutput(String task, String mark){
        System.out.println(line);
        System.out.println("Good job! " + task + " fulfilled!");
        System.out.println(task + " [" + mark + "]\n");
        System.out.println(line);
    }

    /**
     * Prints out when a task is unmarked
     *
     * @param task name of task that has been removed
     * @param mark mark of completion status of task
     */
    public void chadUnmarkTaskOutput(String task, String mark){
        System.out.println(line);
        System.out.println("Boooo! " + task + " is not done!");
        System.out.println(task + " [" + mark + "]\n");
        System.out.println(line);
    }

}
