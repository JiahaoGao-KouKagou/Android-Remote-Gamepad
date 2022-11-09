package pers.jiahaogao.phonekeymouse.control;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public final class RockerWasdView extends RockerView {
    public RockerWasdView(Context context) {
        super(context);
    }

    public RockerWasdView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        rangeActs = new RangeAct[4];
        rangeActs[0] = new RangeAct('A');  // LEFT
        rangeActs[1] = new RangeAct('W');  // UP
        rangeActs[2] = new RangeAct('D');  // RIGHT
        rangeActs[3] = new RangeAct('S');  // DOWN
    }
}
