
# TaskTrack

TaskTrack is a Java console application that helps students organize assignments and notes. Our prototype allows users to add assignments, add notes, mark assignments as complete, and view overdue assignments.

The application uses the instructor-provided generic `LinkedChain<T>` to store both `Assignment` and `Note` objects through the shared `Item` interface.

All implemented tests pass, and the prototype compiles and runs successfully.

## How to Compile and Run

### Requirements

- Java JDK 17
- Eclipse IDE
- CS2-Support project configured in Eclipse

### Run the Application

1. Open the TaskTrack project in Eclipse.
2. Make sure the project is configured to use JDK 17.
3. Make sure the `CS2-Support` project is available in the Eclipse workspace and builds successfully.
4. Open `MainMenu.java` in the `tracker` package.
5. Right-click and select **Run As > Java Application**.
6. Use the console menu to interact with TaskTrack.

### Run the Tests

1. Make sure `CS2-Support` is available in the Eclipse workspace.
2. Open one of the following test classes:
   - `LinkedChainTest.java`
   - `TrackerManagerTest.java`
   - `AssignmentTest.java`
   - `NoteTest.java`
3. Right-click the test class and select **Run As > JUnit Test**.
4. Review the results in the JUnit window.

All implemented tests pass, and the complete prototype runs successfully.

## Reflection

### 1. What changed from our original specification?

Our original plan included more features for managing assignments and notes, such as searching, removing items, editing items, and organizing assignments by course. For this prototype, we focused on five working features: adding assignments, adding notes, completing assignments, viewing all items, and viewing overdue assignments.

We also implemented overdue assignment detection as our working stretch goal. This allowed us to demonstrate additional functionality while keeping the prototype manageable and functional.

For our final application, we plan to implement all the extended features outlined in our original specification so that the completed project includes the full functionality.

### 2. What changes did we make to LinkedChain?

We used the instructor-provided `LinkedChain<T>` without changing its generic storage logic. Its existing public methods were sufficient for our prototype.

We store both `Assignment` and `Note` objects using `LinkedChain<Item>`. The application interacts with the chain through public methods such as `addToEnd()`, `get()`, `size()`, and `isEmpty()`.

All application-specific logic remains in `TrackerManager`, keeping the linked chain reusable for different element types.

### 3. What unknowns remain?

We still need to decide how to save assignments and notes so they remain available after the program closes. We also need to determine how to expand the menu as we add more features without making it difficult to use.

Another consideration is improving the efficiency of searching and traversing the linked chain as more items are added.

### 4. What does not work yet?

All features included in the current prototype work, and all implemented tests pass.

However, the full MVP is not complete. The current menu does not support searching for items, removing items, editing assignments or notes, filtering assignments by course, or saving data between sessions.

These features will be added during the next development phase.

## Next Steps

Our next goal is to expand the working prototype into the full MVP. 

| Priority | Task | Owner | Planned Tests |
|----------|------|-------|---------------|
| 1 | Add item removal functionality | Sreenidhi | Existing and nonexistent ID tests |
| 2 | Add assignment editing through the application | Zara | Valid and invalid update tests |
| 3 | Add menu options for editing and removing items | Miguel | Menu interaction and invalid-input tests |
| 4 | Add search functionality | Sreenidhi | Matching and nonmatching keyword tests |
| 5 | Add note editing through the application | Zara | Valid and invalid update tests |
| 6 | Improve menu navigation and user feedback | Miguel | Invalid input and navigation tests |
| 7 | Add course filtering | Sreenidhi | Matching and nonmatching course tests |
| 8 | Add assignment priority levels | Zara | Priority assignment and update tests |
| 9 | Integrate new features into the console menu | Miguel | Menu interaction and integration tests |
| 10 | Expand unit and integration tests | All team members | Regression and edge-case tests |

We will implement and test each feature individually before integrating it into the complete application. Each team member will be responsible for their assigned components, and we will work together to verify that all features function correctly.

### Stretch Goals

- **Completed:** Overdue assignment detection, including displaying overdue assignments through the console menu.
- **Not completed yet:** Course filtering, assignment priority levels, course workload summary, and weekly schedule view.
- **Not currently planned:** Recurring assignments.

We will prioritize completing the remaining MVP features before implementing additional stretch goals.

## Attribution Table

| Section / Component | Team Member(s) | Contribution |
|---|---|---|
| `LinkedChain.java` | CS 2114 Staff, Sreenidhi | Instructor provided the generic linked chain; Sreenidhi integrated it into the application and verified its functionality |
| `LinkedChainTest.java` | Sreenidhi | Wrote, reviewed, and ran unit tests for the generic linked chain |
| `Item.java` | Miguel | Wrote the shared interface defining methods for assignments and notes |
| `Assignment.java` | Zara | Wrote and revised the Assignment class with GenAI assistance, including its fields, constructors, getters, and completion functionality |
| `AssignmentTest.java` | Zara | Wrote and ran unit tests for the Assignment class |
| `Note.java` | Zara | Wrote and revised the Note class with GenAI assistance, including its fields, constructors, getters, and update functionality |
| `NoteTest.java` | Zara | Wrote and ran unit tests for the Note class |
| `TrackerManager.java` | Sreenidhi | Wrote and revised the manager class with GenAI assistance; implemented item creation, ID lookup, assignment completion, and overdue detection |
| `TrackerManagerTest.java` | Sreenidhi | Developed, reviewed, and ran unit tests for the manager |
| `MainMenu.java` | Miguel | Wrote the console interface and connected menu operations to TrackerManager with GenAI assistance |
| `README.md` | Sreenidhi | Drafted and revised the project documentation, Reflection, Next Steps, and attribution table with GenAI assistance |
| Integration and testing | All team members | Integrated the classes, reviewed compatibility, and verified that the complete prototype runs and all tests pass |

### Code Ownership and Integration

- **LinkedChain:** Instructor-provided code, integrated and tested by Sreenidhi
- **TrackerManager:** Sreenidhi
- **Item and MainMenu:** Miguel
- **Assignment and Note:** Zara
- **Integration:** All team members

The classes are integrated through the `tracker` package. `MainMenu` calls `TrackerManager`, which uses `LinkedChain<Item>` to store objects created from the `Assignment` and `Note` classes.

## Generative AI Usage

During the development of this prototype, we used Claude to assist with implementing and refining `TrackerManager.java`, `Note.java`, `Assignment.java`, and `MainMenu.java` , developing test cases and drafting the README.

All AI-assisted content was reviewed, edited, and tested as needed.
