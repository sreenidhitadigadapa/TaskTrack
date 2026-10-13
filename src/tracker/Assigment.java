
package tracker;

/**
 * Represents an assignment in TaskTrack.
 * Stores assignment details and completion status.
 *
 */
public class Assignment implements Item {

    private int id;
    private String title;
    private String text;
    private String dueDate;
    private String course;
    private boolean completed;

    /**
     * Creates a new assignment.
     *
     * @param id assignment ID
     * @param title assignment title
     * @param text assignment description
     * @param dueDate assignment due date
     * @param course course name
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

    /**
     * Returns the assignment ID.
     *
     * @return assignment ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the assignment title.
     *
     * @return assignment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the assignment description.
     *
     * @return assignment description
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the due date.
     *
     * @return due date
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Returns the course name.
     *
     * @return course name
     */
    public String getCourse() {
        return course;
    }

    /**
     * Checks if the assignment is complete.
     *
     * @return true if completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks the assignment as completed.
     */
    public void markComplete() {
        completed = true;
    }

    /**
     * Updates the assignment information.
     *
     * @param newTitle updated title
     * @param newText updated description
     * @param newDueDate updated due date
     * @param newCourse updated course
     */
    public void update(
        String newTitle,
        String newText,
        String newDueDate,
        String newCourse) {

        if (newTitle == null
            || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Assignment title cannot be empty.");
        }

        title = newTitle;
        text = newText;
        dueDate = newDueDate;
        course = newCourse;
    }
}
