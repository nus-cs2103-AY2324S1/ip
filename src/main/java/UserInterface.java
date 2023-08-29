import jdk.jfr.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserInterface {
    private final Scanner sc;
    private final Storage store;
    private final CommandParser parser;

    public UserInterface(Scanner sc, Storage store, CommandParser parser) {
        this.sc = sc;
        this.store = store;
        this.parser = parser;
    }

    public void start() {
        System.out.println("Hello! I'm Ducky\nWhat can I do for you?");
        while (true) {
            String input = sc.nextLine();

            try {
                Command c = this.parser.parse(input);
                switch (c.getType()) {
                    case LIST:
                        if (this.store.getStorageSize() == 0) {
                            System.out.println("There are no tasks in your list.");
                            break;
                        }
                        System.out.println("Here are the tasks in your list:");
                        System.out.println(this.store.list());
                        break;
                    case MARK:
                        int mark_index = Integer.parseInt(c.getArgs()[0]) - 1;
                        Task markedTask = this.store.mark(mark_index);
                        this.store.save();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(markedTask);
                        break;
                    case UNMARK:
                        int unmark_index = Integer.parseInt(c.getArgs()[0]) - 1;
                        Task unmarkedTask = this.store.unmark(unmark_index);
                        this.store.save();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(unmarkedTask);
                        break;
                    case DELETE:
                        int delete_index = Integer.parseInt(c.getArgs()[0]) - 1;
                        Task deletedTask = this.store.delete(delete_index);
                        this.store.save();
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deletedTask);
                        System.out.printf("Now you have %d tasks in the list.%n", this.store.getStorageSize());
                        break;
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        System.exit(0);
                    case TODO:
                        TodoTask newTodo = new TodoTask(c.getArgs()[0]);
                        this.store.add(newTodo);
                        this.store.save();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTodo);
                        System.out.printf("Now you have %d tasks in the list.%n", this.store.getStorageSize());
                        break;
                    case DEADLINE:
                        LocalDate deadline = LocalDate.parse(c.getArgs()[1]);
                        DeadlineTask newDeadline = new DeadlineTask(c.getArgs()[0], deadline);
                        this.store.add(newDeadline);
                        this.store.save();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newDeadline);
                        System.out.printf("Now you have %d tasks in the list.%n", this.store.getStorageSize());
                        break;
                    case EVENT:
                        EventTask newEvent = new EventTask(c.getArgs()[0], c.getArgs()[1], c.getArgs()[2]);
                        this.store.add(newEvent);
                        this.store.save();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newEvent);
                        System.out.printf("Now you have %d tasks in the list.%n", this.store.getStorageSize());
                        break;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("That is not an integer!");
            } catch (DateTimeParseException dtpe) {
                System.out.println("Could not parse date! Please use yyyy-mm-dd.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
