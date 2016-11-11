package Model;

import View.PrometheeWindow;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Esta clase implementa el metodo de promethee, como entrada necesita.
 * Alternativas (Evaluadas en cada uno de los criterios) Funciones de los
 * criterios Pesos para cada criterio
 *
 * Salidas Flujos netos Flujos de relacion
 *
 * @author mauricio rondon
 */
public class Promethee {

    private List<Criterion> criteria;
    private PrometheeWindow prometheeWindow;
    private List<Alternative> alternatives;
    private double [][] preferenceIndex;
    private double []  posFlux;
    private double [] negFlux;
    
    private double compare(Alternative a, Alternative b){
        double ans = 0.0;
        double tot = 0.0;
        int i = 0;
        for(Criterion c : criteria){
            try {
                ans+=c.compute(a.getValues().get(i), b.getValues().get(i))*c.getWeight();
                tot+=c.getWeight();
                i++;
            } catch (Exception ex) {
                Logger.getLogger(Promethee.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(prometheeWindow, ex.toString());
            }
        }
        return ans/tot;
    }
    
    public Promethee() {
        preferenceIndex = new double[100][100];
        posFlux = new double[100];
        negFlux = new double[100];
        criteria = new ArrayList<>();
        alternatives = new ArrayList<>();
    }

    public Promethee(List<Criterion> criteria, List<Alternative> alternatives) {
        this();
        this.criteria = criteria;
        this.alternatives = alternatives;
    }

    public void computeFlows(){
        //Computing preference indexes
        int n = alternatives.size();
        for(int i=0 ; i<n ; i++){
            for(int j=0 ; j<n ; j++){
                if(i==j) preferenceIndex[i][j] = 0.0;
                else preferenceIndex[i][j] = compare(alternatives.get(i), alternatives.get(j));
            }
        }
        
        for(int i=0 ; i<n ; i++){
            posFlux[i] = 0.0;
            for(int j=0 ; j<n ; j++){
                posFlux[i]+=getPreferenceIndex()[i][j];
            }
        }
        
        for(int i=0 ; i<n ; i++){
            negFlux[i] = 0.0;
            for(int j=0 ; j<n ; j++){
                negFlux[i]+=getPreferenceIndex()[j][i];
            }
        }
    }
    
    
    public List<Alternative> getTotalOrder() {
        System.out.println(criteria);
        System.out.println(getAlternatives());
        computeFlows();
        return null;
    }

    public Map<Alternative, List<Alternative>> getPartialOrder() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void addCriterion(Criterion criterion) {
        getCriteria().add(criterion);
        updateView();
    }

    public void setWindow(PrometheeWindow aThis) {
        this.prometheeWindow = aThis;
    }

    public void updateView() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s%-6s%-6s%-9s%-20s\n", "Nombre", "Tipo", "Peso", "Min/Max","Parámetros"));
        sb.append("------------------------------------------------------------\n");
        getCriteria().stream().forEach((c) -> {
            sb.append(c);
        });
        sb.append("------------------------------------------------------------\n");
        
        this.getPrometheeWindow().getjTextArea1().setText(sb.toString());
    }

    /**
     * @return the criteria
     */
    public List<Criterion> getCriteria() {
        return criteria;
    }

    /**
     * @param criteria the criteria to set
     */
    public void setCriteria(List<Criterion> criteria) {
        this.criteria = criteria;
    }

    

    /**
     * @return the prometheeWindow
     */
    public PrometheeWindow getPrometheeWindow() {
        return prometheeWindow;
    }

    /**
     * @return the alternatives
     */
    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    /**
     * @param alternatives the alternatives to set
     */
    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    /**
     * @return the preferenceIndex
     */
    public double[][] getPreferenceIndex() {
        return preferenceIndex;
    }

    /**
     * @return the posFlux
     */
    public double[] getPosFlux() {
        return posFlux;
    }

    /**
     * @return the negFlux
     */
    public double[] getNegFlux() {
        return negFlux;
    }

}
