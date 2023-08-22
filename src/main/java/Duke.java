import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Level 0
        String botName = "Pau";
        String introduction = " Hello! I'm " + botName + "\n" + " What can I do for you?";
        String exit = " Bye. Hope to see you again soon!\n";
        System.out.println(introduction);

        //Level 1
        String input;
        Scanner scan = new Scanner(System.in);

        while (true) {
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else {
                System.out.println(input);
            }
        }

    }
}
