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
        while(myScanner.hasNext()){
            String inValue = myScanner.next();
            Task item;
            char derived_prefix;
            switch(inValue) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    return;

                case "list":
                    System.out.println("____________________________________________________________");
                    int i = 1;
                    System.out.println("Here are the tasks in your list:");

                    for (Task t: myList){
                        System.out.println(String.valueOf(i) + "." + t.toString());
                        i++;
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "mark":
                    int number = myScanner.nextInt();
                    item = myList.get(number-1);
                    item.set();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(item.toString());
                    System.out.println("____________________________________________________________");
                    break;

                case "unmark":
                    int numero = myScanner.nextInt();
                    item = myList.get(numero-1);
                    item.unset();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(item.toString());
                    System.out.println("____________________________________________________________");
                    break;

                case "todo":
                    inValue = myScanner.nextLine().substring(1);
                    ToDo t =  new ToDo(inValue);
                    myList.add(t);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t.toString());
                    System.out.println("Now you have "+ String.valueOf(myList.size()) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "deadline":
                    inValue = myScanner.nextLine().substring(1);
                    String[] toBeSplit = inValue.split("/");
                    Deadline d = new Deadline(toBeSplit[0], toBeSplit[1].replace("by ", ""));
                    myList.add(d);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(d.toString());
                    System.out.println("Now you have "+ String.valueOf(myList.size()) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "event":
                    inValue = myScanner.nextLine().substring(1);
                    String[] to_Split = inValue.split("/");
                    Event e = new Event(to_Split[0], to_Split[1].replace("from ", ""), to_Split[2].replace("to ", ""));
                    myList.add(e);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e.toString());
                    System.out.println("Now you have "+ String.valueOf(myList.size()) + " tasks in the list.");
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

