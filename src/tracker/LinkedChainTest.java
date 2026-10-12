package tracker;

// -------------------------------------------------------------------------
/**
 * Tests the generic linked chain with normal and invalid inputs.
 *
 * @author sreenidhitadigadapa
 * @version 11 Oct 2026
 */
import student.TestCase;
public class LinkedChainTest extends TestCase {
    private LinkedChain<String> chain;
    /**
     * Sets up test fixtures before each test.
     */
    public void setUp() { chain = new LinkedChain<String>(); }
    /**
     * Tests empty.
     */
    public void testEmpty() {
        assertTrue(chain.isEmpty()); assertEquals(0, chain.size());
        assertNull(chain.remove()); assertEquals("[]", chain.toString());
        assertFalse(chain.contains("x")); assertEquals(0, chain.count("x"));
    }
    /**
     * Tests add and end.
     */
    public void testAddAndEnd() {
        assertTrue(chain.add("B")); assertTrue(chain.addToEnd("C"));
        assertTrue(chain.add("A"));
        assertEquals("A", chain.get(0)); assertEquals("C", chain.get(2));
        assertEquals("[A, B, C]", chain.toString());
        assertTrue(chain.contains("B")); assertEquals(3, chain.size());
    }
    /**
     * Tests bad add.
     */
    public void testBadAdd() {
        try { chain.add(null); fail("Expected exception"); }
        catch (IllegalArgumentException e) { assertTrue(chain.isEmpty()); }
        try { chain.addToEnd(null); fail("Expected exception"); }
        catch (IllegalArgumentException e) { assertEquals(0, chain.size()); }
    }
    /**
     * Tests remove and missing.
     */
    public void testRemoveAndMissing() {
        chain.addToEnd("A"); chain.addToEnd("B"); chain.addToEnd("C");
        assertTrue(chain.remove("B")); assertFalse(chain.remove("missing"));
        assertFalse(chain.remove(null)); assertEquals("A", chain.remove());
        assertEquals("C", chain.remove()); assertNull(chain.remove());
    }
    /**
     * Tests duplicates and count.
     */
    public void testDuplicatesAndCount() {
        chain.add("x"); chain.add("x");
        assertEquals(2, chain.count("x")); assertTrue(chain.remove("x"));
        assertEquals(1, chain.count("x")); assertEquals(0, chain.count(null));
    }
    /**
     * Tests bad get.
     */
    public void testBadGet() {
        chain.add("x"); assertEquals("x", chain.get(0));
        try { chain.get(-1); fail("Expected exception"); }
        catch (IndexOutOfBoundsException e) { assertEquals(1, chain.size()); }
        try { chain.get(1); fail("Expected exception"); }
        catch (IndexOutOfBoundsException e) { assertEquals(1, chain.size()); }
    }
    /**
     * Tests array and clear.
     */
    public void testArrayAndClear() {
        chain.addToEnd("A"); chain.addToEnd("B");
        String[] values = chain.toArray(new String[2]);
        assertEquals("A", values[0]); assertEquals("B", values[1]);
        try { chain.toArray(new String[1]); fail("Expected exception"); }
        catch (IllegalArgumentException e) { assertEquals(2, chain.size()); }
        try { chain.toArray(null); fail("Expected exception"); }
        catch (IllegalArgumentException e) { assertEquals(2, chain.size()); }
        chain.clear(); chain.clear(); assertTrue(chain.isEmpty());
        assertEquals(0, chain.toArray(new String[0]).length);
    }
}
