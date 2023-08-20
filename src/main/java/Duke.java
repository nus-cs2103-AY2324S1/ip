import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] textStorage = new String[100];
        int storageIndex = 0;

        String chatBotName = "Benedit Cucumber Badge";
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");

        boolean dontTerminate = true;

        while (dontTerminate) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                dontTerminate = false;
            } else if(input.equals("list")) {
                for (int i = 0; i < storageIndex; i++) {
                    int itemNumber = i + 1;
                    System.out.println(itemNumber + ". " + textStorage[i]);
                }
            } else {
                textStorage[storageIndex] = input;
                storageIndex++;
                System.out.println(input);
            }
        }



    }
}
