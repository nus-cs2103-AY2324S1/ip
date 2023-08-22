import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Ko...ko...ko..nichi...wa!!! I... I am Gotoh... Hitori desu! Σ(っ °Д °;)っ");
        System.out.println("You... can call me... Bocchi. They usually... call me Bocchi chan...");
        System.out.println("What can... can I do for you? (°□°;) ");
        System.out.println("____________________________________________________________");
        Scanner echoObject = new Scanner(System.in);
        String echo = echoObject.nextLine();
        while(!echo.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(echo);
            System.out.println("____________________________________________________________");
            Scanner nextEchoObject = new Scanner(System.in);
            echo = nextEchoObject.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("B... b...bye bye!... And ... see you! (〃ω〃)");
        System.out.println("____________________________________________________________");


    }
}
