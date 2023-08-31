package Parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Comm.ListCommand;
import Comm.AddDeadlineCommand;
import Comm.AddEventCommand;
import Comm.AddTodoCommand;
import Comm.ByeCommand;
import Comm.Command;
import Comm.DeleteCommand;
import Comm.EmptyCommand;
import Comm.ErrorCommand;
import Comm.MarkCommand;
import Comm.MiscCommand;
import Comm.UnmarkCommand;

public class Parser {

    public static Command parse (String str) {
        if (str.isEmpty()) { //empty command

            return new EmptyCommand();

        } else if (str.startsWith("todo")) { //todo command

            try {
                String[] split = str.split(" ");
                if (split.length < 2) {
                    throw new IllegalArgumentException();
                } else {
                    return new AddTodoCommand(str.substring(split[0].length()).trim());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Hey! I think you forget to enter the todo description or leave a space after the command!");
                return new ErrorCommand();
            }

        } else if (str.startsWith("deadline")) { // deadline command

            try {
                String[] split = str.split("/by");
                if (str.equals("deadline")) {
                    throw new IllegalArgumentException("Hey! Please enter the task description or leave a space after the command!");
                } else if (split.length < 2) {
                    throw new IllegalArgumentException("Hey! Please provide a deadline for your task in this format dd/MM/yyyy HHmm!");
                } else {
                    DateTime dateTime = new DateTime();
                    String formattedDate = dateTime.formatDateTime(split[1].trim());
                    String taskDesc = split[0].trim().substring(8).trim();

                    if (formattedDate.equals("Invalid format")) {
                        throw new IllegalArgumentException("Hey! Please provide a deadline for your task in this format dd/MM/yyyy HHmm!");
                    }
                    return new AddDeadlineCommand(taskDesc, formattedDate);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return new ErrorCommand();
            }

        } else if (str.startsWith("event")) { // event command

            try {

                if (str.equals("event")) {

                    throw new IllegalArgumentException("Hey! Please enter the event description or leave a space after the command!");
                }


                String[] split_1 = str.split("/from");

                if (split_1.length < 2) {

                    throw new IllegalArgumentException("Hey! Please provide a time range for your event in this format /from dd/MM/yyyy HHmm /to HHmm");
                }


                String[] split_2 = split_1[1].split("/to");

                if (split_2.length < 2)  {

                    throw new IllegalArgumentException("Hey! Please provide an end time for your event");

                } else {

                    DateTime dateTime = new DateTime();
                    String formattedStartTime = dateTime.formatDateTime(split_2[0].trim());
                    String formattedEndTime = dateTime.formatDateTime(split_2[1].trim());

                    String taskDesc = split_1[0].trim().substring(5).trim();

                    if (formattedStartTime.equals("Invalid format")) {

                        throw new IllegalArgumentException("Hey! Please provide a time range for your event in this format /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm");
                    }

                    if (formattedEndTime.equals("Invalid format")) {

                        throw new IllegalArgumentException("Hey! Please provide an end time for your event in this format dd/MM/yyyy HHmm!");
                    }

                    return new AddEventCommand(taskDesc, formattedStartTime, formattedEndTime);

                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return new ErrorCommand();
            }

        } else if (str.equals("list")) {

            return new ListCommand();

        } else if (str.startsWith("delete")) {

            return new DeleteCommand(str);

        } else if (str.startsWith("mark")) {

            return new MarkCommand(str);

        } else if (str.startsWith("unmark")) {

            return new UnmarkCommand(str);

        } else if (str.equals("bye")) {

            return new ByeCommand();

        } else {

            return new MiscCommand();

        }

    }
}
