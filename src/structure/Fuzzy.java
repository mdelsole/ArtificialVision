package structure;

//The basic unit of thought

public class Fuzzy {

    double solidityThreshold = 0.0;

    /*
    Pseudocode:

    procedure establish
     */

    //Tell us if this is an established fuzzy or not
    public boolean isEstablished(){
        return solidityThreshold > 0.6;
    }


}
