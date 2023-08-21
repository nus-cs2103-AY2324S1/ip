public class Duke {
    private String name = "KAM_BOT";
    private static final String line = "————————————————————————————————————————————————————";

    public Duke(String name) {
        this.name = name;
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Duke.line);
    }

    public void greet() {
        System.out.println(Duke.line);
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
        System.out.println(Duke.line);
    }

    public static void main(String[] args) {
        Duke bot = new Duke("Kam_BOT");
        bot.greet();
        bot.exit();
    }
}
