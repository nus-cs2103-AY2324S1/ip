import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        boolean listen = true;
        int index = 0;
        /** Captures user input*/
        Scanner jonBird = new Scanner(System.in);
        /** Stores user input*/
        Task[] inputList =  new Task[100];
        /** User input*/
        String input = "";
        System.out.println("Hello! I'm JonBird.\nWhat can I do for you?\n");
        while (listen) {
            input = jonBird.nextLine();
            String[] inp = input.split("\\s+");
            int taskIndex = 0;
            if (inp[0].equalsIgnoreCase("mark") || inp[0].equalsIgnoreCase("unmark")) {
                if (inp.length == 2) {
                    try {
                        taskIndex = Integer.parseInt(inp[1]);
                        input = inp[0];
                    } catch (NumberFormatException e) {
                    }
                    ;
                }
            }

            switch (input) {
                case "":
                    if (inp.length == 0) {
                        System.out.println("Please enter something!");
                    } else {
                        System.out.println("Unknown command!");
                    }

                case "bye":
                    listen = false;
                    break;
                case "list":
                    printList(inputList, index);
                    break;
                case "unmark":
                    inputList[taskIndex-1].markAsUndone();
                    break;
                case "mark":
                    inputList[taskIndex-1].markAsDone();
                    break;
                default:
                    inputList[index] = new Task(input);
                    index += 1;
                    System.out.println("\tadded: " + input);
            }
        }
        jonBird.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(Task[] list, int index) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println("\t"+ (i+1) + ". " + list[i].printTask());
        }
    }
}
