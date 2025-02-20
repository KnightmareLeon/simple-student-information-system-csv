package app.buttons.edit;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import app.buttons.DataButton;
import app.errors.EmptyInputException;
import app.errors.ExistingIDException;
import app.errors.ExistingNameException;
import app.errors.NoRowSelectedException;
import app.errors.NullPointerExceptionWithWindow;
import app.input.StudentInput;
import app.tables.ManagementTable;

public class EditStudentButton extends DataButton{

    public EditStudentButton(ManagementTable mTable) {
            super(mTable);
            this.setText("Edit Student");
    }
    
    @Override
    protected void setUpComponents(ManagementTable mTable) throws NoRowSelectedException {
        this.getDataFrame().setTitle("Edit Student");

        StudentInput stdInput = new StudentInput(this.getDataFrame(), mTable, this.getGBC());
        
        this.getActionButton().setText("Edit Student");
        this.getActionButton().addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    int row = mTable.getSelectedRow();
                    if(mTable.getDMap().hasID(stdInput.getID()) 
                        && !mTable.getValueAt(row, 0).equals(stdInput.getID())){
                        throw new ExistingIDException();
                    } else if(mTable.getDMap().hasStudentName(stdInput.getFN() + " " + stdInput.getLN()) 
                        && !(mTable.getValueAt(row, 1) + " " + mTable.getValueAt(row, 2)).equals(
                        stdInput.getFN() + " " + stdInput.getLN())){
                        throw new ExistingNameException();
                    }
                    String[] data = {
                        stdInput.getID(),
                        stdInput.getFN(),
                        stdInput.getLN(),
                        stdInput.getYL(),
                        stdInput.getG(),
                        stdInput.getPC()};

                    mTable.getSTM().editData(mTable.convertRowIndexToModel(row), data, mTable.getDMap());
                    JOptionPane.showMessageDialog(getActionButton(), "Student edited successfully!");
                    getDataFrame().dispose();
                } catch(EmptyInputException e) {
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                } catch(NullPointerExceptionWithWindow e) {
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                } catch(ExistingIDException e) {
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                } catch(ExistingNameException e){
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                }
                
            }
        });

        this.getGBC().gridy = 1; this.getGBC().fill = GridBagConstraints.HORIZONTAL;
        this.getDataFrame().add(this.getActionButton(),this.getGBC());
    }

}
