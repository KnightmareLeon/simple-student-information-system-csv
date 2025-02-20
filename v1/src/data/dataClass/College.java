package data.dataClass;

import java.util.HashMap;

public class College {

    private String code;
    private String name;
    private HashMap<String,Program> prgMap = new HashMap<String,Program>(); //List of Programs

    public College(String code, String name){
        this.code = code;
        this.name = name;
    }
    public void setCode(String code){this.code = code;}
    public void setName(String name){this.name = name;}

    public String getCode(){return code;}
    public String getName(){return name;}

    public void addProgram(Program prg){prgMap.put(prg.getCode(),prg);}
    public void removeProgram(Program prg){prgMap.remove(prg.getCode(),prg);}

    public int getTotalProgram(){return prgMap.size();}

    public void transferPrograms(College newCollege){
        this.prgMap.forEach((key, value) ->{newCollege.addProgram(value);});
        this.prgMap.clear();
    }
}
