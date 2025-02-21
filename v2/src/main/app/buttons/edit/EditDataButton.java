package main.app.buttons.edit;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.app.buttons.DataButton;
import main.app.tables.ManagementTable;

/**
 * Abstract button that will edit the data type that will be handled by its child
 * classes: {@link EditStudentButton}, {@link EditProgramButton}, and
 * {@link EditCollegeButton}. Sets the icon downloaded from 
 * https://www.svgrepo.com/svg/42233/pencil-edit-button.
 */
public abstract class EditDataButton extends DataButton {
    private ImageIcon edit = new ImageIcon(this.ICON_FILE_DIRECTORY + "pencil-edit-button-svgrepo-com.png");
    private Image editImg = edit.getImage();
    private Image scaledImg = editImg.getScaledInstance((int)(WIDTH * 0.09),(int)(HEIGHT * 0.48), Image.SCALE_SMOOTH);
    public EditDataButton(ManagementTable mTable) {
        super(mTable);
        this.setActionText("Edit ");
        this.setIcon(new ImageIcon(scaledImg));
        this.setBackground(new Color(139, 128, 0));
    }

}
