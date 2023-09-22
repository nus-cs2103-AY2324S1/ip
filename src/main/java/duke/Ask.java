package duke;

/**
 * Answers a trivia question
 */
public class Ask extends Command{

    //The trivia question
    private String question;

    //The trivialist to be used
    private TriviaList trivia;

    /**
     * Instantiates an instance of Ask
     * @param question The question to be asked
     * @param trivia The trivialist to retrieve the answer from
     */
    public Ask(String question, TriviaList trivia) {
        this.question = question;
        this.trivia = trivia;
    }

    /**
     * Answers the trivia question
     * @return the answer to the trivia question
     */
    public String execute() {
        String answer = trivia.getTrivia(question);
        return answer.isEmpty() ? "I don't know..." : answer;
    }

}
