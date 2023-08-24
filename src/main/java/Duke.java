import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class

public class Duke {
    public static void main(String[] args) {
        String logo =
                "___________  __________  __________  ||   \n"
                +"|         | |         | |         |  ||      \n"
                +"-----------  ---------- ----------   ||  \n"
                + "    ||         ||          ||        ||       \n"
                + "    ||         ||          ||        ||           \n"
                + "    ||         ||          ||        ||           \n"
                + "    ||         ||          ||        ||       \n"
                + "    ||      __________  __________   ||      \n"
                + "    ||      |         | |         |  ______ \n"
                + "    ||      ----------  ----------   ______    \n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");

        Scanner myScanner = new Scanner(System.in);
        ArrayList<String> myList = new ArrayList<String>(); // Create an ArrayList object

        while(true){
            String inValue = myScanner.nextLine();
            switch(inValue) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    return;

                case "list":
                    System.out.println("____________________________________________________________");
                    int i = 1;
                    for (String s: myList){
                        System.out.println(String.valueOf(i) + ". " + s);
                        i++;
                    }
                    System.out.println("____________________________________________________________");
                    break;

                default:
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + inValue);
                    System.out.println("____________________________________________________________");
                    myList.add(inValue);
                    break;
                    
            }
        }
    }
}
