import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

public class Veda {

    private final static String NAME = "Veda";
    private static ArrayList<String> tasks = new ArrayList<String>(100);

    private static void addTask(String task) {
        if (tasks.add(task)) {
            System.out.println("added in mission: " + task);
        } else {
            System.out.println("System is unable to accommodate the new mission");
        }
    }

    public static void main(String[] args) {
        //Greet users upon initialisation
        System.out.println("____________________________________________________________");
        System.out.println(NAME + " initialised. How may I help you?");
        System.out.println("____________________________________________________________");

        Scanner inScanner = new Scanner(System.in);

        while (true) {
            String input = inScanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                //User wishes to exit the program
                break;
            } else if (input.toLowerCase().equals("list")) {
                //User wishes to see his listed missions
                tasks.forEach( task -> System.out.println(tasks.indexOf(task) + ". " + task));

                continue;
            }

            //Add tasks
            addTask(input);
        }


        //Exits the program
        System.out.println("Bye. All the best for your mission!");
    }
}
