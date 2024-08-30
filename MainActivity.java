package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        String[] units = {"Centimeters", "Meters", "Kilometers", "Inches", "Feet", "Grams", "Kilograms"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double input = Double.parseDouble(inputValue.getText().toString());
                String fromUnit = fromUnitSpinner.getSelectedItem().toString();
                String toUnit = toUnitSpinner.getSelectedItem().toString();
                double result = convertUnits(input, fromUnit, toUnit);
                resultTextView.setText(String.valueOf(result));
            }
        });
    }

    private double convertUnits(double input, String fromUnit, String toUnit) {
        // Length conversions
        if (fromUnit.equals("Centimeters")) {
            if (toUnit.equals("Meters")) return input / 100;
            if (toUnit.equals("Kilometers")) return input / 100000;
            if (toUnit.equals("Inches")) return input / 2.54;
            if (toUnit.equals("Feet")) return input / 30.48;
        }
        if (fromUnit.equals("Meters")) {
            if (toUnit.equals("Centimeters")) return input * 100;
            if (toUnit.equals("Kilometers")) return input / 1000;
            if (toUnit.equals("Inches")) return input * 39.3701;
            if (toUnit.equals("Feet")) return input * 3.28084;
        }
        if (fromUnit.equals("Kilometers")) {
            if (toUnit.equals("Centimeters")) return input * 100000;
            if (toUnit.equals("Meters")) return input * 1000;
            if (toUnit.equals("Inches")) return input * 39370.1;
            if (toUnit.equals("Feet")) return input * 3280.84;
        }
        if (fromUnit.equals("Inches")) {
            if (toUnit.equals("Centimeters")) return input * 2.54;
            if (toUnit.equals("Meters")) return input / 39.3701;
            if (toUnit.equals("Kilometers")) return input / 39370.1;
            if (toUnit.equals("Feet")) return input / 12;
        }
        if (fromUnit.equals("Feet")) {
            if (toUnit.equals("Centimeters")) return input * 30.48;
            if (toUnit.equals("Meters")) return input / 3.28084;
            if (toUnit.equals("Kilometers")) return input / 3280.84;
            if (toUnit.equals("Inches")) return input * 12;
        }

        // Mass conversions
        if (fromUnit.equals("Grams")) {
            if (toUnit.equals("Kilograms")) return input / 1000;
        }
        if (fromUnit.equals("Kilograms")) {
            if (toUnit.equals("Grams")) return input * 1000;
        }

        return input; // Default case: if no conversion needed or invalid units
    }
}
