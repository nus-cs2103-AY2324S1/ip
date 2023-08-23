import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");

        ArrayList<Task> list = new ArrayList<Task>();

        while (true) {
            Scanner obj = new Scanner(System.in);
            String input = obj.nextLine();
            boolean isMark = false;
            int markNo = 0;
            char[] charArray = input.toCharArray();

            if (charArray[0] == 'm' && charArray[1] == 'a' && charArray[2] == 'r' && charArray[3] == 'k'
                && Character.isWhitespace(charArray[4]) && Character.isDigit(charArray[5])) {
                isMark = true;
                markNo = Character.getNumericValue(charArray[5]);
            }

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ".[" + list.get(i - 1).getStatusIcon() + "] " + list.get(i - 1).toString());
                }
            } else if (isMark) {
                if (markNo > 0 && markNo <= list.size()) {
                    System.out.println("Nice! I've marked this task as done:");
                    list.get(markNo - 1).markAsDone();
                    System.out.println("[X] " + list.get(markNo - 1).toString());
                } else {
                    System.out.println("Sorry, there is no such task!");
                }
            } else {
                Task newTask = new Task(input);
                list.add(newTask);
                System.out.println("added: " + input);
            }
        }
    }

}
