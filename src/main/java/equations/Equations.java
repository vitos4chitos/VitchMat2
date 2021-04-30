package equations;

import java.util.ArrayList;
import java.util.Arrays;

public class Equations {

    private ArrayList<String> equations;

    public Equations(){
        equations = new ArrayList<String>(Arrays.asList("1) x^3 + 2,84x^2 - 5,606x - 14,766", "2) 5/x - 2x", "3) e^2x - 2", "4) x^3 - x + 4"));
    }

    public double getValue(int numOfFunction, double x){
        switch (numOfFunction){
            case (1):
                return x * x * x + 2.84 * x * x - 5.606 * x - 14.766;
            case (2):
                return 5 / x - 2 * x;
            case (3):
                return Math.pow(Math.E, 2*x) - 2;
            case (4):
                return x*x*x - x + 4;
            default:
                return 0;
        }
    }
    public double getValueOfFirstDerivative(int numOfFunction, double x){
        switch (numOfFunction){
            case (1):
                return 3 * x * x + 2.84 * 2 * x - 5.606;
            case (2):
                return (-5/Math.pow(x, 2)) - 2;
            case (3):
                return 2 * Math.pow(Math.E, 2*x);
            case (4):
                return 3 * x * x - 1;
            default:
                return 0;
        }
    }

    public double getValueOfFi(int numOfFunction, double x, double lambda){
        switch (numOfFunction){
            case (1):
                return x + lambda * (x * x * x + 2.84 * x * x - 5.606 * x - 14.766);
            case (2):
                return x + lambda * (5 / x - 2 * x);
            case (3):
                return x + lambda * (Math.pow(Math.E, 2*x) - 2);
            case (4):
                return x + lambda * (x*x*x - x + 4);
            default:
                return 0;
        }
    }

    public void getEquations(){
        equations.forEach(System.out::println);
    }

    public String getEquation(int numOfFunction){
        switch (numOfFunction){
            case (1):
                return "x^3 + 2,84x^2 - 5,606x - 14,766";
            case (2):
                return "5/x - 2x";
            case (3):
                return "e^2x - 2";
            case (4):
                return "x^3 - x + 4";
            default:
                return "";
        }
    }
}
