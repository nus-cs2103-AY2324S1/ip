import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

        public static void printWithTab(String s) {
            String tab = "     ";
            System.out.println(tab + s);
        }

        public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = """
        _______         _           
        |  ____|       (_)           
        | |__ ___  _ __ _ _ __   ___ 
        |  __/ _ \\| '__| | '_ \\ / _ \\
        | | | (_) | |  | | | | |  __/
        |_|  \\___/|_|  |_|_| |_|\\___|
                      """;

        ArrayList<String> chatHistory = new ArrayList<String>();

        System.out.println(logo);
        printWithTab(line);
        printWithTab("Hello! I'm Forine");
        printWithTab("What can I do for you?");
        
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            printWithTab(line);
            if (input.equals("list")) {
                for (int i = 0; i < chatHistory.size(); i++) {
                    printWithTab(i + ". " + chatHistory.get(i));
                }
            }
            else {
                printWithTab("added: " + input);
                chatHistory.add(input);
            }
            printWithTab(line);
            input = sc.nextLine();
        }
        sc.close();
        printWithTab("Bye. Hope to see you again soon!");
        printWithTab(line);   
    }
}
