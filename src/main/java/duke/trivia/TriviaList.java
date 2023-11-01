package duke.trivia;

import java.util.ArrayList;

public class TriviaList {
    private final ArrayList<Trivia> trivias;

    /**
     * Constructs a <code>TriviaList</code> object with an empty list of trivias.
     */
    public TriviaList() {
        this.trivias = new ArrayList<>();
    }

    /**
     * Constructs a <code>TriviaList</code> object with a selected list of trivias.
     * @param trivias The list of trivias.
     */
    public TriviaList(ArrayList<Trivia> trivias) {
        this.trivias = trivias;
    }

    /**
     * Adds a trivia to the list of trivias.
     * @param trivia The trivia to be added.
     */
    public void addTrivia(Trivia trivia) {
        trivias.add(trivia);
    }

    /**
     * Removes a trivia from the list of trivias.
     * @param index The index of the trivia to be removed.
     * @return The trivia that is removed.
     */
    public Trivia removeTrivia(int index) {
        return trivias.remove(index);
    }

    /**
     * Gets the size of the list of trivias.
     * @return The size of the list of trivias.
     */
    public int getSize() {
        return trivias.size();
    }

    /**
     * Gets a trivia from the list of trivias.
     * @param index The index of the trivia to be retrieved.
     * @return The trivia that is retrieved.
     */
    public Trivia getTrivia(int index) {
        return trivias.get(index);
    }

    /**
     * Returns an ArrayList of trivias that match the keyword.
     *
     * @param keyword The keyword to search for.
     * @return An ArrayList of trivias that match the keyword.
     */
    public ArrayList<Trivia> findTrivias(String keyword) {
        ArrayList<Trivia> matchedTrivias = new ArrayList<>();
        for (Trivia trivia : trivias) {
            if (trivia.getQuestion().contains(keyword)) {
                matchedTrivias.add(trivia);
            }
        }
        return matchedTrivias;
    }

    /**
     * Gets the list of trivias.
     * @return The list of trivias.
     */
    public ArrayList<Trivia> getTrivias() {
        return trivias;
    }
}