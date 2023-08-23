import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        int index = 0;
        /** Captures user input*/
        Scanner jonBird = new Scanner(System.in);
        /** Stores user input*/
        String[] inputList =  new String[100];
        /** User input*/
        String input = "";
        System.out.println("Hello! I'm JonBird.\nWhat can I do for you?\n");
        while (true) {
            input = jonBird.nextLine();
            if (input.equalsIgnoreCase("bye")) break;
            if (input.equalsIgnoreCase("list")) {
                printList(inputList, index);
            } else {
                inputList[index] = input;
                index += 1;
                System.out.println("\tadded: " + input);
            }
        }
        jonBird.close();
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void printList(String[] list, int index) {
        for (int i = 0; i < index; i++) {
            System.out.println("\t"+ (i+1) + ". " + list[i]);
        }
    }
}
