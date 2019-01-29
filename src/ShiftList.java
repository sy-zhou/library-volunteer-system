/**
 * A ShiftList is a linked list that stores a list of Shift objects.
 * 
 * @author Sally Zhou
 * @version 1.0 2018-03-07
 */
public class ShiftList
{
    private Shift header;
    int size;

    /**
     * Constructs a ShiftList with no Shifts.
     */
    public ShiftList()
    {
        header = null;
        size = 0;
    } // end of constructor ShiftList()

    // accessors //
    
    /**
     * Returns the Shift object with the specified day and time.
     * @param day the day
     * @param time the time
     * @return the Shift object with the specified day and time
     */
    public Shift find(int day, int time)
    {
        if (isEmpty())
            return null;
        
        boolean found = false;
        Shift n = header;
        do
        {
            if (n.getDay() == day && n.getTime() == time)
            {
                found = true;
            }
            else
            {
                n = n.getNext();
            }
        } while (!found && n != null);
        return n;
    } // end of method find(int day, int time)
    
    /**
     * Returns the day and time of a Shift in the specified index of the ShiftList.
     * @param index the index of the Shift in the ShiftList you want to get the values from
     * @return an int array of the day and time of the specified Shift
     */
    public int[] getShift(int index)
    {
        if (header == null)
            return null;
        return traverse(index).getShift();
    } // end of method getShift(int index)
    
    /**
     * Determines the size of the ShiftList
     * @return the size of the ShiftList
     */
    public int size()
    {
        return size;
    } // end of method size()
    
    /**
     * Traverses the ShiftList to find the Shift at the ith index
     * 
     * @param i the index
     * @return the Shift at the ith index
     */
    public Shift traverse(int i)
    {
        Shift n = header;

        if (i < 0) 
            return null;

        for (int j = 0; j < i; j++)
        {
            if (n == null)
                return null;
            n = n.getNext();
        }
        return n;
    } // end of method traverse(int i)
    
    // mutators //
    
    /**
     * Adds a new Shift object to the ShiftList
     * 
     * @return whether the Shift object was added successfully
     */
    public boolean add(int day, int time)
    {
        Shift newShift = new Shift(day, time);
        
        if (isEmpty())
        {
            header = newShift;
        }
        else
        {
            Shift prev = traverse(size - 1);
            prev.setNext(newShift);
        }
        size++;
        return true;
    } // end of method add(int day, int time)
    
    /**
     * Deletes the Shift with the specified day and time
     * 
     * @return whether the Shift was deleted successfully
     */
    public boolean delete(int day, int time)
    {
        if (isEmpty())
            return true;
        
        boolean found = false;
        Shift n = header;
        Shift prev = null;
        do
        {
            if (n.getDay() == day && n.getTime() == time)
            {
                found = true;
            }
            else
            {
                prev = n;
                n = n.getNext();
            }
        } while (!found && n != null);
        
        if (found)
        {
            prev.setNext(n.getNext());
            size--;
            return true;
        }
        else
            return false;
    } // end of method delete(int day, int time)
    
    /**
     * Deletes all the Shifts in the ShiftList.
     */
    public void deleteAll()
    {
        if (!isEmpty())
        {
            header = null;
            size = 0;
        }
    } // end of method deleteAll()
    
    /**
     * Determines whether the ShiftList is empty
     * 
     * @return whether the ShiftList is empty
     */
    public boolean isEmpty()
    {
        if (header == null) 
            return true;
        return false;
    } // end of method isEmpty()
} // end of class ShiftList