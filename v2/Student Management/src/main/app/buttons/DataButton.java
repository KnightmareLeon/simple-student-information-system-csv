package main.app.buttons;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.app.errors.NoRowSelectedException;
import main.app.frames.DataFrame;
import main.app.tables.ManagementTable;

/**
 * Abstract {@code JButton} class that sets up the components
 * needed to get input for adding or editing data. Initializes
 * a {@link main.app.frames.DataFrame DataFrame} in which will
 * be set up in.
 */
public abstract class DataButton extends JButton{
    private DataFrame dFrame;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JButton actionButton;
    private String actionText;
    private String data;

    protected final int WIDTH = 135;
    protected final int HEIGHT = 30;
    protected final String ICON_FILE_DIRECTORY = "Student Management/src/resources/icons/";
    
    public DataButton(ManagementTable mTable){
        
        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setUp(mTable);
            }
        });
        
    }

    /**
     * Initializes the {@link main.app.frames.DataFrame DataFrame}
     * and components.
     * @param mTable
     */
    private void setUp(ManagementTable mTable){
        this.dFrame = new DataFrame(mTable.getCTM(), mTable.getDMap());
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.dFrame.setLayout(gbl);
        this.actionButton = new JButton();
        try {
            this.setUpComponents(mTable);
            this.dFrame.pack();
            this.dFrame.setLocationRelativeTo(null);
            this.dFrame.setVisible(true);
        } catch (NoRowSelectedException e) {
            e.startErrorWindow(mTable);
            e.printStackTrace();
        }
    }

    /**
     * Sets up components for handling data.
     * @param mTable - this app's custom {@code JTable} that also includes {@code DataMap}
     * @throws NoRowSelectedException when user doesn't select a row in the 
     * {@code ManagementTable}. (NOTE: Only {@link main.app.buttons.edit.EditDataButton
     * EditDataButtons} will throw this.)
     */
    protected abstract void setUpComponents(ManagementTable mTable) throws NoRowSelectedException;

    /**
     * Gets the {@link main.app.frames.DataFrame DataFrame} that will be initialized.
     * @return {@code DataFrame}
     */
    protected DataFrame getDataFrame(){return this.dFrame;}

    /**
     * Gets the {@link java.awt.GridBagConstraints GridBagConstraints} used for
     * setting up the components in the {@link main.app.frames.DataFrame 
     * DataFrame}.
     * @return {@code GridBagConstraints}
     */
    protected GridBagConstraints getGBC(){return this.gbc;}

    /**
     * Gets the JButton that will do the action of handling data.
     * @return {@code JButton}
     */
    protected JButton getActionButton(){return this.actionButton;};

    /**
     * Sets what kind of action that the {@code DataButton} will do
     * @param actionText - {@code String} value that is either
     * <b>Add</b> or <b>Edit</b>
     */
    protected void setActionText(String actionText){this.actionText = actionText;}

    /**
     * Sets what kind of data that is being handled.
     * @param data - {@code String} value that is either 
     * <b>Student</b>, <b>Program</b>, or <b>College</b>
     */
    protected void setDataText(String data){this.data = data;}

    /**
     * Gets both the action and data text
     * @return {@link #actionText} and {@link #data}
     */
    protected String getActionDataText(){return actionText + data;}
}
