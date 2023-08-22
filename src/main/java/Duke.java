

import java.util.Scanner;
/**
 * This program is a chatbot, somebodyhaha, used to mark completion of tasks
 * marking the completion of tasks
 *
 * @author: Low Jun Yu
 * @version: CS2103T AY23/24 Semester 1
 */
public class Duke {
    /**
     * The program reads input given by the user to perform functions to help
     * add, edit, track and delete tasks inputted
     */
    public static void main(String[] args) {
        int count = 0;

        Task[] tasklst = new Task[100];

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        Printer.printGreeting();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // while loop containing possible inputs
        while(!input.equals("bye")) {
            String[] words = input.split("\\s+");
            String[] fields;
            switch(words[0]) {
                case "list":
                    Printer.printList(tasklst);
                    break;
                case "mark":
                    int temp = Character.getNumericValue(input.charAt(5));
                    tasklst[temp - 1].markAsDone();
                    break;
                case "unmark":
                    int temp2 = Character.getNumericValue(input.charAt(7));
                    tasklst[temp2 - 1].unmarkAsDone();
                    break;
                case "todo":
                    tasklst[count] = new ToDo(input.substring(5));
                    Printer.addTask(tasklst[count], count);
                    count++;
                    break;
                case "deadline":
                    fields = input.substring(9).split("/by ");
                    System.out.println(fields[0]);
                    tasklst[count] = new Deadline(fields[0], fields[1]);
                    Printer.addTask(tasklst[count], count);
                    count++;
                    break;
                case "event":
                    fields = input.substring(6).split("/from |/to ");
                    tasklst[count] = new Event(fields[0], fields[1], fields[2]);
                    Printer.addTask(tasklst[count], count);
                    count++;
                    break;
                default:
                    Printer.print("added: " + input);
                    Task newtsk = new Task(input);
                    tasklst[count] = newtsk;
                    count++;
                    break;
            }
            input = sc.nextLine();
        }

        Printer.printExit();
    }
}
