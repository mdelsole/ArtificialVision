package vision.baseHierarchy;
import util.utility;

import java.util.ArrayList;

public class SimpleCellLayer1 {

    //Builds the dictionary of S1 features
    //This method returns an array of S1 (Gabor) filters. Each Gabor filter is a square 2D array. The inner loop runs
    //through orientations (4 orientations per scale) while the outer loop runs through scales
    public ArrayList buildS1Filters(){

        System.out.println("Building Simple Cell Filters");
        //For any utility methods we need to use
        utility util = new utility();
        double temp;

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

        //Store the finalized set of filters
        double [][][] filterks;
        //Temporary filter array used for each receptive field size
        double [][][][] filtersThjisSize;
        //Temporary filter array used for each theta in each size grid
        double [][] tempFilters;

        ArrayList<ArrayList> filters = new ArrayList<ArrayList>();
        ArrayList<double [][]> filtersThisSize = new ArrayList<double [][]>();


        int counter = 0;

        //i is the receptive field size
        //Grids below size 7 won't do much, and grids above 30 take too much time/resources
        for (int i = 7; i < 30; i+=2){

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

                        //Calculate
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

                //Normalize
                double mean = util.mean(tempFilters);
                double squaredSum = util.squaredSum(tempFilters);
                for (int k = 0; k<i; k++) {
                    for (int r = 0; r < i; r++) {

                        tempFilters[k][r] -= mean;
                        tempFilters[k][r] /= Math.sqrt(squaredSum);

                    }
                }
                filtersThisSize.add(tempFilters);
            }
            filters.add(filtersThisSize);
        }
        return filters;
    }


    /*
    Separate method from buildS1Filters because we don't want to build the dictionary every time we use the filters

    Dedicated function for running S1 filters (s1 needs dedicated method, other s layers can use a common one)
    This is because S1 has special aspects (the input is 2D rather than 3D, the filters can get very large, etc.)

    Takes in the image as an array

    Each S1 cell will multiply its inputs by its filter, and then divide the result by the norm of its input
    (normalized dot-product, AKA cosine between inputs and filter. We do this efficiently by convolving the
    image with each filter, then dividing the results pointwise with the square root of the convolution of the squared
    image with a uniform filter of adequate size (for each scale).

    Returns a list of 3D arrays (one per S1 scale). Each 3D array is a list 4 2D maps, one per orientation.

    This function uses Fourier-based convolutions, which help with very large filters.
    */
    public void runS1Filters(double [] image, ArrayList<ArrayList> s1Filters){

        System.out.println("Running Simple Cell Filters");
        ArrayList ouptut = new ArrayList();

        double [] imageSquared = new double[image.length];
        for (int i = 0; i < image.length; i++){
            imageSquared[i] = Math.pow(image[i],2);
        }

        //Useful for unpacking the
        ArrayList<double [][]> filtersThisSize = new ArrayList<double[][]>();
        //Temp variable to determine rfsize during each iteration (7x7, 9x9, etc.)
        double rfSize = 0;


        //Each element in s1Filters is the set of filters (of various orientations) for a particular scale (rfsize).
        for (int i = 0; i < s1Filters.size(); i++){
            for (int j = 0; i < s1Filters.get(i).size(); i++){

                //At any given scale, all filters have the same RF size. Thus, the RF size is simply the x-size of the
                //filter at the first orientation
                

                //The output of every S1 neuron is divided by the Euclidan norm (root-sum-squares) of its inputs. We
                //also take the absolute value.

            }
        }

    }
}
