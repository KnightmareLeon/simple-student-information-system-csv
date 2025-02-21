package main.app.buttons.delete;

import javax.swing.JOptionPane;

import main.app.tables.ManagementTable;

public class DeleteProgramButton extends DeleteDataButton{
    public DeleteProgramButton(ManagementTable mTable){
        super(mTable);
        this.setText("Delete Program");
        this.setVisible(false);
    }

    @Override
    protected void delete(int row, ManagementTable mTable) {
        boolean confirm = true;
        int stdSize = mTable.getDMap().getProgram((String) mTable.getValueAt(row, 0)).getTotalStudents();
        if(stdSize > 0){
            confirm = (JOptionPane.showConfirmDialog(
                        mTable, 
                        "There are " + stdSize + " students in this program. Proceeding " +
                        "to delete this program will remove all these students. Do you want to proceed?", 
                  "Program Deletion Confirmation", 
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) ? true : false;
        } 
        if(confirm){
            mTable.getPTM().deleteData(mTable.convertRowIndexToModel(row), mTable.getDMap());
            JOptionPane.showMessageDialog(mTable, "Program successfully deleted!");
        } 
    }
}
