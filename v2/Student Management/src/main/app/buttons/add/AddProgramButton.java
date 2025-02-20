package main.app.buttons.add;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.app.errors.EmptyInputException;
import main.app.errors.ExistingCodeException;
import main.app.errors.ExistingNameException;
import main.app.input.ProgramInput;
import main.app.tables.ManagementTable;

/**
 * {@code JButton} class that sets up all components needed to add a new
 * {@link main.data.dataClass.Program Program}'s data. The action button that 
 * it sets up will send the new data to the {@link main.data.maps.DataMap 
 * DataMap} and {@link main.app.tables.ManagementTable ManagementTable} when 
 * clicked.
 * @see ProgramInput {@code ProgramInput}
 */
public class AddProgramButton extends AddDataButton{
    public AddProgramButton(ManagementTable mTable){
        super(mTable);
        this.setDataText("Program");
        this.setText(this.getActionDataText());
        this.setVisible(false);
    }

    @Override
    protected void setUpComponents(ManagementTable mTable){
        this.getDataFrame().setTitle("Add Program");

        ProgramInput pInput = new ProgramInput(this.getDataFrame(), mTable.getDMap(), this.getGBC());
        
        //Add Button
        this.getActionButton().setText("Add Program");
        this.getActionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if(mTable.getDMap().hasProgramCode(pInput.getCode())){
                        throw new ExistingCodeException();
                    } else if(mTable.getDMap().hasProgramName(pInput.getName())){
                        throw new ExistingNameException();
                    } else {
                        String[] data = {
                            pInput.getCode(),
                            pInput.getName(),
                            pInput.getCCode()   
                        };
                        mTable.getPTM().addData(data, mTable.getDMap());
                        JOptionPane.showMessageDialog(getActionButton(), "Program added successfully!");
                        getDataFrame().dispose();
                    }
                } catch (EmptyInputException e){
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                } catch (ExistingCodeException e){
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                } catch (ExistingNameException e){
                    e.printStackTrace();
                    e.startErrorWindow(getActionButton());
                }
            }
        });

        this.getGBC().gridx = 0; this.getGBC().gridy = 4; this.getGBC().gridwidth = 3;
        this.getGBC().fill = GridBagConstraints.HORIZONTAL;
        this.getDataFrame().add(getActionButton(), this.getGBC());
    }   
}
