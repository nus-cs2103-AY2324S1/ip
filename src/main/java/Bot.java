import java.util.Scanner;
public class Bot {
    public Store store = new Store();
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String BYE_COMMAND = "bye";

    public void printWelcomeMessage(){
        String name = "LINUS";
        MessagePrint.print(
                "Hello! I'm " + name + "\n"
                        + "What can I do for you?");
    }
    public void printExitMessage() {
        MessagePrint.print( "Bye. Hope to see you again soon!");
    }
    public void chat() {
        Scanner sc = new Scanner(System.in);
        String input;
        while(true) {
            input = sc.nextLine();
            String command = input.split(" ")[0];
            switch(command) {
                case BYE_COMMAND:
                    break;
                case LIST_COMMAND:
                    store.list();
                    continue;
                case MARK_COMMAND:
                    store.mark(Integer.parseInt(input.split(" ")[1]));
                    continue;
                case UNMARK_COMMAND:
                    store.unmark(Integer.parseInt(input.split(" ")[1]));
                    continue;
                default:
                    store.add(input);
                    continue;
            }
            break;
        }
    }
    public void start() {
        printWelcomeMessage();
        chat();
        printExitMessage();
    }
}