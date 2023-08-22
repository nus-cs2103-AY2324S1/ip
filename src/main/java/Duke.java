import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        //Level 0
        String botName = "Pau";
        String introduction = " Hello! I'm " + botName + "\n" + " What can I do for you?";
        String exit = " Bye. Hope to see you again soon!\n";
        System.out.println(introduction);

        //Level 2
        String input;
        Scanner scan = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equals("list")) {
                list.forEach(item -> {
                    System.out.println((list.indexOf(item) + 1) + ". " + item);
                });
            } else {
                System.out.println("added: " + input);
                list.add(input);
            }
        }

    }
}
