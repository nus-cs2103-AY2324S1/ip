import java.util.Scanner;
import java.util.ArrayList;

public class Glen {
    static final String HORLINE = "_____________________________________________________\n";
    static ArrayList<String> tasks = new ArrayList<String>();
    
    public static void main(String[] args) {
        System.out.println(intro());
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.toLowerCase().equals("bye")) {
            if (input.toLowerCase().equals("list")) {
                System.out.println(lst());
            } else {
                System.out.println(add(input));
            }
            input = scan.nextLine();
        }
        System.out.println(exit());
        scan.close();
    }

    static String intro() {
        String logo = "  _____ _            \n" +
                      " / ____| |           \n" +
                      "| |  __| | ___ _ __  \n" +
                      "| | |_ | |/ _ \\  _ \\ \n" +
                      "| |__| | |  __/ | | |\n" +
                      " \\_____|_|\\___|_| |_|\n\n";
        String introText = "Hello, I'm Glen!\n" + 
                           "What can I do for you?\n";
        return HORLINE + logo + introText + HORLINE;
    }

    static String add(String inp) {
        tasks.add(inp);
        return HORLINE + "added: " + inp + "\n" + HORLINE;
    }

    static String lst() {
        String temp = "";
        for(int i = 0; i < tasks.size(); i++) {   
            temp += String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
        }  
        return HORLINE + temp + HORLINE;
    }

    static String exit() {
        return HORLINE + "Bye. Hope to see you again soon!\n" + HORLINE;
    }
}
