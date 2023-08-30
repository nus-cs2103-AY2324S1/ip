import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Parser {
    public interface Command {
        /**
         * Execute the command.
         * @return whether the execution allows the program to continue,
         * true if it can, false means the program must exit
         */
        boolean execute(Duke bot);
    }

    public static class Exit implements Command {
        private Exit() {
        }

        @Override
        public boolean execute(Duke bot) {
            bot.exit();
            return false;
        }
    }

    public enum Type {
        TODO,
        DEADLINE,
        EVENT,
        DEFAULT,
    }

    public static class List implements Command {
        private boolean isExcludingDone;
        private LocalDate date;
        private Type type;

        private List(boolean isExcludingDone, LocalDate date, Type type) {
            this.isExcludingDone = isExcludingDone;
            this.date = date;
            this.type = type;
        }

        @Override
        public boolean execute(Duke bot) {
            switch (this.type) {
                case TODO:
                    bot.showTodos(this.isExcludingDone);
                    break;
                case DEADLINE:
                    bot.showDeadlines(this.isExcludingDone, this.date);
                    break;
                case EVENT:
                    bot.showEvents(this.isExcludingDone, this.date);
                    break;
                case DEFAULT:
                    bot.showList(this.isExcludingDone, this.date);
                    break;
            }
            return true;
        }
    }

    public static class Mark implements Command {
        private boolean isDone;
        private int index;

        private Mark(boolean isDone, int index) {
            this.isDone = isDone;
            this.index = index;
        }

        @Override
        public boolean execute(Duke bot) {
            if (this.isDone) {
                bot.markTaskAsDone(this.index);
            } else {
                bot.markTaskAsNotDone(this.index);
            }
            return true;
        }
    }

    public static class Add implements Command {
        private Task task;

        private Add(Task task) {
            this.task = task;
        }

        @Override
        public boolean execute(Duke bot) {
            bot.addTaskToList(task);
            return true;
        }
    }

    public static class Delete implements Command {
        private int taskIndex;

        private Delete(int taskIndex) {
            this.taskIndex = taskIndex;
        }

        @Override
        public boolean execute(Duke bot) {
            bot.deleteTask(this.taskIndex);
            return true;
        }
    }

    public static class Save implements Command {
        private Save() {}

        @Override
        public boolean execute(Duke bot) {
            bot.saveData();
            return true;
        }
    }

    public static class Echo implements Command {
        private String command;

        private Echo(String command) {
            this.command = command;
        }

        @Override
        public boolean execute(Duke bot) {
            bot.echo(this.command);
            return true;
        }
    }

    public static class Empty implements Command {
        private Empty() {}

        @Override
        public boolean execute(Duke bot) {
            return true;
        }
    }

    public static class ParseError extends Exception {
        private ParseError(String message) {
            super(message);
        }
    }

    public static Command parse(String input) throws ParseError {
        String[] commandArgs = input.split(" ", 2);
        int index;
        switch (commandArgs[0]) {
            case "":
                return new Empty();

            case "exit":
            case "bye":
                return new Exit();

            // show list
            case "list":
                if (commandArgs.length != 1) {
                    String[] args = commandArgs[1].split(" ");
                    boolean isExcludingDone = false;
                    LocalDate date = null;
                    for (String arg: args) {
                        switch (arg) {
                            case "todo":
                            case "deadline":
                            case "event":
                                break;
                            case "-d":
                                isExcludingDone = true;
                                break;
                            default:
                                try {
                                    date = DateTimeManager.parseDate(arg);
                                } catch (DateTimeManager.DateParseException e) {
                                    throw new ParseError("unrecognised \"" + arg + "\"");
                                }
                        }
                    }
                    switch (args[0]) {
                        case "todo":
                            return new List(isExcludingDone, date, Type.TODO);
                        case "deadline":
                            return new List(isExcludingDone, date, Type.DEADLINE);
                        case "event":
                            return new List(isExcludingDone, date, Type.EVENT);
                        default:
                            return new List(isExcludingDone, date, Type.DEFAULT);
                    }
                } else {
                    return new List(false, null, Type.DEFAULT);
                }

            // mark as done
            case "mark":
                index = Parser.getTaskIndexFromCommand(commandArgs);
                return new Mark(true, index);

            // mark as not done
            case "unmark":
                index = Parser.getTaskIndexFromCommand(commandArgs);
                return new Mark(false, index);

            // add to-do
            case "todo":
                if (commandArgs.length != 2) {
                    throw new ParseError("no to-do task provided");
                }
                if (commandArgs[1].equals("")) {
                    throw new ParseError("empty to-do task");
                }
                return new Add(new ToDo(commandArgs[1]));

            // add event
            case "event":
                // number of arguments
                if (commandArgs.length != 2) {
                    throw new ParseError("no event provided");
                }

                // /from keyword
                String[] separateByFrom = commandArgs[1].split(" /from ", 2);
                // no empty event
                if (separateByFrom[0].equals("")) {
                    throw new ParseError("empty event");
                }
                // /from keyword must exist
                if (separateByFrom.length != 2) {
                    throw new ParseError("keyword '/from' not found");
                }

                // /to keyword
                String[] separateByTo = separateByFrom[1].split(" /to ", 2);
                // no empty start time
                if (separateByTo[0].equals("")) {
                    throw new ParseError("empty start time");
                }
                // /to keyword must exist
                if (separateByTo.length != 2) {
                    throw new ParseError("keyword '/to' not found");
                }
                // no empty end time
                if (separateByTo[1].equals("")) {
                    throw new ParseError("empty end time");
                }

                try {
                    LocalDateTime startTime = DateTimeManager.inputToDate(separateByTo[0]);
                    LocalDateTime endTime = DateTimeManager.inputToDate(separateByTo[1]);
                    return new Add(new Event(separateByFrom[0], startTime, endTime));
                } catch (DateTimeManager.DateParseException | DateTimeException e) {
                    throw new ParseError("invalid datetime");
                }

            // add deadline
            case "deadline":
                // number of arguments
                if (commandArgs.length != 2) {
                    throw new ParseError("no deadline found");
                }

                String[] separateByBy = commandArgs[1].split(" /by ", 2);
                // /by keyword must exist
                if (separateByBy.length != 2) {
                    throw new ParseError("keyword '/by' not found");
                }
                // no empty deadline
                if (separateByBy[0].equals("")) {
                    throw new ParseError("empty deadline task");
                }
                // no empty end time
                if (separateByBy[1].equals("")) {
                    throw new ParseError("empty deadline time");
                }

                try {
                    LocalDateTime dateTime = DateTimeManager.inputToDate(separateByBy[1]);
                    return new Add(new Deadline(separateByBy[0], dateTime));
                } catch (DateTimeManager.DateParseException | DateTimeException e) {
                    throw new ParseError("invalid datetime");
                }

            // delete task
            case "delete":
                index = getTaskIndexFromCommand(commandArgs);
                return new Delete(index);

            // save data to hard disk
            case "save":
                return new Save();

            // anything else
            default:
                return new Echo(input);
        }
    }

    private static int getTaskIndexFromCommand(String[] commandArgs) throws ParseError {
        // check for number of arguments
        if (commandArgs.length != 2) {
            throw new ParseError("you have provided wrong number of arguments!");
        }

        // check if second argument is positive integer
        String indexString = commandArgs[1];
        if (indexString.matches("0+") || !indexString.matches("\\d+")) {
            throw new ParseError("you need to provide a positive integer!");
        }

        return Integer.parseInt(indexString) - 1;
    }
}
