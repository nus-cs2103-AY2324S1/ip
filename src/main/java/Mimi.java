import java.util.Scanner;
public class Mimi {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

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

            System.out.println(command + "\n" + LINE);
        }
    }
}
