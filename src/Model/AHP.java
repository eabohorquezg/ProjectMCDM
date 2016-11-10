/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.swing.JTable;

/**
 *
 * @author Edwin
 */

public class AHP {
    
    private String outputlog;
    
    public AHP(){
        outputlog = "";
    }
            
    public String getOutputlog(){
        return outputlog;
    }
            
    public double[][] createMatrix( JTable table ){
        outputlog += "MATRIZ: \n\n";
        double [][]matrix = new double[table.getRowCount()][table.getColumnCount()-1];
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount()-1; j++) {
                matrix[i][j] = Double.parseDouble(table.getModel().getValueAt(i, j+1).toString());    
                outputlog += String.valueOf(matrix[i][j])+"  ";
            }
            outputlog += "\n";
        }
        outputlog += "\n";
        return matrix;
    }
    
    public double[] calculateSumOfColumns( double matrix[][] ){
        double []sumcolumn = new double[matrix.length];
        double acum=0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                acum += matrix[j][i];
            }
            sumcolumn[i] = acum;
            acum = 0;
        }    
        return sumcolumn;
    }
    
    public double[] calculateRowAverage( double matrix[][], double sumcolumn[] ){
        double []average = new double[matrix.length];
        double acum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] /= sumcolumn[j];//normaliza la matriz
                acum += matrix[i][j];
            } 
            average[i] = acum/matrix.length;            
            outputlog += "ALTERNATIVA "+(i+1)+": "+String.valueOf(average[i])+"\n";
            acum = 0;
        }
        return average;
    }
    
    public double[] calculatePrioritiesVector( double matrix[][] ){
        outputlog += "VECTOR DE PRIORIDADES: \n\n";        
        double []sumcolumn = calculateSumOfColumns(matrix);
        double []prioritiesVector = calculateRowAverage(matrix,sumcolumn);        
        outputlog += "\n";        
        return prioritiesVector;
    }

    public void modifyMatrixPairwiseComparison( double criterion[], double MatrixPairwiseComparison[][], int i, int j, double preferencelevel, String []arrayCriteria, int numCriterion ){
        if( criterion[i] <= criterion[j] ){
            if( arrayCriteria[numCriterion].split(":")[1].equalsIgnoreCase("min") ){
                MatrixPairwiseComparison[i][j] = preferencelevel;//minimizar
                outputlog += String.valueOf(MatrixPairwiseComparison[i][j])+"  ";
            }else{
                MatrixPairwiseComparison[i][j] = 1/preferencelevel;//maximizar
                outputlog += String.valueOf(MatrixPairwiseComparison[i][j])+"  ";
            }
        }else{
            if( arrayCriteria[numCriterion].split(":")[1].equalsIgnoreCase("min") ){
                MatrixPairwiseComparison[i][j] = 1/preferencelevel;//minimizar
                outputlog += String.valueOf(MatrixPairwiseComparison[i][j])+"  ";
            }else{
                MatrixPairwiseComparison[i][j] = preferencelevel;//maximizar
                outputlog += String.valueOf(MatrixPairwiseComparison[i][j])+"  ";
            }
        }        
    }

    public double[][] calculateMatrixPairwiseComparison( double criterion[], String []arrayCriteria, int numCriterion ){
        outputlog += "--------------------------------------------\n";
        outputlog += "CRITERIO : "+arrayCriteria[numCriterion].toUpperCase().split(":")[0]+"\n"; 
        outputlog += "--------------------------------------------\n\n";
        outputlog += "MATRIZ DE COMPARACION POR PARES - ALTERNATIVAS: \n\n";                
        double value = 0,preferencelevel=0;
        double [][]MatrixPairwiseComparison = new double[criterion.length][criterion.length];                
        for (int i = 0; i < criterion.length; i++) {
            for (int j = 0; j < criterion.length; j++) {
                if( criterion[i] >= criterion[j] ){
                    value = 100 - criterion[j]*100/criterion[i];
                }else{
                    value = 100 - criterion[i]*100/criterion[j];
                }
                preferencelevel = getRangeValue(value);
                modifyMatrixPairwiseComparison(criterion, MatrixPairwiseComparison, i, j, preferencelevel, arrayCriteria, numCriterion);
            }
            outputlog += "\n";
        }
        outputlog += "\n";
        return MatrixPairwiseComparison;
    }
    
    public double getRangeValue( double value ){
        double preferencelevel=0;
        if( value==0  ){
            return preferencelevel = 1;
        }else if( value > 0 && value < 30  ){
            return preferencelevel = 2;
        }
        for (int i = 3; i <= 9; i++) {
            if( value >= i*10 && value < (i+1)*10 ){
                preferencelevel = i;
                break;
            }
        }
        return preferencelevel;
    }
    
    public double[] calculateResult( double matrixfinal[][], double vectorPriorityCriteria[] , JTable alternatives){
        outputlog += "--------------------------------------------\n";        
        outputlog += "VECTOR DE PRIORIDAD GLOBAL: \n\n";        
        double finalresult[] = new double[alternatives.getRowCount()], acum = 0;
        for (int i = 0; i < alternatives.getRowCount(); i++) {
            for (int j = 0; j < alternatives.getColumnCount()-1; j++) {
                acum += matrixfinal[i][j]*vectorPriorityCriteria[j];
            }
            finalresult[i] = acum;
            outputlog += "ALTERNATIVA "+(i+1)+": "+String.valueOf(finalresult[i])+"\n";
            acum = 0;
        }
        return finalresult;
    }
        
}
