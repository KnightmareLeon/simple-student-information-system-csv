package data.dataClass;

import java.util.HashMap;

public class Program {
    private String code;
    private String name;
    private String cCode; //College Code
    private HashMap<String,Student> stdMap = new HashMap<String,Student>();

    public Program(String code, String name, String cCode){
        this.code = code;
        this.name = name;
        this.cCode = cCode;
    }
    
    public void setCode(String code){this.code = code;}
    public void setName(String name){this.name = name;}
    public void setCCode(String cCode){this.cCode = cCode;}

    public String getCode(){return code;}
    public String getName(){return name;}
    public String getCCode(){return cCode;}

    public void addStudent(Student std){
        this.stdMap.put(std.getID(),std);
    }

    public void removeStudent(Student std){
        this.stdMap.remove(std.getID(),std);
    }

    public int getTotalStudents(){return stdMap.size();}

    public void transferStudents(Program newProgram){
        this.stdMap.forEach((key, value) ->{newProgram.addStudent(value);});
        this.stdMap.clear();
    }
}
