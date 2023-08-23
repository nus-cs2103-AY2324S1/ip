import java.util.Scanner;
public class Tired {
   public static void main(String[] args) {

       Task[] list = new Task[100];
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
                   System.out.println((i + 1) + "."
                           + "[" + list[i].getStatusIcon() + "] " + list[i].description);
               }
           } else if (input.startsWith("mark")) {
               int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
               if (taskIndex >= 0 && taskIndex < pos) {
                   list[taskIndex].markAsDone();
                   System.out.println("Nice! I've marked this task as done:");
                   System.out.println("[" + list[taskIndex].getStatusIcon() + "] " + list[taskIndex].description);
               }
           } else if (input.startsWith("unmark")) {
               int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
               if (taskIndex >= 0 && taskIndex < pos) {
                   list[taskIndex].markAsUndone();
                   System.out.println("OK, I've marked this task as not done yet:");
                   System.out.println("[" + list[taskIndex].getStatusIcon() + "] " + list[taskIndex].description);
               }
           } else {
               Task t = new Task(input);
               list[pos] = t;
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