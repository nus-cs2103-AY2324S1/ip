package emiya.commands;

import emiya.Keyword;

/**
 * A class that represents the Help command.
 */
public class HelpCommand extends Command {

    /**
     * Returns a String containing a description of all commands, as well as how to use them.
     * @return A string containing the description of all commands and their usage.
     */
    public static String help() {
        StringBuilder helpList = new StringBuilder("Here's a list of all possible commands!\n");
        for (Keyword kwd : Keyword.values()) {
            String helpDescription = kwd.getCommand().giveHelpDescription();
            if (helpDescription.equals("")) {
                continue;
            }
            helpList.append(helpDescription);
            helpList.append("\n");
        }
        return helpList.toString();
    }

}
