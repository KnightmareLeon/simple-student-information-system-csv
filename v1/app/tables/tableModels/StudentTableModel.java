package app.tables.tableModels;

import data.maps.DataMap;

public class StudentTableModel extends CSVHandlingTableModel{
    public StudentTableModel(DataMap dMap){
        this.setColumnCount(6);
        this.setFileName("src/data/csv/students.csv");
        this.setColumnIdentifiers(new String[]{
            "ID",
            "First Name",
            "Last Name",
            "Year Level",
            "Gender",
            "Program Code"});
        this.getData(getFileName(),dMap);
    }

    @Override
    public String reformatData(String[] data){return "\n" + data[0] + "," + data[1] + "," + data[2] + "," +  data[3] + "," + data[4] + "," +  data[5];}

    @Override
    public void addToMap(String[] data, DataMap dMap) {dMap.addStudent(data);}

    @Override
    public void deleteFromMap(String code, DataMap dMap){dMap.removeStudent(code);}

    @Override
    public void editDataOnMap(String prevCode, String[] newData, DataMap dMap){dMap.editStudent(prevCode, newData);}
}
