import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;
/**
 * This InstructionsMenuBar is a MenuBar used to display instructions 
 * for users of the VP Library Volunteer System.
 * 
 * @author Sally Zhou
 * @version 1.0 2018-03-07
 */
public class InstructionsMenuBar extends JMenuBar
{
    // constants
    private static final String MENU_HEADING = "How To";
    private static final String MENU_ITEM_HEADING = "Instructions";
    
    // instance fields //
    
    // GUI components
    private JMenu howTo;
    private JMenuItem instructionsMenuItem;
    private JFrame instructionsFrame;
    private JOptionPane instructionsDialog;
    // content
    private String fileName;
    private String instructionsMessage;

    /**
     * Constructs a MenuBar object with content from the specified file
     * @param fileName the file the user wants to extract content from
     */
    public InstructionsMenuBar(String fileName)
    {
        this.fileName = fileName;
        initContent();
        initComponents();
        addActionListeners();
    } // end of constructor Menu(String fileName)

    /*
     * Retrieves message.
     */
    private void initContent()
    {
        if (fileName == "Instructions.LogIn.txt")
        {
            instructionsMessage = getLogInMessage();
        }
        else if (fileName == "Instructions.System.txt")
        {
            instructionsMessage = getSystemMessage();
        }
    } // end of method initContent()
    
    /*
     * Creates GUI components.
     */
    private void initComponents()
    {
        howTo = new JMenu(MENU_HEADING);
        instructionsMenuItem = new JMenuItem(MENU_ITEM_HEADING);
        howTo.add(instructionsMenuItem);
        this.add(howTo);
        
        instructionsFrame = new JFrame(MENU_ITEM_HEADING);
        instructionsDialog = new JOptionPane();
    } // end of method initComponents()

    /*
     * Returns the message for the log in window
     */
    private String getLogInMessage()
    {
        return "To log in to the Library Volunteer System, please enter your\n"
               + "username and password, then left-click the button 'Log In'.\n\n"
               + "If you do not have an account on this system, you can left-click\n"
               + "the 'Create New Account' button and enter a new username and\n"
               + "password to create a new account.";
    }
    
    /*
     * Returns the message for the main program window
     */
    private String getSystemMessage()
    {
        return "Welcome to the VP Library Volunteer System. These are the Volunteer and Timetable panels. \n\n"
               + "In the Volunteers panel, you can view the volunteers in your library system. You can \n"
               + "left-click on one of the volunteers to view their profile. From their profile, you can \n"
               + "edit the volunteer or delete them. From the Volunteers panel, you can also add a \n"
               + "volunteer and adjust the display of volunteers. Please note the following instructions \n"
               + "for searching through the list of volunteers: \n\n"
               + "Search Criteria:\n"
               + "- when searching NAMES, the search term can be a part of a name\n"
               + "- when searching STUDENT NUMBER, the search term must be the entire student number\n\n"
               + "In the Timetable panel, you can add volunteers to certain shifts in the timetable. You\n"
               + "can also save your work in the system and exit the program from both panels.";
    }
    
    /*
     * Adds an ActionListener to the MenuItem button 'Instructions' so that the JOptionPane message 
     * will show when a user clicks on the MenuItem.
     */
    private void addActionListeners()
    {
        instructionsMenuItem.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    instructionsDialog.showMessageDialog(instructionsFrame, instructionsMessage);
                }
            });
    } // end of method addActionListeners()
} // end of class InstructionsMenuBar