package duke;

/**
 * Edits the trivia answer stored by the Duke bot
 */
public class TriviaEdit extends Command {

    //The question to edit.
    private String question;

    //The new answer.
    private String answer;

    //The TriviaList to edit.
    private TriviaList trivia;

    /**
     * Instantiates an instance of TriviaEdit
     * @param question The trivia question to edit
     * @param answer The new answer
     * @param trivia The TriviaList to be used
     */
    public TriviaEdit(String question, String answer, TriviaList trivia) {
        this.question = question;
        this.answer = answer;
        this.trivia = trivia;
    }

    /**
     * Edits a new answer
     * @return
     */
    public String execute() {
        return trivia.editAnswer(question, answer);
    }

}
