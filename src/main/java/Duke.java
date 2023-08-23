import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        String name = "Ip Bot";
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello I'm " + name + "!");
        System.out.println("While I may not be able to fight like Ip Man, I can assist you in other areas!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner scanner = new Scanner(System.in);

        List<String> list = new ArrayList<>();

        while(true) {
            String command = scanner.nextLine().strip();
            if(command.equalsIgnoreCase("bye")){
                break;
            }
            System.out.println(HORIZONTAL_LINE);
            if(command.isEmpty()){
                System.out.println("Nothing happened!");
            }
            else if(command.equalsIgnoreCase("list")){
                for(int index=0;index<list.size();index++) {
                    System.out.printf("%d. %s\n", index+1, list.get(index));
                }
            }
            else {
                list.add(command);
                System.out.println("Added: " + command);
            }
            System.out.println(HORIZONTAL_LINE);
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
