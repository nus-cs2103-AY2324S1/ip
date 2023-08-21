import java.util.Scanner;
public class Bot {
    public Store store = new Store();
    public static final String LIST_COMMAND = "list";
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
            switch(input) {
                case BYE_COMMAND:
                    break;
                case LIST_COMMAND:
                    store.list();
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