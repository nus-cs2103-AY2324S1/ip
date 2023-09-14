package duke.assets.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.assets.storage.TaskList;

public class SortCommand extends CommandAbstract {
    private static final String SORT_COMMAND_REGEX_STRING = "^sort -(a|c)($| -d$)";

    public SortCommand(String input) {
        super(input);
    }

    protected boolean isValid(TaskList tasklist) {
        Pattern inputPattern = Pattern.compile(SORT_COMMAND_REGEX_STRING);
        Matcher inputMatcher = inputPattern.matcher(this.input);
        return inputMatcher.find();
    }

    @Override
    protected String completeOperation(TaskList tasklist) {
        String[] delimitedByFlag = this.input.split(" -");
        boolean reverse = this.input.contains("-d");

        assert (delimitedByFlag.length > 1);
        switch (delimitedByFlag[1]) {
        case "a":
            return tasklist.sortByAlphabetical(reverse);
        case "c":
            return tasklist.sortByChronological(reverse);
        default:
            // fall-through
        }
        return UNHANDLED_EXCEPTION_STRING;
    }

    @Override
    protected String findException() {
        Pattern missingDescendingFlagPattern = Pattern.compile("^sort -(a|c)");
        Matcher inputMatcher = missingDescendingFlagPattern.matcher(this.input);

        // Checks if the descending flag is valid
        if (inputMatcher.find()) {
            return "Please check if you have a typo in your command. If you want to sort in "
                    + "descending order, use the flag -d";
        }

        Pattern missingSortByPattern = Pattern.compile("^sort -[^ac]($| -d$)");
        inputMatcher.reset();

        // Checks if the order in which list is to be sorted in is valid
        if (inputMatcher.usePattern(missingSortByPattern).find()) {
            return "Only sorting by alphabetical order or chronological order supported. Use flags"
                    + "-a for alphabetical and -c for chronological";
        }

        assert(this.input.startsWith("sort"));
        return "Please include how you would like to sort your tasks by. Use the flag -a for"
                + "alphabetical and -c for chronological";
    }
}
