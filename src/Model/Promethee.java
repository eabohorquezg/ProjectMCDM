package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Esta clase implementa el metodo de promethee, como entrada necesita.
 * Alternativas (Evaluadas en cada uno de los criterios)
 * Funciones de los criterios
 * Pesos para cada criterio
 * 
 * Salidas
 * Flujos netos
 * Flujos de relacion
 * 
 * @author mauricio rondon
 */
public class Promethee {
    private List<Criterion> criteria;
    private double [][] alternatives;
    //private List<Alternative> alternatives;
    
    public Promethee(){
        criteria = new ArrayList<>();
        alternatives = new double[100][100];
    }

    public Promethee(List<Criterion> criteria, double[][] alternatives) {
        this.criteria = criteria;
        this.alternatives = alternatives;
    }
 
    public List<Alternative> getTotalOrder(){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Map<Alternative,List<Alternative>> getPartialOrder(){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
}










