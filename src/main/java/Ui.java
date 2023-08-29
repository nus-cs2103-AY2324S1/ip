public class Ui {

    /**
     * This method prints out the initial greeting
     */
    public void greet() {
        //Introduction
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Cleon\n" +
                " What can I do for you?\n");
    }

    public void showLoadingError() {
        System.out.println("Issues loading past data. Creating a new tasklist from scratch");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
