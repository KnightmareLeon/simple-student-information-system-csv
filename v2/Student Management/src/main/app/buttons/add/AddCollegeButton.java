package main.app.buttons.add;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.app.errors.EmptyInputException;
import main.app.errors.ExistingCodeException;
import main.app.errors.ExistingNameException;
import main.app.input.CollegeInput;
import main.app.tables.ManagementTable;

/**
 * {@code JButton} class that sets up all components needed to add a new
 * {@link main.data.dataClass.College College}'s data. The action button that 
 * it sets up will send the new data to the {@link main.data.maps.DataMap 
 * DataMap} and {@link main.app.tables.ManagementTable ManagementTable} when 
 * clicked.
 * @see CollegeInput {@code CollegeInput}
 */
public class AddCollegeButton extends AddDataButton{
    public AddCollegeButton(ManagementTable mTable){
        super(mTable);
        this.setDataText("College");
        this.setText(this.getActionDataText());
        this.setVisible(false);
    }
    @Override
    protected void setUpComponents(ManagementTable mTable){
        this.getDataFrame().setTitle("Add College");

        CollegeInput clgInput = new CollegeInput(this.getDataFrame(), mTable.getDMap(), this.getGBC());

        //Add Button
        this.getActionButton().setText("Add College");
        this.getActionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    if(mTable.getDMap().hasCollegeCode(clgInput.getCode())){
                        throw new ExistingCodeException();
                    } else if (mTable.getDMap().hasCollegeName(clgInput.getName())){
                        throw new ExistingNameException();
                    }
                    String[] data = {
                        clgInput.getCode(),
                        clgInput.getName()
                    };
                    mTable.getCTM().addData(data, mTable.getDMap());
                    JOptionPane.showMessageDialog(getActionButton(), "College added successfully!");
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
