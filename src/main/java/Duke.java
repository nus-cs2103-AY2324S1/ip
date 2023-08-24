import java.util.Scanner;

public class Duke {

    public static void talk(String str) {
        String line = "_".repeat(50);
        System.out.println(line);
        System.out.println(str);
        System.out.println(line);
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm JED, your personal chat-bot!\nWhat can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        Task[] items = new Task[100];
        int count = 0;

        Scanner sc = new Scanner(System.in);

        talk(greeting);

        while (true) {
            try {
                String input = sc.nextLine();
                String[] inputArr = input.split(" ", 2);
                String keyword = inputArr[0];
                if (keyword.equals("bye")) {
                    talk(goodbye);
                    break;
                }
                if (keyword.equals("list")) {
                    String list = "";
                    for (int i = 0; i < count; i++) {
                        list += "  " + (i + 1) + ". " + items[i] + "\n";
                    }
                    talk(list);
                    continue;
                }

                String description = "";
                switch (keyword) {
                    case "mark":
                        if (inputArr.length != 2) {
                            throw new DukeException("OOPS!!! Please include the task number you would like to mark.");
                        }
                        description = input.split(" ", 2)[1];
                        int indexMark = Integer.parseInt(description) - 1;
                        items[indexMark].markDone();
                        talk("Nice! I've marked this task as done:\n  " + items[indexMark]);
                        break;
                    case "unmark":
                        if (inputArr.length != 2) {
                            throw new DukeException("OOPS!!! Please include the task number you would like to unmark.");
                        }
                        description = input.split(" ", 2)[1];
                        int indexUnmark = Integer.parseInt(description) - 1;
                        items[indexUnmark].markUnDone();
                        talk("OK, I've marked this task as not done yet:\n  " + items[indexUnmark]);
                        break;
                    case "todo":
                        if (inputArr.length != 2) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        description = input.split(" ", 2)[1];
                        items[count] = new ToDo(description);
                        talk("Got it. I've added this task:\n  " + items[count] + "\n Now you have " + (count + 1) + " tasks in your list.");
                        count++;
                        break;
                    case "deadline":
                        if (inputArr.length != 2) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                        }
                        description = input.split(" ", 2)[1];
                        String deadlineName = "";
                        String deadlineBy = "";
                        String[] deadlineDescription = description.split("/");
                        for (String str : deadlineDescription) {
                            if (str.startsWith("by")) {
                                deadlineBy = str.split(" ", 2)[1].trim();
                            } else {
                                deadlineName = str.trim();
                            }
                        }
                        if (deadlineName == "") {
                            throw new DukeException("OOPS!! Please include the name of the deadline.");
                        }
                        if (deadlineBy == "") {
                            throw new DukeException("OOPS!! Please include when the deadline is by.");
                        }
                        items[count] = new Deadline(deadlineName, deadlineBy);
                        talk("Got it. I've added this task:\n  " + items[count] + "\n Now you have " + (count + 1) + " tasks in your list.");
                        count++;
                        break;
                    case "event":
                        if (inputArr.length != 2) {
                            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                        }
                        description = input.split(" ", 2)[1];
                        String eventName = "";
                        String eventFrom = "";
                        String eventTo = "";
                        String[] eventDescription = description.split("/");
                        for (String str : eventDescription) {
                            if (str.startsWith("from")) {
                                eventFrom = str.split(" ", 2)[1].trim();
                            } else if (str.startsWith("to")) {
                                eventTo = str.split(" ", 2)[1].trim();
                            } else {
                                eventName = str.trim();
                            }
                        }
                        if (eventName == "") {
                            throw new DukeException("OOPS!! Please include the name of the event.");
                        }
                        if (eventFrom == "") {
                            throw new DukeException("OOPS!! Please include when the event is from.");
                        }
                        if (eventTo == "") {
                            throw new DukeException("OOPS!! Please include when the event is till.");
                        }
                        items[count] = new Event(eventName, eventFrom, eventTo);
                        talk("Got it. I've added this task:\n  " + items[count] + "\n Now you have " + (count + 1) + " tasks in your list.");
                        count++;
                        break;
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                talk(e.getMessage());
            }
        }
    }
}
