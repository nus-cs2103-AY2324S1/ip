public class Duke {

    String name = "Leon";
    public Duke() {
        String message = String.format("Hello. I'm your personal assistant, %s." +
                "\n" +
                "What can I do for you?",
                this.name);
        System.out.println(message + "\n");
        printHorizontalLine();
        exit();
    }

    private void printHorizontalLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke();
    }
}
