package app.buttons.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import app.errors.NoRowSelectedException;
import app.tables.ManagementTable;

public abstract class DeleteDataButton extends JButton {
    public DeleteDataButton(ManagementTable mTable){
        this.setSize(100,10);
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                int row = mTable.getSelectedRow();
                try{
                    if(row == -1){
                        throw new NoRowSelectedException();
                    }
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
