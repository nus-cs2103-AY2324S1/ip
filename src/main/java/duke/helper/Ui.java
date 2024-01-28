package duke.helper;

/**
 * Ui class
 */
public class Ui {
    private static final String NAME = "Duke Max";

    /**
    * construct the UI class
    *
    * @return the bot greeting message
    */
    public static String greet() {
        String[] messageList = {("Hello! I'm " + NAME + "."), ("What can I do for you?")};
        return print(messageList);
    }

    /**
    * executes the exit command and prints the exit message
    *
    * @return the bot response
    */
    public static String exit() {
        return print("Bye. Hope to see you again soon!");
    }

    /**
    * print the given message
    *
    * @param message the message to be printed
    * @return the message input
    */
    public static String print(String message) {
        return message;
    }

    /**
    * print an array of list in order
    *
    * @param messageList the array of messages to be printed
    * @return the message inputs with line breaks
    */
    public static String print(String[] messageList) {
        String response = messageList[0] + "\n";
        for (int i = 1; i < messageList.length; i++) {
            response = response + messageList[i] + "\n";
        }
        return response;
    }
}

