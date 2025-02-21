package app.buttons.edit;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import app.buttons.DataButton;
import app.errors.EmptyInputException;
import app.errors.ExistingCodeException;
import app.errors.ExistingNameException;
import app.errors.NoRowSelectedException;
import app.input.CollegeInput;
import app.tables.ManagementTable;

public class EditCollegeButton extends DataButton{
    public EditCollegeButton(ManagementTable mTable) {
            super(mTable);
            this.setText("Edit College");
            this.setVisible(false);
    }

    @Override
    protected void setUpComponents(ManagementTable mTable) throws NoRowSelectedException {
        this.getDataFrame().setTitle("Edit College");

        CollegeInput clgInput = new CollegeInput(this.getDataFrame(), mTable, this.getGBC());

        this.getActionButton().setText("Edit College");
        this.getActionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    int row = mTable.getSelectedRow();
                    if(mTable.getDMap().hasCollegeCode(clgInput.getCode())
                    && !mTable.getValueAt(row, 0).equals(clgInput.getCode())){
                        throw new ExistingCodeException();
                    } else if (mTable.getDMap().hasCollegeName(clgInput.getName())
                        && !mTable.getValueAt(row, 1).equals(clgInput.getName())){
                        throw new ExistingNameException();
                    } 
                    String[] data = {
                        clgInput.getCode(),
                        clgInput.getName()
                    };
                    mTable.getCTM().editData(mTable.convertRowIndexToModel(row), data, mTable.getDMap());
                    JOptionPane.showMessageDialog(getActionButton(), "College edited successfully!");
                    getDataFrame().dispose();
                } catch(EmptyInputException e){
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                } catch(ExistingCodeException e){
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                } catch(ExistingNameException e){
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                }
                
            }
        });

        //Adding Components

        this.getGBC().gridx = 0; this.getGBC().gridy = 2; this.getGBC().gridwidth = 3; 
        this.getGBC().fill = GridBagConstraints.HORIZONTAL;
        this.getDataFrame().add(this.getActionButton(),this.getGBC());
    }
}
