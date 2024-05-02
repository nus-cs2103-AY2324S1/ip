package duke;

/**
 * TriviaDelete
 */
public class TriviaDelete extends Command {
    //The question to delete
    private String question;

    //The triviaList storing all trivia
    private TriviaList trivia;

    /**
     * Instantiates an instance of TriviaDelete
     * @param question The question to delete
     * @param trivia The trivialist to be used
     */
    public TriviaDelete(String question, TriviaList trivia) {
        this.question = question;
        this.trivia = trivia;
    }

    /**
     * Deletes the trivia question
     * @return The confirmation of the deletion of trivia questions.
     */
    public String execute() {
        return trivia.removeTrivia(question);
    }
}
