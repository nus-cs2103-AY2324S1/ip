package brandon.chatbot.parser;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import brandon.chatbot.commands.taskcommands.*;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.generalcommands.ExitCommand;
import brandon.chatbot.commands.generalcommands.HelpCommand;
import brandon.chatbot.commands.generalcommands.UnknownCommand;
import brandon.chatbot.common.DukeException;

/**
 * Represents the parser that parses the user inputs.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TASK_FIND_ARGS_FORMAT = Pattern.compile("(?<title>[^#]+)?(?<tags>.*)");
    public static final Pattern TODO_ARGS_FORMAT = Pattern.compile("(?<title>[^#]+)(?<tags>.*)");

    public static final Pattern TAG_ARGS_FORMAT = Pattern.compile("(#(?<tag>[^#]+))");
    public static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile(
            "(?<title>[^/]+)"
            + "/by(?<deadline>[^#]+)"
            + "(?<tags>.*)"
    );
    public static final Pattern EVENT_ARGS_FORMAT = Pattern.compile(
            "(?<title>[^/]+)"
            + "/from(?<from>[^/]+)"
            + "/to(?<to>[^#]+)"
            + "(?<tags>.*)"
    );

    /**
     * Parses the user input string and returns a command according to it.
     *
     * @param userInput is the direct input the user types in.
     * @return Command to be run.
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new UnknownCommand();
        }

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case "find":
            return prepareFind(arguments);
        case "todo":
            return prepareTodo(arguments);
        case "delete":
            return prepareDelete(arguments);
        case "list":
            return new ListCommand();
        case "mark":
            return prepareMark(arguments);
        case "unmark":
            return prepareUnmark(arguments);
        case "deadline":
            return prepareDeadline(arguments);
        case "event":
            return prepareEvent(arguments);
        case "help":
            return new HelpCommand();
        case "bye":
            return new ExitCommand();
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Returns a command that finds the task with the same title passed in the argument.
     *
     * @param arg is the title passed by the user.
     * @return the command that finds the task with the given name.
     */
    private Command prepareFind(String arg) {
        final Matcher matcher = TASK_FIND_ARGS_FORMAT.matcher(arg.trim());
        if (!matcher.matches()) {
            return new UnknownCommand();
        }

        String title = matcher.group("title");
        String tagArgs = matcher.group("tags");
        Optional<ArrayList<Tag>> tags = Optional.ofNullable(parseTags(tagArgs));
        if (tags.isPresent()) {
            return new FindTaskByTagCommand(title, tags.get());
        }
        return new FindCommand(title);

    }

    private Command prepareTodo(String args) {
        final Matcher matcher = TODO_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            System.out.println("not working at all!");
            return new UnknownCommand();
        }
        try {
            String title = matcher.group("title");
            String tagArgs = matcher.group("tags");
            Optional<ArrayList<Tag>> tags = Optional.ofNullable(parseTags(tagArgs));
            return new AddTodoCommand(title, tags);
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }


    private ArrayList<Tag> parseTags(String tagArgs) {
        ArrayList<Tag> tags = new ArrayList<>();
        Matcher m = TAG_ARGS_FORMAT.matcher(tagArgs);
        while(m != null && m.find()) {
            tags.add(new Tag(m.group().strip()));
        }
//        printTags(tags);
        return tags.size() > 0 ? tags : null;
    }
//
//    private void printTags(ArrayList<Tag> tags) {
//        for (Tag i: tags) {
//            System.out.println("this is one of the tags:" + i);
//        }
//    }

    private Command prepareDeadline(String args) {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new UnknownCommand();
        }

        try {
            String title = matcher.group("title");
            String deadline = matcher.group("deadline");
            String tagArgs = matcher.group("tags");
            Optional<ArrayList<Tag>> tags = Optional.ofNullable(parseTags(tagArgs));
            return new AddDeadlineCommand(title, deadline.strip(), tags);
        } catch (DukeException e) {
            return new UnknownCommand(e.getMessage());
        }
    }

    private Command prepareEvent(String args) {
        final Matcher matcher = EVENT_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new UnknownCommand();
        }

        try {
            String title = matcher.group("title");
            String from = matcher.group("from").strip();
            String to = matcher.group("to").strip();
            String tagArgs = matcher.group("tags");
            Optional<ArrayList<Tag>> tags = Optional.ofNullable(parseTags(tagArgs));
            return new AddEventCommand(title, from, to, tags);
        } catch (DukeException e) {
            return new UnknownCommand();
        }
    }

    private Command prepareDelete(String args) {
        final int index = Integer.parseInt(args.trim());
        return new DeleteCommand(index);
    }

    private Command prepareMark(String args) {
        final int index = Integer.parseInt(args.trim());
        return new MarkCommand(index);
    }

    private Command prepareUnmark(String args) {
        final int index = Integer.parseInt(args.trim());
        return new UnmarkCommand(index);
    }
}
