import java.util.ArrayList;
import java.util.Scanner;

public class URChatBot {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String logo =
                "         _____   _____\n"
                        + "| | | | /  ___| |   ) | \n"
                        + "| | | | | |     | ___ /\n"
                        + "| |_| | | |___  |   ) \\\n"
                        + "\\___,_| \\_____| |_____|\n";
        System.out.println("Hello! I'm URChatBot.\nWhat can I do for you?\n" + logo);
        while (true) {
            try{
            String command = sc.nextLine();
            if (command.toUpperCase().contentEquals("BYE")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (command.toUpperCase().contentEquals("LIST")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < tasks.size(); i ++) {
                        System.out.println(i+1 + "." + tasks.get(i).toString());
                }
            } else if (command.toUpperCase().startsWith("MARK")){
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                tasks.get(value - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks.get(value - 1).toString());

            } else if (command.toUpperCase().startsWith("UNMARK")){
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                tasks.get(value - 1).markAsUnDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(value - 1).toString());
            } else if (command.toUpperCase().startsWith("DELETE")){
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                if (tasks.size() < 1 || tasks.size() < value) {
                    throw new URChatBotException("OOPS!!! No Task to delete!");
                }
                Task deletedTask = tasks.get(value - 1);
                tasks.remove(value - 1);
                if (tasks.size() == 1 || tasks.size() ==0){
                    System.out.println("Noted. I've removed this task:\n  " + deletedTask.toString() + "\nNow you have " + tasks.size() + " task in the list.");
                } else {
                    System.out.println("Noted. I've removed this task:\n  " + deletedTask.toString() + "\nNow you have " + tasks.size()  + " tasks in the list.");
                }
            } else if (command.toUpperCase().startsWith("TODO")){
                if (command.length() <= 5) {
                    throw new URChatBotException("OOPS!!! The description of a todo cannot be empty.");
                }
                String task = command.substring(command.indexOf("todo") + 5);
                Task newTask = new ToDo(task);
                tasks.add(newTask);
                if (tasks.size() == 1 || tasks.size() ==0){
                    System.out.println("Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " task in the list.");
                } else {
                    System.out.println("Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size()  + " tasks in the list.");
                }
            } else if (command.toUpperCase().startsWith("DEADLINE")){
                if (command.length() <= 5) {
                    throw new URChatBotException("OOPS!!! The description of a deadline cannot be empty.");
                }
                if (!command.contains("/by") || command.substring(command.indexOf("/by") + 3).trim().length() < 1) {
                    throw new URChatBotException("OOPS!!! The deadline cannot be empty.");
                }
                String task = command.substring(command.indexOf("deadline") + 9, command.indexOf("/by") - 1);
                String by = command.substring(command.indexOf("/by") + 4);
                Task newTask = new Deadline(task,by);
                tasks.add(newTask);
                if (tasks.size() == 1 || tasks.size() ==0){
                    System.out.println("Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " task in the list.");
                } else {
                    System.out.println("Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size()  + " tasks in the list.");
                }
            } else if (command.toUpperCase().startsWith("EVENT")){
                if (command.length() <= 5) {
                    throw new URChatBotException("OOPS!!! The description of a todo cannot be empty.");
                }
                if (!command.contains("/from") || !command.contains("/to") || command.substring(command.indexOf("/from") + 5, command.indexOf("/to") - 1).trim().length() < 1|| command.substring(command.indexOf("/to") + 3).trim().length() < 1) {
                    throw new URChatBotException("OOPS!!! The from or/and to cannot be empty.");
                }
                String task = command.substring(command.indexOf("event") + 6, command.indexOf("/from") - 1);
                String from = command.substring(command.indexOf("/from") + 6, command.indexOf("/to") - 1);
                String to = command.substring(command.indexOf("/to") + 4);
                Task newTask = new Event(task, from, to);
                tasks.add(newTask);
                if (tasks.size() == 1 || tasks.size() ==0){
                    System.out.println("Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " task in the list.");
                } else {
                    System.out.println("Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size()  + " tasks in the list.");
                }
            } else{
                throw new URChatBotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }catch (URChatBotException e) {
                System.out.println(" " + e.getMessage());
            }
        }

    }
}
