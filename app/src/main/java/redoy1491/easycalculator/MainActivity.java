package redoy1491.easycalculator;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private double firstValue = Double.NaN;
    private double secondValue;
    private char currentOperator;
    private String currentInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
    }

    public void onNumberClick(View view) {
        try {
            Button button = (Button) view;
            currentInput += button.getText().toString();
            tvResult.setText(currentInput);
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    public void onOperatorClick(View view) {
        try {
            Button button = (Button) view;
            if (!Double.isNaN(firstValue)) {
                secondValue = Double.parseDouble(currentInput);
                firstValue = calculateResult(firstValue, secondValue, currentOperator);
                tvResult.setText(String.valueOf(firstValue));
            } else {
                firstValue = Double.parseDouble(currentInput);
            }
            currentOperator = button.getText().toString().charAt(0);
            currentInput = "";
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    public void onDotClick(View view) {
        try {
            if (!currentInput.contains(".")) {
                currentInput += ".";
                tvResult.setText(currentInput);
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    public void onEqualClick(View view) {
        try {
            if (!Double.isNaN(firstValue)) {
                secondValue = Double.parseDouble(currentInput);
                firstValue = calculateResult(firstValue, secondValue, currentOperator);
                tvResult.setText(String.valueOf(firstValue));
                firstValue = Double.NaN;
                currentInput = "";
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    public void onClearClick(View view) {
        try {
            firstValue = Double.NaN;
            secondValue = Double.NaN;
            currentInput = "";
            tvResult.setText("0");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    public void onDeleteClick(View view) {
        try {
            if (currentInput.length() > 0) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                tvResult.setText(currentInput.isEmpty() ? "0" : currentInput);
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }
    //Hi

    private double calculateResult(double firstValue, double secondValue, char operator) {
        try {
            switch (operator) {
                case '+':
                    return firstValue + secondValue;
                case '-':
                    return firstValue - secondValue;
                case '*':
                    return firstValue * secondValue;
                case '/':
                    if (secondValue != 0) {
                        return firstValue / secondValue;
                    } else {
                        showError("Cannot divide by zero");
                        return 0;
                    }
                default:
                    return 0;
            }
        } catch (Exception e) {
            showError(e.getMessage());
            return 0;
        }
    }

    private void showError(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();
    }
}



