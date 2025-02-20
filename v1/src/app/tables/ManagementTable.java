package app.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;

import app.tables.tableModels.CSVHandlingTableModel;
import app.tables.tableModels.CollegeTableModel;
import app.tables.tableModels.ProgramTableModel;
import app.tables.tableModels.StudentTableModel;
import data.maps.DataMap;

public class ManagementTable extends JTable{
    private CollegeTableModel ctm;
    private ProgramTableModel ptm;
    private StudentTableModel stm;
    private TableRowSorter<CSVHandlingTableModel> rowSorter = new TableRowSorter<>();
    private DataMap dMap = new DataMap();
    private List<RowSorter.SortKey> sortKeys = new ArrayList<>();

    public ManagementTable(){
        this.ctm = new CollegeTableModel(dMap);
        this.ptm = new ProgramTableModel(dMap);
        this.stm = new StudentTableModel(dMap);
        this.ctm.setPTM(this.ptm);
        this.ptm.setSTM(this.stm);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setModel(stm);
        this.rowSorter.setModel(stm);
        this.setRowSorter(rowSorter);
        this.firstSort();       
    }

    public DataMap getDMap(){return dMap;}

    public CollegeTableModel getCTM(){return ctm;}
    public ProgramTableModel getPTM(){return ptm;}
    public StudentTableModel getSTM(){return stm;}

    public TableRowSorter<CSVHandlingTableModel> getRowSorter(){return this.rowSorter;}
    
    public void firstSort(){
        this.sortKeys.clear();
        this.sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        this.sortKeys.add(new RowSorter.SortKey(this.getColumnCount() - 1, SortOrder.ASCENDING));
        this.rowSorter.setSortKeys(this.sortKeys);
        this.rowSorter.sort();
    }
}
