package io;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;
import equations.Equations;
import methods.Methods;

import java.io.*;
import java.util.Scanner;

public class Input {

    private boolean console;
    private Equations equations;

    public Input(boolean console){
        this.console = console;
        equations = new Equations();
    }

    public void input(){
        if(console){
            inputForConsole();
        }
        else{
            inputForFile();
        }
    }

    private void inputForConsole(){
        Scanner scanner = new Scanner(System.in);
        boolean console;
        File file;
        Output output = null;
        while (true){
            System.out.println("Вывод в консоль?(y/n)");
            String a = "";
            a = scanner.nextLine();
            if(a.equals("y")) {
                console = true;
                output = new Output(console);
                break;
            }
            else{
                if(a.equals("n")){
                    console = false;
                    String path;
                    System.out.println("Введите путь до файла: ");
                    path = scanner.nextLine();
                    try{
                        file = new File(path);
                        output = new Output(console, file);
                        break;
                    }
                    catch (Exception e){
                        System.out.println("Файл не найден. Попробуйте ещё раз.");
                        break;
                    }
                }
                else{
                    System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
                }
            }
        }
        System.out.println("Выберете уравнение (Введите номер)");
        equations.getEquations();
        int n = 0;
        while(true){
            try {
                n = Integer.parseInt(scanner.nextLine());
                if(n < 1 || n > 4){
                    System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
                }
                else break;
            }
            catch (Exception exception){
                System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
            }
        }
        System.out.println("Введите номер метода: ");
        int numberOfMethod = 0;
        System.out.println("2: Метод хорд");
        System.out.println("4: Метод секущих");
        System.out.println("5: Метод простой итерации");
        while(true) {
            try {
                numberOfMethod = Integer.parseInt(scanner.nextLine());
                if(numberOfMethod == 2 || numberOfMethod == 4 || numberOfMethod == 5){
                    break;
                }
                else{
                    System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
                }
            }
            catch (Exception exception){
                System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
            }
        }
        System.out.println("Введите погрешность:");
        double e = 0.0d;
        while(true){
            try{
                e = Double.parseDouble(scanner.nextLine());
                if(Double.compare(e, 0.0) > 0){
                    break;
                }
                else{
                    System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
                }
            }
            catch (Exception exeption){
                System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
            }
        }
        System.out.println("Введите интервал(левую границу и правую соответсвенно) построчно");
        double a = 0, b = 0;
        while(true){
            try{
                a = Double.parseDouble(scanner.nextLine());
                b = Double.parseDouble(scanner.nextLine());
                System.out.println("Начинаю вычисление...");
                Methods methods = new Methods(a, b, e, output);
                methods.solve(numberOfMethod, n);
                break;
            }
            catch (Exception exception){
                System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
            }
        }

    }

    private void inputForFile(){
        System.out.println("Введите путь до файла: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        while(true){
            try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                Output output;
                String console = br.readLine();
                String file;
                if(console.equals("n")){
                    file = br.readLine();
                    output = new Output(false, new File(file));
                }
                else{
                    output = new Output(true);
                }
                int n = Integer.parseInt(br.readLine());
                int numOfMethod = Integer.parseInt(br.readLine());
                double e = Double.parseDouble(br.readLine());
                double a = 0, b = 0;
                a = Double.parseDouble(br.readLine());
                b = Double.parseDouble(br.readLine());
                Methods methodsss = new Methods(a, b, e, output);
                methodsss.solve(numOfMethod, n);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
