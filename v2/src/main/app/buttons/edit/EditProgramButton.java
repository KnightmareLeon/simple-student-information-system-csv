package main.app.buttons.edit;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.app.errors.EmptyInputException;
import main.app.errors.ExistingCodeException;
import main.app.errors.ExistingNameException;
import main.app.errors.NoRowSelectedException;
import main.app.input.ProgramInput;
import main.app.tables.ManagementTable;

/**
 * {@code JButton} class that sets up all components needed to edit a
 * {@link main.data.dataClass.Program Program}'s data. The action button that 
 * it sets up will send the new data to the {@link main.data.maps.DataMap 
 * DataMap} and {@link main.app.tables.ManagementTable ManagementTable} when 
 * clicked.
 * @see ProgramInput {@code ProgramInput}
 */
public class EditProgramButton extends EditDataButton{
    public EditProgramButton(ManagementTable mTable) {
            super(mTable);
            this.setDataText("Program");
            this.setText(this.getActionDataText());
            this.setVisible(false);
    }

    @Override
    protected void setUpComponents(ManagementTable mTable) throws NoRowSelectedException {
        this.getDataFrame().setTitle("Edit Program");

        ProgramInput prgInput = new ProgramInput(this.getDataFrame(), mTable, this.getGBC());

        //Edit Button
        this.getActionButton().setText("Edit Program");
        this.getActionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int row = mTable.getSelectedRow();
                    if(mTable.getDMap().hasProgramCode(prgInput.getCode())
                        && !mTable.getValueAt(row, 0).equals(prgInput.getCode())){
                        throw new ExistingCodeException();
                    } else if(mTable.getDMap().hasProgramName(prgInput.getName())
                        && !(mTable.getValueAt(row, 1)).equals(prgInput.getName())){
                        throw new ExistingNameException();
                    }
                    String[] data = {
                        prgInput.getCode(),
                        prgInput.getName(),
                        prgInput.getCCode()   
                    };

                    mTable.getPTM().editData(mTable.convertRowIndexToModel(row), data, mTable.getDMap());
                    JOptionPane.showMessageDialog(getActionButton(), "Program edited successfully!");
                    getDataFrame().dispose();
            
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
        this.getDataFrame().add(this.getActionButton(), this.getGBC());
    }
}
