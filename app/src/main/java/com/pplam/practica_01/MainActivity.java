package com.pplam.practica_01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Convertidor de Temperatura");

        // instancia de la clase temperatura
        final Temperature temperature = new Temperature();

        // Declaracion de variables con sus respectivos componentes
        final EditText editTextGrades = (EditText)findViewById(R.id.etDegrees);
        final RadioButton radioButtonCelsius = (RadioButton)findViewById(R.id.rbCelsiusFarenheit);
        final RadioButton radioButtonFarenheit = (RadioButton)findViewById(R.id.rbFarenheitCelsius);
        final TextView resultTextView = (TextView)findViewById(R.id.resultTextView);
        Button buttonConvert = (Button)findViewById(R.id.btnConvertDegrees);

        /**
         * @description capturamos el evento click del boton convertir
         * @param view
         * @return none
         * */
        buttonConvert.setOnClickListener(view -> {
            String inputTemperature = editTextGrades.getText().toString();
            if(inputTemperature.matches("")) {
                generateAlert("La temperatura se encuetra vacia");
                editTextGrades.requestFocus();
            }
            else {
                if(!(radioButtonClick(radioButtonCelsius) || radioButtonClick(radioButtonFarenheit))) {
                    generateAlert("La cantidad de grados existen pero especifique el tipo de cambio");
                } else {
                    try {
                        temperature.setTemperature(Double.parseDouble(inputTemperature));
                        if(radioButtonClick(radioButtonCelsius))
                            resultTextView.setText(inputTemperature + " celsius son " + temperature.toFarenheit() + " farenheit.");
                        else if(radioButtonClick(radioButtonFarenheit))
                            resultTextView.setText(inputTemperature + " farenheit son " + temperature.toCelsius() + " celsius.");
                    } catch (Exception e) {
                        generateAlert("El contenido de la cadena no es valido");
                    }
                }
            }
        });
    }

    /**
     * @description retornamos el radiobutton pulsado por el usuario
     * @param view
     * @return boolean
     **/
    private boolean radioButtonClick(View view) {
        return ((RadioButton) view).isChecked();
    }

    /**
     * @description creamos una nueva alerta con un mensaje personalizado por el usuario
     * @param message
     * @return void
     **/
    private void generateAlert(String message) {
        new AlertDialog.Builder(MainActivity.this).setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null).show();
    }
}