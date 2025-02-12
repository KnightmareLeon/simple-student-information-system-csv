package app.tables.tableModels;

import javax.swing.table.DefaultTableModel;

import data.maps.DataMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public abstract class CSVHandlingTableModel extends DefaultTableModel{

    private boolean changed = false;
    private String fileName;

    protected void getData(String fileName, DataMap dMap){   
        try{  
            String line = "";  
            String splitBy = ",";
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader(fileName));  
            while ((line = br.readLine()) != null) {   //returns a Boolean value   
                String[] row = line.split(splitBy);    // use comma as separator
                this.addRow(row);
                this.addToMap(row, dMap);
            }  
            br.close();         
        } catch (IOException e)   {  
            e.printStackTrace();  
        }
    }

    public void addData(String[] data, DataMap dMap){
        this.addToMap(data, dMap);
        this.addRow(data);
        this.setChange(true);
    }

    public void deleteData(int row, DataMap dMap){
        this.deleteFromMap((String) this.getValueAt(row, 0), dMap);
        this.removeRow(row);
        this.setChange(true);
    }

    public void editData(int row, String[] newData, DataMap dMap){
        String prevCode = (String) this.getValueAt(row, 0);
        this.editDataOnMap(prevCode, newData, dMap);
        for(int i = 0; i < this.getColumnCount(); i++){
            this.setValueAt(newData[i], row, i);
        }
        this.setChange(true);
    }

    public void saveData(){
        try {
            Files.deleteIfExists(Paths.get(fileName));
            File file = new File(fileName);
            file.createNewFile();
            Writer csvWriter = new BufferedWriter(new FileWriter(fileName, true));
            String[] data = new String[getColumnCount()];
            for(int i = 0; i < getRowCount(); i++){
                for(int j = 0; j < getColumnCount(); j++){
                    data[j] = (String) getValueAt(i, j);
                }
                String strData = (i == 0) ? this.reformatData(data).replace("\n", "") : this.reformatData(data); 
                csvWriter.append(strData);
            }
            
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setChange(false);
    }

    protected void setFileName(String fileName){this.fileName = fileName;}
    protected String getFileName(){return this.fileName;}

    protected abstract String reformatData(String[] data);
    protected abstract void addToMap(String[] data, DataMap dMap);
    protected abstract void deleteFromMap(String code, DataMap dMap);
    protected abstract void editDataOnMap(String prevCode, String[] newData, DataMap dMap);

    @Override
    public boolean isCellEditable(int row, int column) {return false;}

    public boolean isChanged(){return changed;}

    public void setChange(boolean changed){this.changed = changed;}
}
