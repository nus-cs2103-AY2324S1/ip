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

        List<Task> list = new ArrayList<>();

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
                    System.out.printf("%d. %s\n", index+1, list.get(index).toString());
                }
            }
            else if(command.length() >= 5 && command.substring(0, 5).equalsIgnoreCase("mark ")){
                String markIndexStr = command.substring(5).strip();
                if(markIndexStr.matches("^\\d+$")) {
                    int markIndex = Integer.parseInt(markIndexStr) - 1;
                    if(0 <= markIndex && markIndex < list.size()){
                        boolean wasNotMarked = list.get(markIndex).markDone();
                        if(wasNotMarked){
                            System.out.println("Marking task as done: " + list.get(markIndex).toString());
                        }
                        else{
                            System.out.println("Task was already marked as done: " + list.get(markIndex).toString());
                        }
                    }
                    else{
                        System.out.println("Invalid task to mark: " + markIndexStr);
                    }
                }
                else{
                    System.out.println("Invalid task to mark: " + markIndexStr);
                }
            }
            else if(command.length() >= 7 && command.substring(0, 7).equalsIgnoreCase("unmark ")){
                String unmarkIndexStr = command.substring(7).strip();
                if(unmarkIndexStr.matches("^\\d+$")) {
                    int markIndex = Integer.parseInt(unmarkIndexStr) - 1;
                    if(0 <= markIndex && markIndex < list.size()){
                        boolean wasNotMarked = list.get(markIndex).unmarkDone();
                        if(wasNotMarked){
                            System.out.println("Marking task as undone: " + list.get(markIndex).toString());
                        }
                        else{
                            System.out.println("Task was already marked as undone: " + list.get(markIndex).toString());
                        }
                    }
                    else{
                        System.out.println("Invalid task to unmark: " + unmarkIndexStr);
                    }
                }
                else{
                    System.out.println("Invalid task to unmark: " + unmarkIndexStr);
                }
            }
            else {
                list.add(new Task(command));
                System.out.println("Added: " + command);
            }
            System.out.println(HORIZONTAL_LINE);
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
