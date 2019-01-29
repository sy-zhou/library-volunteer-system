/**
 * The VP Library Volunteer System is a program that stores volunteer applicants of the VP Library and is able to  
 * create a volunteer schedule. The program is also able to implement a variety of functionalities which are 
 * listed below:
 * 
 * - Store volunteer applicants and their information, including their name (first, last), student number, grade, 
 *   homeroom teacher, email address, whether they are a returning volunteer, type of position they’re applying for, 
 *   and preferred shifts
 * - Edit applicants’ information (e.g. name, grade)
 * - Sort applicants by name, grade, student number
 * - Search applicants in terms of name or grade by user-defined parameters
 * - Displays information of specified applicants in a legible manner
 * - Create a timetable schedule based on the appicants' preferred shifts
 * 
 * @author Sally Zhou
 * @version 1.0 2018-03-05
 */
public class Main
{

    /**
     * Creates a new Library object and adds it as a parameter to create the 
     * new GUI object. Then invokes the start method from the GUI class.
     *
     * @param argument not used
     */
    public static void main(String[] argument)
    {
        // Create the new Library object
        Library vpLibrary = new Library();
        // Recover volunteer information from text file
        vpLibrary.readDatabase();
        // Create the GUI object
        GUI program = new GUI(vpLibrary);
        // Start the program
        program.start();
    } // end of main(String[] argument)

} // end of class Main