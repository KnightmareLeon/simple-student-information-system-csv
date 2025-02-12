package app.tables.tableModels;

import data.maps.DataMap;

public class ProgramTableModel extends CSVHandlingTableModel implements OtherTableModelEditor{
    private StudentTableModel stm;

    public ProgramTableModel(DataMap dMap){
        this.setColumnCount(3);
        this.setFileName("src/data/csv/programs.csv");
        this.setColumnIdentifiers(new String[]{
            "Code",
            "Name",
            "College Code"});
        this.getData(getFileName(),dMap);
    }

    @Override
    public String reformatData(String[] data){return "\n" + data[0] + "," + data[1] + "," + data[2];}

    @Override
    public void addToMap(String[] data, DataMap dMap) {dMap.addProgram(data);}

    @Override
    public void deleteFromMap(String code, DataMap dMap){dMap.removeProgram(code);}

    @Override
    public void editDataOnMap(String prevCode, String[] newData, DataMap dMap) {
        dMap.editProgram(prevCode, newData);
        if(!prevCode.equals(newData[0])){this.editOtherTableModel(prevCode, newData[0]);}
    }

    @Override
    public void editOtherTableModel(String prevData, String newData){
        for(int row = 0; row < this.stm.getRowCount(); row++){
            if(((String)this.stm.getValueAt(row, 5)).equals(prevData)){
                this.stm.setValueAt(newData, row, 5);
            }
        }
    }

    public void setSTM(StudentTableModel stm){this.stm = stm;}
}
