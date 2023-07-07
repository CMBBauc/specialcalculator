package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultWindow,solutionWindow;
    MaterialButton clearButton, deleteButton;
    MaterialButton zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton;
    MaterialButton additionButton, subtractButton, multiplyButton, divideButton, equalsButton;
    MaterialButton decimalButton, doubleZeroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultWindow = findViewById(R.id.result_window);
        solutionWindow = findViewById(R.id.solution_window);

        assignId(clearButton, R.id.clear_Button);
        assignId(deleteButton, R.id.delete_Button);
        assignId(zeroButton, R.id.zero_Button);
        assignId(oneButton, R.id.one_Button);
        assignId(twoButton, R.id.two_Button);
        assignId(threeButton, R.id.three_Button);
        assignId(fourButton, R.id.four_Button);
        assignId(fiveButton, R.id.five_Button);
        assignId(sixButton, R.id.six_Button);
        assignId(sevenButton, R.id.seven_Button);
        assignId(eightButton, R.id.eight_Button);
        assignId(nineButton, R.id.nine_Button);
        assignId(additionButton, R.id.addition_Button);
        assignId(subtractButton, R.id.subtract_button);
        assignId(multiplyButton, R.id.multiply_Button);
        assignId(divideButton, R.id.divide_Button);
        assignId(equalsButton, R.id.equals_Button);
        assignId(decimalButton, R.id.decimal_Button);
        assignId(doubleZeroButton, R.id.double_ZeroButton);
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionWindow.getText().toString();


        if (buttonText.equals("C")){
            solutionWindow.setText("");
            resultWindow.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solutionWindow.setText(resultWindow.getText());
            return;
        }

        if(buttonText.equals("AC")){
            try {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
            } catch(Exception e){
                dataToCalculate =  "Error: " + e;
            }

        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        solutionWindow.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")){
            resultWindow.setText(finalResult);
        }
    }
    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            //Otherwise return final result vvv
            return context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
        } catch (Exception e){
            return "";
        }
    }

}