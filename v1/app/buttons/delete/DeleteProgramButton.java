package app.buttons.delete;

import javax.swing.JOptionPane;

import app.errors.ProgramDeletionException;
import app.tables.ManagementTable;

public class DeleteProgramButton extends DeleteDataButton{
    public DeleteProgramButton(ManagementTable mTable){
        super(mTable);
        this.setText("Delete Program");
        this.setVisible(false);
    }

    @Override
    protected void delete(int row, ManagementTable mTable) {
        try{
            if(mTable.getDMap().getProgram((String) mTable.getValueAt(row, 0)).getTotalStudents() > 0){
                throw new ProgramDeletionException();
            }
            mTable.getPTM().deleteData(mTable.convertRowIndexToModel(row), mTable.getDMap());
            JOptionPane.showMessageDialog(mTable, "Program successfully deleted!");
        } catch(ProgramDeletionException e){
            e.printStackTrace();
            e.startErrorWindow(mTable);
        }
        
    }
}
