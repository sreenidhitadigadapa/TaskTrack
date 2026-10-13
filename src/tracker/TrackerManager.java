
package tracker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

// -------------------------------------------------------------------------
/**
 * Manages assignments and notes for the TaskTrack application.
 *
 * Stores both item types in the same generic LinkedChain and
 * provides methods for creating, completing, and finding items.
 *
 * Also supports overdue assignment detection as a stretch goal.
 *
 * @author sreenidhitadigadapa
 * @version 13 Oct 2026
 */
public class TrackerManager {

    //~ Fields ................................................................

    private LinkedChain<Item> items;
    private int nextId;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates an empty TrackerManager.
     */
    public TrackerManager() {
        items = new LinkedChain<Item>();
        nextId = 1;
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates and stores a new assignment.
     *
     * The title must not be empty, and the due date must be
     * a valid date in YYYY-MM-DD format.
     *
     * @param title the assignment title
     * @param text the assignment description
     * @param dueDate the assignment due date
     * @param className the course name
     * @return the newly created assignment
     * @throws IllegalArgumentException if input is invalid
     */
    public Assignment createAssignment(
        String title,
        String text,
        String dueDate,
        String className) {

        validateTitle(title);
        validateDate(dueDate);

        Assignment assignment = new Assignment(
            nextId,
            title.trim(),
            clean(text),
            dueDate.trim(),
            clean(className));

        items.addToEnd(assignment);
        nextId++;

        return assignment;
    }


    // ----------------------------------------------------------
    /**
     * Creates and stores a new note.
     *
     * Both the title and note text must contain characters.
     *
     * @param title the note title
     * @param text the note contents
     * @return the newly created note
     * @throws IllegalArgumentException if input is invalid
     */
    public Note createNote(String title, String text) {
        validateTitle(title);

        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Note text cannot be empty.");
        }

        Note note = new Note(
            nextId,
            title.trim(),
            text.trim());

        items.addToEnd(note);
        nextId++;

        return note;
    }


    // ----------------------------------------------------------
    /**
     * Finds an item using its ID.
     *
     * @param id the ID to search for
     * @return the matching item or null if none exists
     */
    public Item findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        int parsedId;

        try {
            parsedId = Integer.parseInt(id.trim());
        }
        catch (NumberFormatException e) {
            return null;
        }

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if (item.getId() == parsedId) {
                return item;
            }
        }

        return null;
    }


    // ----------------------------------------------------------
    /**
     * Marks an assignment complete using its ID.
     *
     * A nonexistent ID or an ID belonging to a note
     * does not change any stored data.
     *
     * @param id the assignment ID
     * @return true if an assignment was completed;
     *         false otherwise
     */
    public boolean completeAssignment(String id) {
        Item item = findById(id);

        if (!(item instanceof Assignment)) {
            return false;
        }

        Assignment assignment = (Assignment)item;
        assignment.markComplete();

        return true;
    }
    
    // ----------------------------------------------------------
    /**
     * Gets all the items in the list.
     * @return items
     */
    public List<Item> getAllItems() {
        List<Item> result = new ArrayList<Item>();
        for (int i = 0; i < items.size(); i++) result.add(items.get(i));
        return result;
        }


    // ----------------------------------------------------------
    /**
     * Returns all incomplete assignments with due dates
     * earlier than the specified date.
     *
     * This helper also makes overdue detection testable
     * without depending on the computer's current date.
     *
     * @param today the date used for comparison
     * @return a list of overdue assignments
     * @throws IllegalArgumentException if today is null
     */
    public List<Assignment> overdueAssignments(
        LocalDate today) {

        if (today == null) {
            throw new IllegalArgumentException(
                "Comparison date cannot be null.");
        }

        List<Assignment> overdue =
            new ArrayList<Assignment>();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if (item instanceof Assignment) {
                Assignment assignment = (Assignment)item;

                String dueDate = assignment.getDueDate();

                if (dueDate != null
                    && !dueDate.trim().isEmpty()
                    && !assignment.isCompleted()) {

                    LocalDate due =
                        LocalDate.parse(dueDate.trim());

                    if (due.isBefore(today)) {
                        overdue.add(assignment);
                    }
                }
            }
        }

        return overdue;
    }


    // ----------------------------------------------------------
    /**
     * Finds assignments that are overdue as of today.
     *
     * This method is called by Miguel's MainMenu.
     *
     * @return all incomplete assignments due before today
     */
    public List<Assignment> overdueAssignments() {
        return overdueAssignments(LocalDate.now());
    }


    // ----------------------------------------------------------
    /**
     * Returns the number of stored items.
     *
     * @return the number of assignments and notes
     */
    public int size() {
        return items.size();
    }


    // ----------------------------------------------------------
    /**
     * Checks whether the manager has no stored items.
     *
     * @return true when the manager is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }


    // ----------------------------------------------------------
    /**
     * Counts the number of stored assignments.
     *
     * @return the assignment count
     */
    public int countAssignments() {
        int count = 0;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Assignment) {
                count++;
            }
        }

        return count;
    }


    // ----------------------------------------------------------
    /**
     * Counts the number of stored notes.
     *
     * @return the note count
     */
    public int countNotes() {
        int count = 0;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Note) {
                count++;
            }
        }

        return count;
    }


    // ----------------------------------------------------------
    /**
     * Removes all stored items.
     *
     * The next ID is not reset so previously issued
     * IDs are not reused.
     */
    public void clear() {
        items.clear();
    }


    //~ Private Methods .......................................................

    // ----------------------------------------------------------
    /**
     * Checks whether an item title is valid.
     *
     * @param title the title to validate
     * @throws IllegalArgumentException if title is blank
     */
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Title cannot be empty.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Checks whether a due date uses YYYY-MM-DD format
     * and represents an actual calendar date.
     *
     * @param dueDate the date to validate
     * @throws IllegalArgumentException if date is invalid
     */
    private void validateDate(String dueDate) {
        if (dueDate == null || dueDate.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Due date cannot be empty.");
        }

        try {
            LocalDate.parse(dueDate.trim());
        }
        catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                "Due date must be a valid YYYY-MM-DD date.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Converts optional null text to an empty string.
     *
     * @param text the original text
     * @return cleaned text
     */
    private String clean(String text) {
        if (text == null) {
            return "";
        }

        return text.trim();
    }
}
