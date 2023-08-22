import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Hello! I am Bob\n"
                + "What can I do for you?\n"
                + "----------------------------\n";
        String bye = "Bye! Hope to see you again soon!\n";
        System.out.println(logo);
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String output = scanner.nextLine();
            if (output.equals("bye")) {
                System.out.println(bye);
                break;
            }
            System.out.println(output);
        }
    }
}
