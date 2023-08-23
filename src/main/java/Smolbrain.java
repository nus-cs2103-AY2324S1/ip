import java.util.ArrayList;
import java.util.Scanner;

public class Smolbrain {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Smolbrain\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> data = new ArrayList<>();
        boolean run = true;

        while(run) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________");
            String[] words = input.split(" ");
            String descr = "";

//            try {
//
//            } catch {
//
//            }

            switch (words[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < data.size(); i++) {
                        System.out.println((i+1) + ". " + data.get(i));
                    }
                    break;

                case "todo":
                    if (words.length < 2) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        break;
                    }
                    for (int i = 1; i < words.length; i++) {
                        descr = descr + words[i] + " ";
                    }
                    descr = descr.substring(0, descr.length()-1);
                    Todo todo = new Todo(descr);
                    data.add(todo);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todo);
                    System.out.println("Now you have " + data.size() +" tasks in the list.");
                    break;

                case "deadline":
                    boolean by = false;
                    String by_text = "";
                    for (int i = 1; i < words.length; i++) {
                        if (words[i].equals("/by")) {
                            by = true;
                            continue;
                        }
                        if (by) {
                            by_text = by_text + words[i] + " ";
                        } else {
                            descr = descr + words[i] + " ";
                        }
                    }
                    if (descr.equals("")) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        break;
                    } else if (by_text.equals("")) {
                        System.out.println("Please provide a time for deadline");
                        break;
                    }
                    descr = descr.substring(0, descr.length()-1);
                    by_text = by_text.substring(0, by_text.length()-1);
                    Deadline deadline = new Deadline(descr, by_text);
                    data.add(deadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(deadline);
                    System.out.println("Now you have " + data.size() +" tasks in the list.");
                    break;

                case "event":
                    boolean from = false;
                    boolean to = false;
                    String from_text = "";
                    String to_text = "";
                    for (int i = 1; i < words.length; i++) {
                        if (words[i].equals("/from")) {
                            from = true;
                            continue;
                        } else if (words[i].equals("/to")){
                            to = true;
                            continue;
                        }
                        if (to) {
                            to_text = to_text + words[i] + " ";
                        } else if (from) {
                            from_text = from_text + words[i] + " ";
                        } else {
                            descr = descr + words[i] + " ";
                        }
                    }
                    if (descr.equals("")) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                        break;
                    } else if (from_text.equals("")) {
                        System.out.println("Please provide a start time for event");
                        break;
                    } else if (to_text.equals("")) {
                        System.out.println("Please provide a end time for event");
                        break;
                    }
                    descr = descr.substring(0, descr.length()-1);
                    from_text = from_text.substring(0, from_text.length()-1);
                    to_text = to_text.substring(0, to_text.length()-1);
                    Event event = new Event(descr, from_text, to_text);
                    data.add(event);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(event);
                    System.out.println("Now you have " + data.size() +" tasks in the list.");
                    break;

                case "mark":
                    if (words.length < 2) {
                        System.out.println("Please give the number of the task to mark.");
                        break;
                    }
                    int marknum;
                    try {
                        marknum = Integer.parseInt(words[1]);
                        data.get(marknum-1).mark();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(data.get(marknum-1));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Please provide a valid number of the task to mark.");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please provide a valid number within the range.");
                        break;
                    }

                case "unmark":
                    if (words.length < 2) {
                        System.out.println("Please give the number of the task to unmark.");
                        break;
                    }
                    int unmarknum;
                    try {
                        unmarknum = Integer.parseInt(words[1]);
                        data.get(unmarknum-1).unmark();
                        System.out.println("OK, I've marked this task as not done yet: ");
                        System.out.println(data.get(unmarknum-1));
                        break;
                    } catch (NumberFormatException ex) {
                        System.out.println("Please provide a valid number of the task to unmark.");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please provide a valid number within the range.");
                        break;
                    }

                case "delete":
                    if (words.length < 2) {
                        System.out.println("Please give the number of the task to delete.");
                        break;
                    }
                    int delnum;
                    try {
                        delnum = Integer.parseInt(words[1]);
                        String task = data.get(delnum-1).toString();
                        data.remove(delnum-1);
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(task);
                        System.out.println("Now you have " + data.size() +" tasks in the list.");
                        break;
                    } catch (NumberFormatException ex) {
                        System.out.println("Please provide a valid number of the task to delete.");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please provide a valid number within the range.");
                        break;
                    }

                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    run = false;
                    break;

                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }

            System.out.println("____________________________________________________________\n");
        }

    }
}
