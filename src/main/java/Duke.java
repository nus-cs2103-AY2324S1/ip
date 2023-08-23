import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        ArrayList<String> storedList = new ArrayList<>();
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
            if (input.equals("list")) {
                for (int i = 0; i < storedList.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + storedList.get(i));
                }

            } else {
                storedList.add(input);
                System.out.println("added: "+ input + "\n" + line);
            }

            input = userInput.nextLine();
        }

        System.out.println("Bye (actually hehe). Hope to see you again!\n" + line);
    }
}