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
        String sf1 = String.format("Hello I'm %s, your personal assistant.",getName());
        System.out.println(sf1);
        System.out.println("What can I do for you today, sir?");
        println();



        while (true) {

            String command = scanIn.nextLine();
            switch (command){
                case "bye":

                    println();
                    System.out.println("Goodbye. Hope to be of service again soon!");
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


