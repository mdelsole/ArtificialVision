package vision.baseHierarchy;
import util.utility;

/*
This function returns a list of list of S1 (Gabor) filters. Each Gabor filter is a square 2D array. The inner lists run
over orientations (4 orientations per scale), and the outer list runs over scales (all the S1 RF sizes defined in the
options).
 */

public class SimpleCellLayer1 {

    //Build the library of S1 features
    public void buildS1Filters(){

        //For any utility methods we need to use
        utility util = new utility();
        double temp;

        //Store the filters here
        double [][] filters;
        //Angle of filter (orientation)
        double theta;
        //TODO: Figure these out
        //It's a quadratic equation,
        double sigma;
        double lambda;
        double gamma;

        //Template grid for filling with different angles
        double [][] x, y;

        //Fill the template grid with different angles
        double [][] x2, y2;

        //Temporary filter array used for each theta in each size grid
        double [][] tempFilters;


        //i is the receptive field size
        //Grids below size 7 won't do much, but grids above 30 take too much time/resources
        for (int i = 7; i < 30; i+=2){

            double [] filtersThisSize;
            for (int j = 0; j < 4; j++){
                //Four quadrants of unit circle
                theta = j * 3.14159265/4;

                //Create template grid for all the different angles
                temp = -i/2;
                x = util.meshGridX(i, temp);
                y = util.meshGridY(i, temp);
                //Initialize x2, y2, and the tempFilters arrays
                x2  = new double[i][i];
                y2  = new double[i][i];
                tempFilters = new double[i][i];

                sigma = (0.0036 * i * i) + (0.35 * i) + 0.18;
                lambda = sigma/0.8;
                gamma = 0.3;

                //Fill x2 and y2 with appropriate angles
                for (int k = 0; k<i; k++){
                    for (int r = 0; r<i; r++){

                        x2[k][r] = (x[k][r] * Math.cos(theta)) + (y[k][r] * Math.sin(theta));
                        y2[k][r] = (-x[k][r] * Math.sin(theta)) + (y[k][r] * Math.cos(theta));
                        temp = -(x2[k][r] * x2[k][r] + gamma * gamma * y2[k][r] * y2[k][r]) / (2 * sigma * sigma);
                        temp = Math.exp(temp);
                        temp *= Math.cos((2*3.14159265 * x2[k][r]) / lambda);
                        tempFilters[k][r] = Math.exp(temp);

                        //
                        if (Math.sqrt(Math.pow(x[k][r],2)+Math.pow(y[k][r],2)) > (i/2)){
                            tempFilters[k][r] = 0;
                        }

                    }
                }
            }
        }
    }
}
