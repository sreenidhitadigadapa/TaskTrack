
package tracker;

import java.time.LocalDate;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Assignment class in TaskTrack.
 * Includes normal, invalid-input, and edge-case tests.
 *
 * @author zaraaomerr
 * @version 19 Oct 2026
 */
public class AssignmentTest extends TestCase {

    //~ Fields ................................................................

    private Assignment assignment;


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates an assignment before each test.
     */
    public void setUp() {
        assignment = new Assignment(
            1,
            "Homework 1",
            "Complete Java exercises",
            "2026-10-20",
            "CS 2114");
    }


    // ----------------------------------------------------------
    /**
     * Tests the constructor and getter methods.
     */
    public void testGetters() {
        assertEquals(1, assignment.getId());
        assertEquals("Homework 1", assignment.getTitle());
        assertEquals(
            "Complete Java exercises",
            assignment.getText());
        assertEquals("2026-10-20", assignment.getDueDate());
        assertEquals("CS 2114", assignment.getCourse());
        assertEquals("Medium", assignment.getPriority());
    }


    // ----------------------------------------------------------
    /**
     * Tests that Assignment implements Item.
     */
    public void testItemInterface() {
        Item item = assignment;

        assertEquals(1, item.getId());
        assertEquals("Homework 1", item.getTitle());
        assertEquals(
            "Complete Java exercises",
            item.getText());
    }


    // ----------------------------------------------------------
    /**
     * Tests that assignments start incomplete.
     */
    public void testNotCompleted() {
        assertFalse(assignment.isCompleted());
    }


    // ----------------------------------------------------------
    /**
     * Tests marking an assignment complete.
     */
    public void testMarkComplete() {
        assignment.markComplete();

        assertTrue(assignment.isCompleted());
    }


    // ----------------------------------------------------------
    /**
     * Tests marking an assignment complete twice.
     */
    public void testMarkCompleteTwice() {
        assignment.markComplete();
        assignment.markComplete();

        assertTrue(assignment.isCompleted());
    }


    // ----------------------------------------------------------
    /**
     * Tests updating assignment information.
     */
    public void testUpdate() {
        assignment.update(
            "Project 2",
            "Finish linked chain",
            "2026-10-25",
            "CS 2114");

        assertEquals("Project 2", assignment.getTitle());
        assertEquals(
            "Finish linked chain",
            assignment.getText());
        assertEquals("2026-10-25", assignment.getDueDate());
        assertEquals("CS 2114", assignment.getCourse());
        assertEquals(1, assignment.getId());
    }


    // ----------------------------------------------------------
    /**
     * Tests keeping completion status after an update.
     */
    public void testUpdateKeepsCompletion() {
        assignment.markComplete();

        assignment.update(
            "Updated Homework",
            "New instructions",
            "2026-10-30",
            "CS 2114");

        assertTrue(assignment.isCompleted());
        assertEquals(1, assignment.getId());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an empty assignment title.
     */
    public void testEmptyTitle() {
        try {
            new Assignment(
                2, "", "Description",
                "2026-10-20", "CS 2114");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a null assignment title.
     */
    public void testNullTitle() {
        try {
            new Assignment(
                2, null, "Description",
                "2026-10-20", "CS 2114");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a blank assignment title.
     */
    public void testBlankTitle() {
        try {
            new Assignment(
                2, "   ", "Description",
                "2026-10-20", "CS 2114");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an invalid due date.
     */
    public void testInvalidDueDate() {
        try {
            new Assignment(
                2, "Homework", "Description",
                "2026-02-30", "CS 2114");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a null due date.
     */
    public void testNullDueDate() {
        try {
            new Assignment(
                2, "Homework", "Description",
                null, "CS 2114");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an empty title during update.
     */
    public void testUpdateEmptyTitle() {
        try {
            assignment.update(
                "", "New text", "2026-10-25", "CS 2114");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Homework 1", assignment.getTitle());
            assertEquals(
                "Complete Java exercises",
                assignment.getText());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a null title during update.
     */
    public void testUpdateNullTitle() {
        try {
            assignment.update(
                null, "New text", "2026-10-25", "CS 2114");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Homework 1", assignment.getTitle());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a blank title during update.
     */
    public void testUpdateBlankTitle() {
        try {
            assignment.update(
                "   ", "New text", "2026-10-25", "CS 2114");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Homework 1", assignment.getTitle());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an invalid date during update.
     */
    public void testUpdateInvalidDate() {
        try {
            assignment.update(
                "Project 2",
                "New text",
                "invalid-date",
                "CS 2114");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Homework 1", assignment.getTitle());
            assertEquals("2026-10-20", assignment.getDueDate());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests allowing assignments with duplicate titles.
     */
    public void testDuplicateTitles() {
        Assignment another = new Assignment(
            2,
            "Homework 1",
            "Different homework",
            "2026-10-22",
            "ECE 1004");

        assertEquals(
            assignment.getTitle(),
            another.getTitle());

        assertFalse(
            assignment.getId() == another.getId());
    }


    // ----------------------------------------------------------
    /**
     * Tests the default priority level.
     */
    public void testDefaultPriority() {
        assertEquals("Medium", assignment.getPriority());
    }


    // ----------------------------------------------------------
    /**
     * Tests changing an assignment's priority.
     */
    public void testSetPriority() {
        assignment.setPriority("High");

        assertEquals("High", assignment.getPriority());

        assignment.setPriority("low");

        assertEquals("Low", assignment.getPriority());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an invalid priority.
     */
    public void testInvalidPriority() {
        try {
            assignment.setPriority("Urgent");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Medium", assignment.getPriority());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a null priority.
     */
    public void testNullPriority() {
        try {
            assignment.setPriority(null);

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Medium", assignment.getPriority());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests an incomplete overdue assignment.
     */
    public void testOverdue() {
        assertTrue(
            assignment.isOverdue(
                LocalDate.of(2026, 10, 21)));
    }


    // ----------------------------------------------------------
    /**
     * Tests an assignment that is not overdue.
     */
    public void testNotOverdue() {
        assertFalse(
            assignment.isOverdue(
                LocalDate.of(2026, 10, 19)));
    }


    // ----------------------------------------------------------
    /**
     * Tests that an assignment due today is not overdue.
     */
    public void testDueTodayNotOverdue() {
        assertFalse(
            assignment.isOverdue(
                LocalDate.of(2026, 10, 20)));
    }


    // ----------------------------------------------------------
    /**
     * Tests that completed assignments are not overdue.
     */
    public void testCompletedNotOverdue() {
        assignment.markComplete();

        assertFalse(
            assignment.isOverdue(
                LocalDate.of(2026, 10, 25)));
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a null comparison date.
     */
    public void testNullComparisonDate() {
        try {
            assignment.isOverdue(null);

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests the string representation of an assignment.
     */
    public void testToString() {
        String expected = "ID: 1"
            + " | Title: Homework 1"
            + " | Description: Complete Java exercises"
            + " | Course: CS 2114"
            + " | Due: 2026-10-20"
            + " | Completed: false"
            + " | Priority: Medium";

        assertEquals(expected, assignment.toString());
    }


    // ----------------------------------------------------------
    /**
     * Tests the string after marking an assignment complete.
     */
    public void testToStringAfterCompletion() {
        assignment.markComplete();

        String expected = "ID: 1"
            + " | Title: Homework 1"
            + " | Description: Complete Java exercises"
            + " | Course: CS 2114"
            + " | Due: 2026-10-20"
            + " | Completed: true"
            + " | Priority: Medium";

        assertEquals(expected, assignment.toString());
    }


    // ----------------------------------------------------------
    /**
     * Tests the string after updating an assignment.
     */
    public void testToStringAfterUpdate() {
        assignment.update(
            "Project 2",
            "Finish linked chain",
            "2026-10-25",
            "CS 2114");

        String expected = "ID: 1"
            + " | Title: Project 2"
            + " | Description: Finish linked chain"
            + " | Course: CS 2114"
            + " | Due: 2026-10-25"
            + " | Completed: false"
            + " | Priority: Medium";

        assertEquals(expected, assignment.toString());
    }
}
