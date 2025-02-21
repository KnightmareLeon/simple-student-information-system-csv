package app.buttons.delete;

import javax.swing.JOptionPane;

import app.tables.ManagementTable;

public class DeleteStudentButton extends DeleteDataButton{

    public DeleteStudentButton(ManagementTable mTable) {
        super(mTable);
        this.setText("Delete Student");
    }

    @Override
    protected void delete(int row, ManagementTable mTable) {
        mTable.getSTM().deleteData(mTable.convertRowIndexToModel(row), mTable.getDMap());
        JOptionPane.showMessageDialog(mTable, "Student successfully deleted!");
    }

}
