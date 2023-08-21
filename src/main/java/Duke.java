import java.util.Scanner;
public class Duke {
    private static String name = "Alfred";

    public static void println() {
        System.out.println("____________________________________________________________");
    }

    public static String getName() {
        return Duke.name;
    }

    public static void main(String[] args) {
        Scanner scanIn = new Scanner(System.in);

        println();
        System.out.println("Hello I'm " + getName());
        System.out.println("What can I do for you?");
        println();



        while (true) {

            String command = scanIn.nextLine();
            switch (command){
                case "bye":

                    println();
                    System.out.println("Bye. Hope to see you again soon!");
                    println();
                    break;

                default:
                    println();
                    System.out.println(command);
                    println();
                    continue;

            }
            break;

        }

    }
}


