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
    private double[][] alternatives;
    private PrometheeWindow prometheeWindow;

    //private List<Alternative> alternatives;
    public Promethee() {
        criteria = new ArrayList<>();
        alternatives = new double[100][100];
    }

    public Promethee(List<Criterion> criteria, double[][] alternatives) {
        this.criteria = criteria;
        this.alternatives = alternatives;
    }

    public List<Alternative> getTotalOrder() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<Alternative, List<Alternative>> getPartialOrder() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void addCriterion(Criterion criterion) {
        criteria.add(criterion);
        updateView();
    }

    public void setWindow(PrometheeWindow aThis) {
        this.prometheeWindow = aThis;
    }

    public void updateView() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s%-6s%-6s%-9s%-20s\n", "Nombre", "Tipo", "Peso", "Min/Max","Parámetros"));
        sb.append("------------------------------------------------------------\n");
        criteria.stream().forEach((c) -> {
            sb.append(c);
        });
        sb.append("------------------------------------------------------------\n");
        
        this.prometheeWindow.getjTextArea1().setText(sb.toString());
    }

}
