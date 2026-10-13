
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
        assertEquals(1, manager.size());
        assertEquals(1, manager.countAssignments());
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
     * Tests creating a valid note.
     */
    public void testCreateNote() {
        Note note = manager.createNote(
            "Lecture Notes", "Linked lists");

        assertNotNull(note);
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
     * Tests finding a nonexistent item.
     */
    public void testFindMissingId() {
        assertNull(manager.findById("999"));
        assertNull(manager.findById("abc"));
        assertNull(manager.findById(null));
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
            manager.overdueAssignments(null);

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
    }
}
