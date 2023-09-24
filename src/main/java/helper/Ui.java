package helper;

public class Ui {
    private static final String PARTITION = "------------------------------------------------------------";
    private static final String NAME = "Duke Max";

    public static void greet() {
        System.out.println(PARTITION);
        String[] messageList = {("Hello! I'm " + NAME + "."), ("What can I do for you?")};
        print(messageList);
    }

    public static void exit() {
        print("Bye. Hope to see you again soon!");
    }

    public static void print(String message) {
        System.out.println(message);
        System.out.println(PARTITION);
    }

    public static void print(String[] messageList) {
        for (String message: messageList) {
            System.out.println(message);
        }
        System.out.println(PARTITION);
    }
}

