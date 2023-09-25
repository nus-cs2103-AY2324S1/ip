package duke.helper;

public class Ui {
    private static final String PARTITION = "------------------------------------------------------------";
    private static final String NAME = "Duke Max";

    /**
    * construct the UI class
    */
    public static void greet() {
        System.out.println(PARTITION);
        String[] messageList = {("Hello! I'm " + NAME + "."), ("What can I do for you?")};
        print(messageList);
    }

    /**
    * executes the exit command and prints the exit message
    */
    public static void exit() {
        print("Bye. Hope to see you again soon!");
    }

    /**
    * print the given message
    *
    * @param message the message to be printed
    */
    public static void print(String message) {
        System.out.println(message);
        System.out.println(PARTITION);
    }

    /**
    * print an array of list in order
    *
    * @param messageList the array of messages to be printed
    */
    public static void print(String[] messageList) {
        for (String message: messageList) {
            System.out.println(message);
        }
        System.out.println(PARTITION);
    }
}

