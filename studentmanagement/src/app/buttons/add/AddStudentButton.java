package app.buttons.add;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import app.buttons.DataButton;
import app.errors.EmptyInputException;
import app.errors.ExistingIDException;
import app.errors.ExistingNameException;
import app.errors.NullPointerExceptionWithWindow;
import app.input.StudentInput;
import app.tables.ManagementTable;

public class AddStudentButton extends DataButton{
    public AddStudentButton(ManagementTable mTable){
        super(mTable);
        this.setText("Add Student");
        this.setVisible(true);
    }
    
    @Override
    protected void setUpComponents(ManagementTable mTable){
        this.getDataFrame().setTitle("Add Student");

        StudentInput stdInput = new StudentInput(this.getDataFrame(), mTable.getDMap(), this.getGBC());
        
        //Add Student
        this.getActionButton().setText("Add Student");
        this.getActionButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    if(mTable.getDMap().hasID(stdInput.getID())){
                        throw new ExistingIDException();
                    } else if(mTable.getDMap().hasStudentName(stdInput.getFN() + " " + stdInput.getLN())){
                        throw new ExistingNameException();
                    } else {

                        String[] data = {
                            stdInput.getID(),
                            stdInput.getFN(),
                            stdInput.getLN(),
                            stdInput.getYL(),
                            stdInput.getG(),
                            stdInput.getPC()
                        };

                        mTable.getSTM().addData(data, mTable.getDMap());
                        JOptionPane.showMessageDialog(getActionButton(), "Student added successfully!");
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
                } catch(ExistingNameException e){
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                }
                
            }
        });

        //Adding Components
        
        this.getGBC().gridy = 1; this.getGBC().fill = GridBagConstraints.HORIZONTAL;
        this.getDataFrame().add(this.getActionButton(),this.getGBC());
    }
}
