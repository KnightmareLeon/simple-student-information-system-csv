package app.tables.tableModels;

import data.maps.DataMap;

public class CollegeTableModel extends CSVHandlingTableModel implements OtherTableModelEditor{
    private ProgramTableModel ptm;
    public CollegeTableModel(DataMap dMap){
        this.setColumnCount(2);
        this.setFileName("src/data/csv/colleges.csv");
        this.setColumnIdentifiers(new String[]{
            "Code",
            "Name"});
        this.getData(getFileName(),dMap);
    }

    @Override
    public String reformatData(String[] data){return "\n" + data[0] + "," + data[1];}

    @Override
    public void addToMap(String[] data, DataMap dMap){dMap.addCollege(data);}

    @Override
    public void deleteFromMap(String code, DataMap dMap){dMap.removeCollege(code);}

    @Override
    public void editDataOnMap(String prevCode, String[] newData, DataMap dMap) {
        dMap.editCollege(prevCode, newData);
        if(!prevCode.equals(newData[0])){this.editOtherTableModel(prevCode, newData[0]);}
    }

    @Override
    public void editOtherTableModel(String prevData, String newData) {
        for(int row = 0; row < this.ptm.getRowCount(); row++){
            if(((String)this.ptm.getValueAt(row, 2)).equals(prevData)){
                this.ptm.setValueAt(newData, row, 2);
            }
        }
    }

    public void setPTM(ProgramTableModel ptm){this.ptm = ptm;}
}
