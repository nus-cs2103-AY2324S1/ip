package anya.parser;

import java.time.LocalDateTime;
import java.util.Scanner;

import anya.command.Command;
import anya.exception.AnyaException;
import anya.exception.InvalidArgumentException;
import anya.storage.Storage;
import anya.task.Deadline;
import anya.task.Event;
import anya.task.Task;
import anya.task.TaskList;
import anya.task.Todo;

public class Parser {
    private Storage storage;
    private TaskList tasks;
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    public Command parseCommand(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        }
        if (input.equals("list")) {
            return Command.LIST;
        }
        if (input.equals("mark")) {
            return Command.MARK;
        }
        if (input.equals("unmark")) {
            return Command.UNMARK;
        }
        if (input.equals("todo")) {
            return Command.TODO;
        }
        if (input.equals("deadline")) {
            return Command.DEADLINE;
        }
        if (input.equals("event")) {
            return Command.EVENT;
        }
        if (input.equals("delete")) {
            return Command.DELETE;
        }
        return Command.UNKNOWN;
    }

    public void parse() {
        Scanner sc = new Scanner(System.in);

        scan:
        while (true) {
            try {
                String[] arguments = sc.nextLine().split(" ", 2);
                Command command = parseCommand(arguments[0]);
                String details;
                if (arguments.length == 1) {
                    details = "";
                } else {
                    details = arguments[1];
                }

                switch (command) {
                    case BYE:
                        storage.save(tasks);
                        break scan;
                    case LIST:
                        tasks.list();
                        break;
                    case MARK: {
                        // Error: No argument or Multiple arguments provided
                        if (details.isEmpty() || details.split(" ").length != 1) {
                            throw new InvalidArgumentException("☹ Waku waku! "
                                    + "Please only input ONE integer after the word mark!");
                        }
                        // Error: Argument provided is not a number
                        try {
                            Integer.parseInt(details);
                        } catch (NumberFormatException e) {
                            throw new InvalidArgumentException("☹ Waku waku! "
                                    + "Please only input INTEGERs after the word mark!");
                        }
                        // Error: Argument provided is not within anya.task numbers
                        int taskNumber = Integer.parseInt(details);
                        if (taskNumber < 1 || taskNumber > tasks.size()) {
                            throw new InvalidArgumentException("☹ Waku waku! "
                                    + "I don't see a task with the number:" + taskNumber);
                        }
                        // Error: Argument provided is already Done (Future implementation)

                        Task t = tasks.get(taskNumber - 1);
                        t.markAsDone();

                        System.out.println("    Waku waku! I've marked this task as Done:\n" + t);
                        break;
                    }
                    case UNMARK: {
                        // Error: No argument or Multiple arguments provided
                        if (details.isEmpty() || details.split(" ").length != 1) {
                            throw new InvalidArgumentException("☹ Waku waku! "
                                    + "Please only input ONE integer after the word unmark!");
                        }
                        // Error: Argument provided is not a number
                        try {
                            Integer.parseInt(details);
                        } catch (NumberFormatException e) {
                            throw new InvalidArgumentException("☹ Waku waku! "
                                    + "Please only input INTEGERs after the word unmark!");
                        }
                        // Error: Argument provided is not within anya.task numbers
                        int taskNumber = Integer.parseInt(details);
                        if (taskNumber < 1 || taskNumber > tasks.size()) {
                            throw new InvalidArgumentException("☹ Waku waku! "
                                    + "I don't see a task with the number:" + taskNumber);
                        }

                        Task t = tasks.get(taskNumber - 1);
                        t.markAsNotDone();

                        System.out.println("    Waku waku! I've marked this task as Not Done:\n" + t);
                        break;
                    }
                    case TODO: {
                        // Error: No argument provided
                        if (details.isEmpty()) {
                            throw new InvalidArgumentException("☹ Waku waku! "
                                    + "Please input a description after the word todo!");
                        }

                        Task t = new Todo(details);
                        tasks.add(t);

                        System.out.println("    Waku waku! I've added this task:\n" + t);
                        System.out.println("    Now you have " + tasks.size() + " tasks in the list!");
                        break;
                    }
                    case DEADLINE: {
                        // Error: No argument or wrong no of arguments provided
                        String[] info = details.split("/by");
                        if (details.isEmpty() || info.length != 2) {
                            throw new InvalidArgumentException("☹ Waku waku! Please input in the following format: "
                                    + "    deadline <taskName> /by <deadline>");
                        }

                        String taskName = info[0].trim();
                        String deadline = info[1].trim();
                        Task t = new Deadline(taskName, deadline);
                        tasks.add(t);

                        System.out.println("    Waku waku! I've added this task:\n" + t);
                        System.out.println("    Now you have " + tasks.size() + " tasks in the list!");
                        break;
                    }
                    case EVENT: {
                        // Error: No argument provided
                        if (details.isEmpty()) {
                            throw new InvalidArgumentException("☹ Waku waku! Please input in the following format:\n"
                                    + "    event <taskName> /from <startTime> /to <endTime>");
                        }
                        // Error: Does not contain /from and /to
                        if (!details.contains("/from") && !details.contains("/to")) {
                            throw new InvalidArgumentException("☹ Waku waku! Please input in the following format:\n"
                                    + "    event <taskName> /from <startTime> /to <endTime>");
                        }

                        String taskName = details.split("/from")[0].trim();
                        String startTime = details.split("/from")[1].trim().split("/to")[0].trim();
                        LocalDateTime startTimeDate = convertStringToDate(startTime);
                        String endTime = details.split("/to")[1].trim();
                        Task t = new Event(taskName, startTime, endTime);
                        tasks.add(t);

                        System.out.println("    Waku waku! I've added this task:\n" + t);
                        System.out.println("    Now you have " + tasks.size() + " tasks in the list!");
                        break;
                    }
                    case DELETE: {
                        // Error: No argument or Multiple arguments provided
                        if (details.isEmpty() || details.split(" ").length != 1) {
                            throw new InvalidArgumentException("☹ Waku waku! Please input in the following format:\n"
                                    + "    delete <taskNumber>");
                        }
                        // Error: Argument provided is not a number
                        try {
                            Integer.parseInt(details);
                        } catch (NumberFormatException e) {
                            throw new InvalidArgumentException("☹ Waku waku! "
                                    + "Please only input INTEGERs after the word unmark!");
                        }
                        // Error: Argument provided is not within anya.task numbers
                        int taskNumber = Integer.parseInt(details);
                        if (taskNumber < 1 || taskNumber > tasks.size()) {
                            throw new InvalidArgumentException("☹ Waku waku! "
                                    + "I don't see a anya.task with the number:" + taskNumber);
                        }

                        Task t = tasks.get(taskNumber - 1);
                        tasks.remove(t);

                        String result = "    Waku waku! I've removed this task:\n" + t;
                        result += "    Now you have " + tasks.size() + " tasks in the list!";
                        System.out.println(result);
                        break;
                    }
                    default:
                        String result = "☹ Waku waku!!! I'm sorry, but I don't know what that means (yet) :( ";
                        System.out.println(result);
                }
            } catch (AnyaException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private LocalDateTime convertStringToDate(String dateString) {
        return LocalDateTime.parse(dateString);
    }
}
