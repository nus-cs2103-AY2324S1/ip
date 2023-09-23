package duke;

/**
 * Adds new trivia to the Trivia List.
 */
public class TriviaAdd extends Command {
    //The trivia question
    private String question;

    //The answer to the trivia question
    private String answer;

    //The trivia list to store the trivia question
    private TriviaList trivia;

    /**
     * Instantiates an instance of TriviaAdd
     * @param question The question to be asked
     * @param answer The answer
     * @param trivia The trivialist to be used
     */
    public TriviaAdd(String question, String answer, TriviaList trivia) {
        this.question = question;
        this.answer = answer;
        this.trivia = trivia;
    }

    /**
     * Adds a trivia question.
     * @return Confirmation of the trivia question's addition
     */
    public String execute() {
        return trivia.addTrivia(question, answer);
    }

}
