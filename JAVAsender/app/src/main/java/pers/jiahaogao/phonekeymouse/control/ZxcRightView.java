package pers.jiahaogao.phonekeymouse.control;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import pers.jiahaogao.phonekeymouse.lib.KeyValue;
import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public final class ZxcRightView extends KeyView {
    public ZxcRightView(Context context) {
        super(context);
    }

    public ZxcRightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        rangeActs = new RangeAct[3];
        rangeActs[0] = new RangeAct(KeyValue.GAMEPAD_A); // Z
        rangeActs[1] = new RangeAct(KeyValue.GAMEPAD_B); // X
        rangeActs[2] = new RangeAct(KeyValue.GAMEPAD_B); // C
    }
}
