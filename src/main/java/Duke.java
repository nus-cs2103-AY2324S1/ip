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
            else{
                int commandEndIndex = 0;
                while(commandEndIndex < command.length() && command.charAt(commandEndIndex) != ' '){
                    commandEndIndex++;
                }
                String commandName = command.substring(0, commandEndIndex).toLowerCase();
                String commandArgs = command.substring(commandEndIndex).strip();
                if(commandName.equals("list")){
                    if(!commandArgs.isEmpty()){
                        System.out.println("list does not take in arguments!");
                    }
                    else {
                        for (int index = 0; index < list.size(); index++) {
                            System.out.printf("%d. %s\n", index + 1, list.get(index).toString());
                        }
                    }
                }
                else if(commandName.equals("mark")){
                    if(commandArgs.matches("^\\d+$")) {
                        int markIndex = Integer.parseInt(commandArgs) - 1;
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
                            System.out.println("Invalid task to mark: " + commandArgs);
                        }
                    }
                    else{
                        System.out.println("Invalid task to mark: " + commandArgs);
                    }
                }
                else if(commandName.equals("unmark")){
                    if(commandArgs.matches("^\\d+$")) {
                        int unmarkIndex = Integer.parseInt(commandArgs) - 1;
                        if(0 <= unmarkIndex && unmarkIndex < list.size()){
                            boolean wasNotMarked = list.get(unmarkIndex).unmarkDone();
                            if(wasNotMarked){
                                System.out.println("Marking task as undone: " + list.get(unmarkIndex).toString());
                            }
                            else{
                                System.out.println("Task was already marked as undone: " + list.get(unmarkIndex).toString());
                            }
                        }
                        else{
                            System.out.println("Invalid task to unmark: " + commandArgs);
                        }
                    }
                    else{
                        System.out.println("Invalid task to unmark: " + commandArgs);
                    }
                }
                else if(commandName.equals("todo")){
                    list.add(new ToDo(commandArgs));
                    System.out.println("Added todo item: " + list.get(list.size()-1));
                }
                else if(commandName.equals("deadline")){
                    int byIndex = commandArgs.indexOf("/by");
                    if(byIndex == -1){
                        System.out.println("Deadline missing a /by argument!");
                    }
                    else {
                        String desc = commandArgs.substring(0, byIndex).strip();
                        String by = commandArgs.substring(byIndex + "/by".length()).strip();
                        list.add(new Deadline(desc, by));
                        System.out.println("Added deadline item: " + list.get(list.size()-1));
                    }
                }
                else if(commandName.equals("event")){
                    int fromIndex = commandArgs.indexOf("/from");
                    int toIndex = commandArgs.indexOf("/to");
                    if(fromIndex == -1){
                        System.out.println("Event missing a /from argument!");
                    }
                    else if(toIndex == -1){
                        System.out.println("Event missing a /to argument!");
                    }
                    else {
                        String desc, from, to;
                        if (fromIndex < toIndex) {
                            desc = commandArgs.substring(0, fromIndex).strip();
                            from = commandArgs.substring(fromIndex + "/from".length(), toIndex).strip();
                            to = commandArgs.substring(toIndex + "/to".length()).strip();
                        } else {
                            desc = commandArgs.substring(0, toIndex).strip();
                            from = commandArgs.substring(fromIndex + "/from".length()).strip();
                            to = commandArgs.substring(toIndex + "/to".length(), fromIndex).strip();
                        }
                        list.add(new Event(desc, from, to));
                        System.out.println("Added event item: " + list.get(list.size()-1));
                    }
                }
                else{
                    System.out.println("Error: " + commandName + " is not a valid command!");
                }
            }
            System.out.println(HORIZONTAL_LINE);
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
