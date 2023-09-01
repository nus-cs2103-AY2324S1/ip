import Exceptions.InvalidInputException;

public class Parser {

    public static boolean parseInput(Storage storage, TaskList list, String input) throws InvalidInputException {
        int index;
        String desc;
        String[] data = input.split(" ");
        StringBuilder builder = new StringBuilder();

        switch (data[0]) {
        case "bye":
            return true;
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.print(i + 1);
                System.out.println("." + list.get(i));
            }
            break;
        case "mark":
            if (data.length == 1) {
                throw new InvalidInputException("Task index must be specified");
            }
            index = Integer.parseInt(data[1]) - 1;
            list.get(index).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(index));
            storage.editTask("mark", index);
            break;
        case "unmark":
            if (data.length == 1) {
                throw new InvalidInputException("Task index must be specified");
            }
            index = Integer.parseInt(data[1]) - 1;
            list.get(index).setUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list.get(index));
            storage.editTask("unmark", index);
            break;
        case "todo":
            if (data.length == 1) {
                throw new InvalidInputException("Todo description cannot be empty");
            }
            for (int i = 1; i < data.length; i++) {
                builder.append(data[i]);
            }
            desc = builder.toString().trim();
            Todo todo = new Todo(desc);
            list.add(todo);
            System.out.println("Got it. I've added this task:");
            System.out.println(todo);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            storage.saveTask(todo);
            break;
        case "deadline":
            if (data.length == 1) {
                throw new InvalidInputException("Deadline description cannot be empty");
            }
            index = 1;
            while (!data[index].equals("/by")) {
                builder.append(data[index]).append(" ");
                index++;
            }
            desc = builder.toString().trim();
            builder.setLength(0);
            index++;
            while (index < data.length) {
                builder.append(data[index]).append(" ");
                index++;
            }
            String by = builder.toString().trim();
            Deadline deadline = new Deadline(desc, by);
            list.add(deadline);
            System.out.println("Got it. I've added this task:");
            System.out.println(deadline);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            storage.saveTask(deadline);
            break;
        case "event":
            if (data.length == 1) {
                throw new InvalidInputException("Event description cannot be empty");
            }
            index = 1;
            while (!data[index].equals("/from")) {
                builder.append(data[index]).append(" ");
                index++;
            }
            desc = builder.toString().trim();
            builder.setLength(0);
            index++;
            while (!data[index].equals("/to")) {
                builder.append(data[index]).append(" ");
                index++;
            }
            String start = builder.toString().trim();
            builder.setLength(0);
            index++;
            while (index < data.length) {
                builder.append(data[index]).append(" ");
                index++;
            }
            String end = builder.toString().trim();
            Event event = new Event(desc, start, end);
            list.add(event);
            System.out.println("Got it. I've added this task:");
            System.out.println(event);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            storage.saveTask(event);
            break;
        case "delete":
            if (data.length == 1) {
                throw new InvalidInputException("Task index must be specified");
            }
            index = Integer.parseInt(data[1]) - 1;
            Task deleted = list.get(index);
            list.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(deleted);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            storage.editTask("delete", index);
            break;
        default:
            throw new InvalidInputException("I don't understand. Please check your input again.");
        }
        return false;
    }
}
