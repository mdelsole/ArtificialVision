package util;

//Bunch of Utility Methods
public class utility {


    //X form of mesh grid, for now assuming lowerLim is always 0
    public double [][] meshGridX(int upperLim, double offset){
        //For now assuming lowerLim is always 0
        double [][] result = new double[upperLim][upperLim];
        for (int i = 0; i<upperLim; i++){
            for (int j = 0; j<upperLim; j++){
                result[i][j] = (double)i + offset;
            }
        }
        return result;
    }

    //Y form of mesh grid, for now assuming lowerLim is always 0
    public double [][] meshGridY(int upperLim, double offset){
        double [][] result = new double[upperLim][upperLim];
        for (int i = 0; i<upperLim; i++){
            for (int j = 0; j<upperLim; j++){
                result[i][j] = (double)j + offset;
            }
        }
        return result;
    }
}
