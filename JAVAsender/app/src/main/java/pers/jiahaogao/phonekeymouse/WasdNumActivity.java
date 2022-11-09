package pers.jiahaogao.phonekeymouse;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pers.jiahaogao.phonekeymouse.lib.KeyValue;
import pers.jiahaogao.phonekeymouse.lib.LibClass;
import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public class WasdNumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wasdnum);

        RangeAct rangeActJ = new RangeAct(KeyValue.NUMPAD1);
        RangeAct rangeActK = new RangeAct(KeyValue.NUMPAD2);
        RangeAct rangeActL = new RangeAct(KeyValue.NUMPAD3);
        RangeAct rangeActU = new RangeAct(KeyValue.NUMPAD4);
        RangeAct rangeActI = new RangeAct(KeyValue.NUMPAD5);
        RangeAct rangeActO = new RangeAct(KeyValue.NUMPAD6);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_numJ)), rangeActJ);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_numK)), rangeActK);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_numL)), rangeActL);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_numU)), rangeActU);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_numI)), rangeActI);
        LibClass.bindBtnOnTouch(((Button) findViewById(R.id.btn_numO)), rangeActO);
    }

}
