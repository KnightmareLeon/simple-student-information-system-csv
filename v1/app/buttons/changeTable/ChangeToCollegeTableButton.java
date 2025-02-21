package app.buttons.changeTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import app.buttons.DataButton;
import app.buttons.delete.DeleteDataButton;
import app.tables.ManagementTable;

public class ChangeToCollegeTableButton extends ChangeToTableButton{
    public ChangeToCollegeTableButton(JScrollPane sp, ManagementTable mTable,
        DataButton[] aDButtons, DeleteDataButton[] dDButtons, DataButton[] eDButtons){
        this.setText("College Table");
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                changeToTable(sp, mTable.getCTM(), mTable, aDButtons, dDButtons, eDButtons);
            }
        });
    }
}
