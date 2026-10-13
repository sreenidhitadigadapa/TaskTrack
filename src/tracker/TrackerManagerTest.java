
package tracker;

import java.time.LocalDate;
import java.util.List;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the TrackerManager class and its public behavior.
 *
 * @author sreenidhitadigadapa
 * @version 13 Oct 2026
 */
public class TrackerManagerTest extends TestCase {

    //~ Fields ................................................................

    private TrackerManager manager;


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a fresh manager before each test.
     */
    public void setUp() {
        manager = new TrackerManager();
    }


    // ----------------------------------------------------------
    /**
     * Tests creating a valid assignment.
     */
    public void testCreateAssignment() {
        Assignment assignment = manager.createAssignment(
            "Homework", "Chapter 3", "2026-10-20", "CS2114");

        assertNotNull(assignment);
        assertEquals(1, assignment.getId());
        assertEquals("Homework", assignment.getTitle());
        assertEquals(1, manager.size());
        assertEquals(1, manager.countAssignments());
        assertFalse(manager.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an assignment with an empty title.
     */
    public void testInvalidAssignmentTitle() {
        try {
            manager.createAssignment(
                "", "Homework", "2026-10-20", "CS2114");

            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals(0, manager.size());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an invalid due date.
     */
    public void testInvalidDueDate() {
        try {
            manager.createAssignment(
                "Homework", "Chapter 3",
                "2026-99-99", "CS2114");

            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertTrue(manager.isEmpty());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a null assignment title.
     */
    public void testNullAssignmentTitle() {
        try {
            manager.createAssignment(
                null, "Homework", "2026-10-20", "CS2114");

            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertTrue(manager.isEmpty());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests creating a valid note.
     */
    public void testCreateNote() {
        Note note = manager.createNote(
            "Lecture Notes", "Linked lists");

        assertNotNull(note);
        assertEquals(1, note.getId());
        assertEquals("Lecture Notes", note.getTitle());
        assertEquals(1, manager.size());
        assertEquals(1, manager.countNotes());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting empty note text.
     */
    public void testInvalidNoteText() {
        try {
            manager.createNote("Lecture Notes", "");

            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals(0, manager.size());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a null note title.
     */
    public void testNullNoteTitle() {
        try {
            manager.createNote(null, "Lecture content");

            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertTrue(manager.isEmpty());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests finding an assignment using its ID.
     */
    public void testFindById() {
        Assignment assignment = manager.createAssignment(
            "Quiz", "Study", "2026-10-20", "CS2114");

        Item found = manager.findById(
            String.valueOf(assignment.getId()));

        assertEquals(assignment, found);
    }


    // ----------------------------------------------------------
    /**
     * Tests finding a note using its ID.
     */
    public void testFindNoteById() {
        Note note = manager.createNote(
            "Notes", "Lecture content");

        Item found = manager.findById(
            String.valueOf(note.getId()));

        assertEquals(note, found);
    }


    // ----------------------------------------------------------
    /**
     * Tests finding a nonexistent or invalid item ID.
     */
    public void testFindMissingId() {
        assertNull(manager.findById("999"));
        assertNull(manager.findById("abc"));
        assertNull(manager.findById(null));
        assertNull(manager.findById(""));
        assertNull(manager.findById(" "));
    }


    // ----------------------------------------------------------
    /**
     * Tests completing a valid assignment.
     */
    public void testCompleteAssignment() {
        Assignment assignment = manager.createAssignment(
            "Project", "Finish code",
            "2026-10-20", "CS2114");

        assertTrue(manager.completeAssignment(
            String.valueOf(assignment.getId())));

        assertTrue(assignment.isCompleted());
    }


    // ----------------------------------------------------------
    /**
     * Tests completing a nonexistent assignment.
     */
    public void testCompleteMissingAssignment() {
        assertFalse(manager.completeAssignment("999"));
        assertFalse(manager.completeAssignment("abc"));
        assertFalse(manager.completeAssignment(null));
    }


    // ----------------------------------------------------------
    /**
     * Tests that a note cannot be completed as an assignment.
     */
    public void testCannotCompleteNote() {
        Note note = manager.createNote(
            "Notes", "Lecture content");

        assertFalse(manager.completeAssignment(
            String.valueOf(note.getId())));
    }


    // ----------------------------------------------------------
    /**
     * Tests retrieving all stored items.
     */
    public void testGetAllItems() {
        Assignment assignment = manager.createAssignment(
            "Homework", "Finish project",
            "2026-10-20", "CS2114");

        Note note = manager.createNote(
            "Study Notes", "Review linked lists");

        List<Item> allItems = manager.getAllItems();

        assertEquals(2, allItems.size());
        assertEquals(assignment, allItems.get(0));
        assertEquals(note, allItems.get(1));
        assertTrue(allItems.get(0) instanceof Assignment);
        assertTrue(allItems.get(1) instanceof Note);
    }


    // ----------------------------------------------------------
    /**
     * Tests retrieving items from an empty manager.
     */
    public void testGetAllItemsEmpty() {
        List<Item> allItems = manager.getAllItems();

        assertNotNull(allItems);
        assertTrue(allItems.isEmpty());
        assertEquals(0, allItems.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests that modifying the returned list does not
     * remove items from the manager.
     */
    public void testGetAllItemsSeparateList() {
        manager.createNote(
            "Notes", "Lecture content");

        List<Item> allItems = manager.getAllItems();

        allItems.clear();

        assertEquals(1, manager.size());
        assertEquals(0, allItems.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests overdue detection using a fixed date.
     */
    public void testOverdueAssignments() {
        manager.createAssignment(
            "Old Homework", "Finish",
            "2026-10-01", "CS2114");

        manager.createAssignment(
            "Future Homework", "Finish",
            "2026-10-30", "CS2114");

        List<Assignment> overdue =
            manager.overdueAssignments(
                LocalDate.of(2026, 10, 13));

        assertEquals(1, overdue.size());
        assertEquals("Old Homework",
            overdue.get(0).getTitle());
    }


    // ----------------------------------------------------------
    /**
     * Tests that completed assignments are not overdue.
     */
    public void testCompletedNotOverdue() {
        Assignment assignment = manager.createAssignment(
            "Old Project", "Finish",
            "2026-10-01", "CS2114");

        manager.completeAssignment(
            String.valueOf(assignment.getId()));

        assertTrue(manager.overdueAssignments(
            LocalDate.of(2026, 10, 13)).isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests that an assignment due today is not overdue.
     */
    public void testDueTodayNotOverdue() {
        manager.createAssignment(
            "Today's Homework", "Finish",
            "2026-10-13", "CS2114");

        List<Assignment> overdue =
            manager.overdueAssignments(
                LocalDate.of(2026, 10, 13));

        assertTrue(overdue.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests overdue detection with an empty collection.
     */
    public void testOverdueEmptyCollection() {
        assertTrue(manager.overdueAssignments(
            LocalDate.of(2026, 10, 13)).isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a null comparison date.
     */
    public void testNullComparisonDate() {
        try {
            manager.overdueAssignments((LocalDate)null);

            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertTrue(manager.isEmpty());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests counting two different item types.
     */
    public void testCountItems() {
        manager.createAssignment(
            "Homework", "Finish",
            "2026-10-20", "CS2114");

        manager.createNote("Notes", "Study");

        assertEquals(1, manager.countAssignments());
        assertEquals(1, manager.countNotes());
        assertEquals(2, manager.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests counts for an empty collection.
     */
    public void testEmptyCounts() {
        assertEquals(0, manager.countAssignments());
        assertEquals(0, manager.countNotes());
        assertEquals(0, manager.size());
        assertTrue(manager.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests clearing all stored items.
     */
    public void testClear() {
        manager.createNote("Notes", "Study");

        manager.clear();

        assertTrue(manager.isEmpty());
        assertEquals(0, manager.size());
        assertTrue(manager.getAllItems().isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests that IDs remain unique after clearing items.
     */
    public void testIdsAfterClear() {
        Note first = manager.createNote(
            "First Note", "First content");

        manager.clear();

        Note second = manager.createNote(
            "Second Note", "Second content");

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
        assertEquals(1, manager.size());
    }
}
