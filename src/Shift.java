/**
 * A Shift object that contains day and time values.
 * 
 * @author Sally Zhou
 * @version 1.0 2018-03-07
 */
public class Shift
{
    // constants
    private static final int MAX_DAY = 4;
    private static final int MAX_TIME = 2;
    
    // instance fields
    private int day;
    private int time;
    private Shift next;
    
    /**
     * Constructs a default Shift object with default values for day and time. 
     */
    public Shift()
    {
        day = -1;
        time = -1;
        next = null;
    } // end of constructor Shift()
    
    /**
     * Constructs a new Shift object with the specified day and time.
     * @param day the specified day
     * @param time the specified time
     */
    public Shift(int day, int time)
    {
        this();
        setShift(day, time);
    } // end of constructor Shift(int day, int time)
    
    // accessors //
    
    /**
     * Returns the day of this Shift.
     * @return the day of this Shift.
     */
    public int getDay()
    {
        return day;
    } // end of method getDay()
    
    /**
     * Returns the next Shift.
     * @return the next Shift
     */
    public Shift getNext()
    {
        return next;
    } // end of method getNext()
    
    /**
     * Returns an array of int that contains the day and time values of this Shift.
     * @return an array of int that contains the day and time values of this Shift.
     */
    public int[] getShift()
    {
        int[] shifts = new int[2];
        shifts[0] = day;
        shifts[1] = time;
        return shifts;
    } // end of method getShift()
    
    /**
     * Returns a String of the day and time values of this Shift.
     * @return a String representing the day and time values of this Shift.
     */
    public String getShiftString()
    {
        String shift = "";
        switch (day)
        {
            case 0: shift = shift + "MONDAY ";
                    break;
            case 1: shift = shift + "TUESDAY ";
                    break;
            case 2: shift = shift + "WEDNESDAY ";
                    break;
            case 3: shift = shift + "THURSDAY ";
                    break;
            case 4: shift = shift + "FRIDAY ";
                    break;
        }
        switch (time)
        {
            case 0: shift = shift + "MORNING";
                    break;
            case 1: shift = shift + "LUNCH";
                    break;
            case 2: shift = shift + "AFTERNOON";
                    break;
        }
        return shift;
    } // end of method getShiftString()
    
    /**
     * Returns the time of this Shift.
     * @return the day of this Shift
     */
    public int getTime()
    {
        return time;
    } // end of method getTime()
    
    // mutators //
    
    /**
     * Sets the next Shift to the specified Shift.
     * @param nextShift the specified next Shift.
     */
    public void setNext(Shift nextShift)
    {
        next = nextShift;
    } // end of method setNext(Shift nextShift)
    
    /**
     * Edits this Shift to have the specified day and time.
     * 
     * @param day the specified day
     * @param time the specified time
     */
    public void setShift(int day, int time)
    {
        if (0 <= day && day <= MAX_DAY && 0 <= time && time <= MAX_TIME)
        {
            this.day = day;
            this.time = time;
        }
    } // end of method setShift(int day, int time)
    
    /**
     * Returns a string representation of this Shift.
     *
     * @return a string representing this Shift
     */
    public String toString()
    {
       return
          getClass().getName()
          + "["
          + "day: " + day
          + ", time: " + time
          + "]";
    } // end of method toString()
} // end of class Shift