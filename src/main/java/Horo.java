import java.util.Scanner;

public class Horo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " _    _ \n"
                    + "| |  | |\n"
                    + "| |__| | ___  _ __ ___\n"
                    + "|  __  |/ _ \\| '__/ _ \\\n"
                    + "| |  | | (_) | | | (_) |\n"
                    + "|_|  |_|\\___/|_|  \\___/\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Horo");
        System.out.println("What can I do for you?");

        System.out.println("Type anything. 'bye' to exit");
        
        while(true){
          System.out.print(">");
          String input = scanner.nextLine();
          if(input.compareToIgnoreCase("bye") == 0 ){
            break;
          }
          System.out.println(input);
        }
        
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();        
    }
}
