package pers.jiahaogao.phonekeymouse;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pers.jiahaogao.phonekeymouse.lib.KeyValue;
import pers.jiahaogao.phonekeymouse.lib.LibClass;
import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public class ZxcRightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxcright);

        RangeAct rangeActShift = new RangeAct(KeyValue.GAMEPAD_X);
        LibClass.bindBtnOnClick(((Button) findViewById(R.id.btn_zxcRightShift)), rangeActShift);

    }

}
