package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

// -------------------------------------------------------------------------
/**
 * Manages items through the public LinkedChain API.
 *
 * @author sreenidhitadigadapa
 * @version 11 Oct 2026
 */
public class TrackerManager {
    private final LinkedChain<Item> items;
    private int nextId;
    /**
     * Creates a new TrackerManager instance.
     */
    public TrackerManager() { items = new LinkedChain<Item>(); nextId = 1; }
    /**
     * Performs the create assignment operation.
     * @param title input for this operation
     * @param text input for this operation
     * @param dueDate input for this operation
     * @param course input for this operation
     * @return result of the operation
     */
    public Assignment createAssignment(String title, String text, String dueDate, String course) {
        Assignment value = new Assignment(nextId, title, text, dueDate, course);
        items.addToEnd(value);
        nextId++;
        return value;
    }
    /**
     * Performs the create note operation.
     * @param title input for this operation
     * @param text input for this operation
     * @return result of the operation
     */
    public Note createNote(String title, String text) {
        Note value = new Note(nextId, title, text);
        items.addToEnd(value);
        nextId++;
        return value;
    }
    /**
     * Performs the find by id operation.
     * @param id input for this operation
     * @return result of the operation
     */
    public Item findById(String id) {
        int target;
        try { target = Integer.parseInt(id == null ? "" : id.trim()); }
        catch (NumberFormatException e) { return null; }
        for (int i = 0; i < items.size(); i++) {
            Item value = items.get(i);
            if (value.getId() == target) return value;
        }
        return null;
    }
    /**
     * Performs the complete assignment operation.
     * @param id input for this operation
     * @return result of the operation
     */
    public boolean completeAssignment(String id) {
        Item value = findById(id);
        if (!(value instanceof Assignment)) return false;
        ((Assignment)value).markComplete();
        return true;
    }
    /**
     * Performs the remove by id operation.
     * @param id input for this operation
     * @return result of the operation
     */
    public boolean removeById(String id) {
        Item value = findById(id);
        return value != null && items.remove(value);
    }
    /**
     * Performs the remove item operation.
     * @param id input for this operation
     * @return result of the operation
     */
    public boolean removeItem(String id) { return removeById(id); }
    /**
     * Performs the clear operation.
     */
    public void clear() { items.clear(); }
    /**
     * Performs the size operation.
     * @return result of the operation
     */
    public int size() { return items.size(); }
    /**
     * Performs the count assignments operation.
     * @return result of the operation
     */
    public int countAssignments() { return getAssignments().size(); }
    /**
     * Performs the count notes operation.
     * @return result of the operation
     */
    public int countNotes() { return getNotes().size(); }
    /**
     * Returns the all items.
     * @return result of the operation
     */
    public List<Item> getAllItems() {
        List<Item> result = new ArrayList<Item>();
        for (int i = 0; i < items.size(); i++) result.add(items.get(i));
        return result;
    }
    /**
     * Returns the assignments.
     * @return result of the operation
     */
    public List<Assignment> getAssignments() {
        List<Assignment> result = new ArrayList<Assignment>();
        for (Item value : getAllItems())
            if (value instanceof Assignment) result.add((Assignment)value);
        return result;
    }
    /**
     * Returns the notes.
     * @return result of the operation
     */
    public List<Note> getNotes() {
        List<Note> result = new ArrayList<Note>();
        for (Item value : getAllItems())
            if (value instanceof Note) result.add((Note)value);
        return result;
    }
    /**
     * Performs the search operation.
     * @param keyword input for this operation
     * @return result of the operation
     */
    public List<Item> search(String keyword) {
        List<Item> result = new ArrayList<Item>();
        if (keyword == null || keyword.trim().isEmpty()) return result;
        String query = keyword.trim().toLowerCase(Locale.ROOT);
        for (Item value : getAllItems()) {
            if (value.getTitle().toLowerCase(Locale.ROOT).contains(query)
                || value.getText().toLowerCase(Locale.ROOT).contains(query))
                result.add(value);
        }
        return result;
    }
    /**
     * Performs the filter by course operation.
     * @param course input for this operation
     * @return result of the operation
     */
    public List<Assignment> filterByCourse(String course) {
        List<Assignment> result = new ArrayList<Assignment>();
        if (course == null || course.trim().isEmpty()) return result;
        for (Assignment value : getAssignments())
            if (value.getCourse().equalsIgnoreCase(course.trim())) result.add(value);
        return result;
    }
    /**
     * Returns the incomplete assignments.
     * @return result of the operation
     */
    public List<Assignment> getIncompleteAssignments() {
        List<Assignment> result = new ArrayList<Assignment>();
        for (Assignment value : getAssignments())
            if (!value.isCompleted()) result.add(value);
        return result;
    }
    /**
     * Performs the save operation.
     */
    public void save() { save("tasktrack.txt"); }
    /**
     * Performs the save operation.
     * @param filename input for this operation
     */
    public void save(String filename) {
        if (filename == null || filename.trim().isEmpty())
            throw new IllegalArgumentException("Filename cannot be empty");
        List<String> lines = new ArrayList<String>();
        for (Item value : getAllItems()) lines.add(value.toString());
        try { Files.write(Path.of(filename), lines, StandardCharsets.UTF_8); }
        catch (IOException e) { throw new IllegalStateException("Could not save data", e); }
    }
}
