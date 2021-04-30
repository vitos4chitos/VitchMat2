package methods;

import equations.Equations;
import io.Output;

public class Methods {

    private double a;
    private double b;
    private double e;
    private boolean flag;
    private Equations equations;
    private Output output;

    public Methods(double a, double b, double e, Output output){
        this.a = a;
        this.b = b;
        this.e = e;
        equations = new Equations();
        this.output = output;
    }

    public void solve(int numOfMethod, int numOfFunction){
        switch (numOfMethod){
            case (2):
                MethodOfHord(numOfFunction);
                break;
            case (4):
                SecantMethod(numOfFunction);
                break;
            case (5):
                SimpleIterationMethod(numOfFunction);
                break;
            default:
                System.out.println("Не поддерживается");
                break;
        }
    }

    private void MethodOfHord(int numOfFunction){
        if(Double.compare(equations.getValue(numOfFunction, a), 0.0d) * Double.compare(equations.getValue(numOfFunction, b), 0.0d) > 0){
            System.out.println("На данном промежутке нет корней, проверьте интервал");
            System.exit(0);
        }
        double aa = a, bb = b;
        double fa;
        double fb;
        double x = 0.0;
        double xn = 0.0d;
        double fx;
        double eps = 0.0d;
        flag = true;
        int iterations = 0;
        while (flag){
            fa = equations.getValue(numOfFunction, a);
            fb = equations.getValue(numOfFunction, b);
            x = a - ((b - a)/(fb - fa)) * fa;
            fx = equations.getValue(numOfFunction, x);
            iterations++;
            if(iterations != 1) {
                eps = Math.abs(xn - x);
                if (Double.compare(eps, e) < 0) {
                    flag = false;
                }
            }
            xn = x;
            try {
                output.makeStrokeForHord(a, b, x, fa, fb, fx, iterations, eps);
            }
            catch (Exception exception){
                System.out.println("Что-то пошло не так");
                System.exit(0);
            }
            if(fa * fx > 0){
                a = x;
            }
            else{
                b = x;
            }

        }
        output.finalStroke(x, iterations, e);
        output.makeChart(aa, bb, numOfFunction);
    }

    private void SecantMethod(int numOfFunction){
        double x0 = a;
        double x1 = x0 - (equations.getValue(numOfFunction, x0) / equations.getValueOfFirstDerivative(numOfFunction, x0));
        b = x1;
        double x2;
        double eps;
        int iterations = 0;
        while (true) {
            x2 = x1 - (x1 - x0) / ((equations.getValue(numOfFunction, x1) - (equations.getValue(numOfFunction, x0)))) * (equations.getValue(numOfFunction, x1));
            iterations++;
            eps = Math.abs(x2 - x1);
            output.makeStrokeForSecant(x0, x1, x2, equations.getValue(numOfFunction, x2), eps, iterations);
            if (Double.compare(eps, e) < 0) {
                break;
            }
            else{
                x0 = x1;
                x1 = x2;
            }
        }
        output.finalStroke(x2, iterations, x2);
        output.makeChart(a, b, numOfFunction);
    }

    private void SimpleIterationMethod(int numOfFunction){
        if(Double.compare(equations.getValue(numOfFunction, a), 0.0d) * Double.compare(equations.getValue(numOfFunction, b), 0.0d) > 0){
            System.out.println("На данном промежутке нет корней, проверьте интервал");
            System.exit(0);
        }
        double lambda = -1/Math.max(equations.getValueOfFirstDerivative(numOfFunction, a), equations.getValueOfFirstDerivative(numOfFunction, b));
        double x0;
        if(Double.compare(Math.max(equations.getValueOfFirstDerivative(numOfFunction, a), equations.getValueOfFirstDerivative(numOfFunction, b)),
                equations.getValueOfFirstDerivative(numOfFunction, a)) == 0){
            x0 = a;
        }
        else {
            x0 = b;
        }
        double x1;
        double x2;
        double fx1;
        double eps;
        int iterations = 0;
        while (true){
            x1 = equations.getValueOfFi(numOfFunction, x0, lambda);
            x2 = equations.getValueOfFi(numOfFunction, x1, lambda);
            fx1 = equations.getValue(numOfFunction, x1);
            eps = Math.abs(x1 - x0);
            iterations++;
            output.makeStrokeForSimpleIterations(x0, x1, x2, fx1, eps, iterations);
            if (Double.compare(eps, e) < 0) {
                break;
            }
            x0 = x1;
            x1 = x2;
        }
        output.finalStroke(x1, iterations, e);
        output.makeChart(a, b, numOfFunction);
    }
}
