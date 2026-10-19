
package tracker;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the extended features of TrackerManager.
 * Includes normal, invalid-input, and edge-case tests.
 *
 * @author sreenidhitadigadapa
 * @version 19 Oct 2026
 */
public class TrackerManagerTest extends TestCase {

    //~ Fields ................................................................

    private TrackerManager manager;


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates an empty manager before each test.
     */
    public void setUp() {
        manager = new TrackerManager();
    }


    // ----------------------------------------------------------
    /**
     * Tests removing an item when duplicate titles exist.
     */
    public void testRemoveByIdDuplicates() {

        Assignment first = manager.createAssignment(
            "Homework", "First", "2026-10-20", "CS");

        Assignment second = manager.createAssignment(
            "Homework", "Second", "2026-10-21", "CS");

        assertTrue(manager.removeById(
            String.valueOf(first.getId())));

        assertNull(manager.findById(
            String.valueOf(first.getId())));

        assertEquals(
            second,
            manager.findById(
                String.valueOf(second.getId())));

        assertEquals(1, manager.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests removing an item that does not exist.
     */
    public void testRemoveMissingItem() {

        manager.createNote("Notes", "Study Java");

        assertFalse(manager.removeById("999"));
        assertEquals(1, manager.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests removing from an empty tracker.
     */
    public void testRemoveFromEmpty() {

        assertFalse(manager.removeById("1"));
        assertTrue(manager.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests searching assignments and notes.
     */
    public void testSearch() {

        manager.createAssignment(
            "Linked Lists",
            "Practice Java",
            "2026-10-20",
            "CS 2114");

        manager.createNote(
            "Exam Notes",
            "Review linked lists");

        List<Item> results = manager.search("LINKED");

        assertEquals(2, results.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests searching for a nonexistent keyword.
     */
    public void testSearchMissing() {

        manager.createNote("Java", "Practice");

        assertTrue(manager.search("Python").isEmpty());
        assertEquals(1, manager.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests searching with empty input.
     */
    public void testSearchEmptyInput() {

        assertTrue(manager.search("").isEmpty());
        assertTrue(manager.search(null).isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests filtering assignments by course.
     */
    public void testFilterByCourse() {

        manager.createAssignment(
            "Homework 1", "Java", "2026-10-20", "CS 2114");

        manager.createAssignment(
            "Homework 2", "Math", "2026-10-21", "MATH 3134");

        assertEquals(
            1,
            manager.filterByCourse("cs 2114").size());

        assertTrue(
            manager.filterByCourse("ECE 1004").isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests editing an assignment.
     */
    public void testUpdateAssignment() {

        Assignment assignment = manager.createAssignment(
            "Homework", "Old", "2026-10-20", "CS");

        assertTrue(manager.updateAssignment(
            String.valueOf(assignment.getId()),
            "Project",
            "New description",
            "2026-10-25",
            "ECE"));

        assertEquals("Project", assignment.getTitle());
        assertEquals("ECE", assignment.getCourse());
        assertEquals(
            "2026-10-25",
            assignment.getDueDate());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an invalid assignment update.
     */
    public void testInvalidAssignmentUpdate() {

        Assignment assignment = manager.createAssignment(
            "Homework", "Old", "2026-10-20", "CS");

        try {
            manager.updateAssignment(
                String.valueOf(assignment.getId()),
                "",
                "New",
                "2026-10-25",
                "CS");

            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals(
                "Homework",
                assignment.getTitle());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests editing an existing note.
     */
    public void testUpdateNote() {

        Note note = manager.createNote(
            "Old Notes", "Old text");

        assertTrue(manager.updateNote(
            String.valueOf(note.getId()),
            "New Notes",
            "New text"));

        assertEquals("New Notes", note.getTitle());
        assertEquals("New text", note.getText());
    }


    // ----------------------------------------------------------
    /**
     * Tests that a missing note cannot be updated.
     */
    public void testUpdateMissingNote() {

        assertFalse(manager.updateNote(
            "999", "Title", "Text"));
    }


    // ----------------------------------------------------------
    /**
     * Tests assigning a priority level.
     */
    public void testPriority() {

        Assignment assignment = manager.createAssignment(
            "Homework", "Java", "2026-10-20", "CS");

        assertEquals("Medium", assignment.getPriority());

        assertTrue(manager.setAssignmentPriority(
            String.valueOf(assignment.getId()),
            "high"));

        assertEquals("High", assignment.getPriority());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an invalid priority.
     */
    public void testInvalidPriority() {

        Assignment assignment = manager.createAssignment(
            "Homework", "Java", "2026-10-20", "CS");

        try {
            manager.setAssignmentPriority(
                String.valueOf(assignment.getId()),
                "Urgent");

            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals(
                "Medium",
                assignment.getPriority());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests overdue assignment detection.
     */
    public void testOverdueAssignments() {

        manager.createAssignment(
            "Old", "Past", "2026-10-18", "CS");

        manager.createAssignment(
            "Today", "Current", "2026-10-19", "CS");

        assertEquals(
            1,
            manager.overdueAssignments(
                LocalDate.of(2026, 10, 19)).size());
    }


    // ----------------------------------------------------------
    /**
     * Tests that completed assignments are not overdue.
     */
    public void testCompletedNotOverdue() {

        Assignment assignment = manager.createAssignment(
            "Old", "Past", "2026-10-18", "CS");

        assignment.markComplete();

        assertTrue(
            manager.overdueAssignments(
                LocalDate.of(2026, 10, 19)).isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests the course workload summary.
     */
    public void testCourseWorkload() {

        manager.createAssignment(
            "HW1", "A", "2026-10-20", "CS");

        manager.createAssignment(
            "HW2", "B", "2026-10-21", "CS");

        manager.createAssignment(
            "HW3", "C", "2026-10-22", "ECE");

        Map<String, Integer> workload =
            manager.courseWorkload();

        assertEquals(2, workload.get("CS").intValue());
        assertEquals(1, workload.get("ECE").intValue());
    }


    // ----------------------------------------------------------
    /**
     * Tests the weekly assignment schedule.
     */
    public void testWeeklySchedule() {

        manager.createAssignment(
            "Monday", "A", "2026-10-19", "CS");

        manager.createAssignment(
            "Friday", "B", "2026-10-23", "CS");

        manager.createAssignment(
            "Next Week", "C", "2026-10-27", "CS");

        List<Assignment> schedule =
            manager.weeklySchedule(
                LocalDate.of(2026, 10, 19));

        assertEquals(2, schedule.size());
        assertEquals(
            "Monday",
            schedule.get(0).getTitle());
        assertEquals(
            "Friday",
            schedule.get(1).getTitle());
    }


    // ----------------------------------------------------------
    /**
     * Tests counting assignments and notes.
     */
    public void testCounts() {

        manager.createAssignment(
            "Homework", "Java", "2026-10-20", "CS");

        manager.createNote("Notes", "Study");

        assertEquals(1, manager.countAssignments());
        assertEquals(1, manager.countNotes());
        assertEquals(2, manager.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests clearing a populated tracker.
     */
    public void testClear() {

        manager.createNote("Notes", "Study");

        manager.clear();

        assertTrue(manager.isEmpty());
        assertEquals(0, manager.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests clearing an already empty tracker.
     */
    public void testClearEmpty() {

        manager.clear();
        manager.clear();

        assertTrue(manager.isEmpty());
    }
}
