package app.buttons;

import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import app.buttons.add.*;
import app.buttons.changeTable.*;
import app.buttons.delete.*;
import app.buttons.edit.*;
import app.tables.ManagementTable;

public class ManagementToolbar extends JToolBar{

    private SaveButton saveButton;

    private DataButton addStdButton;
    private DataButton addPrgButton;
    private DataButton addClgButton;

    private DeleteDataButton delStdButton;
    private DeleteDataButton delPrgButton;
    private DeleteDataButton delClgButton;

    private DataButton editStdButton;
    private DataButton editPrgButton;
    private DataButton editClgButton;

    private ChangeToTableButton cStdTblButton;
    private ChangeToTableButton cPrgTblButton;
    private ChangeToTableButton cClgTblButton;

    public ManagementToolbar(JScrollPane sp, ManagementTable mTable){
        this.saveButton = new SaveButton(mTable);

        this.addStdButton = new AddStudentButton(mTable);
        this.addPrgButton = new AddProgramButton(mTable);
        this.addClgButton = new AddCollegeButton(mTable);
        
        this.delStdButton = new DeleteStudentButton(mTable);
        this.delPrgButton = new DeleteProgramButton(mTable);
        this.delClgButton = new DeleteCollegeButton(mTable);

        this.editStdButton = new EditStudentButton(mTable);
        this.editPrgButton = new EditProgramButton(mTable);
        this.editClgButton = new EditCollegeButton(mTable);

        this.cStdTblButton = new ChangeToStudentTableButton(sp, mTable, 
            new DataButton[]{addStdButton, addPrgButton, addClgButton}, 
            new DeleteDataButton[]{delStdButton, delPrgButton, delClgButton},
            new DataButton[]{editStdButton, editPrgButton, editClgButton});
        this.cPrgTblButton = new ChangeToProgramTableButton(sp, mTable,
            new DataButton[]{addPrgButton, addStdButton, addClgButton}, 
            new DeleteDataButton[]{delPrgButton, delStdButton, delClgButton},
            new DataButton[]{editPrgButton, editStdButton, editClgButton});
        this.cClgTblButton = new ChangeToCollegeTableButton(sp, mTable, 
            new DataButton[]{addClgButton, addStdButton, addPrgButton},
            new DeleteDataButton[]{delClgButton, delPrgButton, delStdButton},
            new DataButton[]{editClgButton, editStdButton, editPrgButton});
        
        //Adding Buttons
        this.add(saveButton);

        this.add(cStdTblButton);
        this.add(cPrgTblButton);
        this.add(cClgTblButton);

        this.add(addStdButton);
        this.add(addPrgButton);
        this.add(addClgButton);

        this.add(delStdButton);
        this.add(delPrgButton);
        this.add(delClgButton);

        this.add(editStdButton);
        this.add(editPrgButton);
        this.add(editClgButton);
    }

}
