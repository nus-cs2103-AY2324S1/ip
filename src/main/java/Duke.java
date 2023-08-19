public class Duke {
    public static void main(String[] args) {
        greet();
        exit();
    }

    private static void greet() {
        System.out.println("____________________________________________________________");
        sendMessage(" Hello! I'm Heimdallr\n" +
                " What can I do for you?");
    }

    private static void exit() {
        sendMessage(" Bye. Hope to see you again soon!");
    }

    public static void sendMessage(String msg) {
        System.out.println(msg +
                "\n____________________________________________________________");
    }
}
