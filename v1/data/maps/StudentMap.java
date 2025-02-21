package data.maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import data.dataClass.Student;

public class StudentMap extends HashMap<String,Student> implements NameChecker{
    private Set<String> sNames = new HashSet<String>();

    public Student put(String key, Student value){
        if(this.containsKey(key) && !sNames.contains(value.getFN() + " " + value.getLN())){
            sNames.remove(this.get(key).getFN() + " " + this.get(key).getLN());
        }
        sNames.add(value.getFN() + " " + value.getLN());
        super.put(key, value);
        return value;
    }

    public Student remove(String key){
        sNames.remove(this.get(key).getFN() + " " + this.get(key).getLN());
        return super.remove(key);
    }

    @Override
    public boolean hasName(String name) {return sNames.contains(name);}

    
}
