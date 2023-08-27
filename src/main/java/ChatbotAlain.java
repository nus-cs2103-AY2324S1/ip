import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;

public class ChatbotAlain{
    private static void saveTasksToFile(ArrayList<Task> list, String fileName, Boolean except, String msg) throws IOException {
        File listFile = new File(fileName);
        if (!listFile.exists()) {
            listFile.createNewFile();
        }
        FileWriter writer = new FileWriter(listFile);
        String filecontent = "";
        if (except) {
           filecontent +="Oops! Seems like there is an exception detected in your input\n";
           filecontent += msg + "\n";
        } else {
            filecontent += "____________________________________________________________\n"
                    + "Here are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                filecontent += " " + (i + 1) + ". " + list.get(i) + "\n";
            }
            filecontent += "____________________________________________________________\n";
        }
        writer.write(filecontent);
        writer.close();
    }
    public static void main(String[] args) throws AlainException, IOException {

        try {
            ArrayList<Task> list = new ArrayList<>();
            int numDeleted = 0;
            String renameAndGreeting = "____________________________________________________________\n"
                    + " Hello! I'm Alain\n"
                    + " What can I do for you?\n"
                    + "____________________________________________________________\n";
            System.out.println(renameAndGreeting);
            while (true) {
                Scanner s = new Scanner(System.in);
                String text = new String();
                text = s.nextLine();
                boolean isMatchMark = Pattern.matches("mark \\d+", text);
                boolean isMatchUnmark = Pattern.matches("unmark \\d+", text);
                boolean isDeadline = Pattern.matches("deadline .+", text);
                boolean isToDo = Pattern.matches("todo .+", text);
                boolean isEvent = Pattern.matches("event .+", text);
                boolean isDelete = Pattern.matches("delete .+", text);
                if (isDelete) {
                    String numericPart = text.substring(7);
                    int pos = Integer.parseInt(numericPart) - 1;
                    if (pos >= 0 && pos < list.size()) {
                        Task removedTask = list.remove(pos);
                        String output = "____________________________________________________________\n"
                                + " Noted. I've removed this task:\n"
                                + "   " + removedTask + "\n"
                                + " Now you have " + list.size() + " tasks in the list.\n"
                                + "____________________________________________________________\n";
                        System.out.println(output);
                    } else {
                        throw new AlainException(" ☹ OOPS!!! Invalid task index.");
                    }
                    continue;
                }
                if (isToDo) {
                    String mission = text.substring(4);
                    if (mission.length() == 0) {
                        throw new AlainException(" ☹ OOPS!!! The description of a Todo cannot be empty.");
                    }
                    list.add(new ToDos(mission));
                    String output = "____________________________________________________________\n"
                            + " Got it. I've added this task:\n"
                            + "   " + list.get(list.size() - 1) + "\n"
                            + " Now you have " + list.size() + " tasks in the list.\n"
                            + "____________________________________________________________\n";
                    System.out.println(output);
                    continue;
                }
                if (isDeadline) {
                    String mission = text.substring(8);
                    if (mission.length() == 0) {
                        throw new AlainException(" ☹ OOPS!!! The description of a Deadline cannot be empty.");
                    }
                    String[] parts = mission.split("/");
                    if (parts.length != 2) {
                        throw new AlainException(" ☹ OOPS!!! The description of a Deadline is invalid");
                    }
                    list.add(new Deadlines(parts[0], parts[1]));
                    String output = "____________________________________________________________\n"
                            + " Got it. I've added this task:\n"
                            + "   " + list.get(list.size() - 1) + "\n"
                            + " Now you have " + list.size() + " tasks in the list.\n"
                            + "____________________________________________________________\n";
                    System.out.println(output);
                    continue;
                }
                if (isEvent) {
                    String mission = text.substring(5);
                    if (mission.length() == 0) {
                        throw new AlainException(" ☹ OOPS!!! The description of a Event cannot be empty.");
                    }
                    String[] parts = mission.split("/");
                    if (parts.length != 3) {
                        throw new AlainException(" ☹ OOPS!!! The description of a Event is invalid");
                    }
                    list.add(new Events(parts[0], parts[1], parts[2]));
                    String output = "____________________________________________________________\n"
                            + " Got it. I've added this task:\n"
                            + "   " + list.get(list.size() - 1) + "\n"
                            + " Now you have " + list.size() + " tasks in the list.\n"
                            + "____________________________________________________________\n";
                    System.out.println(output);
                    continue;
                }
                if (text.equals("bye")) {
                    break;
                } else if (isMatchMark) {
                    String numericPart = text.substring(5);
                    list.get(Integer.parseInt(numericPart) - 1).markAsDone();
                    String output = "____________________________________________________________\n"
                            + " Nice! I've marked this task as done:\n"
                            + "   " + list.get(Integer.parseInt(numericPart) - 1) + "\n"
                            + "____________________________________________________________\n";
                    System.out.println(output);
                    continue;
                } else if (isMatchUnmark) {
                    String numericPart = text.substring(7);
                    list.get(Integer.parseInt(numericPart) - 1).markAsUndone();
                    String output = "____________________________________________________________\n"
                            + " OK, I've marked this task as not done yet:\n"
                            + "   " + list.get(Integer.parseInt(numericPart) - 1) + "\n"
                            + "____________________________________________________________\n";
                    System.out.println(output);
                    continue;
                } else if (text.equals("list")) {
                    String output = "";
                    output += "____________________________________________________________\n"
                            + "Here are the tasks in your list:\n";
                    for (int i = 0; i < list.size(); i++) {
                        output += " " + (i + 1) + ". " + list.get(i) + "\n";
                    }
                    output += "____________________________________________________________\n";
                    System.out.println(output);
                    continue;
                }
                throw new AlainException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            String endingLine = "____________________________________________________________\n"
                    + " Bye. Hope to see you again soon!\n"
                    + "____________________________________________________________\n";
            System.out.println(endingLine);
            saveTasksToFile(list, "list.txt", false, null);
        } catch (AlainException e) {
            System.out.println("____________________________________________________________\n");
            System.out.println(e.getMessage());
            System.out.println("____________________________________________________________\n");
            saveTasksToFile(null, "list.txt", true, e.getMessage());
        }
    }
}
