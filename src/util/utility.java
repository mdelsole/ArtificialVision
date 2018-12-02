package util;

//Bunch of Utility Methods
public class utility {

    ///////////////////////  1D ARRAY OPERATIONS  /////////////////////////


    public double [][] to2D (double [] array, int rowLength, int colHeight){
        int k = 0;
        double [][] result = new double [rowLength][colHeight];
        for (int i = 0; i < rowLength; i++){
            for (int j = 0; j < colHeight; j++){
                result[i][j] = array[k];
                k++;
            }
        }
        return result;
    }


    ///////////////////////  2D ARRAY OPERATIONS  /////////////////////////


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

    //Calculate mean of a 2D array
    public double mean(double[][] array) {
        int counter=0;
        double sum = 0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                sum = sum+array[i][j];
                counter++;
            }
        }
        return sum / counter;
    }

    //Calculate sum of a 2D array
    public double sum(double[][] array) {
        double sum = 0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                sum += sum+array[i][j];
            }
        }
        return sum;
    }

    //Calculate squared sum (abs value) of a 2D array
    public double squaredSum(double[][] array) {
        double sum = 0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                sum += Math.pow(sum+array[i][j],2);
            }
        }
        return sum;
    }

    //Converts a 2D matrix A into a 1D matrix B
    public int[] to1D(int[][] A){

        int[] B = new int[A.length * A[0].length];
        int index = 0;

        for(int i = 0;i < A.length;i++){
            for(int j = 0;j < A[0].length;j++){
                B[index++] = A[i][j];
            }
        }
        return B;
    }

}
