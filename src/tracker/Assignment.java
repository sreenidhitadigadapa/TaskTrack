
package tracker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// -------------------------------------------------------------------------
/**
 * Represents an assignment in TaskTrack.
 * Stores assignment details and completion status.
 *
 * @author zaraaomerr
 * @version 19 Oct 2026
 */
public class Assignment implements Item {

    //~ Fields ................................................................

    private final int id;
    private String title;
    private String text;
    private String dueDate;
    private String course;
    private boolean completed;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new incomplete assignment.
     *
     * @param id assignment ID
     * @param title assignment title
     * @param text assignment description
     * @param dueDate due date in YYYY-MM-DD format
     * @param course course name
     * @throws IllegalArgumentException if title or date is invalid
     */
    public Assignment(
        int id,
        String title,
        String text,
        String dueDate,
        String course) {

        this.id = id;
        update(title, text, dueDate, course);
        this.completed = false;
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Returns the assignment ID.
     *
     * @return assignment ID
     */
    @Override
    public int getId() {
        return id;
    }


    // ----------------------------------------------------------
    /**
     * Returns the assignment title.
     *
     * @return assignment title
     */
    @Override
    public String getTitle() {
        return title;
    }


    // ----------------------------------------------------------
    /**
     * Returns the assignment description.
     *
     * @return assignment description
     */
    @Override
    public String getText() {
        return text;
    }


    // ----------------------------------------------------------
    /**
     * Returns the assignment due date.
     *
     * @return due date in YYYY-MM-DD format
     */
    public String getDueDate() {
        return dueDate;
    }


    // ----------------------------------------------------------
    /**
     * Returns the assignment course.
     *
     * @return course name
     */
    public String getCourse() {
        return course;
    }


    // ----------------------------------------------------------
    /**
     * Checks whether the assignment is complete.
     *
     * @return true if completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }


    // ----------------------------------------------------------
    /**
     * Marks the assignment as completed.
     */
    public void markComplete() {
        completed = true;
    }


    // ----------------------------------------------------------
    /**
     * Updates the assignment information.
     * Keeps the ID and completion status unchanged.
     *
     * @param newTitle updated title
     * @param newText updated description
     * @param newDueDate updated due date
     * @param newCourse updated course
     * @throws IllegalArgumentException if title or date is invalid
     */
    public void update(
        String newTitle,
        String newText,
        String newDueDate,
        String newCourse) {

        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Assignment title cannot be empty.");
        }

        if (newDueDate == null) {
            throw new IllegalArgumentException(
                "Due date must be YYYY-MM-DD.");
        }

        try {
            LocalDate.parse(newDueDate.trim());
        }
        catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                "Due date must be a valid YYYY-MM-DD date.");
        }

        title = newTitle.trim();
        text = newText == null ? "" : newText.trim();
        dueDate = newDueDate.trim();
        course = newCourse == null ? "" : newCourse.trim();
    }


    // ----------------------------------------------------------
    /**
     * Checks whether the assignment is overdue.
     * Completed assignments are not considered overdue.
     *
     * @param today date used for comparison
     * @return true if incomplete and due before today
     * @throws IllegalArgumentException if today is null
     */
    public boolean isOverdue(LocalDate today) {
        if (today == null) {
            throw new IllegalArgumentException(
                "Comparison date cannot be null.");
        }

        return !completed
            && LocalDate.parse(dueDate).isBefore(today);
    }


    // ----------------------------------------------------------
    /**
     * Returns a readable description of the assignment.
     *
     * @return assignment details as a string
     */
    @Override
    public String toString() {
        return "ID: " + id
            + " | Title: " + title
            + " | Description: " + text
            + " | Course: " + course
            + " | Due: " + dueDate
            + " | Completed: " + completed;
    }
}
