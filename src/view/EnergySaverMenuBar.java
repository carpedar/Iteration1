/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
package view;

import model.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class PowerPaintMenuBar is a menu bar for program EnergySaver.
 *
 * GNU Licensed icon from:
 * http://www.iconarchive.com/show/crystal-clear-icons-by-everaldo/App-energy-star-icon.html
 *
 * @author Alex Smith
 * @author Darren Carpenter
 * @author Nikolai Carlson
 * @author Keegan Kell
 * @author Lola Howell
 * @version 16 April 2016 - Iteration 1
 */
public class EnergySaverMenuBar extends JMenuBar {
    /**
     * Fake serialVersionUID to squelch warning.
     */
    private static final long serialVersionUID = -1717799894208755241L;

    /** Paint roller icon for JFrame icon and about message dialog. */
    private static final Icon ROLLER = new ImageIcon("icons/estar-large-icon.png");

    /** Description about the program. */
    private static final String ABOUT_PROGRAM = "TCSS 360\n"
                                              + "Software Development & Quality Assurance Techniques\n"
                                              + "SPRING 2017\n\n"
                                              + "DEVELOPED AND DESIGNED BY\n"
                                              + "Alex R. Smith - developer\n"
                                              + "Darren T. Carpenter - front-end developer\n"
                                              + "Nikolai Carlson - developer\n"
                                              + "Keegan J. Kell - developer\n"
                                              + "Lola Howell - UX design / front-end developer\n\n"
                                              + "GNU Licensed Icon from: \n"
                                              + "http://icons.iconarchive.com";

    /** Panel reference (used to pass menuBar values to the panel).
     *  Needed for passing myGroup to panel? */
    private final LayoutPanel myPanel;
    
    /** Similar to creating the board in Tetris.*/
    private Group myGroup;

    /** Tools menu on the menu bar, contains the various tools. */
    //private final JMenu myToolsMenu;

    /**
     * Constructs the menu bar.
     *
     * @param theFrame b
     * @param thePanel b
     */
    public EnergySaverMenuBar(final JFrame theFrame, final LayoutPanel thePanel) {
        super();
        myPanel = thePanel;
        myGroup = new Group();

        setup(theFrame);
    }

    /**
     * Helps build the menu bar for this GUI.
     *
     * @param theFrame the containing JFrame of this menu bar
     */
    private void setup(final JFrame theFrame) {
        add(buildFileMenu(theFrame));
        add(buildOptionsMenu());
        //add(myToolsMenu);
        add(buildHelpMenu());
    }

    /**
     * Shows signup dialog box to collect name & email, part of file menu items.
     *
     * @param frame the frame (might not be needed)/////////////////////////////////////////
     */
    private void showSignup(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("First Name:", SwingConstants.RIGHT));
        labels.add(new JLabel("Email:", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));

        JTextField userName = new JTextField();
        JTextField userEmail = new JTextField();
        controls.add(userName);
        controls.add(userEmail);

        p.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(frame, p, "Signup", JOptionPane.QUESTION_MESSAGE);

        myGroup.signUp(userName.getText(), userEmail.getText());
    }
    
    /**
     * Shows login dialog box to retrieve name & email, part of file menu items.
     *
     * @param frame the frame (might not be needed)/////////////////////////////////////////
     */
    private void showLogin(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("First Name:", SwingConstants.RIGHT));
        labels.add(new JLabel("Email:", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));

        JTextField userName = new JTextField();
        JTextField userEmail = new JTextField();
        controls.add(userName);
        controls.add(userEmail);

        p.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(frame, p, "Login", JOptionPane.QUESTION_MESSAGE);

        myGroup.login(userName.getText(), userEmail.getText());
        
        if (myGroup.getUser(userName.getText(), userEmail.getText()) == null) {
        	System.out.printf("User %s does not exist with email %s.\n", 
        					  userName.getText(), userEmail.getText());
        } else {
        	System.out.printf("User %s logged in succesfully.\n", 
					  		  userName.getText(), userEmail.getText());
        }
    }

    /**
     * Builds/returns file menu.
     *
     * @param theFrame the containing JFrame of this menu bar
     * @return a "file" menu with some menu items
     */
    private JMenu buildFileMenu(final JFrame theFrame) {
        final JMenu fileMenu = new JMenu("Account");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        final JMenuItem signupItem = new JMenuItem("Signup");
        signupItem.setMnemonic(KeyEvent.VK_S);
        signupItem.addActionListener(new ActionListener() {

            /**
             * SignUp menuItem event.
             *
             * @param theEvent signUp menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                showSignup(theFrame);
            }
        });

        fileMenu.add(signupItem);

        fileMenu.addSeparator();

        final JMenuItem loginItem = new JMenuItem("Login");
        loginItem.setMnemonic(KeyEvent.VK_L);
        loginItem.addActionListener(new ActionListener() {

            /**
             * Login menuItem event.
             *
             * @param theEvent login menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                showLogin(theFrame);
            }
        });

        fileMenu.add(loginItem);

        final JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.setMnemonic(KeyEvent.VK_O);
        logoutItem.addActionListener(new ActionListener() {

            /**
             * Logout menuItem event.
             *
             * @param theEvent logout menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	String name = myGroup.getCurrentUserName();
                myGroup.logout();
                System.out.printf("User %s logged out, goodbye.\n", name);
            }
        });

        fileMenu.add(logoutItem);

        fileMenu.addSeparator();

        final JMenuItem exitItem = new JMenuItem("Quit");
        exitItem.setMnemonic(KeyEvent.VK_Q);
        exitItem.addActionListener(new ActionListener() {

            /**
             * Closes window upon quit menuItem event.
             *
             * @param theEvent quit menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING));
            }
        });

        fileMenu.add(exitItem);
        return fileMenu;
    }

    /**
     * Builds/returns options menu.
     *
     * @return an "options" menu with some menu items
     */
    private JMenu buildOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        
        final JMenuItem imp = new JMenuItem("Import Group");
        imp.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myGroup.importGroup();
            }
        });
        
        final JMenuItem exp = new JMenuItem("Export Group");
        exp.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myGroup.exportGroup();
            }
        });
        
        optionsMenu.add(imp);
        optionsMenu.add(exp);

        return optionsMenu;
    }

    /**
     * Returns/builds help menu.
     *
     * @return a "help" menu with an "About..." menu item
     */
    private JMenu buildHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        final JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(new ActionListener() {

            /**
             * Shows a dialog screen describing program upon menu selection.
             *
             * @param theEvent menu selection of About...
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null,  ABOUT_PROGRAM, "About Energy Saver",
                                              JOptionPane.PLAIN_MESSAGE, ROLLER);
            }
        });

        helpMenu.add(aboutItem);

        return helpMenu;
    }

    /**
     * Returns String representation of menu bar.
     *
     * @return string representation of menu bar
     */
    @Override
    public String toString() {
        return "Account|Options|Help";
    }

}
