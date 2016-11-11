/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    
    
    @Override
    public String toString() {
        return name + " " + getValues() ;
    }

    /**
     * @return the values
     */
    public List<Double> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<Double> values) {
        this.values = values;
    }

    public void addValue(double parseDouble) {
        this.values.add(parseDouble);
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Alternative){
            Alternative aux = (Alternative)o;
            return this.name.equals(aux.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
}
