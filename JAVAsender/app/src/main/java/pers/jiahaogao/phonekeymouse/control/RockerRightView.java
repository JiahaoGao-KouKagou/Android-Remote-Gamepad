package pers.jiahaogao.phonekeymouse.control;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import pers.jiahaogao.phonekeymouse.lib.KeyValue;
import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public final class RockerRightView extends RockerView{
    public RockerRightView(Context context) {
        super(context);
    }

    public RockerRightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        rangeActs = new RangeAct[4];
        rangeActs[0] = new RangeAct(KeyValue.GAMEPAD_DPAD_LEFT);    // LEFT
        rangeActs[1] = new RangeAct(KeyValue.GAMEPAD_DPAD_UP);      // UP
        rangeActs[2] = new RangeAct(KeyValue.GAMEPAD_DPAD_RIGHT);   // RIGHT
        rangeActs[3] = new RangeAct(KeyValue.GAMEPAD_DPAD_DOWN);    // DOWN
    }
}
