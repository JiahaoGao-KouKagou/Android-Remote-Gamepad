package pers.jiahaogao.phonekeymouse.control;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import pers.jiahaogao.phonekeymouse.lib.KeyValue;
import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public final class RockerArrowView extends RockerView{
    public RockerArrowView(Context context) {
        super(context);
    }

    public RockerArrowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        rangeActs = new RangeAct[4];
        rangeActs[0] = new RangeAct(KeyValue.LEFT);
        rangeActs[1] = new RangeAct(KeyValue.UP);
        rangeActs[2] = new RangeAct(KeyValue.RIGHT);
        rangeActs[3] = new RangeAct(KeyValue.DOWN);
    }
}
