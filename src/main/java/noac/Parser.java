package noac;

import noac.command.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class meant to parse strings into relevant data such as the user input and date string.
 */
public class Parser {

    /**
     * Parse the user input and return the relevant command given by the user.
     *
     * @param fullCommand The user input.
     * @return The correct command to be executed based on input.
     * @throws NoacException For any errors that needs to be displayed to user.
     */
    public static Command parse(String fullCommand) throws NoacException{

        Command returnCommand;


        String[] userInputArr = fullCommand.split(" ");
        String command = userInputArr[0];

        switch (command) {
        case "list":

            returnCommand = new ListCommand();

            break;

        case "mark":
        case "unmark":

            if (userInputArr.length > 2 || userInputArr.length <= 1) {
                throw new NoacException("☹ OOPS!!! Please enter in the format mark [TASK_NUMBER] e.g. mark 1");

            } else if (!userInputArr[1].matches("\\d+")) {

                throw new NoacException("☹ OOPS!!! Please enter in the format mark [TASK_NUMBER] e.g. mark 1");
            }

            int taskNo = Integer.parseInt(userInputArr[1]) - 1;

            returnCommand = new MarkCommand(taskNo, command.equals("mark"));

            break;

        case "todo":
            if (userInputArr.length > 1) {
                String description = "";

                for (int i = 1; i < userInputArr.length; i++) {
                    description += userInputArr[i] + " ";
                }

                description = description.substring(0, description.length() - 1);

                returnCommand = new TodoCommand(description);


            } else {
                throw new NoacException("☹ OOPS!!! The description of a todo cannot be empty.");
            }

            break;

        case "deadline":


            String description = "";
            String by = "";

            boolean afterBy = false;

            for (int i = 1; i < userInputArr.length; i++) {
                if (userInputArr[i].equals("/by")) {
                    afterBy = true;
                    continue;
                }
                if (afterBy) {
                    by += userInputArr[i] + " ";
                } else {
                    description += userInputArr[i] + " ";
                }

            }

            if (!afterBy) {
                throw new NoacException("☹ OOPS!!! The input must contain the command /by");
            }

            if (by.length() == 0 || description.length() == 0) {
                throw new NoacException("☹ OOPS!!! The description and by of a deadline cannot \n     be empty");
            }

            by = by.substring(0, by.length() - 1);
            description = description.substring(0, description.length() - 1);

            returnCommand = new DeadlineCommand(description, parseDate(by));

            break;


        case "event":

            String descript = "";
            String from = "";
            String to = "";

            String status = "event";


            for (int i = 1; i < userInputArr.length; i++) {
                if (userInputArr[i].equals("/from")) {
                    status = "from";
                    continue;
                }
                if (userInputArr[i].equals("/to")) {
                    status = "to";
                    continue;
                }

                if (status.equals("event")) {
                    descript += userInputArr[i] + " ";
                } else if (status.equals("from")) {
                    from += userInputArr[i] + " ";
                } else if (status.equals("to")) {
                    to += userInputArr[i] + " ";
                }

            }

            if (!status.equals("to")) {

                throw new NoacException("☹ OOPS!!! The input must contain the command /from and /to \n     in this order");
            }

            if (descript.length() == 0 || from.length() == 0 || to.length() == 0) {

                throw new NoacException("☹ OOPS!!! The description, from and to of a event cannot \n     be empty!");

            }

            from = from.substring(0, from.length() - 1);
            to = to.substring(0, to.length() - 1);
            descript = descript.substring(0, descript.length() - 1);


            returnCommand = new EventCommand(descript, parseDate(from), parseDate(to));

            break;


        case "delete":

            if(userInputArr.length > 2 || userInputArr.length <= 1) {
                throw new NoacException("☹ OOPS!!! Please enter in the format delete [TASK_NUMBER] e.g. delete 1");

            } else if (!userInputArr[1].matches("\\d+")) {

                throw new NoacException("☹ OOPS!!! Please enter in the format delete [TASK_NUMBER] e.g. delete 1");
            }


            int taskNo2 = Integer.parseInt(userInputArr[1]) - 1;

            returnCommand = new DeleteCommand(taskNo2);

            break;

        case "on":
            LocalDate localDate;

            if (userInputArr.length != 2) {
                throw new NoacException("☹ OOPS!!! Please input date in this format yyyy-MM-dd");
            }
            try {
                localDate = LocalDate.parse(userInputArr[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            } catch (DateTimeParseException exception) {
                throw new NoacException("☹ OOPS!!! Please input date in this format yyyy-MM-dd");
            }

            returnCommand = new OnCommand(localDate);

            break;

        case "bye":

            returnCommand = new ExitCommand();
            break;

        default:
            throw new NoacException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return returnCommand;
    }


    /**
     * Parse date string to LoaclDateTime object.
     *
     * @param date The string date to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException Exception if the parsing fails.
     */
    public static LocalDateTime parseDate(String date) throws DateTimeParseException {
        LocalDateTime localDateTime;

        if(date.length() == 10) {
            localDateTime = LocalDateTime.parse(date + " 0000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return localDateTime;
    }


}
