
package tracker;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Assignment class and its public methods.
 *
 * @author zaraaomerr
 * @version 13 Oct 2026
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
        assertEquals(
            "2026-10-20",
            assignment.getDueDate());
        assertEquals("CS 2114", assignment.getCourse());
    }


    // ----------------------------------------------------------
    /**
     * Tests that Assignment implements Item correctly.
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
     * Tests that an assignment starts incomplete.
     */
    public void testNotCompleted() {
        assertFalse(assignment.isCompleted());
    }


    // ----------------------------------------------------------
    /**
     * Tests marking an assignment as complete.
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
        assertEquals(
            "2026-10-25",
            assignment.getDueDate());
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
     * Tests rejecting an empty title.
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
     * Tests rejecting a null title.
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
     * Tests rejecting a title containing only spaces.
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
            assertEquals(
                "2026-10-20",
                assignment.getDueDate());
            assertEquals("CS 2114", assignment.getCourse());
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
            assertEquals(
                "Complete Java exercises",
                assignment.getText());
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
            assertEquals(
                "2026-10-20",
                assignment.getDueDate());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests allowing assignments with the same title.
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
        assertFalse(assignment.getId() == another.getId());
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
            + " | Completed: false";

        assertEquals(expected, assignment.toString());
    }


    // ----------------------------------------------------------
    /**
     * Tests that completing an assignment updates its
     * string representation.
     */
    public void testToStringAfterCompletion() {
        assignment.markComplete();

        String expected = "ID: 1"
            + " | Title: Homework 1"
            + " | Description: Complete Java exercises"
            + " | Course: CS 2114"
            + " | Due: 2026-10-20"
            + " | Completed: true";

        assertEquals(expected, assignment.toString());
    }


    // ----------------------------------------------------------
    /**
     * Tests that updating an assignment changes its
     * string representation.
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
            + " | Completed: false";

        assertEquals(expected, assignment.toString());
    }
}
