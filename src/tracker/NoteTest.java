
package tracker;

import student.TestCase;

/**
 * Tests the Note class.
 *
 */
public class NoteTest extends TestCase {

    private Note note;

    /**
     * Creates a note before each test.
     */
    public void setUp() {
        note = new Note(
            1,
            "Exam Notes",
            "Study for ECE exam");
    }

    /**
     * Tests the constructor and getters.
     */
    public void testGetters() {
        assertEquals(1, note.getId());
        assertEquals("Exam Notes", note.getTitle());
        assertEquals(
            "Study for ECE exam",
            note.getText());
    }

    /**
     * Tests that Note implements Item correctly.
     */
    public void testItemInterface() {
        Item item = note;

        assertEquals(1, item.getId());
        assertEquals("Exam Notes", item.getTitle());
        assertEquals(
            "Study for ECE exam",
            item.getText());
    }

    /**
     * Tests updating a note.
     */
    public void testUpdate() {
        note.update(
            "Updated Notes",
            "Review circuits and resistors");

        assertEquals("Updated Notes", note.getTitle());
        assertEquals(
            "Review circuits and resistors",
            note.getText());
        assertEquals(1, note.getId());
    }

    /**
     * Tests multiple updates.
     */
    public void testMultipleUpdates() {
        note.update("Math Notes", "Study derivatives");
        note.update("CS Notes", "Study linked lists");

        assertEquals("CS Notes", note.getTitle());
        assertEquals(
            "Study linked lists",
            note.getText());
    }

    /**
     * Tests rejecting an empty title.
     */
    public void testEmptyTitle() {
        try {
            new Note(2, "", "Study for exam");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests rejecting a null title.
     */
    public void testNullTitle() {
        try {
            new Note(2, null, "Study for exam");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests rejecting a title containing only spaces.
     */
    public void testBlankTitle() {
        try {
            new Note(2, "   ", "Study for exam");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests rejecting empty note text.
     */
    public void testEmptyText() {
        try {
            new Note(2, "Empty Note", "");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests rejecting null note text.
     */
    public void testNullText() {
        try {
            new Note(2, "Null Note", null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests rejecting text containing only spaces.
     */
    public void testBlankText() {
        try {
            new Note(2, "Blank Note", "   ");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests rejecting an empty title during update.
     */
    public void testUpdateEmptyTitle() {
        try {
            note.update("", "New text");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
            assertEquals(
                "Study for ECE exam",
                note.getText());
        }
    }

    /**
     * Tests rejecting a null title during update.
     */
    public void testUpdateNullTitle() {
        try {
            note.update(null, "New text");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
            assertEquals(
                "Study for ECE exam",
                note.getText());
        }
    }

    /**
     * Tests rejecting a blank title during update.
     */
    public void testUpdateBlankTitle() {
        try {
            note.update("   ", "New text");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
            assertEquals(
                "Study for ECE exam",
                note.getText());
        }
    }

    /**
     * Tests rejecting empty text during update.
     */
    public void testUpdateEmptyText() {
        try {
            note.update("New Title", "");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
            assertEquals(
                "Study for ECE exam",
                note.getText());
        }
    }

    /**
     * Tests rejecting null text during update.
     */
    public void testUpdateNullText() {
        try {
            note.update("New Title", null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
            assertEquals(
                "Study for ECE exam",
                note.getText());
        }
    }

    /**
     * Tests rejecting blank text during update.
     */
    public void testUpdateBlankText() {
        try {
            note.update("New Title", "   ");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
            assertEquals(
                "Study for ECE exam",
                note.getText());
        }
    }

    /**
     * Tests allowing notes with identical content.
     */
    public void testDuplicateNotes() {
        Note another = new Note(
            2,
            "Exam Notes",
            "Study for ECE exam");

        assertEquals(note.getTitle(), another.getTitle());
        assertEquals(note.getText(), another.getText());
        assertFalse(note.getId() == another.getId());
    }
}

