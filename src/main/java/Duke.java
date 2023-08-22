import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "       .__               \n"
                + "  ____ |__| ____   ____  \n"
                + "/    \\|  |/    \\ /  _  \\\n"
                + "|   |  \\  |   |  (  <_> )\n"
                + "|___|  /__|___|  /\\____/ \n"
                + "     \\/        \\/        ";
        final String HORIZONTAL = "_____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL);
        System.out.println("Hello! I'm NINO!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL);
        while(true) {
            String command = scanner.nextLine();
            evaluate(command);
            if(command.equals("bye")){
                break;
            }
        }
    }
    public static void evaluate(String command) {
        final String HORIZONTAL = "_____________________________________________________________";
        if(command.equals("bye")) {
            System.out.println(HORIZONTAL);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL);
        } else {
            System.out.println(HORIZONTAL);
            System.out.println(command);
            System.out.println(HORIZONTAL);
        }
    }
}
