
package tracker;

import student.TestCase;

/**
 * Tests the TrackerManager class.
 *
 * @author sreenidhitadigadapa
 * @version October 2026
 */
public class TrackerManagerTest extends TestCase
{
    private TrackerManager manager;

    public void setUp()
    {
        manager = new TrackerManager();
    }

    public void testCreateAssignment()
    {
        Assignment assignment = manager.createAssignment(
            "Lab Report", "Finish questions", "2026-10-20", "CS 2114");

        assertNotNull(assignment);
        assertEquals("Lab Report", assignment.getTitle());
        assertEquals(1, manager.size());
        assertEquals(1, manager.countAssignments());
    }

    public void testInvalidAssignment()
    {
        Exception exception = null;

        try
        {
            manager.createAssignment(
                "   ", "Description", "2026-10-20", "CS 2114");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals(0, manager.size());
    }

    public void testCreateNote()
    {
        Note note = manager.createNote(
            "Study Plan", "Review recursion");

        assertNotNull(note);
        assertEquals("Study Plan", note.getTitle());
        assertEquals(1, manager.countNotes());
    }

    public void testInvalidNote()
    {
        Exception exception = null;

        try
        {
            manager.createNote("Empty Note", "   ");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals(0, manager.size());
    }

    public void testFindById()
    {
        Assignment assignment = manager.createAssignment(
            "Homework", "Problems 1-5", "2026-10-25", "MATH 3134");

        assertEquals(assignment, manager.findById("1"));
        assertNull(manager.findById("999"));
        assertNull(manager.findById("abc"));
        assertNull(manager.findById(null));
    }

    public void testCompleteAssignment()
    {
        Assignment assignment = manager.createAssignment(
            "Project", "Finish project", "2026-10-30", "CS 2114");

        assertFalse(assignment.isCompleted());
        assertTrue(manager.completeAssignment("1"));
        assertTrue(assignment.isCompleted());

        assertFalse(manager.completeAssignment("999"));
    }

    public void testCannotCompleteNote()
    {
        manager.createNote("Reminder", "Attend meeting");

        assertFalse(manager.completeAssignment("1"));
        assertEquals(1, manager.size());
    }

    public void testRemoveItem()
    {
        manager.createNote("Meeting", "Group discussion");

        assertTrue(manager.removeItem("1"));
        assertEquals(0, manager.size());

        assertFalse(manager.removeItem("1"));
        assertFalse(manager.removeItem("invalid"));
    }

    public void testClear()
    {
        manager.createNote("Reminder", "Study");
        manager.createAssignment(
            "Exam", "Review", "2026-11-01", "CS 2114");

        manager.clear();

        assertEquals(0, manager.size());

        manager.clear();
        assertEquals(0, manager.size());
    }

    public void testGetAssignments()
    {
        manager.createAssignment(
            "Essay", "Write report", "2026-11-02", "ENGL 1106");
        manager.createNote("Reminder", "Study");

        assertEquals(1, manager.getAssignments().size());
        assertEquals(1, manager.countAssignments());
    }

    public void testGetNotes()
    {
        manager.createNote("Topic", "Linked lists");
        manager.createAssignment(
            "Quiz", "Study", "2026-11-03", "CS 2114");

        assertEquals(1, manager.getNotes().size());
        assertEquals(1, manager.countNotes());
    }

    public void testSearch()
    {
        manager.createNote("Recursion", "Review methods");
        manager.createAssignment(
            "Homework", "Practice recursion",
            "2026-11-04", "CS 2114");

        assertEquals(2, manager.search("recursion").size());
        assertEquals(0, manager.search("missing").size());
        assertEquals(0, manager.search("").size());
        assertEquals(0, manager.search(null).size());
    }

    public void testDuplicates()
    {
        manager.createAssignment(
            "Homework", "First", "2026-11-01", "CS 2114");
        manager.createAssignment(
            "Homework", "Second", "2026-11-02", "MATH 3134");

        assertEquals(2, manager.countAssignments());
        assertEquals(2, manager.size());
    }

    public void testOverdueAssignments()
    {
        Assignment first = manager.createAssignment(
            "Task A", "Work", "2026-10-01", "CS 2114");

        manager.createAssignment(
            "Task B", "Work", "2026-10-02", "CS 2114");

        first.markComplete();

        assertEquals(1, manager.overdueAssignments().size());
    }

    public void testEmptyManager()
    {
        assertEquals(0, manager.size());
        assertEquals(0, manager.countAssignments());
        assertEquals(0, manager.countNotes());
        assertEquals(0, manager.getAssignments().size());
        assertEquals(0, manager.getNotes().size());
        assertNull(manager.findById("1"));
        assertFalse(manager.removeItem("1"));
        assertFalse(manager.completeAssignment("1"));
    }
}

