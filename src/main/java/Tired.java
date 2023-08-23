import java.util.Scanner;
public class Tired {
   public static void main(String[] args) {

       String[] list = new String[100];
       int pos = 0;

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
           if (input.equals("list")) {
               for (int i = 0; i < pos; i++) {
                   System.out.println((i + 1) + "." + list[i]);
               }
           } else {
               list[pos] = input;
               System.out.println("added: " + input);
               pos += 1;
           }
           System.out.println(horizontalLine + "\n");
           input = sc.nextLine();
       }

       System.out.println(horizontalLine);
       System.out.println("Bye. Hope to see you again soon!");
       System.out.println(horizontalLine);
   }
}