
package tracker;

import student.TestCase;

/**
 * Tests the LinkedChain class.
 *
 * @author sreenidhitadigadapa
 * @version October 2026
 */
public class LinkedChainTest extends TestCase
{
    private LinkedChain<String> chain;

    public void setUp()
    {
        chain = new LinkedChain<String>();
    }

    public void testEmptyChain()
    {
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
        assertNull(chain.remove());
        assertEquals("[]", chain.toString());
    }

    public void testAdd()
    {
        assertTrue(chain.add("Math"));
        assertTrue(chain.add("Science"));

        assertEquals(2, chain.size());
        assertEquals("Science", chain.get(0));
        assertEquals("Math", chain.get(1));
        assertFalse(chain.isEmpty());
    }

    public void testAddToEnd()
    {
        assertTrue(chain.addToEnd("History"));
        assertTrue(chain.addToEnd("English"));

        assertEquals("History", chain.get(0));
        assertEquals("English", chain.get(1));
    }

    public void testRemoveFirst()
    {
        chain.add("One");
        chain.add("Two");

        assertEquals("Two", chain.remove());
        assertEquals(1, chain.size());
        assertEquals("One", chain.get(0));
    }

    public void testRemoveEntry()
    {
        chain.add("A");
        chain.add("B");
        chain.add("C");

        assertTrue(chain.remove("B"));
        assertFalse(chain.contains("B"));
        assertEquals(2, chain.size());

        assertFalse(chain.remove("Missing"));
        assertFalse(chain.remove(null));
    }

    public void testDuplicates()
    {
        chain.add("Quiz");
        chain.add("Quiz");
        chain.add("Project");

        assertEquals(2, chain.count("Quiz"));
        assertTrue(chain.contains("Quiz"));

        assertTrue(chain.remove("Quiz"));
        assertEquals(1, chain.count("Quiz"));
    }

    public void testClear()
    {
        chain.add("Task");
        chain.clear();

        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());

        chain.clear();
        assertTrue(chain.isEmpty());
    }

    public void testContains()
    {
        assertFalse(chain.contains("Missing"));

        chain.add("Exam");

        assertTrue(chain.contains("Exam"));
        assertFalse(chain.contains("Homework"));
        assertFalse(chain.contains(null));
    }

    public void testToArray()
    {
        chain.add("First");
        chain.add("Second");

        String[] values = new String[2];
        String[] result = chain.toArray(values);

        assertEquals("Second", result[0]);
        assertEquals("First", result[1]);
    }

    public void testToString()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");

        assertEquals("[A, B]", chain.toString());
    }

    public void testInvalidIndex()
    {
        chain.add("Task");

        Exception exception = null;

        try
        {
            chain.get(-1);
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        exception = null;

        try
        {
            chain.get(1);
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }

    public void testNullAdd()
    {
        Exception exception = null;

        try
        {
            chain.add(null);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals(0, chain.size());
    }

    public void testInvalidArray()
    {
        chain.add("Task");

        Exception exception = null;

        try
        {
            chain.toArray(new String[0]);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }
}
