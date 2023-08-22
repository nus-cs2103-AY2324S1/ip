import java.util.Scanner;
public class Duke {


    public static void main(String[] args) {
        Duke lati = new Duke();

        System.out.println("____________________________________________________________\n" +
                " Hellooooooooooo! I'm Lati!\n" +
                " What can I do for you? :3\n" +
                "____________________________________________________________");

        Scanner scan = new Scanner(System.in);
        String comd = scan.nextLine();

        while (!comd.equals("bye")) {
            lati.messageHandler(comd);
            comd = scan.nextLine();
        }

        System.out.println(" Byeeeeee. Hope to see you again soon~~\n" +
                "____________________________________________________________");
    }

    //In-class method to manage messages, currently set to just echo the message input of the user.
    public void messageHandler(String tally) {

        System.out.println(tally + "\n" +
                "____________________________________________________________");

        
    }




}
