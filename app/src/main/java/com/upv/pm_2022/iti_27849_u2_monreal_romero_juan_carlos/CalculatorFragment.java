package com.upv.pm_2022.iti_27849_u2_monreal_romero_juan_carlos;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorFragment extends Fragment {

    private Button A, B, trian, det, inv, tran, adj, rang, diag, pow_btn,
            seven, eight, nine, four, five, six, one, two, three,
            plus, minus, asterisk, solve, fillMatrices;
    private EditText inputData;
    private TextView outputData;
    private EditText A_0_0, A_0_1, A_0_2, A_1_0, A_1_1, A_1_2, A_2_0, A_2_1, A_2_2;
    private EditText B_0_0, B_0_1, B_0_2, B_1_0, B_1_1, B_1_2, B_2_0, B_2_1, B_2_2;
    private ArrayList<EditText> matAList, matBList;
    private int[][] matA = new int[3][3];
    private int[][] matB = new int[3][3];
    private double[][] temp = new double[3][3];
    private boolean isInverseActive = false;
    private Button drawInverse;


    public CalculatorFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View newView = inflater.inflate(R.layout.fragment_calculator, container, false);
        solve = (Button) newView.findViewById(R.id.solve);
        drawInverse = newView.findViewById(R.id.drawInvMatrix);
        A = (Button) newView.findViewById(R.id.A);
        B = (Button) newView.findViewById(R.id.B);
        trian = (Button) newView.findViewById(R.id.trian);
        det = (Button) newView.findViewById(R.id.det);
        inv = (Button) newView.findViewById(R.id.inv);
        tran = (Button) newView.findViewById(R.id.tran);
        adj = (Button) newView.findViewById(R.id.adj);
        rang = (Button) newView.findViewById(R.id.rang);
        diag = (Button) newView.findViewById(R.id.diag);
        pow_btn = (Button) newView.findViewById(R.id.pow_btn);
        seven = (Button) newView.findViewById(R.id.seven);
        eight = (Button) newView.findViewById(R.id.eight);
        nine = (Button) newView.findViewById(R.id.nine);
        four = (Button) newView.findViewById(R.id.four);
        five = (Button) newView.findViewById(R.id.five);
        six = (Button) newView.findViewById(R.id.six);
        one = (Button) newView.findViewById(R.id.one);
        two = (Button) newView.findViewById(R.id.two);
        three = (Button) newView.findViewById(R.id.three);
        plus = (Button) newView.findViewById(R.id.plus);
        minus = (Button) newView.findViewById(R.id.minus);
        asterisk = (Button) newView.findViewById(R.id.asterisk);
        fillMatrices = newView.findViewById(R.id.fillMatrices);
        
        inputData = (EditText) newView.findViewById(R.id.inputData);
        outputData = (TextView) newView.findViewById(R.id.outputData);
        matAList = new ArrayList<>();
        matAList.add((EditText) newView.findViewById(R.id.A_0_0));
        matAList.add((EditText) newView.findViewById(R.id.A_0_1));
        matAList.add((EditText) newView.findViewById(R.id.A_0_2));
        matAList.add((EditText) newView.findViewById(R.id.A_1_0));
        matAList.add((EditText) newView.findViewById(R.id.A_1_1));
        matAList.add((EditText) newView.findViewById(R.id.A_1_2));
        matAList.add((EditText) newView.findViewById(R.id.A_2_0));
        matAList.add((EditText) newView.findViewById(R.id.A_2_1));
        matAList.add((EditText) newView.findViewById(R.id.A_2_2));
        matBList = new ArrayList<>();
        matBList.add((EditText) newView.findViewById(R.id.B_0_0));
        matBList.add((EditText) newView.findViewById(R.id.B_0_1));
        matBList.add((EditText) newView.findViewById(R.id.B_0_2));
        matBList.add((EditText) newView.findViewById(R.id.B_1_0));
        matBList.add((EditText) newView.findViewById(R.id.B_1_1));
        matBList.add((EditText) newView.findViewById(R.id.B_1_2));
        matBList.add((EditText) newView.findViewById(R.id.B_2_0));
        matBList.add((EditText) newView.findViewById(R.id.B_2_1));
        matBList.add((EditText) newView.findViewById(R.id.B_2_2));

        A.setOnClickListener(view -> {
            inputData.append("A");
        });
        B.setOnClickListener(view -> {
            inputData.append("B");
        });
        A.setOnClickListener(view -> {
            inputData.append("A");
        });
        trian.setOnClickListener(view -> {
            inputData.append("trian()");
        });
        det.setOnClickListener(view -> {
            inputData.append("det()");
        });
        inv.setOnClickListener(view -> {
            inputData.append("inv()");
        });
        tran.setOnClickListener(view -> {
            inputData.append("tran()");
        });
        adj.setOnClickListener(view -> {
            inputData.append("adj()");
        });
        rang.setOnClickListener(view -> {
            inputData.append("rang()");
        });
        diag.setOnClickListener(view -> {
            inputData.append("diag()");
        });
        pow_btn.setOnClickListener(view -> {
            inputData.append("^2");
        });
        seven.setOnClickListener(view -> {
            inputData.append("7");
        });
        eight.setOnClickListener(view -> {
            inputData.append("8");
        });
        nine.setOnClickListener(view -> {
            inputData.append("9");
        });
        plus.setOnClickListener(view -> {
            inputData.append("+");
        });
        four.setOnClickListener(view -> {
            inputData.append("4");
        });
        five.setOnClickListener(view -> {
            inputData.append("5");
        });
        six.setOnClickListener(view -> {
            inputData.append("6");
        });
        minus.setOnClickListener(view -> {
            inputData.append("-");
        });
        one.setOnClickListener(view -> {
            inputData.append("1");
        });
        two.setOnClickListener(view -> {
            inputData.append("2");
        });
        three.setOnClickListener(view -> {
            inputData.append("3");
        });
        asterisk.setOnClickListener(view -> {
            inputData.append("*");
        });
        solve.setOnClickListener( view -> {
            isInverseActive = false;
            if (isMatricesValid()){
                outputData.setText("RESULTS");
                String input = inputData.getText().toString();
                //TODO: Clean the inputData
                sanitizeInput(input);
            } else {
                Toast.makeText(view.getContext(), "Please fill the Matrices!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        fillMatrices.setOnClickListener( view -> {

            Random random = new Random();
            Random random2 = new Random();

            for (EditText editText : matAList) {
                String r1 = String.valueOf(random.nextInt(100) + 1);
                editText.setText(r1);
            }
            for (EditText element : matBList) {
                String r2 = String.valueOf(random2.nextInt(100) + 1);
                element.setText(r2);
            }
            inputData.requestFocus();
        });
        
        drawInverse.setOnClickListener( view -> {
            if (isInverseActive ) {
                Intent intent = new Intent(getContext(),InverseGraphic.class);
                intent.putExtra("list", temp);
                startActivity(intent);
            }else {
                Toast.makeText(getContext(), "First calculate the inverse on any matrix!", Toast.LENGTH_SHORT).show();
            }
        });

        return newView;
    }
    /**
     * Iterate over the Matrix A and B to check
     * if one row is empty then return false
     * @return true | false
     */
    private boolean isMatricesValid() {
        //TODO: Iterate over matrices
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matAList.get(counter).getText().toString().isEmpty()){
                    return false;
                }
                matA[i][j] = Integer.parseInt(matAList.get(counter).getText().toString());
                counter++;
            }
        }
        counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matBList.get(counter).getText().toString().isEmpty()){
                    return false;
                }
                matB[i][j] = Integer.parseInt(matBList.get(counter).getText().toString());
                counter++;
            }
        }
        return true;
    }

    /**
     * Sanitize the input of the user to find the expression to be solved
     * It's divided in two primary functionalities
     * The first one handle the normal operation using the method: {@link #doOperation(String, String)}
     * The second one handle the functions of the matrix using the method: {@link #doFunction(String, String)}
     * It will ignore everything else
     * @param inputData By the user to be sanitized
     */
    private void sanitizeInput(String inputData) {

        if (!inputData.isEmpty()){
            if (inputData.contains("+") || inputData.contains("*") || inputData.contains("-")){
                Pattern pattern = Pattern.compile("[+*-]");
                Matcher matcher = pattern.matcher(inputData);
                while (matcher.find()){
                    if(!matcher.group().isEmpty()){
                        String operator = matcher.group();
                        if (!doOperation(operator, inputData)){
                            Toast.makeText(this.getContext(), "Please ENTER VALID DATA", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }else {
                Pattern pattern = Pattern.compile("(\\w+)\\s*\\(");
                Matcher matcher = pattern.matcher(inputData);
                while (matcher.find()){
                    System.out.println(matcher.groupCount());
                    if (matcher.groupCount() == 1){
                        String func = matcher.group(1).toString();
                        if (!doFunction(func, inputData)){
                            Toast.makeText(this.getContext(), "Please ENTER VALID DATA", Toast.LENGTH_SHORT).show();
                        }
                    }
//                    System.out.println(matcher.group(1).toString());
//                    System.out.println(matcher.group(2).toString());
                }
            }
        }else {
            Toast.makeText(this.getContext(), "Please ENTER VALID DATA", Toast.LENGTH_SHORT).show();
            outputData.setText("ERROR!!! Invalid input");
        }
    }

    /**
     * Handle the functions detected in {@link #sanitizeInput(String)}
     * The function is implemented in a switch case and then the
     * matrix used is detected
     * @param func function to be used
     * @param inputData input of the user
     * @return true | false if the functions it??s not valid
     */
    private boolean doFunction(String func, String inputData){
        MatrixOperations operations = new MatrixOperations();
        switch (func){
            case "det":
                if (inputData.contains("A")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matA,3));
                }
                if (inputData.contains("B")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matB,3));
                }
                break;
            case "trian":
                if (inputData.contains("A")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matA,3));
                }
                if (inputData.contains("B")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matB,3));
                }
                break;
           case "rang":
                if (inputData.contains("A")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matA,3));
                }
                if (inputData.contains("B")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matB,3));
                }
                break;
           case "inv":
                if (inputData.contains("A")){
                    isInverseActive = true;
                    fillTempMatrix(matA);
                    outputData.setText("Result: " + display(operations.inverse(temp)));
                }
                if (inputData.contains("B")){
                    fillTempMatrix(matB);
                    isInverseActive = true;
                    outputData.setText("Result: " + display(operations.inverse(temp)));
                }
                break;
           case "tran":
                if (inputData.contains("A")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matA,3));
                }
                if (inputData.contains("B")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matB,3));
                }
                break;
           case "diag":
                if (inputData.contains("A")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matA,3));
                }
                if (inputData.contains("B")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matB,3));
                }
                break;
           case "adj":
               int [][] adj = new int[3][3];
               if (inputData.contains("A")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matA,3));
                }
                if (inputData.contains("B")){
                    outputData.setText("Result: " + operations.determinantOfMatrix( matB,3));
                }
               break;
            default:
                return false;
        }
        return true;
    }

    /**
     * Handle the operator detected in {@link #sanitizeInput(String)}
     * The operator is implemented in a switch case and then the
     * order or the matrix is detected.
     * @param operator  to be used
     * @param inputData input of the user
     * @return true | false if the operator it??s not valid
     */
    private boolean doOperation(String operator, String inputData){
        MatrixOperations operations = new MatrixOperations();
        String[] aux;
        System.out.println(operator);
        String o;
        switch (operator){
            case "+":
                aux = inputData.split("\\+");
                if (aux.length != 2) return false;
                else {
                    if (aux[0].equals("A")) {
                        o = "Results :" + Arrays.deepToString(operations.addition(matA, matB));
                        //return true;
                    }else {
                        o = "Results :" + Arrays.deepToString(operations.addition(matB, matA));
                    }
                    outputData.setText(o);
                    Toast.makeText(this.getContext(), outputData.getText() , Toast.LENGTH_LONG).show();
                }

                break;
            case "-":
                aux = inputData.split("\\-");
                if (aux.length != 2) return false;
                else {
                    if (aux[0].equals("A")) {
                        o = "Results :" + Arrays.deepToString(operations.substraction(matA, matB));
                    }else {
                        o = "Results :" + Arrays.deepToString(operations.substraction(matB, matA));
                    }
                    outputData.setText(o);
                    Toast.makeText(this.getContext(), outputData.getText() , Toast.LENGTH_LONG).show();
                }
                break;
            case "*":
                aux = inputData.split("\\*");
                if (aux.length != 2) return false;
                else {
                    if (aux[0].equals("A")) {
                        o = "Results :" + Arrays.deepToString(operations.multiply(matA, matB));
                    }else {
                        o = "Results :" + Arrays.deepToString(operations.multiply(matB, matA));
                    }
                    outputData.setText(o);
                    Toast.makeText(this.getContext(), outputData.getText() , Toast.LENGTH_LONG).show();
                }
                break;
            default:
                return false;
        }
        return true;
    }

    private String display(double[][] mat){
        String stringOut = "[";
        for (int i = 0; i < 3; i++) {
            stringOut += "[";
            for (int j = 0; j < 3; j++) {
                if (j == 2)
					stringOut += String.format("%.2f ", mat[i][j]);
				else
                	stringOut += String.format("%.2f, ", mat[i][j]);
            }
            stringOut += "]\n";
        }
        stringOut += "]";
        return stringOut;
    }

    private String display(int[][] mat){
        String stringOut = "[";
        for (int i = 0; i < 3; i++) {
            stringOut += "[";
            for (int j = 0; j < 3; j++) {
                if (j == 2)
					stringOut += String.format("%.2f ", mat[i][j]);
				else
                	stringOut += String.format("%.2f, ", mat[i][j]);
            }
            stringOut += "]\n";
        }
        stringOut += "]";
        return stringOut;
    }

    private void fillTempMatrix(int[][] temp){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.temp[i][j] = temp[i][j];
            }
        }
    }
    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }
}