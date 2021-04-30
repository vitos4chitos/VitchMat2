package io;

import charts.Chart;
import charts.EquationChart;
import equations.Equations;

import java.io.*;

public class Output {

    private boolean console;
    private File file;
    private PrintStream printStream;
    private String temp;


    public Output(boolean console){
        this.console = console;
    }

    public Output(boolean console, File file) throws FileNotFoundException {
        this.console = console;
        this.file = file;
        printStream = new PrintStream(new FileOutputStream(file));
    }

    public void makeStrokeForHord(double a, double b, double x, double fa, double fb, double fx, int numOfLine, double eps) throws IOException {
        if(console){
            if(numOfLine == 1){
                System.out.println("Порядок вывода таблицы: Num, a, b, x, F(a), F(b), F(x), |x(n + 1) - x(n)|");
                System.out.printf(numOfLine +  " %.8f %.8f %.8f %.8f %.8f %.8f -", a, b, x, fa, fb, fx);
            }
            else{
                System.out.printf(numOfLine +  " %.8f %.8f %.8f %.8f %.8f %.8f %.8f", a, b, x, fa, fb, fx, eps);
            }
            System.out.println();
        }
        else{
            if(numOfLine == 1){
                printStream.println("Порядок вывода таблицы: Num, a, b, x, F(a), F(b), F(x), |x(n + 1) - x(n)|");
                printStream.printf(numOfLine +  " %.8f %.8f %.8f %.8f %.8f %.8f -", a, b, x, fa, fb, fx);
            }
            else{
                printStream.printf(numOfLine +  " %.8f %.8f %.8f %.8f %.8f %.8f %.8f", a, b, x, fa, fb, fx, eps);
            }
            printStream.println();
        }
    }

    public void makeStrokeForSecant(double x0, double x1, double x2, double fx2, double eps, int numOfLine){
        if(console){
            if(numOfLine == 1){
                System.out.println("Порядок вывода таблицы: Num, X(i-1), X(i), X(i + 1), F(X(i+1)), |X(i + 1) - X(i)|");
            }
            System.out.printf(numOfLine + " %.8f %.8f %.8f %.8f %.8f", x0, x1, x2, fx2, eps);
            System.out.println();
        }
        else{
            if(numOfLine == 1){
                printStream.println("Порядок вывода таблицы: Num, X(i-1), X(i), X(i + 1), F(X(i+1)), |X(i + 1) - X(i)|");
            }
            printStream.printf(numOfLine + " %.8f %.8f %.8f %.8f %.8f", x0, x1, x2, fx2, eps);
            printStream.println();
        }
    }

    public void makeStrokeForSimpleIterations(double x0, double x1, double FiX1, double FX1, double eps, int numOfLine){
        if(console){
            if(numOfLine == 1){
                System.out.println("Порядок вывода таблицы: Num, X(i), X(i+1), Fi(X(i + 1)), F(X(i + 1)), |X(i + 1) - X(i)|");
            }
            System.out.printf(numOfLine + " %.8f %.8f %.8f %.8f %.8f", x0, x1, FiX1, FX1, eps);
            System.out.println();
        }
        else{
            if(numOfLine == 1){
                System.out.println("Порядок вывода таблицы: Num, X(i), X(i+1), Fi(X(i + 1)), F(X(i + 1)), |X(i + 1) - X(i)|");
            }
            printStream.printf(numOfLine + " %.8f %.8f %.8f %.8f %.8f", x0, x1, FiX1, FX1, eps);
            printStream.println();
        }
    }

    public void finalStroke(double x, int iterations, double e){
        if(console){
            System.out.println("Вот что мне удалось определить за  " + iterations + " операций:");
            System.out.printf("x* = %.8f c погрешностью " + e, x);
        }
        else{
            printStream.println("Вот что мне удалось определить за  " + iterations + " операций:");
            printStream.printf("x* = %.8f c погрешностью " + e, x);
        }
    }

    public void makeChart(double a, double b, int numOfFunction){
        Chart chart = new Chart(new Equations(), a, b, numOfFunction);
        EquationChart equationChart = new EquationChart(chart);
    }
}
