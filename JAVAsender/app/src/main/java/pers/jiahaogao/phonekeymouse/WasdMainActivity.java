package pers.jiahaogao.phonekeymouse;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pers.jiahaogao.phonekeymouse.lib.LibClass;
import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public class WasdMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wasdmain);

        RangeAct rangeActJ = new RangeAct('J');
        RangeAct rangeActK = new RangeAct('K');
        RangeAct rangeActL = new RangeAct('L');
        RangeAct rangeActU = new RangeAct('U');
        RangeAct rangeActI = new RangeAct('I');
        RangeAct rangeActO = new RangeAct('O');
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_mainJ)), rangeActJ);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_mainK)), rangeActK);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_mainL)), rangeActL);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_mainU)), rangeActU);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_mainI)), rangeActI);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_mainO)), rangeActO);

    }

}
