package com.limpag.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private EditText numberInput;
    private double valueOne = Double.NaN;
    private double valueTwo;
    private String currentOperation;

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);
        numberInput = findViewById(R.id.numberInput);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> performOperation("+"));

        Button subtractButton = findViewById(R.id.subtractButton);
        subtractButton.setOnClickListener(v -> performOperation("-"));

        Button multiplyButton = findViewById(R.id.multiplyButton);
        multiplyButton.setOnClickListener(v -> performOperation("*"));

        Button divideButton = findViewById(R.id.divideButton);
        divideButton.setOnClickListener(v -> performOperation("/"));

        Button equalsButton = findViewById(R.id.equalsButton);
        equalsButton.setOnClickListener(v -> calculateResult());

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v -> resetCalculator());
    }

    private void performOperation(String operation) {
        if (!Double.isNaN(valueOne)) {
            valueTwo = getNumberFromInput();
            calculateResult();
        } else {
            valueOne = getNumberFromInput();
        }
        currentOperation = operation;
        numberInput.setText("");
    }

    private void calculateResult() {
        if (!Double.isNaN(valueOne)) {
            valueTwo = getNumberFromInput();
            switch (currentOperation) {
                case "+":
                    valueOne += valueTwo;
                    break;
                case "-":
                    valueOne -= valueTwo;
                    break;
                case "*":
                    valueOne *= valueTwo;
                    break;
                case "/":
                    valueOne /= valueTwo;
                    break;
            }
            resultText.setText(decimalFormat.format(valueOne));
            valueOne = Double.NaN;
            currentOperation = null;
        }
    }

    private double getNumberFromInput() {
        String inputText = numberInput.getText().toString();
        return inputText.isEmpty() ? 0 : Double.parseDouble(inputText);
    }

    private void resetCalculator() {
        valueOne = Double.NaN;
        valueTwo = 0;
        currentOperation = null;
        resultText.setText("0.00");
        numberInput.setText("");
    }
}
