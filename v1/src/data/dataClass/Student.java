package data.dataClass;

public class Student {
    private String id;
    private String fn; //First Name
    private String ln; //Last Name
    private String yl; //Year Level
    private String g; //Gender
    private String pc; //Program Code

    public Student(String id, String fn, String ln, String yl, String g, String pc){
        this.id = id;
        this.fn = fn;
        this.ln = ln;
        this.yl = yl;
        this.g = g;
        this.pc = pc;
    }
    
    public void setID(String id){this.id = id;}
    public void setFN(String fn){this.fn = fn;}
    public void setLN(String ln){this.ln = ln;}
    public void setYL(String yl){this.yl = yl;}
    public void setG(String g){this.g = g;}
    public void setPC(String pc){this.pc = pc;}

    public String getID(){return id;}
    public String getFN(){return fn;}
    public String getLN(){return ln;}
    public String getYL(){return yl;}
    public String getG(){return g;}
    public String getPC(){return pc;}

    public void setData(String[] data){
        this.setID(data[0]);
        this.setFN(data[1]);
        this.setLN(data[2]);
        this.setYL(data[3]);
        this.setG(data[4]);
        this.setPC(data[5]);
    }
}
