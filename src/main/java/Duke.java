import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____     ____    _    _\n"
                + "|     \\__/    |  | |  | |\n"
                + "|  | \\ _ / |  |  | |  | |  \n"
                + "|  |       |  |  | |  | |____\n"
                + "|__|       |__|  |_|  |______|\n";
        String horLine = "__________________________________________________________________________";
        String indentation = "     ";
        System.out.println(logo);
        System.out.println(indentation + horLine);
        System.out.println(indentation + "Hi there, I'm Mil - your personal chatbot.\n     How can I help you today?");
        System.out.println(indentation + horLine);
        Scanner scanner = new Scanner(System.in);
        String input;

        List<String> taskList = new ArrayList<>();

        while(scanner.hasNext()) {
            input = scanner.nextLine();
            if(input.equals("bye")) {
                System.out.println(indentation + horLine);
                System.out.println(indentation + "Have a nice day and see you again soon!");
                System.out.println(indentation + horLine);
                break;
            } else if(input.equals("list")) {
                System.out.println(indentation + horLine);
                int i = 1;
                for (String task : taskList) {
                    System.out.println(indentation + i + ". " + task);
                    i++;
                }
                System.out.println(indentation + horLine);
                continue;
            }
            taskList.add(input);
            System.out.println(indentation + horLine);
            System.out.println(indentation + "added: " + input);
            System.out.println(indentation + horLine);
        }


    }
}
