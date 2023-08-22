public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        String greetMessage = horizontalLine +
                "\n Hello! I'm ChatNat!\n" +
                " What can I do for you?\n" +
                horizontalLine;

        String exitMessage = " Bye. Hope to see you again soon!\n" + horizontalLine;

        // Greet the user
        System.out.println(greetMessage);

        // Exit
        System.out.println(exitMessage);
    }
}
