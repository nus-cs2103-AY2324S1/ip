import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    _________________________________________________________________________________");
        System.out.println("    Hello! I'm Thinh's chatbot\n    What can I do for you?");
        System.out.println("    _________________________________________________________________________________");
        Scanner in = new Scanner(System.in);
        String inputStr;
        do {
            inputStr = in.nextLine();
            if (inputStr.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
            } else {
                System.out.println("    " + inputStr);
            }
            System.out.println("    _________________________________________________________________________________");
        } while (!inputStr.equals("bye"));
    }
}
