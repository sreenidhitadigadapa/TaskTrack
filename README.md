
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
