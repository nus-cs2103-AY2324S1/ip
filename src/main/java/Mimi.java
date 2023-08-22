import java.util.Scanner;
public class Mimi {
    public static void main(String[] args) {

        Storage PreviousCommands = new Storage();

        String LINE = "_________________________________________________\n";

        System.out.println(
                LINE
                + "Hello! I'm Mimi.\n"
                + "What can I do for you?\n"
                + LINE
        );

        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            System.out.println(LINE);
            Input input = new Input(command);

            if (input.isExit()) {
                System.out.println(Input.EXIT_MESSAGE + LINE);
                break;
            }

            if (input.isList()) {
                PreviousCommands.listItems();
                System.out.println(LINE);
                continue;
            }

            PreviousCommands.add(input);

            System.out.println(LINE);
        }
    }
}
