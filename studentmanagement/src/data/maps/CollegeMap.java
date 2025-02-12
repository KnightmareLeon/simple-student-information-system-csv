package data.maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import data.dataClass.College;

public class CollegeMap extends HashMap<String,College> implements NameChecker{
    private Set<String> cNames = new HashSet<String>();

    public College put(String key, College value){
        if(this.containsKey(key) && !cNames.contains(value.getName())){
            cNames.remove(this.get(key).getName());
        }
        super.put(key, value);
        cNames.add(value.getName());
        return value;
    }
    public College remove(String key){
        cNames.remove(this.get(key).getName());
        return super.remove(key);
    }
    
    @Override
    public boolean hasName(String name){return cNames.contains(name);}

}
