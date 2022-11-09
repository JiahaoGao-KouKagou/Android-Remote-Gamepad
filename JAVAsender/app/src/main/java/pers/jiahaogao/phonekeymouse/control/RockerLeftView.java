package pers.jiahaogao.phonekeymouse.control;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public final class RockerLeftView extends RockerView{
    public RockerLeftView(Context context) {
        super(context);
    }

    public RockerLeftView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        rangeActs = new RangeAct[4];
        rangeActs[0] = new RangeAct('F');  // LEFT
        rangeActs[1] = new RangeAct('T');  // UP
        rangeActs[2] = new RangeAct('H');  // RIGHT
        rangeActs[3] = new RangeAct('B');  // DOWN
    }
}
