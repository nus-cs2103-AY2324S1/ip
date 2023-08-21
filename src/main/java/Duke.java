import java.util.Scanner;
public class Duke {
    private static String name = "Alfred";

    private static String[] list = new String[100];
    private static int counter = 0;

    public static void println() {
        System.out.println("____________________________________________________________");
    }

    public static String getName() {
        return Duke.name;
    }
    public static int getCounter(){
        return Duke.counter;
    }

    public static void incrementCounter(){
        Duke.counter++;
    }

    public static void setList(String item){
        list[getCounter()] = item;

    }

    public static void main(String[] args) {
        Scanner scanIn = new Scanner(System.in);

        println();
        String sf1 = String.format("Hello I'm %s, your personal assistant.",getName());
        System.out.println(sf1);
        System.out.println("What can I do for you today, sir?");
        println();




        while (true) {

            String text = scanIn.nextLine();
            switch (text){
                case "bye":

                    println();
                    System.out.println("Goodbye. Hope to be of service again soon!");
                    println();
                    break;

                default:
                    println();
                    System.out.println(text);
                    println();
                    continue;

            }
            break;

        }

    }
}


