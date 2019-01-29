import java.util.*;
import java.io.*;
/**
 * The Library class stores a list of volunteers and contains the methods needed to manipulate the volunteers'
 * shifts and characteristics.
 * 
 * @author Sally Zhou
 * @version 1.0 2018-03-07
 */
public class Library
{
    // constants //
    public static final String VOLUNTEER_FILE_NAME = "Database.Volunteers.txt";
    public static final String LOGIN_FILE_NAME = "Database.LogIn.txt";
    
    // class fields //
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    // instance fields //
    private ArrayList<Volunteer> volunteers;
    private ArrayList<String> usernames;
    private ArrayList<String> passwords;
    
    /**
     * Constructs a Library object with an empty ArrayList of Volunteers and empty Strings for username and password.
     */
    public Library()
    {
        volunteers = new ArrayList<>();
        usernames = new ArrayList<>();
        passwords = new ArrayList<>();
    } // end of constructor Library()

    // accessors //
    
    /**
     * Returns the number of volunteers in this Library.
     * @return the number of volunteers in this Library
     */
    public int getSize()
    {
        return volunteers.size();
    }
    
    /**
     * Returns the volunteer preview for the Volunteer in the ith place
     * 
     * @param i the ith index
     * @return the volunteer preview for the Volunteer
     */
    public String getVolunteerPreview(int i)
    {
        return volunteers.get(i).getPreview();
    }
    
    /**
     * Returns the info needed for the Volunteer profile
     * 
     * @param i the ith index
     * @return the volunteer profile for the Volunteer
     */
    public String[] getVolunteerProfile(int i)
    {
        return volunteers.get(i).getProfile();
    }
    
    /**
     * Returns an ArrayList of int arrays that contain the shifts of a specified Volunteer.
     * 
     * @param i the ith index
     * @return an ArrayList of int arrays
     */
    public ArrayList<int[]> getVolunteerShifts(int i)
    {
        return volunteers.get(i).getShifts();
    }
    
    // mutators //
    
    /**
     * Logs in to the VP Library Volunteer System.
     * 
     * @param user the username the user has inputted
     * @param ps the password the user has inputted
     * @return true if the username and password match a pair in the database and the user can log in,
     *         false if the username or password is incorrect
     */
    public boolean logIn(String user, String pw)
    {
        for (int i = 0; i < usernames.size(); i++)
        {
            if (usernames.get(i).equals(user) && passwords.get(i).equals(pw))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Adds a new user to this Library.
     * @param username the new user's username
     * @param password the new user's password
     */
    public void addUser(String username, String password)
    {
        usernames.add(username);
        passwords.add(password);
    }
    
    /**
     * Adds a new Volunteer to the ArrayList volunteers.
     * @param firstN the first name of the Volunteer
     * @param lastN the last name of the Volunteer
     * @param stuNum the student number of the Volunteer
     * @param grade the Volunteer's grade
     * @param teacher the Volunteer's homeroom teacher
     * @param email the Volunteer's email
     * @param rtn whether the Volunteer is a returning volunteer from previous years
     * @param ps the position the Volunteer is applying for
     * @param days array that contains the days the Volunteer can volunteer on
     * @param times array that contains the times during the day the Volunteer can volunteer
     */
    public void addVolunteer(String firstN, String lastN, int stuNum, int grade, String teacher, String email, boolean rtn, String ps, int[] days, int[] times) throws RuntimeException
    {
        if(!stuNumExists(stuNum))
            volunteers.add(new Volunteer(firstN, lastN, stuNum, grade, teacher, email, rtn, ps, days, times));
        else
            throw new RuntimeException("0");
    }
    
    /**
     * Edits the characteristics of the specified Volunteer.
     * @param firstN the first name of the Volunteer
     * @param lastN the last name of the Volunteer
     * @param stuNum the student number of the Volunteer
     * @param grade the Volunteer's grade
     * @param teacher the Volunteer's homeroom teacher
     * @param email the Volunteer's email
     * @param rtn whether the Volunteer is a returning volunteer from previous years
     * @param ps the position the Volunteer is applying for
     * @param days array that contains the days the Volunteer can volunteer on
     * @param times array that contains the times during the day the Volunteer can volunteer
     * @param index the Volunteer to be edited
     */
    public void editVolunteer(String firstN, String lastN, int stuNum, int grade, String teacher, String email, boolean rtn, String ps, int[] days, int[] times, int index) throws RuntimeException
    {
        if(!stuNumExists(stuNum) || volunteers.get(index).getStudentNumber() == stuNum)
            volunteers.get(index).edit(firstN, lastN, stuNum, grade, teacher, email, rtn, ps, days, times);
        else
            throw new RuntimeException("0");
    }
    
    /**
     * Removes the specified volunteer from the library system.
     * @param index the index of the Volunteer to be removed
     */
    public void removeVolunteer(int index)
    {
        volunteers.remove(index);
    }
    
    /**
     * Returns an array of int containing the indexes of Volunteer objects which do not match the specified search term.
     * 
     * @param searchType the value of the Volunteer the user wants to search
     * @param term the keyword the user wants to search for
     * @return an array of int of the Volunteers which do not match the search term
     */
    public int[] search(String searchType, String term) throws NumberFormatException
    {
        if (term.equals("")) 
            return new int[0];
        int[] searched;
        switch (searchType)
        {
            case "NAME":
                searched = searchName(term);
                break;
            
            case "STUDENT NUMBER":
                searched = searchStuNum(term);
                break;
                
            default:
                searched = new int[0];
        }
        return searched;
    }
    
    /**
     * Sorts the array of Volunteer objects according to the user's specification.
     * 
     * @param sortType the type of sorting the user wants
     * @return an int array containing the sorted indexes of the Volunteers
     */
    public int[] sort(String sortType)
    {
        int[] sorted;
        switch (sortType)
        {
            case "Alphabetical":
                sorted = sortAlpha();
                break;
                
            case "Grades":
                sorted = sortGrade();
                break;
            
            case "Student Number":
                sorted = sortStuNum();
                break;
                
            default:
                sorted = new int[0];
        }
        return sorted;
    }
    
    /**
     * Assigns a shift to each volunteer and returns a 2D array of their names in their assigned placements.
     * 4 volunteers positions are available for afternoon shifts.
     * @return a 2D array of ArrayList objects where each ArrayList represents a specific time block and 
     * all the volunteer names stored in each ArrayList represents the volunteers whose shifts are at that time.
     */
    public ArrayList<String>[][] assignShifts()
    {
        ArrayList<String>[][] shifts = new ArrayList[5][3];
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                shifts[i][j] = new ArrayList<String>();
            }
        }
        boolean[] alreadyPlaced = new boolean[volunteers.size()]; // default value is false, meaning no volunteers have been placed
        int[] orderByGrade = sort("Grades"); // array of volunteers sorted by grade
        for (int index  = 0; index < volunteers.size(); index++)
        {
            Volunteer current = volunteers.get(orderByGrade[index]);
            ArrayList<int[]> possibleShifts = current.getShifts();
            for (int j  = 0; j < possibleShifts.size() && !alreadyPlaced[index]; j++)
            {
                int[] shift = possibleShifts.get(j);
                if (shifts[shift[0]][shift[1]].size() < 4)
                {
                    shifts[shift[0]][shift[1]].add(current.getFirstName() + " " + current.getLastName());
                    alreadyPlaced[index] = true;
                }
            }
        }
        return shifts;
    }
    
    /**
     * Reads from the text file 'VolunteersDatabase.txt' and stores content in this Library object
     */
    public void readDatabase()
    {
        try
        {
            // temporary variables
            // String userN, passW;
            int numVolunteers;
            String firstN, lastN;
            int stuNum;
            int grade;
            String teacher;
            String email;
            boolean returning;
            String position;
            
            // instantiation of the BufferedReader object used to read from volunteer file
            BufferedReader fileReader = new BufferedReader(new FileReader(VOLUNTEER_FILE_NAME));
            
            // read the number of volunteer on first line
            numVolunteers = Integer.parseInt(fileReader.readLine());
            
            for (int volunteer = 0; volunteer < numVolunteers; volunteer++)
            {
                firstN = fileReader.readLine();
                lastN = fileReader.readLine();
                stuNum = Integer.parseInt(fileReader.readLine());
                grade = Integer.parseInt(fileReader.readLine());
                teacher = fileReader.readLine();
                email = fileReader.readLine();
                returning = Boolean.valueOf(fileReader.readLine());
                position = fileReader.readLine();
                // reading shifts
                int numShifts = Character.getNumericValue(fileReader.read());
                int space = fileReader.read();
                if (space != 32) // if next value isn't a space
                {
                    numShifts = numShifts * 10 + Character.getNumericValue((space));
                }
                int[] days = new int[numShifts];
                int[] times = new int[numShifts];
                for (int i = 0; i < numShifts; i++)
                {
                    if (space == 32 && i == 0){}
                    else
                        fileReader.read();
                    days[i] = Character.getNumericValue(fileReader.read());
                    fileReader.read();
                    times[i] = Character.getNumericValue(fileReader.read());
                }
                addVolunteer(firstN, lastN, stuNum, grade, teacher, email, returning, position, days, times);
                fileReader.readLine();
            }
            
            // read from log in file
            fileReader = new BufferedReader(new FileReader(LOGIN_FILE_NAME));
            
            int numUsers = Integer.parseInt(fileReader.readLine());
            for (int i = 0; i < numUsers; i++)
            {
                usernames.add(fileReader.readLine());
                passwords.add(fileReader.readLine());
            }
            
        }
        catch (IOException e)
        {
            System.out.println("**Sorry.");
        }
    }
    
    /**
     * Stores the characteristics of this Library object into a text file database.
     */
    public void exitLibrary()
    {
        try
        {
            File file = new File(VOLUNTEER_FILE_NAME);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            // add the number of volunteers in library
            writer.write("" + volunteers.size());
            writer.newLine();
            // add all volunteers and corresponding shifts
            for (Volunteer volunteer : volunteers)
            {
                writer.write("" + volunteer.getFirstName());
                writer.newLine();
                writer.write("" + volunteer.getLastName());
                writer.newLine();
                writer.write("" + volunteer.getStudentNumber());
                writer.newLine();
                writer.write("" + volunteer.getGrade());
                writer.newLine();
                writer.write("" + volunteer.getTeacher());
                writer.newLine();
                writer.write("" + volunteer.getEmail());
                writer.newLine();
                writer.write("" + String.valueOf(volunteer.getReturning()));
                writer.newLine();
                writer.write("" + volunteer.getPosition());
                writer.newLine();
                int numShifts = volunteer.getNumOfShifts();
                writer.write("" + numShifts);
                for (int i = 0; i < numShifts; i++)
                {
                    int[] shifts = volunteer.getShift(i);
                    writer.write(" " + shifts[0] + " " + shifts[1]);
                }
                writer.newLine();
            }
            writer.close();
            
            file = new File(LOGIN_FILE_NAME);
            fos = new FileOutputStream(file);
            writer = new BufferedWriter(new OutputStreamWriter(fos));
            // add the number of users
            writer.write("" + usernames.size());
            writer.newLine();
            for (int i = 0; i < usernames.size(); i++)
            {
                writer.write(usernames.get(i));
                writer.newLine();
                writer.write(passwords.get(i));
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("**Sorry.");
        }
    }
    
    // private methods //
    
    private boolean stuNumExists(int number)
    {
        for (Volunteer volunteer : volunteers)
        {
            if (volunteer.getStudentNumber() == number)
                return true;
        }
        return false;
    }
    
    /*
     * Recursive method used to compare which String is alphabetically greater.
     * @param first the first String to be compared. Must be smaller or equal in length to second String.
     * @param second the second String to be compared
     * @param index the index of the String
     * @return a negative number if the first String is smaller, 0 if they are equal alphabetically, a positive number if the first String is greater
     */
    private int compareTo(String first, String second, int index)
    {
        if (index == first.length())
        {
            return 0;
        }
        else if (first.substring(index, index + 1).compareTo(second.substring(index, index + 1)) != 0)
        {
            return first.substring(index, index + 1).compareTo(second.substring(index, index + 1));
        }
        else
        {
            return compareTo(first, second, index + 1);
        }
    }
    
    /*
     * Sequential/Linear search used to search for the specified term in the name of the Volunteer.
     * @param term the keyword the user wants to search for
     * @return the indexes of the Volunteer array that do not match the specified keyword
     */
    private int[] searchName(String term)
    {
        ArrayList<Integer> count = new ArrayList<Integer>();
        for (int i = 0; i < volunteers.size(); i++)
        {
            String volunteerName = (volunteers.get(i).getFirstName() + " " + volunteers.get(i).getLastName());
            if (!volunteerName.contains(term.toUpperCase()))
            {
                count.add(i);
            }
        }
        int[] searched = new int[count.size()];
        for (int i = 0; i < count.size(); i++)
        {
            searched[i] = count.get(i);
        }
        
        return searched;
    }
    
    /*
     * Binary search used to search for the specified term in the student number of the Volunteer.
     * @param term the student number the user wants to search for
     * @return the indexes of the Volunteer array that do not match the specified student number
     */
    private int[] searchStuNum(String term)
    {
        int number = Integer.parseInt(term);
        int[] sortedIndex = sortStuNum(); // using the student number sorting method to get sorted indexes
        int[] sortedStuNums = new int[volunteers.size()]; // store into array of student number instead of array of indexes
        for (int i = 0; i < volunteers.size(); i++)
        {
            sortedStuNums[i] = volunteers.get(sortedIndex[i]).getStudentNumber();
        }
        // binary search
        int bottom = 0;
        int top = sortedStuNums.length - 1;
        int middle;
        boolean found = false;
        int location = -1;
        while (bottom <= top && !found)
        {
            middle = (bottom + top) / 2;
            if (sortedStuNums[middle] == number) // success
            {
                found = true;
                location = middle;
            }
            else if (sortedStuNums[middle] < number) // not in bottom half
                bottom = middle + 1;
            else // number cannot be in top half
                top = middle - 1;
        }
        
        if (found)
        {
            // store all indexes in an array except for the index with the found value
            int[] searched = new int[sortedIndex.length - 1];
            for (int i = 0; i < sortedIndex.length; i++)
            {
                if (i < location)
                    searched[i] = sortedIndex[i];
                else if (i > location)
                    searched[i - 1] = sortedIndex[i];
            }
            return searched;
        }
        else // student number not found
            return new int[0];
    }
    
    /*
     * Sorts the volunteers alphabetically by first name.
     * @return an int array with the indexes of the sorted volunteers
     */
    private int[] sortAlpha()
    {
        ArrayList<String> volunteerNames = new ArrayList<String>();
        ArrayList<Integer> count = new ArrayList<Integer>();
        for (int i = 0; i < volunteers.size(); i++)
        {
            volunteerNames.add(volunteers.get(i).getFirstName());
            count.add(i);
        }
        int[] sorted = new int[volunteers.size()];
        for (int i = 0; i < volunteers.size(); i++)
        {
            int smallest = 0;
            for (int j = 0; j < volunteerNames.size(); j++)
            {
                if (volunteerNames.get(smallest).length() < volunteerNames.get(j).length())
                {
                    if (compareTo(volunteerNames.get(smallest), volunteerNames.get(j), 0) > 0)
                    {
                        smallest = j;
                    }
                }
                else
                {
                    if (compareTo(volunteerNames.get(j), volunteerNames.get(smallest), 0) < 0)
                    {
                        smallest = j;
                    }
                }
            }
            // delete smallest value
            sorted[i] = count.get(smallest);
            volunteerNames.remove(smallest);
            count.remove(smallest);
        }
        return sorted;
    }
    
    /*
     * Sorts the volunteers by increasing grade.
     * @return an int array with the indexes of the sorted volunteers
     */
    private int[] sortGrade()
    {
        ArrayList<Integer> volunteerGrades = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++)
        {
            volunteerGrades.add(volunteers.get(i).getGrade());
            count.add(i);
        }
        int[] sorted = new int[volunteers.size()];
        for (int i = 0; i < volunteers.size(); i++)
        {
            int smallest = 0;
            for (int j = 0; j < volunteerGrades.size(); j++)
            {
                if (volunteerGrades.get(smallest) > volunteerGrades.get(j)) // grade of volunteer at index j is smaller
                {
                    smallest = j;
                }
                else if (volunteerGrades.get(smallest) == volunteerGrades.get(j)) // grades are same, then compare names
                {
                    if (volunteers.get(smallest).getFirstName().compareTo(volunteers.get(j).getFirstName()) > 0)
                    {
                        smallest = j;
                    }
                }
            }
            // delete smallest value
            sorted[i] = count.get(smallest);
            volunteerGrades.remove(smallest);
            count.remove(smallest);
        }
        return sorted;
    }
    
    /*
     * Sorts the volunteers by increasing student number by using selection sort.
     * @return an int array with the indexes of the sorted volunteers
     */
    private int[] sortStuNum()
    {
        ArrayList<Integer> volunteerStuNums = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++)
        {
            volunteerStuNums.add(volunteers.get(i).getStudentNumber());
            count.add(i);
        }
        int[] sorted = new int[volunteers.size()];
        for (int i = 0; i < volunteers.size(); i++)
        {
            int smallest = 0;
            for (int j = 0; j < volunteerStuNums.size(); j++)
            {
                if (volunteerStuNums.get(smallest) > volunteerStuNums.get(j))
                {
                    smallest = j;
                }
            }
            sorted[i] = count.get(smallest);
            volunteerStuNums.remove(smallest);
            count.remove(smallest);
        }
        return sorted;
    }
    
} // end of class Main