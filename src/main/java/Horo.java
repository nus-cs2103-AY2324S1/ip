import java.util.ArrayList;
import java.util.Scanner;

public class Horo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> store = new ArrayList<String>();

        String logo = " _    _ \n"
                    + "| |  | |\n"
                    + "| |__| | ___  _ __ ___\n"
                    + "|  __  |/ _ \\| '__/ _ \\\n"
                    + "| |  | | (_) | | | (_) |\n"
                    + "|_|  |_|\\___/|_|  \\___/\n";
        System.out.println(logo);

        String introduction = "Hello! I'm Horo\n" 
                            + "What can I do for you?\n"
                            + "Type anything to store.\n"
                            + "'list' to list\n"
                            + "'bye' to exit\n";
        System.out.println(introduction);
        
        while(true){
          System.out.print(">");
          String input = scanner.nextLine();
          if(input.compareToIgnoreCase("bye") == 0 ){
            break;
          }
          if(input.compareToIgnoreCase("list") == 0 ){
            for(int i = 0; i < store.size(); i++){
               System.out.println((i+1) + ". " + store.get(i));
            }
            continue;
          }
          store.add(input);
          System.out.println("Added: " + input);
        }
        
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();        
    }
}
