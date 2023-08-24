import java.util.Scanner;

public class Duke {
    private static String horiLine = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println(greetFunction("Jack"));

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")){
            System.out.println(repeatFunction(input));
            input = scanner.nextLine();
        }
        System.out.println(byeFunction());
        scanner.close();



    }

    public static String greetFunction(String name){
        String greetings = horiLine +"\nHello! I'm " + name + "\n"
                + "What can I do for you?\n" + horiLine;
        return greetings;
    }
    public static String byeFunction(){

        String byeword = horiLine + "\nBye. Hope to see you again soon\n" + horiLine;
        return byeword;
    }
    public static String repeatFunction(String text){
        String byeword = horiLine +"\n" + text + "\n" + horiLine;
        return byeword;
    }

}
