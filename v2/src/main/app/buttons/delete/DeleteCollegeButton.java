package main.app.buttons.delete;

import javax.swing.JOptionPane;

import main.app.tables.ManagementTable;

public class DeleteCollegeButton extends DeleteDataButton{
    public DeleteCollegeButton(ManagementTable mTable){
        super(mTable);
        this.setText("Delete College");
        this.setVisible(false);
    }

    @Override
    protected void delete(int row, ManagementTable mTable) {
        boolean confirm = true;
        int prgSize = mTable.getDMap().getCollege((String) mTable.getValueAt(row, 0)).getTotalProgram();
        int stdSize = mTable.getDMap().getCollege((String) mTable.getValueAt(row, 0)).getTotalStudents();
        if(prgSize > 0){
            confirm = (JOptionPane.showConfirmDialog(
                        mTable, 
                        "There are " + prgSize + " programs in this college with a total of " +
                        stdSize + " students under them. Proceeding to delete this college will " + 
                        "remove all these programs and students. Do you want to proceed?", 
                  "College Deletion Confirmation", 
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) ? true : false;
        }

        if(confirm){
            mTable.getCTM().deleteData(mTable.convertRowIndexToModel(row), mTable.getDMap());
            JOptionPane.showMessageDialog(mTable, "College successfully deleted!");
        }
        

        /* } catch(CollegeDeletionException e) {
            e.printStackTrace();
            e.startErrorWindow(mTable);
        }  */      
    }
}
