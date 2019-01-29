import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import static javax.swing.GroupLayout.Alignment.*;
import java.util.*;
/**
 * This class contains the GUI used to display the VP Library Volunteer System as well as
 * accompanying methods used to respond to user input. The VP Library Volunteer System is
 * comprised of 2 main panels, the 'Volunteers' panel and the 'Timetable' panel. Users may
 * log-in to this system using a username and password and save the changes they have made
 * to a text file.
 * 
 * @author Sally Zhou
 * @version 1.0 2018-03-07
 */
public class GUI
{
    // constants
    private static final String LOG_IN_FILE = "Instructions.LogIn.txt";
    private static final String SYSTEM_FILE = "Instructions.System.txt";
    private static final String FRAME_HEADING = "VP Library Volunteer System";
    private static final String VOLUNTEER_FRAME_HEADING = "Volunteers";
    private static final String TIMETABLE_FRAME_HEADING = "Timetable";
    private static final String VOLUNTEER_PROFILE_HEADING = "Volunteer Profile";
    // sort and search options for volunteer panel
    private static final String[] SORT_TYPES = new String[] {"Alphabetical", "Grades", "Student Number"};
    private static final String[] SEARCH_TYPES = new String[] {"NAME", "STUDENT NUMBER"};
    // sort options for timetable panel
    private static final String[] PRIORITY_TYPES = {"Grade 9", "Grade 10", "Returning"};
    private static final String[] DAYS = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
    private static final String[] DAYS_SHORT = {"M", "T", "W", "T", "F"};
    private static final String[] TIMES = {"MORNING", "LUNCH", "AFTERNOON"};
    private static final String[] TIMES_SHORT = {"Morn.", "Lunch", "Aftn."};
    // static fields
    private static int numPreviewButtonsShown = 0;
    
    // instance field for menubar
    private JMenuBar menuBar;
    
    // instance fields for log in frame
    private JFrame logInFrame;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton logInButton;
    private JButton newAccountButton;
    // instance fields for create a new account frame
    private JDialog createAccountFrame;
    private JTextField createUsernameField;
    private JTextField createPasswordField;
    
    // frame for volunteers and timetable
    private JFrame librarySystemFrame;
    // tabbed pane for volunteers and timetable
    private JTabbedPane librarySystemPane;
    
    // instance fields for volunteer panel
    private JPanel volunteerFramePanel;
    private JButton volunteerButton;
    private JComboBox<String> sortTypesComboBox;
    private JComboBox<String> searchTypesComboBox;
    private JTextField searchTypesTextField;
    private JScrollPane volunteerScrollPane;
    private JButton volunteerUpdateButton;
    private JButton addVolunteerButton;
    private JButton logOffButtonVolunteer;
    // volunteer preview
    private JPanel volunteerPreviewsPanel;
    private ArrayList<JButton> volunteerPreviews;
    private GridLayout volunteerPreviewsLayout;
    
    // instance fields for timetable panel
    private JPanel timetableFramePanel;
    private JButton timetableButton;
    private JComboBox<String> setPriorityComboBox;
    private JComboBox<String> changePlacementsDayComboBox;
    private JComboBox<String> changePlacementsTimeComboBox;
    private JTextField changePlacementsTextField;
    private JButton updateTimetableButton;
    private JPanel timetableContainerPanel;
    private JButton exportTextFileButton;
    private JButton logOffButtonTimetable;
    // timetable panel
    private JPanel timetablePanel;
    private JPanel[][] timetablePlacements;
    private GridLayout timetableLayout;
    
    // instances for add a volunteer applicant frame
    private JDialog addVolunteerFrame;
    private JTextField firstNameTextFieldA;
    private JTextField lastNameTextFieldA;
    private JTextField stuNumTextFieldA;
    private JTextField gradeTextFieldA;
    private JTextField teacherTextFieldA;
    private JTextField emailTextFieldA;
    private JCheckBox returningCheckBoxA;
    private JRadioButton shelvingRadioButtonA;
    private JRadioButton circRadioButtonA;
    private JCheckBox[][] shiftsCheckBoxA;
    private JButton saveButtonA;
    private JButton cancelButtonA;
    
    // instance fields for edit a volunteer applicant frame
    private JDialog editVolunteerFrame;
    private JTextField firstNameTextFieldE;
    private JTextField lastNameTextFieldE;
    private JTextField stuNumTextFieldE;
    private JTextField gradeTextFieldE;
    private JTextField teacherTextFieldE;
    private JTextField emailTextFieldE;
    private JCheckBox returningCheckBoxE;
    private JRadioButton shelvingRadioButtonE;
    private JRadioButton circRadioButtonE;
    private JCheckBox[][] shiftsCheckBoxE;
    private JButton saveButtonE;
    private JButton cancelButtonE;
    
    // library object
    private Library library;
    
    /**
     * Creates a GUI object that takes a parameter of a Library object.
     * 
     * @param library the library whose info the GUI shows
     */
    public GUI(Library library)
    {
        this.library = library;
        initComponents();
    }
    
    /**
     * Starts the program by displaying the log in frame.
     */
    public void start()
    {
        logInFrame.setVisible(true);
    }
    
    // initializing GUI components //
    
    /*
     * Central method used to initialize all frames.
     */
    private void initComponents()
    {
        makeLogInFrame();
        makeLibrarySystemFrames();
        makeAddVolunteerFrame();
        makeEditVolunteerFrame();
    }
    
    /*
     * Creates and initializes the log in frame.
     */
    private void makeLogInFrame()
    {
        // initializing GUI components inside frame
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        usernameTextField = new JTextField("", 15);
        passwordTextField = new JTextField("", 15);
        logInButton = new JButton("Log In");
        logInButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {logIn();}
            });
        newAccountButton = new JButton("Create New Account");
        newAccountButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {makeCreateAccountFrame();}
            });
            
        // initializing frame
        logInFrame = new JFrame(FRAME_HEADING);
        logInFrame.getRootPane().setDefaultButton(logInButton);
        JPanel contentPane = (JPanel) logInFrame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 18, 12, 12));
        
        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setBorder(BorderFactory.createTitledBorder("Log In"));
        contentPane.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        // formatting using GroupLayout
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(usernameLabel)
                        .addComponent(passwordLabel))
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(usernameTextField)
                        .addComponent(passwordTextField)))
            .addComponent(logInButton)
            .addComponent(newAccountButton))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(usernameLabel)
                .addComponent(usernameTextField))
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(passwordLabel)
                .addComponent(passwordTextField))
            .addComponent(logInButton)
            .addComponent(newAccountButton)
        );
        // add menu bar with instructions for logging in
        menuBar = new InstructionsMenuBar(LOG_IN_FILE);
        logInFrame.setJMenuBar(menuBar);
        
        logInFrame.pack();
        logInFrame.setLocation(350, 250);
    }
    
    /*
     * Creates and initializes the create a new account frame. Uses BorderLayout.
     */
    private void makeCreateAccountFrame()
    {
        JFrame frame = new JFrame();
        createAccountFrame = new JDialog(frame, "Create new account");
        JPanel contentPane = (JPanel) createAccountFrame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        JPanel logInPanel = new JPanel();
        logInPanel.setLayout(new GridLayout(2, 2, 2, 2));
        
        logInPanel.add(new JLabel("Enter new username: "));
        createUsernameField = new JTextField("", 15);
        logInPanel.add(createUsernameField);
        logInPanel.add(new JLabel("Enter new password: "));
        createPasswordField = new JTextField("", 15);
        logInPanel.add(createPasswordField);
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {createNewAccount();}
            });
        contentPane.add(logInPanel, BorderLayout.CENTER);
        contentPane.add(createButton, BorderLayout.PAGE_END);
        
        createAccountFrame.pack();
        createAccountFrame.setModal(true); // user must complete actions on this frame before moving to another frame
        createAccountFrame.setVisible(true);
    }
    
    /*
     * Central method for creating library system frames.
     */
    private void makeLibrarySystemFrames()
    {
        // creating the main library system frame
        librarySystemFrame = new JFrame(FRAME_HEADING);
        // changing default operation of close button so that a confirm exit window appears instead
        librarySystemFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        librarySystemFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {confirmExit();}
        });
        // creating the panels and components inside
        makeVolunteerFrame();
        makeTimetableFrame();
        // adding volunteer and timetable panels to tabbed pane and then the tabbed pane to main frame
        librarySystemPane = new JTabbedPane();
        librarySystemPane.addTab(VOLUNTEER_FRAME_HEADING, volunteerFramePanel);
        librarySystemPane.addTab(TIMETABLE_FRAME_HEADING, timetableFramePanel);
        librarySystemFrame.add(librarySystemPane);
        // initializing menu bar
        menuBar = new InstructionsMenuBar(SYSTEM_FILE);
        librarySystemFrame.setJMenuBar(menuBar);
        librarySystemFrame.pack();
        librarySystemFrame.validate();
    }
    
    /*
     * Creates the 'Volunteers' tabbed pane in the library system frame.
     */
    private void makeVolunteerFrame()
    {
        // initializing volunteer preview panel inside scroll pane
        volunteerPreviews = new ArrayList<>();
        volunteerPreviewsPanel = new JPanel();
        volunteerPreviewsPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        volunteerPreviewsLayout = new GridLayout(library.getSize(), 1, 0, 5);
        volunteerPreviewsPanel.setLayout(volunteerPreviewsLayout);
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        container.add(volunteerPreviewsPanel);
        // other GUI components
        JLabel sortLabel = new JLabel("Sort By: ");
        sortTypesComboBox = new JComboBox<>(SORT_TYPES);
        JLabel searchLabel = new JLabel("Search: ");
        searchTypesComboBox = new JComboBox<>(SEARCH_TYPES);
        searchTypesTextField = new JTextField(10);
        volunteerScrollPane = new JScrollPane(container);
        volunteerUpdateButton = new JButton("Update");
        volunteerUpdateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {updateVolunteerPreviewsPanel();}
            });
        addVolunteerButton = new JButton("Add a Volunteer");
        addVolunteerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {addVolunteerFrame.setVisible(true);}
            });
        logOffButtonVolunteer = new JButton("Log Off & Exit");
        logOffButtonVolunteer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {saveExit();}
            });
        
        // initializing volunteer preview buttons
        for (int i = 0; i < library.getSize(); i++)
        {
            final int temp = i;
            volunteerPreviews.add(new JButton(library.getVolunteerPreview(i)));
            volunteerPreviews.get(i).addActionListener(new ActionListener() {
                    int index = temp;
                    public void actionPerformed(ActionEvent e) {displayVolunteerProfile(index);}
                });
            volunteerPreviews.get(i).setPreferredSize(new Dimension(740, 40));
        }
        // initialzing GUI components in the panel
        updateVolunteerPreviewsPanel();
        
        // initializing volunteer panel
        volunteerFramePanel = new JPanel();
        volunteerFramePanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        // adding layout manager and formatting panel
        GroupLayout layout = new GroupLayout(volunteerFramePanel);
        volunteerFramePanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        // formatting using GroupLayout
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(sortLabel)
                    .addComponent(sortTypesComboBox)
                    .addComponent(searchLabel)
                    .addComponent(searchTypesComboBox)
                    .addComponent(searchTypesTextField)
                    .addComponent(volunteerUpdateButton))
                .addComponent(volunteerScrollPane)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(addVolunteerButton)
                    .addComponent(logOffButtonVolunteer)))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(sortLabel)
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(sortTypesComboBox))
                .addComponent(searchLabel)
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(searchTypesComboBox)
                    .addComponent(searchTypesTextField)
                    .addComponent(volunteerUpdateButton)))
            .addComponent(volunteerScrollPane)
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(addVolunteerButton)
                .addComponent(logOffButtonVolunteer))
        );
    }
    
    /*
     * Creates the 'Timetable' tabbed pane in the library system frame.
     */
    private void makeTimetableFrame()
    {   
        // create GUI components in general pane
        JLabel setPriorityLabel = new JLabel("Set Priority:");
        setPriorityComboBox = new JComboBox<>(PRIORITY_TYPES);
        JLabel changePlacementsLabel = new JLabel("Change # of Placements:");
        changePlacementsDayComboBox = new JComboBox<>(DAYS);
        changePlacementsTimeComboBox = new JComboBox<>(TIMES);
        changePlacementsTextField = new JTextField(15);
        updateTimetableButton = new JButton("Update");
        updateTimetableButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Sorry, this feature is coming soon.");
                }
            });
        exportTextFileButton = new JButton("Export to Text File");
        exportTextFileButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Sorry, this feature is coming soon.");
                }
            });
        logOffButtonTimetable = new JButton("Log Off & Exit");
        logOffButtonTimetable.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {saveExit();}
            });
        // create GUI components inside timetableContainerPanel
        timetablePlacements = new JPanel[6][4];
        timetablePanel = new JPanel();
        timetablePanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        timetablePanel.setLayout(new GridLayout(4, 6));
        timetableContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        timetableContainerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        timetableContainerPanel.add(timetablePanel);
        updateTimetablePanel();
        
        timetableFramePanel = new JPanel();
        timetableFramePanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        
        GroupLayout layout = new GroupLayout(timetableFramePanel);
        timetableFramePanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        // formatting using GroupLayout
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(setPriorityLabel)
                    .addComponent(setPriorityComboBox)
                    .addComponent(changePlacementsLabel)
                    .addComponent(changePlacementsDayComboBox)
                    .addComponent(changePlacementsTimeComboBox)
                    .addComponent(changePlacementsTextField)
                    .addComponent(updateTimetableButton))
                .addComponent(timetableContainerPanel)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(exportTextFileButton)
                    .addComponent(logOffButtonTimetable)))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(setPriorityLabel)
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(setPriorityComboBox))
                .addComponent(changePlacementsLabel)
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(changePlacementsDayComboBox)
                    .addComponent(changePlacementsTimeComboBox)
                    .addComponent(changePlacementsTextField)
                    .addComponent(updateTimetableButton)))
            .addComponent(timetableContainerPanel)
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(exportTextFileButton)
                .addComponent(logOffButtonTimetable))
        );
    }
    
    /*
     * Creates the 'Add Volunteer' frame, where the user can input characteristics for a Volunteer and add the Volunteer
     * to this Library.
     */
    private void makeAddVolunteerFrame()
    {
        // initializing variables
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel stuNumLabel = new JLabel("Student #:");
        JLabel gradeLabel = new JLabel("Grade:");
        JLabel teacherLabel = new JLabel("HR Teacher:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel returningLabel = new JLabel("Returning:");
        JLabel positionTypeLabel = new JLabel("Type of Position:");
        JLabel availableTimesLabel = new JLabel("Available Times:");
        firstNameTextFieldA = new JTextField(10);
        lastNameTextFieldA = new JTextField(10);
        stuNumTextFieldA = new JTextField(10);
        gradeTextFieldA = new JTextField(10);
        teacherTextFieldA = new JTextField(10);
        emailTextFieldA = new JTextField(10);
        returningCheckBoxA = new JCheckBox("");
        shelvingRadioButtonA = new JRadioButton("Shelving");
        circRadioButtonA = new JRadioButton("Circ.");
        shiftsCheckBoxA = new JCheckBox[DAYS.length][TIMES.length];
        // format grid for the shifts
        JPanel shiftsPanel = new JPanel();
        shiftsPanel.setBorder(new EmptyBorder(4, 12, 12, 12));
        shiftsPanel.setLayout(new GridLayout(4, 6));
        shiftsPanel.add(new JLabel(""));
        for (int day = 0; day < DAYS.length; day++)
        {
            shiftsPanel.add(new JLabel(DAYS_SHORT[day]));
        }
        for (int time = 0; time < TIMES.length; time++)
        {
            for (int day = 0; day < DAYS.length; day++)
            {
                if (day == 0)
                    shiftsPanel.add(new JLabel(TIMES_SHORT[time]));
                shiftsCheckBoxA[day][time] = new JCheckBox("");
                shiftsPanel.add(shiftsCheckBoxA[day][time]);
                //shiftsCheckBoxA[day][time].setSelected(false);
            }
        }
        saveButtonA = new JButton("Add Volunteer");
        saveButtonA.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addVolunteerButtonClicked();
                }
            });
        cancelButtonA = new JButton("Cancel");
        cancelButtonA.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addVolunteerFrame.setVisible(false);
                    clearAddVolunteerFrame();
                }
            });
        // Group the radio buttons
        ButtonGroup positionType = new ButtonGroup();
        positionType.add(shelvingRadioButtonA);
        positionType.add(circRadioButtonA);
        
        JFrame frame = new JFrame();
        addVolunteerFrame = new JDialog(frame, "Add a Volunteer");
        addVolunteerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addVolunteerFrame.addWindowListener(new WindowAdapter() {
            @Override // make sure that when close button is clicked, all fields are reset to default
            public void windowClosing(WindowEvent windowEvent) {
                clearAddVolunteerFrame();
                addVolunteerFrame.setVisible(false);
            }
        });
        JPanel contentPane = (JPanel) addVolunteerFrame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        
        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        // formatting using GroupLayout
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(firstNameLabel)
                        .addComponent(lastNameLabel)
                        .addComponent(stuNumLabel)
                        .addComponent(gradeLabel)
                        .addComponent(teacherLabel)
                        .addComponent(emailLabel)
                        .addComponent(returningLabel))
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(firstNameTextFieldA)
                        .addComponent(lastNameTextFieldA)
                        .addComponent(stuNumTextFieldA)
                        .addComponent(gradeTextFieldA)
                        .addComponent(teacherTextFieldA)
                        .addComponent(emailTextFieldA)
                        .addComponent(returningCheckBoxA))
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(positionTypeLabel)
                            .addComponent(shelvingRadioButtonA)
                            .addComponent(circRadioButtonA))
                        .addComponent(availableTimesLabel)
                        .addComponent(shiftsPanel)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(saveButtonA)
                    .addComponent(cancelButtonA)))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(firstNameLabel)
                        .addComponent(firstNameTextFieldA))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(lastNameLabel)
                        .addComponent(lastNameTextFieldA))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(stuNumLabel)
                        .addComponent(stuNumTextFieldA))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(gradeLabel)
                        .addComponent(gradeTextFieldA))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(teacherLabel)
                        .addComponent(teacherTextFieldA))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(emailLabel)
                        .addComponent(emailTextFieldA))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(returningLabel)
                        .addComponent(returningCheckBoxA)))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(positionTypeLabel)
                        .addComponent(shelvingRadioButtonA)
                        .addComponent(circRadioButtonA))
                    .addComponent(availableTimesLabel)
                    .addComponent(shiftsPanel)))
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(saveButtonA)
                .addComponent(cancelButtonA))
        );
        
        addVolunteerFrame.pack();
        addVolunteerFrame.setLocation(370, 220);
        addVolunteerFrame.setModal(true);
    }
    
    /*
     * Creates the 'Edit Volunteer' frame, where the user can edit the characteristics of a specified Volunteer.
     */
    private void makeEditVolunteerFrame()
    {
        // initializing variables
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel stuNumLabel = new JLabel("Student #:");
        JLabel gradeLabel = new JLabel("Grade:");
        JLabel teacherLabel = new JLabel("HR Teacher:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel returningLabel = new JLabel("Returning:");
        JLabel positionTypeLabel = new JLabel("Type of Position:");
        JLabel availableTimesLabel = new JLabel("Available Times:");
        firstNameTextFieldE = new JTextField(10);
        lastNameTextFieldE = new JTextField(10);
        stuNumTextFieldE = new JTextField(10);
        gradeTextFieldE = new JTextField(10);
        teacherTextFieldE = new JTextField(10);
        emailTextFieldE = new JTextField(10);
        returningCheckBoxE = new JCheckBox("");
        shelvingRadioButtonE = new JRadioButton("Shelving");
        circRadioButtonE = new JRadioButton("Circ.");
        shiftsCheckBoxE = new JCheckBox[5][3];
        // format grid for the shifts
        JPanel shiftsPanel = new JPanel();
        shiftsPanel.setBorder(new EmptyBorder(0, 12, 12, 12));
        shiftsPanel.setLayout(new GridLayout(4, 6));
        shiftsPanel.add(new JLabel(""));
        for (int day = 0; day < DAYS.length; day++)
        {
            shiftsPanel.add(new JLabel(DAYS_SHORT[day]));
        }
        for (int time = 0; time < TIMES.length; time++)
        {
            for (int day = 0; day < DAYS.length; day++)
            {
                if (day == 0)
                    shiftsPanel.add(new JLabel(TIMES_SHORT[time]));
                shiftsCheckBoxE[day][time] = new JCheckBox("");
                shiftsPanel.add(shiftsCheckBoxE[day][time]);
            }
        }
        saveButtonE = new JButton("Save Changes");
        cancelButtonE = new JButton("Cancel");
        cancelButtonE.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {editVolunteerFrame.setVisible(false);}
            });
        // Group the radio buttons
        ButtonGroup positionType = new ButtonGroup();
        positionType.add(shelvingRadioButtonE);
        positionType.add(circRadioButtonE);
        
        JFrame frame = new JFrame();
        editVolunteerFrame = new JDialog(frame, "Edit Volunteer");
        JPanel contentPane = (JPanel) editVolunteerFrame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        
        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        // formatting using GroupLayout
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(firstNameLabel)
                        .addComponent(lastNameLabel)
                        .addComponent(stuNumLabel)
                        .addComponent(gradeLabel)
                        .addComponent(teacherLabel)
                        .addComponent(emailLabel)
                        .addComponent(returningLabel))
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(firstNameTextFieldE)
                        .addComponent(lastNameTextFieldE)
                        .addComponent(stuNumTextFieldE)
                        .addComponent(gradeTextFieldE)
                        .addComponent(teacherTextFieldE)
                        .addComponent(emailTextFieldE)
                        .addComponent(returningCheckBoxE))
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(positionTypeLabel)
                            .addComponent(shelvingRadioButtonE)
                            .addComponent(circRadioButtonE))
                        .addComponent(availableTimesLabel)
                        .addComponent(shiftsPanel)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(saveButtonE)
                    .addComponent(cancelButtonE)))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(firstNameLabel)
                        .addComponent(firstNameTextFieldE))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(lastNameLabel)
                        .addComponent(lastNameTextFieldE))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(stuNumLabel)
                        .addComponent(stuNumTextFieldE))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(gradeLabel)
                        .addComponent(gradeTextFieldE))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(teacherLabel)
                        .addComponent(teacherTextFieldE))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(emailLabel)
                        .addComponent(emailTextFieldE))
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(returningLabel)
                        .addComponent(returningCheckBoxE)))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(positionTypeLabel)
                        .addComponent(shelvingRadioButtonE)
                        .addComponent(circRadioButtonE))
                    .addComponent(availableTimesLabel)
                    .addComponent(shiftsPanel)))
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(saveButtonE)
                .addComponent(cancelButtonE))
        );
        
        editVolunteerFrame.pack();
        editVolunteerFrame.setLocation(370, 220);
        editVolunteerFrame.setModal(true);
    }
    
    // methods to be invoked when user makes an action //
    
    /*
     * Allows the user to log in to the library system.
     */
    private void logIn() 
    {
        String user = usernameTextField.getText();
        String pw = passwordTextField.getText();
        boolean validLogIn = library.logIn(user, pw);
        if (validLogIn)
        {
            logInFrame.setVisible(false);
            librarySystemFrame.setVisible(true);
        }
        else
        {
            JOptionPane message = new JOptionPane();
            message.showMessageDialog(null, "Your username or password is incorrect. Please try again.");
        }
        // clear text fields
        usernameTextField.setText("");
        passwordTextField.setText("");
    } // end of method logIn()
    
    /*
     * Displays the volunteer profile of a specified volunteer.
     * @param index the specified volunteer
     */
    private void displayVolunteerProfile(int index)
    {
        final int i = index;
        JFrame frame = new JFrame();
        final JDialog volunteerProfile = new JDialog(frame, VOLUNTEER_PROFILE_HEADING);
        JPanel contentPane = (JPanel) volunteerProfile.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        
        // access info needed for this Volunteer profile
        String[] profile = library.getVolunteerProfile(index);
        
        // create GUI components for the profile
        JLabel nameLabel = new JLabel("Name:");
        JLabel stuNumLabel = new JLabel("Student #:");
        JLabel gradeLabel = new JLabel("Grade:");
        JLabel teacherLabel = new JLabel("HR Teacher:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel returningLabel = new JLabel("Returning:");
        JLabel positionLabel = new JLabel("Position:");
        JLabel nameInfo = new JLabel(profile[0] + " " + profile[1]);
        JLabel stuNumInfo = new JLabel(profile[2]);
        JLabel gradeInfo = new JLabel(profile[3]);
        JLabel teacherInfo = new JLabel(profile[4]);
        JLabel emailInfo = new JLabel(profile[5]);
        JLabel returningInfo = new JLabel(profile[6]);
        JLabel positionInfo = new JLabel(profile[7]);
        JButton editVolunteerButton = new JButton("Edit");
        editVolunteerButton.addActionListener(new ActionListener() {
                int volunteer = i;
                public void actionPerformed(ActionEvent e) {
                    volunteerProfile.setVisible(false);
                    displayEditVolunteerFrame(volunteer);}
            });
        JButton deleteVolunteerButton = new JButton("Delete");
        deleteVolunteerButton.addActionListener(new ActionListener() {
                int volunteer = i;
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this volunteer?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        volunteerProfile.setVisible(false);
                        deleteVolunteer(i);
                    }
                }
            });
        
        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        // formatting using GroupLayout
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(nameLabel)
                        .addComponent(stuNumLabel)
                        .addComponent(gradeLabel)
                        .addComponent(teacherLabel)
                        .addComponent(emailLabel)
                        .addComponent(returningLabel)
                        .addComponent(positionLabel))
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(nameInfo)
                        .addComponent(stuNumInfo)
                        .addComponent(gradeInfo)
                        .addComponent(teacherInfo)
                        .addComponent(emailInfo)
                        .addComponent(returningInfo)
                        .addComponent(positionInfo)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(editVolunteerButton)
                    .addComponent(deleteVolunteerButton)))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(nameLabel)
                .addComponent(nameInfo))
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(stuNumLabel)
                .addComponent(stuNumInfo))
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(gradeLabel)
                .addComponent(gradeInfo))
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(teacherLabel)
                .addComponent(teacherInfo))
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(emailLabel)
                .addComponent(emailInfo))
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(returningLabel)
                .addComponent(returningInfo))
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(positionLabel)
                .addComponent(positionInfo))
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(editVolunteerButton)
                .addComponent(deleteVolunteerButton))
        );
        
        volunteerProfile.pack();
        volunteerProfile.setModal(true);
        volunteerProfile.setVisible(true);
    }
    
    /*
     * Displays the 'Edit Volunteer' frame of a specified volunteer and fills the fields with the volunteer's information.
     * @param index the specified volunteer
     */
    private void displayEditVolunteerFrame(int index)
    {
        final int i = index;
        // accessing info needed for display
        String[] profile = library.getVolunteerProfile(index);
        ArrayList<int[]> allShifts = library.getVolunteerShifts(index);
        // display info
        firstNameTextFieldE.setText(profile[0]);
        lastNameTextFieldE.setText(profile[1]);
        stuNumTextFieldE.setText(profile[2]);
        gradeTextFieldE.setText(profile[3]);
        teacherTextFieldE.setText(profile[4]);
        emailTextFieldE.setText(profile[5]);
        returningCheckBoxE.setSelected(Boolean.valueOf(profile[6]));
        if (profile[7].equals("SHELVING"))
            shelvingRadioButtonE.setSelected(true);
        else
            circRadioButtonE.setSelected(true);
        // clear any 'selected' check boxes for shift
        for (int time = 0; time < TIMES.length; time++)
        {
            for (int day = 0; day < DAYS.length; day++)
                shiftsCheckBoxE[day][time].setSelected(false);
        }
        // set volunteer's specified shifts to appear 'selected'
        for (int[] shift : allShifts)
        {
            shiftsCheckBoxE[shift[0]][shift[1]].setSelected(true);
        }
        // delete ActionListeners from before and then add new ActionListener that matches the volunteer index
        if (saveButtonE.getActionListeners().length != 0)
            saveButtonE.removeActionListener((saveButtonE.getActionListeners())[0]);
        saveButtonE.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) {
                    editVolunteerButtonClicked(i);
                }
            });
        editVolunteerFrame.setVisible(true);
    }
    
    /*
     * Method is invoked when the 'Add Volunteer' button is pressed. Central method used to create volunteer, add volunteer to 
     * this Library, and update volunteer preview. Handles errors from invalid user input.
     */
    private void addVolunteerButtonClicked()
    {
        try
        {
            addVolunteer();
            addVolunteerFrame.setVisible(false); // will only reach this line if addVolunteer() didn't throw an exception
            clearAddVolunteerFrame();
            updateVolunteerPreviewContent(0, library.getSize() - 1);
            updateTimetablePanel();
            JOptionPane.showMessageDialog(null, "The volunteer has been added.");
        }
        catch (NumberFormatException e)
        {
            JOptionPane message = new JOptionPane();
            message.showMessageDialog(null, "You entered an invalid value. Please try again.");
        }
        catch (RuntimeException e)
        {
            JOptionPane message = new JOptionPane();
            if (e.getMessage().equals("0"))
                message.showMessageDialog(null, "That student number already exists within\nthis system. Please try again.");
            else if (e.getMessage().equals("1"))
                message.showMessageDialog(null, "You did not select a type of position. Please try again.");
            else if (e.getMessage().equals("2"))
                message.showMessageDialog(null, "You did not select a shift. Please try again.");
        }
    }
    
    /*
     * Creates a new Volunteer and adds the volunteer to this Library.
     */
    private void addVolunteer() throws NumberFormatException, RuntimeException
    {
        String fs = firstNameTextFieldA.getText().toUpperCase();
        String ls = lastNameTextFieldA.getText().toUpperCase();
        int stuN =  Integer.parseInt(stuNumTextFieldA.getText());
        int gr = Integer.parseInt(gradeTextFieldA.getText());
        String tchr = teacherTextFieldA.getText().toUpperCase();
        String email = emailTextFieldA.getText();
        boolean rtn = returningCheckBoxA.isSelected();
        // check if values are ok
        if (fs.equals("") || ls.equals("") || tchr.equals("") || email.equals(""))
            throw new NumberFormatException();
        if (String.valueOf(stuN).length() != 9 || gr < 9 || gr > 12)
            throw new NumberFormatException();
        String ps = "";
        if (shelvingRadioButtonA.isSelected())
            ps = "SHELVING";
        else if (circRadioButtonA.isSelected())
            ps = "CIRC";
        else
            throw new RuntimeException("1"); // 1 indicates an error regarding position
        // retrieve the selected shifts
        ArrayList<Integer> tempDays = new ArrayList<>();
        ArrayList<Integer> tempTimes = new ArrayList<>();
        for (int time = 0; time < TIMES.length; time++)
        {
            for (int day = 0; day < DAYS.length; day++)
            {    
                if (shiftsCheckBoxA[day][time].isSelected()) // if a shift has been selected
                {
                    tempDays.add(day);
                    tempTimes.add(time);
                }
            }
        }
        if (tempDays.size() == 0) // if no shifts are selected
            throw new RuntimeException("2"); // 2 indicates an error regarding shifts
        int[] days = new int[tempDays.size()];
        int[] times = new int[tempTimes.size()];
        for (int i = 0; i < days.length; i++) // store shifts into arrays of day and time
        {
            days[i] = tempDays.get(i).intValue();
            times[i] = tempTimes.get(i).intValue();
        }
        library.addVolunteer(fs, ls, stuN, gr, tchr, email, rtn, ps, days, times);
    }
    
    /*
     * Clears the 'Add Volunteer' frame after each time it is opened.
     */
    private void clearAddVolunteerFrame()
    {
        firstNameTextFieldA.setText("");
        lastNameTextFieldA.setText("");
        stuNumTextFieldA.setText("");
        gradeTextFieldA.setText("");
        teacherTextFieldA.setText("");
        emailTextFieldA.setText("");
        returningCheckBoxA.setSelected(false);
        shelvingRadioButtonA.setSelected(false);
        circRadioButtonA.setSelected(false);
        // clear any 'selected' check boxes for shift
        for (int time = 0; time < TIMES.length; time++)
        {
            for (int day = 0; day < DAYS.length; day++)
                shiftsCheckBoxA[day][time].setSelected(false);
        }
    }
    
    /*
     * Method is invoked when the 'Edit Volunteer' button is pressed. Central method used to edit volunteer 
     * and update volunteer preview. Handles errors from invalid user input.
     */
    private void editVolunteerButtonClicked(int index)
    {
        try
        {
            editVolunteer(index);
            editVolunteerFrame.setVisible(false); // will only reach this line if editVolunteer(index) didn't throw an exception
            clearEditVolunteerFrame();
            updateVolunteerPreviewContent(1, index);
            updateTimetablePanel();
            JOptionPane.showMessageDialog(null, "The volunteer has been edited.");
        }
        catch (NumberFormatException e)
        {
            JOptionPane message = new JOptionPane();
            message.showMessageDialog(null, "You entered an invalid value. Please try again.");
        }
        catch (RuntimeException e)
        {
            JOptionPane message = new JOptionPane();
            if (e.getMessage().equals("0"))
                message.showMessageDialog(null, "That student number already exists within\nthis system. Please try again.");
            else if (e.getMessage().equals("1"))
                message.showMessageDialog(null, "You did not select a type of position. Please try again.");
            else if (e.getMessage().equals("2"))
                message.showMessageDialog(null, "You did not select a shift. Please try again.");
        }
    }
    
    /*
     * Edits the characteristics of the specified Volunteer.
     */
    private void editVolunteer(int index) throws NumberFormatException, RuntimeException
    {
        String fs = firstNameTextFieldE.getText().toUpperCase();
        String ls = lastNameTextFieldE.getText().toUpperCase();
        int stuN =  Integer.parseInt(stuNumTextFieldE.getText());
        int gr = Integer.parseInt(gradeTextFieldE.getText());
        String tchr = teacherTextFieldE.getText().toUpperCase();
        String email = emailTextFieldE.getText();
        boolean rtn = returningCheckBoxE.isSelected();
        // check if values are ok
        if (fs.equals("") || ls.equals("") || tchr.equals("") || email.equals(""))
            throw new NumberFormatException();
        if (String.valueOf(stuN).length() != 9 || gr < 9 || gr > 12)
            throw new NumberFormatException();
        String ps = "";
        if (shelvingRadioButtonE.isSelected())
            ps = "SHELVING";
        else if (circRadioButtonE.isSelected())
            ps = "CIRC";
        else
            throw new RuntimeException("1"); // 1 indicates an error regarding position
        // retrieve the selected shifts
        ArrayList<Integer> tempDays = new ArrayList<>();
        ArrayList<Integer> tempTimes = new ArrayList<>();
        for (int time = 0; time < TIMES.length; time++)
        {
            for (int day = 0; day < DAYS.length; day++)
            {
                if (shiftsCheckBoxE[day][time].isSelected()) // if a shift has been selected
                {
                    tempDays.add(day);
                    tempTimes.add(time);
                }
            }
        }
        if (tempDays.size() == 0) // if no shifts are selected
            throw new RuntimeException("2"); // 2 indicates an error regarding shifts
        int[] days = new int[tempDays.size()];
        int[] times = new int[tempTimes.size()];
        for (int i = 0; i < days.length; i++) // store shifts into arrays of day and time
        {
            days[i] = tempDays.get(i).intValue();
            times[i] = tempTimes.get(i).intValue();
        }
        library.editVolunteer(fs, ls, stuN, gr, tchr, email, rtn, ps, days, times, index);
    }
    
    /*
     * Clears the 'Edit Volunteer' frame after each time it is opened.
     */
    private void clearEditVolunteerFrame()
    {
        firstNameTextFieldE.setText("");
        lastNameTextFieldE.setText("");
        stuNumTextFieldE.setText("");
        gradeTextFieldE.setText("");
        teacherTextFieldE.setText("");
        emailTextFieldE.setText("");
        returningCheckBoxE.setSelected(false);
        shelvingRadioButtonE.setSelected(false);
        circRadioButtonE.setSelected(false);
        // clear any 'selected' check boxes for shift
        for (int time = 0; time < TIMES.length; time++)
        {
            for (int day = 0; day < DAYS.length; day++)
                shiftsCheckBoxE[day][time].setSelected(false);
        }
    }
    
    /*
     * Deletes the specified Volunteer from the library database and updates the volunteer preview.
     */
    private void deleteVolunteer(int index)
    {
        library.removeVolunteer(index);
        updateVolunteerPreviewContent(2, index);
        updateTimetablePanel();
        JOptionPane.showMessageDialog(null, "The volunteer has been deleted.");
    }
    
    /*
     * Updates the content shown in the volunteer preview according to which action has been invoked.
     */
    private void updateVolunteerPreviewContent(int option, int index)
    {
        final int i = index;
        volunteerPreviewsLayout.setRows(library.getSize());
        switch (option)
        {
            case 0: // Add volunteer
                // create a new button with text set to the latest volunteer preview text and add the new button to volunteerPreviews
                volunteerPreviews.add(new JButton(library.getVolunteerPreview(index)));
                volunteerPreviews.get(index).addActionListener(new ActionListener() {
                    int volunteer = i;
                    public void actionPerformed(ActionEvent e) {displayVolunteerProfile(volunteer);}
                });
                volunteerPreviews.get(index).setPreferredSize(new Dimension(740, 40));
                volunteerPreviewsPanel.add(volunteerPreviews.get(index));
                numPreviewButtonsShown++;
                break;
            
            case 1: // Edit volunteer
                // set the text of the specified Volunteer to the new changes
                volunteerPreviews.get(index).setText(library.getVolunteerPreview(index));
                break;
                
            case 2: // Delete volunteer
                // remove the button from the preview panel & reset the ActionListeners for the following buttons so that it matches with the volunteers
                volunteerPreviewsPanel.remove(volunteerPreviews.get(index));
                volunteerPreviews.remove(index);
                for (int j = index; j < volunteerPreviews.size(); j++)
                {
                    final int temp = j;
                    if (volunteerPreviews.get(j).getActionListeners().length != 0)
                        volunteerPreviews.get(j).removeActionListener((volunteerPreviews.get(j).getActionListeners())[0]);
                        
                    volunteerPreviews.get(j).addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {displayVolunteerProfile(temp);}
                        });
                }
                numPreviewButtonsShown--;
                break;
        }
        updateVolunteerPreviewsPanel();
    }
    
    /*
     * Updates volunteer preview panel every time user makes an action.
     */
    private void updateVolunteerPreviewsPanel()
    {
        try
        {
            // reseting volunteerPreviewsPanel
            for (int i = 0; i < numPreviewButtonsShown; i++)
            {
                volunteerPreviewsPanel.remove(volunteerPreviews.get(i));
            }
            numPreviewButtonsShown = 0;
            // adding buttons to scroll pane panel in specified sort order
            int[] sort = library.sort((String) sortTypesComboBox.getSelectedItem());
            for (int i = 0; i < library.getSize(); i++)
            {
                volunteerPreviewsPanel.add(volunteerPreviews.get(sort[i]));
                numPreviewButtonsShown++;
            }
            int[] search = library.search((String) searchTypesComboBox.getSelectedItem(), searchTypesTextField.getText());
            for (int i = 0; i < search.length; i++)
            {
                volunteerPreviewsPanel.remove(volunteerPreviews.get(search[i]));
                numPreviewButtonsShown--;
            }
            librarySystemFrame.validate();
            librarySystemFrame.repaint();
        }
        catch (NumberFormatException e)
        {
            JOptionPane message = new JOptionPane();
            message.showMessageDialog(null, "You entered an invalid value. Please try again.");
        }
    }
    
    /*
     * Updates the timetable every time user makes an action.
     */
    private void updateTimetablePanel()
    {
        // reset timetablePanel
        timetableContainerPanel.remove(timetablePanel);
        timetablePanel = new JPanel();
        timetablePanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        timetablePanel.setLayout(new GridLayout(4, 6));
        // add blank space, then days
        JLabel blank = new JLabel("");
        blank.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timetablePanel.add(blank);
        for (int day = 0; day < DAYS.length; day++)
        {
            JLabel dayLabel = new JLabel(DAYS[day]);
            dayLabel.setBorder(new EmptyBorder(3, 3, 3, 3));
            JPanel dayPanel = new JPanel();
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dayPanel.add(dayLabel);
            timetablePanel.add(dayPanel);
        }
        // retrieve volunteer placements
        ArrayList<String>[][] volunteerPlacements = library.assignShifts();
        for (int time = 0; time < TIMES.length; time++)
        {
            // add time labels
            JLabel timeLabel = new JLabel(TIMES[time]);
            timeLabel.setBorder(new EmptyBorder(3, 3, 3, 3));
            JPanel timePanel = new JPanel();
            timePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            timePanel.add(timeLabel);
            timetablePanel.add(timePanel);
            for (int day = 0; day < DAYS.length; day++)
            {
                // add volunteers to their shifts
                JPanel timeBlock = new JPanel();
                timeBlock.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                timeBlock.setLayout(new BoxLayout(timeBlock, BoxLayout.Y_AXIS));
                for (int i = 0; i < volunteerPlacements[day][time].size(); i++)
                {
                    JLabel volunteer = new JLabel(volunteerPlacements[day][time].get(i));
                    volunteer.setBorder(new EmptyBorder(3, 3, 3, 3));
                    timeBlock.add(volunteer);
                }
                timetablePanel.add(timeBlock);
            }
        }
        // add newly formatted timetablePanel to container
        timetableContainerPanel.add(timetablePanel);
        librarySystemFrame.validate();
        librarySystemFrame.repaint();
    }
    
    /*
     * Creates a new account that can be used to access the library system.
     */
    private void createNewAccount() 
    {
        String username = createUsernameField.getText();
        String ps = createPasswordField.getText();
        library.addUser(username,ps);
        createAccountFrame.setVisible(false);
        JOptionPane.showMessageDialog(null, "New account created! Make sure to log in using your new\naccount in order to save it in the system.");
    }
    
    /*
     * Asks user to confirm their intention to exit the program without saving. Exits if they confirm their willingness.
     */
    private void confirmExit()
    {
        // opens a JOptionPane that asks user to confirm that they want to exit the program
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            saveExit();
        }
    }
    
    /*
     * Writes the content in the Library to a text file database and then exits the program.
     */
    private void saveExit()
    {
        library.exitLibrary();
        System.exit(0);
    }
} // end of class GUI