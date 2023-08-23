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

        System.out.println(logo);
        printWithTab(line);
        printWithTab("Hello! I'm Forine");
        printWithTab("What can I do for you?");

        printWithTab("Bye. Hope to see you again soon!");
        printWithTab(line);   
    }
}
