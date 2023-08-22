import java.util.Scanner;

public class Hong {
    public static void main(String[] args) {

        String line = "---------------------------------------------------------\n";
        String firstMessage = line + "Hello! I'm Hong\nWhat can I do for you?\n" + line;
        System.out.println(firstMessage);
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else {
                String currentMessage = line + userInput + "\n" + line;
                System.out.println(currentMessage);
            }
        }
        String exitMessage = line + "Bye. Hope to see you again soon!\n" + line;
        System.out.println(exitMessage);
    }
}
