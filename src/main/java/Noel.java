import java.util.Scanner;

public class Noel {

    public static void printFunction(String message){
        String filler = "____________________________________________________________";
        System.out.println(filler + "\n" + message + "\n" + filler);
    }
    public static void main(String[] args) {

        String helloMsg = " Hello! I'm Noel!\nWhat can I do for you?";
        String byeMsg = " Bye. Hope to see you again soon!";

        printFunction(helloMsg);

        Scanner in = new Scanner(System.in);
        String nextLine = in.nextLine();

        while (!nextLine.equals("bye")){
            printFunction(nextLine);
            nextLine = in.nextLine();
        }

        printFunction(byeMsg);
    }
}
