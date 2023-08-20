import Exceptions.EmptyTasksException;
import Exceptions.InvalidArgumentException;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidTaskDescriptionException;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;

public class Commands {

    private enum CommandsList {
        LIST,
        TODO,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        BYE
    }

    private String cmd;
    private ArrayList<Task> taskList;
    String divider = "\n____________________________________________________________";
    String endLogo = "               ＿   ★★EVERYDAY★★\n" +
            "           ／     j     ★★ IS A  ★★\n" +
            "        ／     /ｰ'          ★★ MACHO  ★★\n" +
            "     〈       ヽ               ★★ DAY!!!  ★★\n" +
            "           ､       ヽ ﾍ⌒ ヽﾌ\n" +
            "             〉       ´ ･ω )        ,-､、\n" +
            "           / ノ         ￣⌒ヽ　「　   〉\n" +
            "          ﾉ       ' L          `ヽ.／   /\n" +
            "     ／    , '           .ノ＼    ´    /\n" +
            "    (                ∠_       ヽ､＿,.\n" +
            "     ＼   (            ヽ ";

    public Commands(String cmd, ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.cmd = cmd;
    }

    public String getList() {
        if (this.taskList.isEmpty()) {
            return "No tasks recorded, macho!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            Task task = this.taskList.get(i);
            sb.append(index).append(".").append(task.toString()).append("\n");
        }

        return sb.toString().trim();
    }

    public String createToDo(String input) throws InvalidTaskDescriptionException {
        String taskDesc = input.split(" ", 2)[1];
        if (input.split(" ").length < 2) {
            throw new InvalidTaskDescriptionException("To-Do task");
        } else {
            Todo td = new Todo(taskDesc, false);
            this.taskList.add(td);

            return "Got it macho! I've added this task:\n" + td.toString() + "\n" +
                    "You now have " + this.taskList.size() + " tasks in the list, macho!";
        }
    }

    public String createDeadline(String input) throws InvalidArgumentException {
        String[] parts = input.split("/by", 2);
        if (parts.length == 1) {
            throw new InvalidArgumentException(input.substring(9), "/by");
        } else {
            String taskDesc = parts[0].split(" ", 2)[1];
            String by = parts[1];
            Deadline dl = new Deadline(taskDesc, false, by);
            this.taskList.add(dl);

            return "Got it macho! I've added this task:\n" + dl.toString() + "\n" + "You now have " + this.taskList.size()
                    + " tasks in the list, macho!";
        }
    }

    public String createEvent(String input) throws InvalidArgumentException {
        String[] parts = input.split("\\s+/from\\s+|\\s+/to\\s+");
        if (parts.length < 3) {
            throw new InvalidArgumentException(input.substring(6), "/from and /to");
        } else {
            String taskDesc = parts[0].split(" ", 2)[1];
            String afterFrom = parts[1];
            String afterTo = parts[2];
            Events ev = new Events(taskDesc, false, afterFrom, afterTo);
            this.taskList.add(ev);
            return "Got it macho! I've added this task:\n" + ev.toString() + "\n" + "You now have " + this.taskList.size()
                    + " tasks in the list, macho!";
        }
    }

    public String unmark(String input) throws EmptyTasksException {
        int index = Integer.parseInt(input.split(" ", 2)[1]);
        if (index < 0 || this.taskList.isEmpty()) {
            throw new EmptyTasksException(input);
        } else {
            Task task = this.taskList.get(index - 1);
            task.markedAsUndone();
            return "I have marked this task as undone yet, per your request, macho!\n" + task.toString();
        }
    }

    public String mark(String input) throws EmptyTasksException {
        int index = Integer.parseInt(input.split(" ", 2)[1]);
        if (index < 0 || this.taskList.isEmpty()) {
            throw new EmptyTasksException(input);
        } else {
            Task task = this.taskList.get(index - 1);
            task.markedAsDone();
            return "I have marked this task as done per your request, macho!\n" + task.toString();
        }
    }

    public ArrayList<Task> execute(String input) throws InvalidCommandException {
        CommandsList command;
        try {
            try {
                System.out.println(divider);
                command = CommandsList.valueOf(this.cmd.toUpperCase());
            } catch (Exception e) {
                throw new InvalidCommandException(this.cmd);
            }

            switch (command) {
                case LIST:
                    System.out.println(getList());
                    break;

                case TODO:
                    System.out.println(createToDo(input));
                    break;

                case DEADLINE:
                    System.out.println(createDeadline(input));
                    break;

                case EVENT:
                    System.out.println(createEvent(input));
                    break;

                case UNMARK:
                    System.out.println(unmark(input));
                    break;

                case MARK:
                    System.out.println(mark(input));
                    break;

                case BYE:
                    System.out.println("\nBye! Hope to see you again soon, macho!\n" + endLogo);
                    break;

                default:
                    System.out.println("Invalid command macho! Please try again!");
            }
            System.out.println(divider);
        } catch (InvalidArgumentException | EmptyTasksException | InvalidCommandException |
                 InvalidTaskDescriptionException e) {
            System.out.println(e.getMessage());
            System.out.println(divider);
            return this.taskList;
        }

        return this.taskList;
    }


}
