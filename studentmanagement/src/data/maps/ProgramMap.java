package data.maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import data.dataClass.Program;

public class ProgramMap extends HashMap<String,Program> implements NameChecker{
    private Set<String> pNames = new HashSet<String>();

    public Program put(String key, Program value){
        if(this.containsKey(key) && !pNames.contains(value.getName())){
            pNames.remove(this.get(key).getName());
        }
        pNames.add(value.getName());
        super.put(key, value);
        return value;
    }

    public Program remove(String key){
        pNames.remove(this.get(key).getName());
        return super.remove(key);
    }

    @Override
    public boolean hasName(String name) {return pNames.contains(name);}

}
