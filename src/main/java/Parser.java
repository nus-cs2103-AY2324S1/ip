import java.util.ArrayList;
public class Parser {
    public Parser() {
    }
    public void parse(String s, ArrayList<SingleTask> taskList) throws DukeException {
        if (s.matches(".*\\bdelete\\b.*")) {
            String[] parts = s.split(" ");
            if (parts.length < 2) {
                throw new DukeException("Boy ah need to know which one u want delete eh.");
            }
            int number = Integer.parseInt(parts[1]);
            if (number - 1 >=  taskList.size()) {
                throw new DukeException("Boy ah cannot delete a task you dont have eh.");
            }
            SingleTask task = taskList.get(number - 1);
            System.out.println(task.remove());
            taskList.remove(number - 1);
            System.out.println(String.format("Got %d task in list boy", taskList.size()));
        } else if (s.equals("list")) {
            if (taskList.size() == 0) {
                System.out.println("No tasks here ah boy");
            } else {
                System.out.println("Here are your tasks ah boy:");
                for (int i = 0; i < taskList.size(); i++) {
                    SingleTask task = taskList.get(i);
                    System.out.println((i + 1) + task.listString());
                }
            }
        } else if (s.matches(".*\\bmark\\b.*")) {
            String[] parts = s.split(" ");
            int number = Integer.parseInt(parts[1]);
            SingleTask task = taskList.get(number - 1);
            task.mark();
        } else if (s.matches(".*\\bunmark\\b.*")) {
            String[] parts = s.split(" ");
            int number = Integer.parseInt(parts[1]);
            SingleTask task = taskList.get(number - 1);
            task.unmark();
        } else if (s.matches("(?i)^\\s*(todo|event|deadline)\\b.*")) {
            String[] parts = s.split(" ", 2);
            String TypeOfEvent = parts[0].toLowerCase();
            switch (TypeOfEvent) {
                case "todo":
                    if (parts.length < 2) {
                        throw new DukeException("Boy ah todo need description eh.");
                    }
                    String content = parts[1];
                    SingleTask taskT = new ToDo(content);
                    taskList.add(taskT);
                    System.out.println(taskT.toString());
                    System.out.println(String.format("Got %d task in list boy", taskList.size()));
                    break;
                case "deadline":
                    if (parts.length < 2) {
                        throw new DukeException("Boy need to know when the deadline is eh.");
                    }
                    String[] part = parts[1].split("/by ");
                    if (part.length < 2) {
                        throw new DukeException("BOY AH The deadline need to write a /by time!!");
                    }
                    SingleTask taskD = new Deadline(part[0], part[1]);
                    taskList.add(taskD);
                    System.out.println(taskD.toString());
                    System.out.println(String.format("Got %d task in list boy", taskList.size()));
                    break;
                case "event":
                    if (parts.length < 2) {
                        throw new DukeException("Boy need to know the event description eh.");
                    }
                    String[] strarray = parts[1].split("/");
                    if (strarray.length < 3) {
                        throw new DukeException("BOY!! The event command need /from and /to times.");
                    }
                    String from = strarray[1].replace("from", "").trim();
                    String to = strarray[2].replace("to", "").trim();
                    SingleTask taskE = new Event(strarray[0], from, to);
                    taskList.add(taskE);
                    System.out.println(taskE.toString());
                    System.out.println(String.format("Got %d task in list boy", taskList.size()));
                    break;
                default:
            }
        } else {
            throw new DukeException("Boy idk what you saying eh must tell me todo or deadline or event :(");
        }
    }
}
