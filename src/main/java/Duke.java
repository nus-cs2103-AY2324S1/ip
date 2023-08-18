public class Duke {
    private void greet() {
        String msg = "Hello! I'm Atlas\n"
                + "What can I do for you?\n";
        System.out.println(msg);
    }

    private void exit() {
        String msg = "Bye. Hope to see you again soon!\n";
        System.out.println(msg);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.exit();
    }
}
