package app.buttons.changeTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import app.buttons.DataButton;
import app.buttons.delete.DeleteDataButton;
import app.tables.ManagementTable;

public class ChangeToProgramTableButton extends ChangeToTableButton{
    public ChangeToProgramTableButton(JScrollPane sp, ManagementTable mTable,
        DataButton[] aDButtons, DeleteDataButton[] dDButtons,  DataButton[] eDButtons){
        this.setText("Program Table");
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                changeToTable(sp, mTable.getPTM(), mTable, aDButtons, dDButtons, eDButtons);
            }
        });
    }
}