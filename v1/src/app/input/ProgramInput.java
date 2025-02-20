package app.input;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.errors.EmptyInputException;
import app.errors.NoRowSelectedException;
import app.frames.DataFrame;
import app.input.fields.AutoCompletingComboBox;
import app.input.fields.UpperCaseOnlyTextField;
import app.tables.ManagementTable;
import data.maps.DataMap;

public class ProgramInput extends DataInput{
    //Input Labels
    private JLabel codeLabel = new JLabel("Code:");
    private JLabel nameLabel = new JLabel("Name:");
    private JLabel cCodeLabel = new JLabel("College Code:");
    
    //Input Fields
    private JTextField codeField = new UpperCaseOnlyTextField();
    private JTextField nameField = new UpperCaseOnlyTextField();
    private JComboBox<String> cCodeList;

    //Constructor for Adding Data
    public ProgramInput(DataFrame dFrame, DataMap dMap, GridBagConstraints frameGBC){
        this.setUpComponents(dFrame, dMap, frameGBC);
    }
    
    //Constructor for Editing Data
    public ProgramInput(DataFrame dFrame, ManagementTable mTable, GridBagConstraints frameGBC) throws NoRowSelectedException{
        this.setUpComponents(dFrame, mTable.getDMap(), frameGBC);
        this.getData(mTable);
    }

    @Override
    protected void setUpComponents(DataFrame dFrame, DataMap dMap, GridBagConstraints frameGBC){
        
        cCodeList = new AutoCompletingComboBox(dMap.getCollegeList());
        frameGBC.gridx = frameGBC.gridy = 0; dFrame.add(codeLabel,frameGBC);
        frameGBC.gridy = 1; dFrame.add(nameLabel,frameGBC);        
        frameGBC.gridy = 2; dFrame.add(cCodeLabel,frameGBC);
        frameGBC.gridx = 1; frameGBC.gridy = 0; frameGBC.gridwidth = 2; frameGBC.fill = GridBagConstraints.HORIZONTAL; dFrame.add(codeField,frameGBC);
        frameGBC.gridy = 1; dFrame.add(nameField, frameGBC);
        frameGBC.gridy = 2; dFrame.add(cCodeList, frameGBC);
    }

    @Override
    protected void getData(ManagementTable mTable) throws NoRowSelectedException{
        int row = mTable.getSelectedRow();
        if(row == -1){throw new NoRowSelectedException();}
        String prevCode = (String) mTable.getValueAt(row, 0);
        String prevName = (String) mTable.getValueAt(row, 1);
        String prevCCode = (String) mTable.getValueAt(row, 2);

        codeField.setText(prevCode);
        nameField.setText(prevName);
        cCodeList.setSelectedItem(prevCCode);
    }

    public String getCode() throws EmptyInputException{
        if(codeField.getText().isEmpty()){
            throw new EmptyInputException();
        }
        return codeField.getText();
    }
    public String getName() throws EmptyInputException{
        if(nameField.getText().isEmpty()){
            throw new EmptyInputException();
        }
        return nameField.getText();
    }
    public String getCCode(){ return (String) cCodeList.getSelectedItem();}

}
