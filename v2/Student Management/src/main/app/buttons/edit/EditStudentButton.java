package main.app.buttons.edit;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.app.errors.EmptyInputException;
import main.app.errors.ExistingIDException;
import main.app.errors.NoRowSelectedException;
import main.app.errors.NullPointerExceptionWithWindow;
import main.app.input.StudentInput;
import main.app.tables.ManagementTable;

/**
 * {@code JButton} class that sets up all components needed to edit a
 * {@link main.data.dataClass.Student Student}'s data. The action button that 
 * it sets up will send the new data to the {@link main.data.maps.DataMap 
 * DataMap} and {@link main.app.tables.ManagementTable ManagementTable} when 
 * clicked.
 * @see StudentInput {@code StudentInput}
 */
public class EditStudentButton extends EditDataButton{

    public EditStudentButton(ManagementTable mTable) {
        super(mTable);
        this.setDataText("Student");
        this.setText(this.getActionDataText());
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
                    boolean confirm = true;
                    if(mTable.getDMap().hasID(stdInput.getID()) 
                        && !mTable.getValueAt(row, 0).equals(stdInput.getID())){
                        throw new ExistingIDException();
                    }

                    String[] data = {
                        stdInput.getID(),
                        stdInput.getFN(),
                        stdInput.getLN(),
                        stdInput.getYL(),
                        stdInput.getG(),
                        stdInput.getPC()};
                    
                    if (mTable.getDMap().hasStudentName(stdInput.getFN() + " " + stdInput.getLN())
                        && !(mTable.getValueAt(row, 1) + " " + mTable.getValueAt(row, 2)).equals(
                        stdInput.getFN() + " " + stdInput.getLN())){
                        confirm = (JOptionPane.showConfirmDialog(
                                    getActionButton(), 
                            "Name already exists. Do you want to proceed?", 
                              "Same Name Confirmation", 
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) ? true : false;
                    }

                    if(confirm){
                        mTable.getSTM().editData(mTable.convertRowIndexToModel(row), data, mTable.getDMap());
                        JOptionPane.showMessageDialog(getActionButton(), "Student edited successfully!");
                        getDataFrame().dispose();
                    }
                   
                } catch(EmptyInputException e) {
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                } catch(NullPointerExceptionWithWindow e) {
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                } catch(ExistingIDException e) {
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                } 
            }
        });

        this.getGBC().gridy = 1; this.getGBC().fill = GridBagConstraints.HORIZONTAL;
        this.getDataFrame().add(this.getActionButton(),this.getGBC());
    }

}
