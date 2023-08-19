public class Duke {
    public static void main(String[] args) {
        // Initialising objects
        UI ui = new UI();
        Parser p = new Parser();

        // Opening Dialogue
        ui.line();
        System.out.println("Hello, I'm Prawn");
        System.out.println("What would you like me to do sire?");
        ui.line();

        // Main Loop
        p.run();

        // Ending Dialogue
        System.out.println("Bye. Hope to see you again soon!");
        ui.line();
    }
}
