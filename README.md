
# TaskTrack — Project 2 Prototype + Tests

## Overview

TaskTrack is a Java console application that helps students organize assignments and notes. Users can add, view, search, complete, and remove assignments, as well as create and manage notes.

The application uses a generic `LinkedChain<Item>` to store both `Assignment` and `Note` objects. The project separates the user interface, application logic, and data models to make the code easier to maintain and test.

## MVP Features

The current prototype includes:

- Add assignments with a title, description, due date, and course.
- Add notes with a title and text.
- View all assignments and notes.
- Search items by title or text.
- Mark assignments as complete.
- Remove items using their IDs.
- View incomplete assignments.
- Display assignment and note counts.
- Clear all stored items.
- Save a text snapshot of the current data.

### Implemented Stretch Goal: Course Filtering

We implemented course filtering as our first stretch goal. Users can enter a course name to view assignments associated with that course.

The feature is implemented in `TrackerManager.filterByCourse(String)` and is accessible through menu option in `Main.java`.

Course matching ignores capitalization and surrounding whitespace.

## Project Structure

All Java files belong to the `tracker` package.

| File | Purpose | Owner |
|---|---|---|
| `Item.java` | Shared interface for assignments and notes | Miguel |
| `Assignment.java` | Assignment model and completion functionality | Zara |
| `Note.java` | Note model and update functionality | Zara |
| `LinkedChain.java` | Generic linked data structure | Sreenidhi |
| `TrackerManager.java` | Application logic and item management | Sreenidhi |
| `Main.java` | Console menu and user interaction | Miguel |
| `LinkedChainTest.java` | LinkedChain tests | Sreenidhi |
| `TrackerManagerTest.java` | Manager tests | Sreenidhi |
| `AssignmentTest.java` | Assignment model tests | Zara |
| `NoteTest.java` | Note model tests | Zara |

## How to Run

1. Open the project in Eclipse using JDK 17.
2. Ensure `student.jar` is included in the project's build path.
3. Run `Main.java` as a Java Application.
4. Select an option from the console menu.
5. Run the test classes as JUnit Tests.

## LinkedChain Reuse

Our project uses two different element types:

- `Assignment`
- `Note`

Both classes implement the `Item` interface and are stored using the same generic `LinkedChain<Item>`.

We do not create separate linked-chain implementations for different item types. This demonstrates generic reuse and satisfies the LinkedChain reuse requirement.

## Using the LinkedChain Public API

The application accesses LinkedChain only through its public methods, including:

- `addToEnd()`
- `get()`
- `size()`
- `remove()`
- `clear()`

All application-specific logic is handled by `TrackerManager`, not LinkedChain.

The application does not directly access private nodes or internal references such as `firstNode`.

### Big-O Analysis

Let **n** represent the number of stored items.

| Operation | Time Complexity | Explanation |
|---|---|---|
| Add assignment or note | O(n) | Adding to the end requires traversing the chain |
| Find item by ID | O(n²) | Repeated indexed access traverses the chain |
| Remove item | O(n²) | Finds the item before removing it |
| Search items | O(n²) | Uses repeated indexed access |
| Filter assignments by course | O(n²) | Traverses stored items through the manager |
| Count assignments or notes | O(n²) | Uses filtered item retrieval |
| Mark assignment complete | O(n²) | First locates the assignment |
| Get number of items | O(1) | Uses the stored size |
| Clear all items | O(1) | Resets the chain references |

The current implementation prioritizes correct functionality and reuse of the provided LinkedChain API.

A possible future improvement is using a single-pass traversal or iterator to reduce the time complexity of searching and filtering.

## Validation and Testing

The application includes validation to prevent invalid input from causing unexpected crashes.

| Input or Situation | Expected Behavior |
|---|---|
| Blank assignment title | Reject input and display an error |
| Blank note title | Reject input and display an error |
| Blank note text | Reject input and display an error |
| Invalid or nonexistent item ID | Return a not-found response |
| Attempt to complete a note | Return false |
| Unknown course name | Display no matching items |
| Invalid menu option | Display an error and continue |
| Remove nonexistent item | Handle without crashing |
| Empty collection | Display an appropriate empty result |

### Test Coverage

**LinkedChainTest.java**
- Adding elements
- Removing elements
- Handling duplicates
- Empty collections
- Invalid input and indexes

**TrackerManagerTest.java**
- Creating assignments and notes
- Searching for items
- Removing items
- Marking assignments complete
- Filtering assignments by course
- Handling invalid IDs
- Handling empty collections
- Clearing stored items
- Saving data

**AssignmentTest.java**
- Creating assignments
- Accessing assignment information
- Updating assignments
- Marking assignments complete
- Handling invalid input

**NoteTest.java**
- Creating notes
- Accessing note information
- Updating notes
- Handling invalid input

The test classes are included in the project. 

## Reflection

### 1. What Changed From Our Original Specification?

We organized the project around a shared `Item` interface, allowing assignments and notes to use the same generic LinkedChain. We also separated the console menu from the application logic by using `Main` for user interaction and `TrackerManager` for managing stored items.

We implemented course filtering as our first stretch goal because it could use the course information already stored in assignments.

### 2. Did We Make Any Changes to LinkedChain?

We did not add application-specific functionality to LinkedChain. Instead, we designed TrackerManager to use the public methods provided by the generic data structure.

### 3. What Unknowns Remain?

One remaining consideration is performance when the number of assignments and notes becomes large. Because indexed access requires traversing the linked structure, some search and filtering operations currently take O(n²) time.

We also need to confirm that the integrated project passes all tests using the course testing environment and that its input-validation behavior matches our original scope.

### 4. What Does Not Work Yet?

The current prototype can save a text snapshot, but it does not load previously saved data back into the application.

Other stretch goals, such as automatic overdue detection, priority levels, recurring assignments, weekly schedules, and course workload summaries, have not yet been implemented.

## Team Contributions and Implementation Status

| Team Member | Responsibility | Status |
|---|---|---|
| Miguel | `Item.java` shared interface | Complete in code |
| Miguel | `Main.java` and interactive menu | Complete in code |
| Zara | `Assignment.java` | Complete in code |
| Zara | `Note.java` | Complete in code |
| Zara | `AssignmentTest.java` | Complete in code |
| Zara | `NoteTest.java` | Complete in code |
| Sreenidhi | LinkedChain integration | Complete in reference code; instructor version must be restored |
| Sreenidhi | `LinkedChainTest.java` | Complete in code |
| Sreenidhi | `TrackerManager.java` | Complete in code |
| Sreenidhi | `TrackerManagerTest.java` | Complete in code |
| Everyone | README and reflection | Complete in draft |

## Next Steps

| Priority | Task | Owner |
|---|---|---|
| 1 | Confirm final versions of shared interface and menu | Miguel and Zara |
| 2 | Attach or link system diagram | Miguel |
| 3 | Run all four test classes using `student.jar` and resolve failures | Sreenidhi and Zara |
| 4 | Test complete console menu, including course filtering and invalid input | Miguel |
| 5 | Review Big-O estimates and validation against original scope | Entire team |
| 6 | Complete final integration review and submit | Entire team |

### Stretch Goal Status

| Stretch Goal | Status |
|---|---|
| Course filtering | Implemented |
| Priority levels | Not complete |
| Overdue assignment detection | Not complete |
| Course workload summary | Not complete |
| Weekly schedule | Not complete |
| Recurring assignments | Not complete |

### Additional Tests to Verify

Before submission, we will verify:

- Course filtering with matching and nonmatching courses.
- Searching an empty collection.
- Removing a nonexistent item.
- Marking an assignment complete.
- Invalid assignment and note creation.
- Saving with an invalid filename.
- Invalid menu choices.

## GenAI Usage

Generative AI was used to assist with drafting and revising Java code, test cases, documentation, and the README.

We are responsible for reviewing the generated material, verifying correctness, testing the final application, and ensuring that the documented contributions accurately reflect each member's work.

## Conclusion

TaskTrack provides a Java application structure for organizing assignments and notes using a reusable generic linked data structure.

The prototype includes the planned core functionality and a course-filtering stretch goal. Future development will focus on additional scheduling features, improved performance, and persistent data storage.
