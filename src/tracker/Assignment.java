
package tracker;

// -------------------------------------------------------------------------
/**
 * Represents an assignment in TaskTrack.
 * Stores assignment details and completion status.
 *
 * @author zaraaomerr
 * @version 13 Oct 2026
 */
public class Assignment implements Item {

    //~ Fields ................................................................

    private int id;
    private String title;
    private String text;
    private String dueDate;
    private String course;
    private boolean completed;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new assignment.
     *
     * @param id assignment ID
     * @param title assignment title
     * @param text assignment description
     * @param dueDate assignment due date
     * @param course course name
     * @throws IllegalArgumentException if the title is empty
     */
    public Assignment(
        int id,
        String title,
        String text,
        String dueDate,
        String course) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Assignment title cannot be empty.");
        }

        this.id = id;
        this.title = title;
        this.text = text;
        this.dueDate = dueDate;
        this.course = course;
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
     * Returns the course name.
     *
     * @return course name
     */
    public String getCourse() {
        return course;
    }


    // ----------------------------------------------------------
    /**
     * Checks whether the assignment has been completed.
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
     *
     * @param newTitle updated title
     * @param newText updated description
     * @param newDueDate updated due date
     * @param newCourse updated course
     * @throws IllegalArgumentException if the new title is empty
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

        title = newTitle;
        text = newText;
        dueDate = newDueDate;
        course = newCourse;
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
