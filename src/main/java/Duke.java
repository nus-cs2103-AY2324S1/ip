import java.util.Scanner;
public class Duke {

    static String indent = "   ";
    static String horizontalLines = indent  +"_______________________________________";
    static String stringArray[] = new String[100];
    static int count = 0;
    public static void displayList() {
        System.out.println(horizontalLines);
        int len = stringArray.length;
        for (int i = 0; i < count; i++) {
            int num = i + 1;
            System.out.println(indent + num + ". " + stringArray[i]);
        }
        System.out.println(horizontalLines);
    }

    public static void main(String[] args) {
        String name = "zac";
        Scanner obj = new Scanner(System.in);

        System.out.println(horizontalLines);
        System.out.println(indent + "Hello! I'm " + name);
        System.out.println(indent + "What can I do for you?");
        System.out.println(horizontalLines);

        while (true) {
            String userInput = obj.nextLine();
            if (userInput.equals("list")) {
                displayList();
                continue;
            }
            if (userInput.equals("bye")) {
                break;
            }
            stringArray[count++] = userInput;
            System.out.println(horizontalLines);
            System.out.println(indent + "added: " + userInput);
            System.out.println(horizontalLines);
        }

        System.out.println(horizontalLines);
        System.out.println(indent + "Bye. Hope to see you again soon!");
        System.out.println(horizontalLines);
    }
}
