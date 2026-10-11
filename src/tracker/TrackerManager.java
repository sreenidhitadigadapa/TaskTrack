package tracker;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Manages assignments and notes for TaskTrack.
 *
 * @author sreenidhitadigadapa
 * @version October 2026
 */
public class TrackerManager
{
    private LinkedChain<Item> toDoList;
    private int nextId;

    /**
     * Creates an empty tracker.
     */
    public TrackerManager()
    {
        toDoList = new LinkedChain<Item>();
        nextId = 1;
    }

    /**
     * Creates and stores an assignment.
     *
     * @param title assignment title
     * @param text assignment description
     * @param dueDate due date
     * @param course course name
     * @return created assignment
     */
    public Assignment createAssignment(
        String title,
        String text,
        String dueDate,
        String course)
    {
        if (title == null || title.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "Assignment title cannot be empty");
        }

        Assignment assignment = new Assignment(
            nextId, title.trim(), text, dueDate, course);

        toDoList.add(assignment);
        nextId++;
        return assignment;
    }

    /**
     * Creates and stores a note.
     *
     * @param title note title
     * @param text note text
     * @return created note
     */
    public Note createNote(String title, String text)
    {
        if (text == null || text.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "Note text cannot be empty");
        }

        Note note = new Note(nextId, title, text);
        toDoList.add(note);
        nextId++;
        return note;
    }

    /**
     * Finds an item by its ID.
     *
     * @param id item ID as a string
     * @return matching item, or null
     */
    public Item findById(String id)
    {
        if (id == null)
        {
            return null;
        }

        int targetId;

        try
        {
            targetId = Integer.parseInt(id.trim());
        }
        catch (NumberFormatException e)
        {
            return null;
        }

        for (int i = 0; i < toDoList.size(); i++)
        {
            Item item = toDoList.get(i);

            if (item.getId() == targetId)
            {
                return item;
            }
        }

        return null;
    }

    /**
     * Marks an assignment complete.
     *
     * @param id assignment ID
     * @return true if completed, false otherwise
     */
    public boolean completeAssignment(String id)
    {
        Item item = findById(id);

        if (item instanceof Assignment)
        {
            ((Assignment)item).markComplete();
            return true;
        }

        return false;
    }

    /**
     * Removes an item by ID.
     *
     * @param id item ID
     * @return true if removed
     */
    public boolean removeItem(String id)
    {
        Item item = findById(id);

        if (item == null)
        {
            return false;
        }

        return toDoList.remove(item);
    }

    /**
     * Removes all items.
     */
    public void clear()
    {
        toDoList.clear();
    }

    /**
     * Returns all assignments.
     *
     * @return list of assignments
     */
    public List<Assignment> getAssignments()
    {
        List<Assignment> results = new ArrayList<Assignment>();

        for (int i = 0; i < toDoList.size(); i++)
        {
            Item item = toDoList.get(i);

            if (item instanceof Assignment)
            {
                results.add((Assignment)item);
            }
        }

        return results;
    }

    /**
     * Returns all notes.
     *
     * @return list of notes
     */
    public List<Note> getNotes()
    {
        List<Note> results = new ArrayList<Note>();

        for (int i = 0; i < toDoList.size(); i++)
        {
            Item item = toDoList.get(i);

            if (item instanceof Note)
            {
                results.add((Note)item);
            }
        }

        return results;
    }

    /**
     * Searches titles and text for a keyword.
     *
     * @param keyword search term
     * @return matching items
     */
    public List<Item> search(String keyword)
    {
        List<Item> results = new ArrayList<Item>();

        if (keyword == null || keyword.trim().isEmpty())
        {
            return results;
        }

        String query = keyword.trim().toLowerCase();

        for (int i = 0; i < toDoList.size(); i++)
        {
            Item item = toDoList.get(i);

            String title = item.getTitle();
            String text = item.getText();

            boolean titleMatch = title != null
                && title.toLowerCase().contains(query);
            boolean textMatch = text != null
                && text.toLowerCase().contains(query);

            if (titleMatch || textMatch)
            {
                results.add(item);
            }
        }

        return results;
    }

    /**
     * Returns incomplete assignments.
     *
     * @return incomplete assignments
     */
    public List<Assignment> overdueAssignments()
    {
        List<Assignment> results = new ArrayList<Assignment>();

        for (Assignment assignment : getAssignments())
        {
            if (!assignment.isCompleted())
            {
                results.add(assignment);
            }
        }

        return results;
    }

    /**
     * Gets the total number of items.
     *
     * @return item count
     */
    public int size()
    {
        return toDoList.size();
    }

    /**
     * Counts assignments.
     *
     * @return assignment count
     */
    public int countAssignments()
    {
        return getAssignments().size();
    }

    /**
     * Counts notes.
     *
     * @return note count
     */
    public int countNotes()
    {
        return getNotes().size();
    }

    /**
     * Saves items to a text file.
     */
    public void save()
    {
        try (PrintWriter writer =
            new PrintWriter(new FileWriter("tasktrack.txt")))
        {
            for (int i = 0; i < toDoList.size(); i++)
            {
                Item item = toDoList.get(i);
                writer.println(item.getId() + " | "
                    + item.getTitle() + " | " + item.getText());
            }
        }
        catch (IOException e)
        {
            throw new IllegalStateException(
                "Could not save TaskTrack data", e);
        }
    }
}

