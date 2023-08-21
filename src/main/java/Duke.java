import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String EXIT_PHRASE = "bye";
        String LIST_PHRASE = "list";
        String[] toDoList = new String[100];
        int i = 0;
        System.out.println(
                "Hello! I'm Sunacchi!\n" +
                        "What can I do for you?\n"
        );

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        String userInput = myObj.next();
        while (i < 100) {
            if (userInput.equals(EXIT_PHRASE)) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if (userInput.equals(LIST_PHRASE)) {
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + "." + " " + toDoList[j]);
                }
                userInput = myObj.next();
                continue;
            }
            if (userInput.equals("")) {
                userInput = myObj.next();
                continue;
            }
            toDoList[i] = userInput;
            System.out.println("added: " + userInput);
            i++;
            userInput = myObj.next();
        }
    }
}
