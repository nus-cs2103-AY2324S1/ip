package brandon.chatbot.parser;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.generalcommands.ExitCommand;
import brandon.chatbot.commands.generalcommands.HelpCommand;
import brandon.chatbot.commands.generalcommands.UnknownCommand;
import brandon.chatbot.commands.taskcommands.AddDeadlineCommand;
import brandon.chatbot.commands.taskcommands.AddEventCommand;
import brandon.chatbot.commands.taskcommands.AddTodoCommand;
import brandon.chatbot.commands.taskcommands.DeleteCommand;
import brandon.chatbot.commands.taskcommands.FindCommand;
import brandon.chatbot.commands.taskcommands.ListCommand;
import brandon.chatbot.commands.taskcommands.MarkCommand;
import brandon.chatbot.commands.taskcommands.UnmarkCommand;
import brandon.chatbot.tag.Tag;

/**
 * Represents the parser that parses the user inputs.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TASK_FIND_ARGS_FORMAT = Pattern.compile("(?<title>[^#]+)?(?<tags>.*)");
    public static final Pattern TODO_ARGS_FORMAT = Pattern.compile("(?<title>[^#]+)?(?<tags>.*)?");

    public static final Pattern TAG_ARGS_FORMAT = Pattern.compile("(#(?<tag>[^#]+))");
    public static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile(
            "(?<title>[^/]+)?"
            + "(/by(?<deadline>[^#]+))?"
            + "(?<tags>.*)"
    );
    public static final Pattern EVENT_ARGS_FORMAT = Pattern.compile(
            "(?<title>[^/]+)?"
            + "(/from(?<from>[^/]+))?"
            + "(/to(?<to>[^#]+))?"
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
        Matcher matcher = TASK_FIND_ARGS_FORMAT.matcher(arg.trim());
        if (!matcher.matches()) {
            return new UnknownCommand();
        }

        String title = matcher.group("title");
        String tagArgs = matcher.group("tags");
        Optional<String> titleOptional = Optional.ofNullable(title);
        Optional<ArrayList<Tag>> optionalTags = Optional.ofNullable(parseTags(tagArgs));

        return new FindCommand(titleOptional, optionalTags);
    }

    private Command prepareTodo(String args) {
        final Matcher matcher = TODO_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new UnknownCommand();
        }

        String title = matcher.group("title");
        String tagArgs = matcher.group("tags");
        Optional<ArrayList<Tag>> optionalTags = Optional.ofNullable(parseTags(tagArgs));
        return new AddTodoCommand(title, optionalTags);
    }

    /**
     * Parses the tags from a string and add tags to ArrayList of tags.
     *
     * @param tagArgs is a string of tags not parsed.
     * @return the arraylist of tags parsed from the parameter string.
     */
    public ArrayList<Tag> parseTags(String tagArgs) {
        ArrayList<Tag> tags = new ArrayList<>();
        Matcher m = TAG_ARGS_FORMAT.matcher(tagArgs);
        while (m.find()) {
            tags.add(new Tag(m.group().strip()));
        }
        return tags.size() > 0 ? tags : null;
    }

    private Command prepareDeadline(String args) {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new UnknownCommand();
        }
        String title = matcher.group("title");
        String deadline = matcher.group("deadline").strip();
        String tagArgs = matcher.group("tags").strip();
        Optional<ArrayList<Tag>> tags = Optional.ofNullable(parseTags(tagArgs));
        return new AddDeadlineCommand(title, deadline, tags);
    }

    private Command prepareEvent(String args) {
        final Matcher matcher = EVENT_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new UnknownCommand();
        }

        String title = matcher.group("title");
        String from = matcher.group("from");
        String to = matcher.group("to");
        String tagArgs = matcher.group("tags");
        Optional<ArrayList<Tag>> tags = Optional.ofNullable(parseTags(tagArgs));
        return new AddEventCommand(title, from, to, tags);
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
