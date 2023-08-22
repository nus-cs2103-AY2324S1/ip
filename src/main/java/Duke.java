import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = "        ┏┓\n"
                + "        ┃┃\n"
                + "        ┃┗━┳┓╋┏┳━━┓\n"
                + "        ┃┏┓┃┃╋┃┃┃━┫\n"
                + "        ┗━━┻━┓┏┻━━┛\n"
                + "        ╋╋╋┏━┛┃\n"
                + "        ╋╋╋┗━━┛\n";
        System.out.println(line + "\nHello! I'm \n" + logo);
        System.out.println("How can I help you? \n" + line + "\n");
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input + "\n" + line);
            input = userInput.nextLine();
        }

        System.out.println("Bye (actually hehe). Hope to see you again!\n" + line);
    }
}