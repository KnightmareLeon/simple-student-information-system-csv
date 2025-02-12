package app.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import app.tables.ManagementTable;

public class SaveButton extends JButton{
    public SaveButton(ManagementTable mTable){
        this.setText("Save");
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
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
            
        });
    }
}
