import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int count = 0;
        String[] strlst = new String[100];

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
                Printer.printList(strlst);
                input = sc.nextLine();
            }
            else {
                Printer.print("added: " + input);
                strlst[count] = input;
                input = sc.nextLine();
                count++;
            }

        }

        Printer.printExit();
    }
}
