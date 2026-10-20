
package tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

// -------------------------------------------------------------------------
/**
 * Manages assignments and notes in TaskTrack.
 * Uses a generic LinkedChain to store items.
 *
 * @author sreenidhitadigadapa
 * @version 19 Oct 2026
 */
public class TrackerManager {

    //~ Fields ................................................................

    private LinkedChain<Item> items;
    private int nextId;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates an empty tracker manager.
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
     * @param title assignment title
     * @param text assignment description
     * @param dueDate assignment due date
     * @param course course name
     * @return newly created assignment
     * @throws IllegalArgumentException if assignment data is invalid
     */
    public Assignment createAssignment(
        String title,
        String text,
        String dueDate,
        String course) {

        Assignment assignment = new Assignment(
            nextId, title, text, dueDate, course);

        items.addToEnd(assignment);
        nextId++;

        return assignment;
    }


    // ----------------------------------------------------------
    /**
     * Creates and stores a new note.
     *
     * @param title note title
     * @param text note content
     * @return newly created note
     * @throws IllegalArgumentException if note data is invalid
     */
    public Note createNote(String title, String text) {

        Note note = new Note(nextId, title, text);

        items.addToEnd(note);
        nextId++;

        return note;
    }


    // ----------------------------------------------------------
    /**
     * Finds an item using its unique ID.
     *
     * @param id ID of the item to find
     * @return matching item or null if not found
     */
    public Item findById(String id) {

        if (id == null) {
            return null;
        }

        int number;

        try {
            number = Integer.parseInt(id.trim());
        }
        catch (NumberFormatException e) {
            return null;
        }

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if (item.getId() == number) {
                return item;
            }
        }

        return null;
    }


    // ----------------------------------------------------------
    /**
     * Marks an assignment as complete using its ID.
     *
     * @param id assignment ID
     * @return true if the assignment was found and completed
     */
    public boolean completeAssignment(String id) {

        Item item = findById(id);

        if (!(item instanceof Assignment)) {
            return false;
        }

        ((Assignment)item).markComplete();
        return true;
    }


    // ----------------------------------------------------------
    /**
     * Removes an assignment or note using its unique ID.
     *
     * @param id ID of the item to remove
     * @return true if the item was removed
     */
    public boolean removeById(String id) {

        Item item = findById(id);

        if (item == null) {
            return false;
        }

        return items.remove(item);
    }


    // ----------------------------------------------------------
    /**
     * Updates an existing assignment.
     *
     * @param id assignment ID
     * @param title updated title
     * @param text updated description
     * @param date updated due date
     * @param course updated course
     * @return true if the assignment was updated
     * @throws IllegalArgumentException if new values are invalid
     */
    public boolean updateAssignment(
        String id,
        String title,
        String text,
        String date,
        String course) {

        Item item = findById(id);

        if (!(item instanceof Assignment)) {
            return false;
        }

        ((Assignment)item).update(title, text, date, course);
        return true;
    }


    // ----------------------------------------------------------
    /**
     * Updates an existing note.
     *
     * @param id note ID
     * @param title updated note title
     * @param text updated note text
     * @return true if the note was updated
     * @throws IllegalArgumentException if new values are invalid
     */
    public boolean updateNote(
        String id,
        String title,
        String text) {

        Item item = findById(id);

        if (!(item instanceof Note)) {
            return false;
        }

        ((Note)item).update(title, text);
        return true;
    }




    // ----------------------------------------------------------
    /**
     * Returns all assignments and notes.
     *
     * @return list of all stored items
     */
    public List<Item> getAllItems() {

        List<Item> result = new ArrayList<Item>();

        for (int i = 0; i < items.size(); i++) {
            result.add(items.get(i));
        }

        return result;
    }


    // ----------------------------------------------------------
    /**
     * Returns all stored assignments.
     *
     * @return list of assignments
     */
    public List<Assignment> getAssignments() {

        List<Assignment> result = new ArrayList<Assignment>();

        for (Item item : getAllItems()) {
            if (item instanceof Assignment) {
                result.add((Assignment)item);
            }
        }

        return result;
    }


    // ----------------------------------------------------------
    /**
     * Returns all stored notes.
     *
     * @return list of notes
     */
    public List<Note> getNotes() {

        List<Note> result = new ArrayList<Note>();

        for (Item item : getAllItems()) {
            if (item instanceof Note) {
                result.add((Note)item);
            }
        }

        return result;
    }


    // ----------------------------------------------------------
    /**
     * Searches item titles and descriptions for a keyword.
     * The search is not case-sensitive.
     *
     * @param keyword text to search for
     * @return list of matching items
     */
    public List<Item> search(String keyword) {

        List<Item> result = new ArrayList<Item>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return result;
        }

        String searchText = keyword.trim()
            .toLowerCase(Locale.ROOT);

        for (Item item : getAllItems()) {

            String title = item.getTitle()
                .toLowerCase(Locale.ROOT);

            String text = item.getText()
                .toLowerCase(Locale.ROOT);

            if (title.contains(searchText)
                || text.contains(searchText)) {

                result.add(item);
            }
        }

        return result;
    }


    // ----------------------------------------------------------
    /**
     * Returns assignments belonging to a specified course.
     *
     * @param course course name to search for
     * @return assignments matching the course
     */
    public List<Assignment> filterByCourse(String course) {

        List<Assignment> result = new ArrayList<Assignment>();

        if (course == null || course.trim().isEmpty()) {
            return result;
        }

        for (Assignment assignment : getAssignments()) {
            if (assignment.getCourse()
                .equalsIgnoreCase(course.trim())) {

                result.add(assignment);
            }
        }

        return result;
    }


    // ----------------------------------------------------------
    /**
     * Returns incomplete assignments due before a given date.
     *
     * @param today date used for comparison
     * @return list of overdue assignments
     * @throws IllegalArgumentException if today is null
     */
    public List<Assignment> overdueAssignments(LocalDate today) {

        if (today == null) {
            throw new IllegalArgumentException(
                "Date cannot be null.");
        }

        List<Assignment> result = new ArrayList<Assignment>();

        for (Assignment assignment : getAssignments()) {
            if (assignment.isOverdue(today)) {
                result.add(assignment);
            }
        }

        return result;
    }


    // ----------------------------------------------------------
    /**
     * Returns assignments overdue as of today.
     *
     * @return list of overdue assignments
     */
    public List<Assignment> overdueAssignments() {
        return overdueAssignments(LocalDate.now());
    }


    // ----------------------------------------------------------
    /**
     * Returns the total number of stored items.
     *
     * @return number of items
     */
    public int size() {
        return items.size();
    }


    // ----------------------------------------------------------
    /**
     * Checks whether the tracker is empty.
     *
     * @return true if no items are stored
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }


    // ----------------------------------------------------------
    /**
     * Returns the number of assignments.
     *
     * @return assignment count
     */
    public int countAssignments() {
        return getAssignments().size();
    }


    // ----------------------------------------------------------
    /**
     * Returns the number of notes.
     *
     * @return note count
     */
    public int countNotes() {
        return getNotes().size();
    }


    // ----------------------------------------------------------
    /**
     * Removes all assignments and notes from the tracker.
     */
    public void clear() {
        items.clear();
    }


    // ----------------------------------------------------------
    /**
     * Counts assignments grouped by course.
     * Includes both completed and incomplete assignments.
     *
     * @return map of course names and assignment counts
     */
    public Map<String, Integer> courseWorkload() {

        Map<String, Integer> counts =
            new TreeMap<String, Integer>();

        for (Assignment assignment : getAssignments()) {

            String course = assignment.getCourse();

            if (counts.containsKey(course)) {
                counts.put(course, counts.get(course) + 1);
            }
            else {
                counts.put(course, 1);
            }
        }

        return counts;
    }


    // ----------------------------------------------------------
    /**
     * Returns assignments due within a seven-day period.
     * Results are sorted by due date.
     *
     * @param start first day of the week
     * @return assignments due during the selected week
     * @throws IllegalArgumentException if start is null
     */
    public List<Assignment> weeklySchedule(LocalDate start) {

        if (start == null) {
            throw new IllegalArgumentException(
                "Start date cannot be null.");
        }

        List<Assignment> result =
            new ArrayList<Assignment>();

        for (Assignment assignment : getAssignments()) {

            LocalDate due =
                LocalDate.parse(assignment.getDueDate());

            if (!due.isBefore(start)
                && due.isBefore(start.plusDays(7))) {

                result.add(assignment);
            }
        }

        result.sort((a, b) ->
            a.getDueDate().compareTo(b.getDueDate()));

        return result;
    }
}
