package main.app.buttons.delete;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.app.errors.NoRowSelectedException;
import main.app.tables.ManagementTable;

/**
 * Abstract {@link JButton} that will delete the data type that will be handled by its child
 * classes: {@link DeleteStudentButton}, {@link DeleteProgramButton}, and
 * {@link DeleteCollegeButton}. Sets the icon downloaded from 
 * https://www.svgrepo.com/svg/78105/subtract.
 */
public abstract class DeleteDataButton extends JButton {
    private final int WIDTH = 135;
    private final int HEIGHT = 30;
    private ImageIcon thrashCan = new ImageIcon("Student Management/src/resources/icons/subtract-svgrepo-com.png");
    private Image thrashCanImg = thrashCan.getImage();
    private Image scaledImg = thrashCanImg.getScaledInstance((int)(WIDTH * 0.13),(int)(HEIGHT * 0.55), Image.SCALE_SMOOTH);
    public DeleteDataButton(ManagementTable mTable){
        this.setPreferredSize(new Dimension(this.WIDTH,this.HEIGHT));
        this.setIcon(new ImageIcon(scaledImg));
        this.setBackground(new Color(139,0,0));
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                int row = mTable.getSelectedRow();
                try{
                    if(row == -1){throw new NoRowSelectedException();}
                    int confirm = JOptionPane.showConfirmDialog(
                        mTable,
                        "Please confirm if you want to delete this.",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION){
                        delete(row, mTable);
                    }
                   
                } catch(NoRowSelectedException e){
                    e.printStackTrace();
                    e.startErrorWindow(mTable);
                }
            }
        });

    }

    protected abstract void delete(int row, ManagementTable mTable);
}
