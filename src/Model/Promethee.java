package Model;

import View.PrometheeWindow;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    
    public Promethee() {
        criteria = new ArrayList<>();
        alternatives = new ArrayList<>();
    }

    public Promethee(List<Criterion> criteria, List<Alternative> alternatives) {
        this.criteria = criteria;
        this.alternatives = alternatives;
    }

    public List<Alternative> getTotalOrder() {
        System.out.println(criteria);
        System.out.println(getAlternatives());
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

}
