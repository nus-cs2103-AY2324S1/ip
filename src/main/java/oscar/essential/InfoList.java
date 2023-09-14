package oscar.essential;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import oscar.exception.OscarException;
import oscar.info.Info;
import oscar.info.Task;

/**
 * Contains ArrayList of infos that Oscar can interact with.
 */
public class InfoList {
    private ArrayList<Info> infoList;

    /**
     * Uses an empty info list.
     */
    public InfoList() {
        this.infoList = new ArrayList<Info>();
    }

    /**
     * Uses the saved info list.
     *
     * @param stream Object input stream to be read.
     * @throws OscarException Unable to load object input stream.
     */
    public InfoList(ObjectInputStream stream) throws OscarException {
        assert stream != null;
        this.infoList = load(stream);
    }

    /**
     * Loads the info list from the object input stream.
     * Solution adapted by <a href="https://howtodoinjava.com/java/collections/arraylist/
     * serialize-deserialize-arraylist/">...</a>
     *
     * @param stream Deserialized save file stream.
     * @return Saved info list in an ArrayList.
     * @throws OscarException Unable to handle object input stream.
     */
    ArrayList<Info> load(ObjectInputStream stream) throws OscarException {
        try {
            @SuppressWarnings("unchecked")
            ArrayList<Info> tempList = (ArrayList<Info>) stream.readObject();
            infoList = tempList;
            return infoList;
        } catch (IOException e) {
            throw new OscarException("Sorry! There is an error loading the saved info list.\n");
        } catch (ClassNotFoundException e) {
            throw new OscarException("Sorry! Class cannot be found.\n");
        }
    }

    /**
     * Saves the current info list by serializing it.
     *
     * @param stream Object output stream of saved file.
     * @throws OscarException Input or output error.
     */
    public void save(ObjectOutputStream stream) throws OscarException {
        try {
            stream.writeObject(infoList);
        } catch (IOException e) {
            throw new OscarException("Sorry! There is an issue with your input or output.\n");
        }
    }

    /**
     * Obtains the number of items in the info list.
     *
     * @return Count of items.
     */
    public int getSize() {
        return infoList.size();
    }

    /**
     * Lists stored items in chronological order of addition.
     *
     * @return Items in info list.
     */
    public String list() {
        StringBuilder result = new StringBuilder("Here are the items in your list:\n");
        for (int i = 1; i <= getSize(); i++) {
            Info currentInfo = infoList.get(i - 1);
            result.append(i).append(".").append(currentInfo).append("\n");
        }
        return result.append("\n").toString();
    }

    /**
     * Displays the number of items stored in the info list.
     *
     * @return Message that informs users of number of items in info list.
     */
    public String listCount() {
        int listSize = getSize();
        if (listSize == 0) {
            return "You now have no items in the list. Add some now!\n";
        } else if (listSize == 1) {
            return "You now have 1 item in the list.\n";
        } else {
            return "You now have " + listSize + " items in the list.\n";
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index Item number.
     * @return Description of task.
     * @throws OscarException Item selected is a note and cannot be marked.
     */
    public String mark(int index) throws OscarException {
        Info currentInfo = infoList.get(index);
        if (!(currentInfo instanceof Task)) {
            throw new OscarException("Sorry! The item cannot be marked as done.\n");
        }
        Task currentTask = ((Task) currentInfo);
        currentTask.markAsDone();
        return currentTask.toString();
    }

    /**
     * Marks a task as not done.
     *
     * @param index Item number.
     * @return Description of task.
     * @throws OscarException Item selected is a note and cannot be marked.
     */
    public String unmark(int index) throws OscarException {
        Info currentInfo = infoList.get(index);
        if (!(currentInfo instanceof Task)) {
            throw new OscarException("Sorry! The item cannot be marked as not done.\n");
        }
        Task currentTask = ((Task) currentInfo);
        currentTask.markAsNotDone();
        return currentTask.toString();
    }

    /**
     * Deletes an item.
     *
     * @param index Item number.
     * @return Description of item.
     */
    public String delete(int index) {
        Info currentInfo = infoList.remove(index);
        return currentInfo.toString();
    }

    /**
     * Adds an item to the info list.
     *
     * @param info Item to be added.
     */
    public void add(Info info) {
        infoList.add(info);
    }

    /**
     * Lists all items in the info list containing the keyword.
     *
     * @param keyword String to match.
     * @return List of items containing keyword.
     */
    public String find(String keyword) {
        StringBuilder result = new StringBuilder("Here are the matching infos in your list:");
        for (int i = 1; i <= infoList.size(); i++) {
            Info currentItem = infoList.get(i - 1);
            if (currentItem.getDescription().contains(keyword)) {
                result.append(i).append(".").append(currentItem);
            }
        }
        return result.append("\n").toString();
    }
}
