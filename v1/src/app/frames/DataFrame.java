package app.frames;

import javax.swing.JFrame;

import app.tables.tableModels.CSVHandlingTableModel;
import data.maps.DataMap;

public class DataFrame extends JFrame {
    public DataFrame(CSVHandlingTableModel tm, DataMap dMap){
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
