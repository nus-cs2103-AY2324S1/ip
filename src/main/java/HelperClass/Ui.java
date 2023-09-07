package HelperClass;

public class Ui {

    private String MyName;

    public Ui(String MyName) {
        this.MyName = MyName;
    }

    /**
     * print message in a specific format
     * @param message the original message to be printed
     */
    public void Speak(String message) {
        System.out.println("---------------------------");
        System.out.println(message);
        System.out.println("---------------------------");
    }

    public String Greet() {

        return "Hello! I'm " + MyName + "\n" + "What can I do for you?";

    }

    public String Exit() {

        return " Bye. Hope to see you again soon!";

    }


}
