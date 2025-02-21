package app.frames;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import app.tables.ManagementTable;

public class SaveChecker extends WindowAdapter {
    
    private ManagementTable mTable;
    public SaveChecker(ManagementTable mTable){
        this.mTable = mTable;
    }

    @Override
    public void windowClosing(WindowEvent evt){
        boolean unsaved = mTable.getSTM().isChanged() || mTable.getPTM().isChanged() || mTable.getCTM().isChanged();
        if(unsaved){
            int option = JOptionPane.showConfirmDialog((Frame) evt.getSource(),
            "There are still unsaved changes. Would you like to save and close?",
            "Unsaved", JOptionPane.YES_NO_CANCEL_OPTION);
            
            if(option != JOptionPane.CANCEL_OPTION){
                if(option == JOptionPane.YES_OPTION){
                    if(mTable.getSTM().isChanged()){
                    mTable.getSTM().saveData();
                    }
                    if(mTable.getPTM().isChanged()){
                        mTable.getPTM().saveData();
                    }
                    if(mTable.getCTM().isChanged()){
                        mTable.getCTM().saveData();
                    }
                }        
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
}
