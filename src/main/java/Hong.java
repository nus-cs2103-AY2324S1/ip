import java.util.Scanner;
import java.util.ArrayList;

public class Hong {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<String>();
        String line = "---------------------------------------------------------";
        String firstMessage = line + "\nHello! I'm Hong\nWhat can I do for you?\n" + line;


        System.out.println(firstMessage);
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                myObj.close();
                break;
            } else if (userInput.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < tasks.size(); i++) {
                    String currentItem = (i + 1) + ". " + tasks.get(i);
                    System.out.println(currentItem);
                }
                System.out.println(line);
            } else {
                String currentMessage = line + "\n" + "added: " + userInput + "\n" + line;
                System.out.println(currentMessage);
                tasks.add(userInput);
            }
        }
        String exitMessage = line + "\n" + "Bye. Hope to see you again soon!\n" + line;
        System.out.println(exitMessage);
    }
}
