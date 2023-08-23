import java.util.Scanner;
public class Tired {
   public static void main(String[] args) {

       String name = "Tired";
       String horizontalLine = "____________________________________________________________";

       System.out.println(horizontalLine);
       System.out.println("Hello! I'm " + name);
       System.out.println("What can I do for you?");
       System.out.println(horizontalLine + "\n");

       Scanner sc = new Scanner(System.in);
       String input = sc.nextLine();

       while (!input.equals("bye")) {
           System.out.println(horizontalLine);
           System.out.println(input);
           System.out.println(horizontalLine + "\n");
           input = sc.nextLine();
       }

       System.out.println(horizontalLine);
       System.out.println("Bye. Hope to see you again soon!");
       System.out.println(horizontalLine);
   }
}