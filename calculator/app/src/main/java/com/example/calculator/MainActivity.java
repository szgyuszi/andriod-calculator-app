package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView solutionTxt, resultTxt;
    private MaterialButton btnC, btnOpenBracket, btnCloseBracket;
    private MaterialButton btnDivide, btnMultiply, btnPlus, btnMinus, btnEquals;
    private MaterialButton
    btn7, btn8, btn9,
    btn4, btn5, btn6,
    btn1, btn2, btn3,
    btn0;
    private MaterialButton btnAllClear, btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTxt = findViewById(R.id.result_txt);
        solutionTxt = findViewById(R.id.solution_txt);

        assignButton(btnC, R.id.button_c);
        assignButton(btnOpenBracket, R.id.open_bracket);
        assignButton(btnCloseBracket, R.id.close_bracket);
        assignButton(btnMultiply, R.id.button_multiply);
        assignButton(btnDivide, R.id.divide_symbol);
        assignButton(btnPlus, R.id.button_plus);
        assignButton(btnMinus, R.id.button_minus);
        assignButton(btnEquals, R.id.button_equals);
        assignButton(btn0, R.id.button_0);
        assignButton(btn1, R.id.button_1);
        assignButton(btn2, R.id.button_2);
        assignButton(btn3, R.id.button_3);
        assignButton(btn4, R.id.button_4);
        assignButton(btn5, R.id.button_5);
        assignButton(btn6, R.id.button_6);
        assignButton(btn7, R.id.button_7);
        assignButton(btn8, R.id.button_8);
        assignButton(btn9, R.id.button_9);
        assignButton(btnAllClear, R.id.button_ac);
        assignButton(btnDot, R.id.button_dot);


    }

    private void assignButton(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton materialButton = (MaterialButton) view;
        String btnTxt = materialButton.getText().toString();

        if (btnTxt.equals("AC")) {
            solutionTxt.setText("");
            resultTxt.setText("0");
            return;
        }
        String dataToCalculate = solutionTxt.getText().toString();

        if(btnTxt.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        } else {
            dataToCalculate = dataToCalculate + btnTxt;
        }



        solutionTxt.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Error")) {
            resultTxt.setText(finalResult);
        }


    }

    private String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalRes = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalRes.endsWith(".0")) {
                finalRes = finalRes.replace(".0", "");
            }
            return finalRes;
        } catch (Exception e) {
            return "Error";
        }
    }



}