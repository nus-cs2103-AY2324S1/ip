public class Duke {
    private static final int lineLength = 60;
    private static final String horizontalLine = "_".repeat(lineLength);
    private static final String msgIndent = " ";
    private static final String name = "chatBot";
    private static final String helloMsg = String.format("Hello! I'm %s", name);
    private static final String requestMsg = "What can I do for you?";
    private static final String goodbyeMsg = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        Duke.printMsg(helloMsg + "\n" + requestMsg);
        Duke.printMsg(goodbyeMsg);
    }

    private static void printMsg(String msg) {
        for (String line : msg.split("\n")) {
            System.out.println(msgIndent + line);
        }
        System.out.println(horizontalLine);
    }
}
