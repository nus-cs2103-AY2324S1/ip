import java.util.Scanner;
public class Duke {
    private final UI ui = new UI();
    private final Actions action = new Actions();
    public void initiateChatbot() {
        ui.horizontalLines();
        ui.display("Hello! I'm Whelmed.");
        ui.display("What can I do for you?");
        ui.horizontalLines();
        String input = ui.readInput();
        while (!input.toLowerCase().equals("bye")) {
            ui.horizontalLines();
            if (input.toLowerCase().equals("list")) {
                ui.displayActions(action.list());
            } else {
                action.add(input);
                ui.display("added: " + input);
            }
            ui.horizontalLines();
            input = ui.readInput();
        }
        ui.horizontalLines();
        ui.display("Bye. Hope to see you again soon!");
        ui.horizontalLines();
    }
    public static void main(String[] args) {
        Duke Whelmed = new Duke();
        Whelmed.initiateChatbot();
    }
}