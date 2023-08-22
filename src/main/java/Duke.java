import java.util.Scanner;

public class Duke {
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

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                Printer.printList(tasklst);
                input = sc.nextLine();
            } else if (input.contains("unmark")) {
                int temp = Character.getNumericValue(input.charAt(7));
                tasklst[temp - 1].unmarkAsDone();
                input = sc.nextLine();
            } else if (input.contains("mark")) {
                int temp = Character.getNumericValue(input.charAt(5));
                tasklst[temp - 1].markAsDone();
                input = sc.nextLine();
            } else {
                Printer.print("added: " + input);
                Task newtsk = new Task(input);
                tasklst[count] = newtsk;
                count++;
                input = sc.nextLine();
            }
        }

        Printer.printExit();
    }
}
