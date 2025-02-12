package app.buttons.changeTable;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import app.buttons.DataButton;
import app.buttons.delete.DeleteDataButton;
import app.tables.ManagementTable;
import app.tables.tableModels.CSVHandlingTableModel;

public abstract class ChangeToTableButton extends JButton{
    public void changeToTable(JScrollPane sp, CSVHandlingTableModel tm, ManagementTable mTable, 
        DataButton[] aDButtons, DeleteDataButton[] dDButtons, DataButton[] eDButtons){
        mTable.setModel(tm);
        mTable.getRowSorter().setModel(tm);
        mTable.firstSort();
        sp.setViewportView(mTable);

        aDButtons[0].setVisible(true);
        aDButtons[1].setVisible(false);
        aDButtons[2].setVisible(false);

        dDButtons[0].setVisible(true);
        dDButtons[1].setVisible(false);
        dDButtons[2].setVisible(false);
        
        eDButtons[0].setVisible(true);
        eDButtons[1].setVisible(false);
        eDButtons[2].setVisible(false);
    }
}
