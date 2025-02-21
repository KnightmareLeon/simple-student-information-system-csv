package main.data.maps;

import main.data.dataClass.College;
import main.data.dataClass.Program;
import main.data.dataClass.Student;

/**
 * The main map that handles all the data during the application's runtime.
 * @see StudentMap {@code StudentMap}
 * @see ProgramMap {@code ProgramMap}
 * @see CollegeMap {@code CollegeMap}
 */
public class DataMap {
    private CollegeMap cMap = new CollegeMap();
    private ProgramMap pMap = new ProgramMap();
    private StudentMap sMap = new StudentMap();

    public void addCollege(String[] data){
        //data[0] - Code
        //data[1] - Name
        this.cMap.put(data[0], new College(data[0], data[1]));
    }

    public void addProgram(String[] data){
        //data[0] - Code
        //data[1] - Name
        //data[2] - College Code
        this.pMap.put(data[0], new Program(data[0],data[1],data[2]));
        this.getCollege(data[2]).addProgram(getProgram(data[0]));
    }

    public void addStudent(String[] data){
        //data[0] - ID
        //data[1] - First Name
        //data[2] - Last Name
        //data[3] - Year Level
        //data[4] - Gender
        //data[5] - Program Code
        this.sMap.put(data[0], new Student(data[0],data[1],data[2],data[3],data[4],data[5]));
        this.getProgram(data[5]).addStudent(this.getStudent(data[0]));
    }

    public void removeCollege(String cCode){
        for(String code : this.cMap.get(cCode).getProgramList()){
            for(String ID : this.pMap.get(code).getStudentList()){this.sMap.remove(ID);}
            this.pMap.remove(code);
        }
        this.cMap.remove(cCode);
    }

    public void removeProgram(String pCode){
        this.getCollege(getProgram(pCode).getCCode()).removeProgram(getProgram(pCode));
        for(String ID : this.pMap.get(pCode).getStudentList()){this.sMap.remove(ID);}
        this.pMap.remove(pCode);
    }

    public void removeStudent(String id){
        this.getProgram(getStudent(id).getPC()).removeStudent(getStudent(id));
        this.sMap.remove(id);
    }

    public void editStudent(String prevID, String[] newData){
        String prevPC = this.getStudent(prevID).getPC();
        if(!prevID.equals(newData[0]) || !prevPC.equals(newData[5])){ //Checks if ID or Program Code was changed 
            this.removeStudent(prevID);
        }
        this.addStudent(newData);
    }

    public void editProgram(String prevCode, String[] newData){
        String prevCCode = this.getProgram(prevCode).getCCode();
        String prevName = this.getProgram(prevCode).getName();
        if(!prevCode.equals(newData[0])){
            this.addProgram(newData);
            this.getProgram(prevCode).transferStudents(this.getProgram(newData[0]));
            this.removeProgram(prevCode);
        } else if(!prevCCode.equals(newData[2])){
            this.getCollege(getProgram(prevCode).getCCode()).removeProgram(getProgram(prevCode));
            this.getProgram(prevCode).setCCode(newData[2]);
            this.getCollege(newData[2]).addProgram(this.getProgram(prevCode));
        } 
        if (!prevName.equals(newData[1])){
            this.getProgram(newData[0]).setName(newData[1]);
            this.pMap.removeName(prevName);
        }
        
    }

    public void editCollege(String prevCode, String[] newData){
        if(!prevCode.equals(newData[0])){
            this.addCollege(newData);
            this.getCollege(prevCode).transferPrograms(this.getCollege(newData[0]));
            this.removeCollege(prevCode);
        } else {
            this.cMap.removeName(this.getCollege(prevCode).getName());
            this.getCollege(prevCode).setName(newData[1]);
        }
        
    }

    public College getCollege(String cCode){return this.cMap.get(cCode);}
    public Program getProgram(String pCode){return this.pMap.get(pCode);}
    public Student getStudent(String id){return this.sMap.get(id);}

    public String[] getProgramList(){return this.pMap.keySet().toArray(new String[pMap.size()]);}
    public String[] getCollegeList(){return this.cMap.keySet().toArray(new String[cMap.size()]);}

    public boolean hasStudentName(String name){return this.sMap.hasName(name);}
    public boolean hasProgramName(String name){return this.pMap.hasName(name);}
    public boolean hasCollegeName(String name){return this.cMap.hasName(name);}

    public boolean hasID(String id){return this.sMap.containsKey(id);}
    public boolean hasProgramCode(String code){return this.pMap.containsKey(code);}
    public boolean hasCollegeCode(String code){return this.cMap.containsKey(code);}
}
