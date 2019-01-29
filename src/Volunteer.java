import java.util.ArrayList;
/**
 * A Volunteer has many characteristics, such as a first name, a last name, a student number, etc.
 * 
 * @author Sally Zhou
 * @version 1.0 2018-03-07
 */
public class Volunteer
{
    // instance fields
    private String firstName;
    private String lastName;
    private int stuNum;
    private int grade;
    private String teacher;
    private String email;
    private boolean returning;
    private String position;
    private ShiftList shifts;

    // constructors

    /**
     * Creates a new Volunteer with default values.
     */
    public Volunteer()
    {
        firstName = "";
        lastName = "";
        stuNum = 0;
        grade = 0;
        teacher = "";
        email = "";
        returning = false;
        position = "";
        shifts = new ShiftList();
    }

    /**
     * Creates a new Volunteer with specified characteristics.
     * @param firstN the first name of the Volunteer
     * @param lastN the last name of the Volunteer
     * @param stuNum the student number of the Volunteer
     * @param grade the Volunteer's grade
     * @param teacher the Volunteer's homeroom teacher
     * @param email the Volunteer's email
     * @param returning whether the Volunteer is a returning volunteer from previous years
     * @param position the position the Volunteer is applying for
     * @param days array that contains the days the Volunteer can volunteer on
     * @param times array that contains the times during the day the Volunteer can volunteer
     */
    public Volunteer(String firstN, String lastN, int stuNum, int grade, String teacher, String email, boolean returning, String position, int[] days, int[] times)
    {
        this();
        setName(firstN, lastN);
        setStudentNumber(stuNum);
        setGrade(grade);
        setTeacher(teacher);
        setEmail(email);
        setReturning(returning);
        setPosition(position);
        for (int i = 0; i < days.length; i++)
        {
            addShift(days[i], times[i]);
        }
    }

    // accessors //

    /**
     * Returns the first name of this Volunteer
     * @return the first name of this Volunteer
     */
    public String getFirstName() {return firstName;}
    
    /**
     * Returns the last name of this Volunteer
     * @return the last name of this Volunteer
     */
    public String getLastName() {return lastName;}

    /**
     * Returns the student number of this Volunteer
     * @return the student number of this Volunteer
     */
    public int getStudentNumber() {return stuNum;}

    /**
     * Returns the grade of this Volunteer
     * @return the grade of this Volunteer
     */
    public int getGrade() {return grade;}

    /**
     * Returns the teacher of this Volunteer
     * @return the teacher of this Volunteer
     */
    public String getTeacher() {return teacher;}

    /**
     * Returns the email of this Volunteer
     * @return the email of this Volunteer
     */
    public String getEmail() {return email;}

    /**
     * Returns whether this Volunteer is a returning volunteer
     * @return whether this Volunteer is a returning volunteer
     */
    public boolean getReturning() {return returning;}
    
    /**
     * Returns the position of this Volunteer
     * @return the position of this Volunteer
     */
    public String getPosition() {return position;}
    
    /**
     * Returns the shift with the specified day and time values. Used to find the day and time
     * values in order to write to file at close
     * @return the shift with the specified day and time values
     */
    public int[] getShift(int shiftNum)
    {
        if (0 <= shiftNum && shiftNum < shifts.size())
        {
            return shifts.getShift(shiftNum);
        }
        return null;
    }
    
    /**
     * Returns all of the shifts of this Volunteer
     * @return an ArrayList of an int array containing the day and time of all of this Volunteer's shifts
     */
    public ArrayList<int[]> getShifts() 
    {
        ArrayList<int[]> allShifts = new ArrayList<int[]>();
        for (int i = 0; i < shifts.size(); i++)
        {
            allShifts.add(shifts.getShift(i));
        }
        return allShifts;
    }
    
    /**
     * Returns the number of shifts this Volunteer is available for
     * @return the number of shifts this Volunteer is available for
     */
    public int getNumOfShifts() {return shifts.size();}
    
    /**
     * Returns the volunteer preview for this Volunteer in the form of a String.
     * @return a String representing the volunteer preview for this Volunteer
     */
    public String getPreview()
    {
        return firstName + " " + lastName + " | " + grade + " | " + position;
    }
    
    /**
     * Returns the info needed for the profile of this Volunteer.
     * @return a String array containing the profile for this Volunteer
     */
    public String[] getProfile()
    {
        String[] profile = new String[8];
        profile[0] = getFirstName();
        profile[1] = getLastName();
        profile[2] = "" + getStudentNumber();
        profile[3] = "" + getGrade();
        profile[4] = getTeacher();
        profile[5] = getEmail();
        profile[6] = String.valueOf(getReturning());
        profile[7] = getPosition();
        return profile;
    }

    // mutators //

    /**
     * Sets the name of this Volunteer.
     * @param firstN the new first name
     * @param lastN the new last name
     */
    public void setName(String firstN, String lastN)
    {
        this.firstName = firstN.toUpperCase();
        this.lastName = lastN.toUpperCase();
    }

    /**
     * Sets the student number of this Volunteer.
     * @param stuNum the new student number
     */
    public void setStudentNumber(int stuNum)
    {
        this.stuNum = stuNum;
    }

    /**
     * Sets the grade of this Volunteer.
     * @param grade the new grade
     */
    public void setGrade(int grade)
    {
        this.grade = grade;
    }

    /**
     * Sets the homeroom teacher of this Volunteer.
     * @param teacher the new teacher
     */
    public void setTeacher(String teacher)
    {
        this.teacher = teacher.toUpperCase();
    }

    /**
     * Sets the email of this Volunteer.
     * @param email the new email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Sets whether this Volunteer is a returning volunteer.
     * @param returning whether this Volunteer is a returning volunteer
     */
    public void setReturning(boolean returning)
    {
        this.returning = returning;
    }
    
    /**
     * Sets the preferred volunteer position of this Volunteer.
     * @param ps the preferred volunteer position
     */
    public void setPosition(String ps)
    {
        ps = ps.toUpperCase();
        if (ps.equals("SHELVING") || ps.equals("CIRC"))
        {
            this.position = ps;
        }
    }
    
    /**
     * Sets the shifts of this Volunteer.
     * @param shifts the new shifts
     */
    public void setShifts(ShiftList shifts)
    {
        this.shifts = shifts;
    }
    
    /**
     * Adds a Shift to the shifts of this Volunteer.
     * @param day the day of the new shift
     * @param time the time of the new shift
     */
    public void addShift(int day, int time)
    {
        shifts.add(day, time);
    }

    /**
     * Edits the characteristics of this Volunteer using the specified information.
     * @param firstN the first name of the Volunteer
     * @param lastN the last name of the Volunteer
     * @param stuNum the student number of the Volunteer
     * @param grade the Volunteer's grade
     * @param teacher the Volunteer's homeroom teacher
     * @param email the Volunteer's email
     * @param returning whether the Volunteer is a returning volunteer from previous years
     * @param position the position the Volunteer is applying for
     * @param days array that contains the days the Volunteer can volunteer on
     * @param times array that contains the times during the day the Volunteer can volunteer
     */
    public void edit(String firstN, String lastN, int stuNum, int grade, String teacher, String email, boolean rtn, String ps, int[] days, int[] times)
    {
        setName(firstN, lastN);
        setStudentNumber(stuNum);
        setGrade(grade);
        setTeacher(teacher);
        setEmail(email);
        setReturning(rtn);
        setPosition(ps);
        shifts.deleteAll();
        for (int i = 0; i < days.length; i++)
        {
            addShift(days[i], times[i]);
        }
    }
    
    /**
     * Returns a string representation of this Volunteer.
     * @return a string representing this Volunteer
     */
    public String toString()
    {
        return
            getClass().getName()
            + "["
            + "first name: " + firstName
            + ", last name: " + lastName
            + ", student #: " + stuNum
            + ", grade: " + grade
            + ", teacher: " + teacher
            + ", email: " + email
            + ", returning: " + String.valueOf(returning)
            + ", position: " + position
            + ", shifts: " + shifts.toString()
            + "]";
    } // end of method toString()
} // end of class Volunteer