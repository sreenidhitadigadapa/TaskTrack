package tracker;
// -------------------------------------------------------------------------
/**
 *  Represents the item interface.
 *  Determines the methods that every class
 *  implementing Item will have.
 * 
 *  @author miguel33
 *  @version 10 Oct 2026
 */
public interface Item {

    // ----------------------------------------------------------
    /**
     * Returns the Id number of the Item.
     * @return Id number.
     */
    public int getId();
    
    // ----------------------------------------------------------
    /**
     * Returns the title of the Item.
     * @return title of the Item.
     */
    public String getTitle();
    
    // ----------------------------------------------------------
    /**
     * Returns the text of the Item.
     * @return text of the Item.
     */
    public String getText();
}