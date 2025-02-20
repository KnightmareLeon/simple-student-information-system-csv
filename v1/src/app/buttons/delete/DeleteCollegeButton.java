package app.buttons.delete;

import javax.swing.JOptionPane;

import app.errors.CollegeDeletionException;
import app.tables.ManagementTable;

public class DeleteCollegeButton extends DeleteDataButton{
    public DeleteCollegeButton(ManagementTable mTable){
        super(mTable);
        this.setText("Delete College");
        this.setVisible(false);
    }

    @Override
    protected void delete(int row, ManagementTable mTable) {
        try{
            if(mTable.getDMap().getCollege((String) mTable.getValueAt(row, 0)).getTotalProgram() > 0){
                throw new CollegeDeletionException();
            } 
            mTable.getCTM().deleteData(mTable.convertRowIndexToModel(row), mTable.getDMap());
            JOptionPane.showMessageDialog(mTable, "College successfully deleted!");

        } catch(CollegeDeletionException e) {
            e.printStackTrace();
            e.startErrorWindow(mTable);
        }        
    }
}
