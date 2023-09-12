package ballsorting;

public class CustomErrorHandling {
    public static String emptySearchTerm() {
        return "☹ OOPS!!! Please enter a search term";
    }
    public static String emptyTodoDescription() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
    public static String emptyDeadlineDescription() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }
    public static String emptyDeadlineDuedate() {
        return "☹ OOPS!!! The deadline of a deadline cannot be empty.";
    }
    public static String emptyEventDescription() {
        return "☹ OOPS!!! The description of an event cannot be empty.";
    }
    public static String emptyEventStartDate() {
        return "☹ OOPS!!! The start time of an event cannot be empty.";
    }
    public static String emptyEventEndDate() {
        return "☹ OOPS!!! The end time of an event cannot be empty.";
    }
    public static String commandNotFound() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
