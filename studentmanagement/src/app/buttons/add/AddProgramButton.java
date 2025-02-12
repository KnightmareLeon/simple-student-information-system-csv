package app.buttons.add;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import app.buttons.DataButton;
import app.errors.EmptyInputException;
import app.errors.ExistingCodeException;
import app.errors.ExistingNameException;
import app.input.ProgramInput;
import app.tables.ManagementTable;

public class AddProgramButton extends DataButton{
    public AddProgramButton(ManagementTable mTable){
        super(mTable);
        this.setText("Add Program");
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

        this.getGBC().gridx = 0; this.getGBC().gridy = 3; this.getGBC().gridwidth = 3;
        this.getGBC().fill = GridBagConstraints.HORIZONTAL;
        this.getDataFrame().add(getActionButton(), this.getGBC());
    }   
}
