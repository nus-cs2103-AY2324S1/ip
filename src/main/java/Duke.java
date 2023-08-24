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
        System.out.println("____________________________________________________________");

        Scanner myScanner = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<Task>(); // Create an ArrayList object

        while(true){
            String inValue = myScanner.next();
            switch(inValue) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    return;

                case "list":
                    System.out.println("____________________________________________________________");
                    int i = 1;
                    for (Task t: myList){
                        String prefix = t.getStatusIcon();
                        System.out.println(String.valueOf(i) + "." + "[" + prefix + "]" + " " + t.get());
                        i++;
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "mark":
                    int number = myScanner.nextInt();
                    myList.get(number-1).set();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + myList.get(number-1).get());
                    System.out.println("____________________________________________________________");
                    break;

                case "unmark":
                    int numero = myScanner.nextInt();
                    myList.get(numero-1).unset();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + myList.get(numero-1).get());
                    System.out.println("____________________________________________________________");
                    break;

                default:
                    inValue += myScanner.nextLine();
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + inValue);
                    System.out.println("____________________________________________________________");
                    myList.add(new Task(inValue));
                    break;
                    
            }
        }
    }
}

