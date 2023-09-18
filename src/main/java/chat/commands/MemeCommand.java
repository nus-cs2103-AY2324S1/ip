package chat.commands;

import chat.exceptions.ChatException;
import chat.tasks.TaskList;
import chat.utils.Storage;

public class MemeCommand extends Command {

    private String[] memeList = {"Why is the Average NUS Student so Average ?\nI don't understand why the normal NUS students has such average tastes and interests. They listen to music like Taylor Swift, play games like Genshin Impact and wear Uniqlo Clothes.\nPlease help me understand why this is the case. I thought everyone in univerisety would be different and quirky, wear cosplay or eleborate get-ups to classes, read post-mordern albanian meta-critiques of communist narratives and listen to pyschelic jazz music. Why isn't this the case ?",
            "memes dont stay funny for very long you know",
            "My name is Walter Hartwell White. I live at 308 Negra Arroyo Lane, Albuquerque, New Mexico, 87104. This is my confession. If you're watching this tape, I'm probably dead, murdered by my brother-in-law Hank Schrader. Hank has been building a meth empire for over a year now and using me as his chemist. ",
            "Please DO NOT buy the BTS meal if you don't stan them. You're preventing the actual BTS fans who have waited for months from having the BTS meal experience. Eating the sauces without understanding their significance is literally cultural appropriation and it's not okay",
            "I just downvoted your comment.\r\n" + //
                "\r\n" + //
                "# FAQ\r\n" + //
                "\r\n" + //
                "## What does this mean?\r\n" + //
                "\r\n" + //
                "The amount of karma (points) on your comment and Reddit account has decreased by one.\r\n" + //
                "\r\n" + //
                "## Why did you do this?\r\n" + //
                "\r\n" + //
                "There are several reasons I may deem a comment to be unworthy of positive or neutral karma. These include, but are not limited to:\r\n" + //
                "\r\n" + //
                "Rudeness towards other Redditors, Spreading incorrect information, Sarcasm not correctly flagged with a /s.\r\n" + //
                "\r\n" + //
                "## Am I banned from the Reddit?\r\n" + //
                "\r\n" + //
                "No - not yet. But you should refrain from making comments like this in the future. Otherwise I will be forced to issue an additional downvote, which may put your commenting and posting privileges in jeopardy.\r\n" + //
                "\r\n" + //
                "## I don't believe my comment deserved a downvote. Can you un-downvote it?\r\n" + //
                "\r\n" + //
                "Sure, mistakes happen. But only in exceedingly rare circumstances will I undo a downvote. If you would like to issue an appeal, shoot me a private message explaining what I got wrong. I tend to respond to Reddit PMs within several minutes. Do note, however, that over 99.9% of downvote appeals are rejected, and yours is likely no exception.\r\n" + //
                "\r\n" + //
                "## How can I prevent this from happening in the future?\r\n" + //
                "\r\n" + //
                "Accept the downvote and move on. But learn from this mistake: your behavior will not be tolerated on Reddit.com. I will continue to issue downvotes until you improve your conduct. Remember: Reddit is privilege, not a right."};

    public MemeCommand() {        
    };

    @Override
    public String execute(TaskList tasklist, Storage storage) throws ChatException {
        int randomMemeIndex = (int) (Math.random() * memeList.length);
        return memeList[randomMemeIndex];
    }
    
}
