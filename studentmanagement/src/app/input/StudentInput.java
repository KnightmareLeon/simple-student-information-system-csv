package app.input;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.Year;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import app.errors.EmptyInputException;
import app.errors.NoRowSelectedException;
import app.errors.NullPointerExceptionWithWindow;
import app.frames.DataFrame;
import app.input.fields.AutoCompletingComboBox;
import app.input.fields.UpperCaseOnlyTextField;
import app.tables.ManagementTable;
import data.maps.DataMap;

public class StudentInput extends DataInput{

    private JPanel inputPanel = new JPanel();
    private GridBagLayout panelGBL = new GridBagLayout();
    private GridBagConstraints panelGBC = new GridBagConstraints();

    //Input Labels
    private JLabel idLabel = new JLabel("ID:");
    private JLabel fnLabel = new JLabel("First Name:"); //First Name
    private JLabel lnLabel = new JLabel("Last Name:"); //Last Name
    private JLabel ylLabel = new JLabel("Year Level:"); //Year Level
    private JLabel gLabel = new JLabel("Gender:");  //Gender
    private JLabel pcLabel = new JLabel("Program Code:"); //Program Code

    //Input Lists for ID
    private JComboBox<String> yearList;
    private JComboBox<String> idNumList;
    private JPanel idPanel = new JPanel();

    //Input Text Fields for First Name and Last Name
    private JTextField fnField = new UpperCaseOnlyTextField();
    private JTextField lnField = new UpperCaseOnlyTextField();

    //Input Buttons for Year Level
    private JPanel ylPanel = new JPanel();
    private ButtonGroup ylList = new ButtonGroup();
    private JRadioButton yOneButton = new JRadioButton("1");
    private JRadioButton yTwoButton = new JRadioButton("2");
    private JRadioButton yThreeButton = new JRadioButton("3");
    private JRadioButton yFourButton = new JRadioButton("4");
    private JRadioButton yFiveButton = new JRadioButton("5");
    private JRadioButton yFivePlusButton = new JRadioButton("5+");

    //Input Buttons for Gender
    private ButtonGroup gList = new ButtonGroup();
    private JPanel gPanel = new JPanel();
    private JRadioButton maleButton = new JRadioButton("MALE");
    private JRadioButton femaleButton = new JRadioButton("FEMALE");
    private JRadioButton othersButton = new JRadioButton("OTHERS");

    //Input List for Program Code
    private JComboBox<String> pcList;

    //Constructor for Adding Data
    public StudentInput(DataFrame dFrame, DataMap dMap, GridBagConstraints frameGBC){
        this.setUpComponents(dFrame, dMap, frameGBC);
    }

    //Constructor for Editing Data
    public StudentInput(DataFrame dFrame, ManagementTable mTable, GridBagConstraints frameGBC) throws NoRowSelectedException{
        this.setUpComponents(dFrame, mTable.getDMap(), frameGBC);
        this.getData(mTable);
    }

    @Override
    protected void setUpComponents(DataFrame dFrame, DataMap dMap, GridBagConstraints frameGBC){
        inputPanel.setLayout(panelGBL);

        //Setting up Input Lists for ID
        int currentYear = Year.now().getValue();
        int yearLimit = currentYear - 1966 + 1;
        String[] years = new String[yearLimit - 1];
        String[] idNum = new String[9999];
        for(int i = 1; i < 10000; i++){
            if (i < yearLimit){
                years[i - 1] = String.valueOf(1966 + i);
            }
            idNum[i - 1] = String.format("%04d",i);
        }

        yearList = new AutoCompletingComboBox(years);
        idNumList = new AutoCompletingComboBox(idNum);
        yearList.setSelectedItem(Integer.toString(currentYear));

        idPanel.setLayout(new GridLayout(1,2));
        idPanel.add(yearList);
        idPanel.add(idNumList);

        //Setting up Year Level Buttons
        ylPanel.setLayout(new GridLayout(1,6));
        ylList.add(yOneButton); ylPanel.add(yOneButton); yOneButton.setActionCommand(yOneButton.getText());
        ylList.add(yTwoButton); ylPanel.add(yTwoButton); yTwoButton.setActionCommand(yTwoButton.getText());
        ylList.add(yThreeButton); ylPanel.add(yThreeButton); yThreeButton.setActionCommand(yThreeButton.getText());
        ylList.add(yFourButton); ylPanel.add(yFourButton); yFourButton.setActionCommand(yFourButton.getText());
        ylList.add(yFiveButton); ylPanel.add(yFiveButton); yFiveButton.setActionCommand(yFiveButton.getText());
        ylList.add(yFivePlusButton); ylPanel.add(yFivePlusButton); yFivePlusButton.setActionCommand(yFivePlusButton.getText());

        //Setting up Gender Buttons
        gPanel.setLayout(new GridLayout(1,3));
        gList.add(maleButton); gPanel.add(maleButton); maleButton.setActionCommand(maleButton.getText());
        gList.add(femaleButton); gPanel.add(femaleButton); femaleButton.setActionCommand(femaleButton.getText());
        gList.add(othersButton); gPanel.add(othersButton); othersButton.setActionCommand(othersButton.getText());
        
        //Setting up Input Program List
        String[] prgList = dMap.getProgramList();
        pcList = new AutoCompletingComboBox(prgList);

        //Adding Componets
        panelGBC.insets = new Insets(3, 3, 3, 3);
        panelGBC.ipady = 10;
        panelGBC.gridx = panelGBC.gridy = 0; panelGBC.fill = GridBagConstraints.HORIZONTAL; inputPanel.add(idLabel, panelGBC);
        panelGBC.gridy = 1; inputPanel.add(fnLabel, panelGBC);
        panelGBC.gridy = 2; inputPanel.add(lnLabel, panelGBC);
        panelGBC.gridy = 3; inputPanel.add(ylLabel, panelGBC);
        panelGBC.gridy = 4; inputPanel.add(gLabel, panelGBC);
        panelGBC.gridy = 5; inputPanel.add(pcLabel, panelGBC);
        panelGBC.gridx = 1; panelGBC.gridy = 0; panelGBC.fill = GridBagConstraints.NONE; panelGBC.anchor = GridBagConstraints.FIRST_LINE_START ;inputPanel.add(idPanel, panelGBC);
        panelGBC.gridy = 1; inputPanel.add(fnField, panelGBC);
        panelGBC.gridy = 2; inputPanel.add(lnField, panelGBC);
        panelGBC.gridy = 3; inputPanel.add(ylPanel, panelGBC);
        panelGBC.gridy = 4; inputPanel.add(gPanel, panelGBC);
        panelGBC.gridy = 5; inputPanel.add(pcList, panelGBC);
        
        frameGBC.gridx = frameGBC.gridy = 0;
        dFrame.add(inputPanel, frameGBC);
    }
    
    @Override
    protected void getData(ManagementTable mTable) throws NoRowSelectedException{
        int row = mTable.getSelectedRow();
        if(row == -1){throw new NoRowSelectedException();}
        String prevID = (String) mTable.getValueAt(row, 0);
        String prevFN = (String) mTable.getValueAt(row, 1);
        String prevLN = (String) mTable.getValueAt(row, 2);
        String prevYL = (String) mTable.getValueAt(row, 3);
        String prevG = (String) mTable.getValueAt(row, 4);
        String prevPC = (String) mTable.getValueAt(row, 5);
        this.yearList.setSelectedItem(prevID.substring(0,4));
        this.idNumList.setSelectedItem(prevID.substring(5,9));
        this.fnField.setText(prevFN);
        if(prevYL.equals("1")){
            this.ylList.setSelected(yOneButton.getModel(), true);
        } else if(prevYL.equals("2")){
            this.ylList.setSelected(yTwoButton.getModel(), true);
        } else if(prevYL.equals("3")){
            this.ylList.setSelected(yThreeButton.getModel(), true);
        } else if(prevYL.equals("4")){
            this.ylList.setSelected(yFourButton.getModel(), true);
        } else if(prevYL.equals("5")){
            this.ylList.setSelected(yFiveButton.getModel(), true);
        } else if(prevYL.equals("5+")){
            this.ylList.setSelected(yFivePlusButton.getModel(), true);
        }
        if(prevG.equals("MALE")){
            this.gList.setSelected(maleButton.getModel(), true);
        } else if(prevG.equals("FEMALE")){
            this.gList.setSelected(femaleButton.getModel(), true);
        } else if(prevYL.equals("OTHERS")){
            this.gList.setSelected(othersButton.getModel(), true);
        }
        
        this.lnField.setText(prevLN);
        this.pcList.setSelectedItem(prevPC);
    }

    public String getID(){return yearList.getSelectedItem() + "-" + idNumList.getSelectedItem();}

    public String getFN() throws EmptyInputException{
        if(fnField.getText().isEmpty()){
            throw new EmptyInputException();
        }
        return fnField.getText();
    }

    public String getLN() throws EmptyInputException{
        if(lnField.getText().isEmpty()){
            throw new EmptyInputException();
        }
        return lnField.getText();
    }

    public String getYL(){
        if(ylList.getSelection() == null){
            throw new NullPointerExceptionWithWindow();
        } else {
            return ylList.getSelection().getActionCommand();
        }
    }

    public String getG() throws NullPointerExceptionWithWindow{
        if(gList.getSelection() == null){
            throw new NullPointerExceptionWithWindow();
        } else {
            return gList.getSelection().getActionCommand();
        }        
    }

    public String getPC(){return (String) pcList.getSelectedItem();}
    
}
