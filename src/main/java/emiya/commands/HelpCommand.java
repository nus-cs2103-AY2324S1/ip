package emiya.commands;

import emiya.Keyword;

public class HelpCommand implements Command {
    public static String help(){
        StringBuilder helpList = new StringBuilder("Here's a list of all possible commands!\n");
        for (Keyword kwd : Keyword.values()) {
            helpList.append(kwd.getCommand().giveHelpDescription());
            helpList.append("\n");
        }
        return helpList.toString();
    }
    @Override
    public String giveHelpDescription() {
        return "";
    }
}
