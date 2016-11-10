/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mauricio
 */
public class Alternative {
    private String name;
    private List<Double> values;

    public Alternative(){
        name = "";
        values = new ArrayList<>();
    }
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public void addValue(double value){
        this.values.add(value);
    }
    
    @Override
    public String toString() {
        return name + " " + values ;
    }
    
}
