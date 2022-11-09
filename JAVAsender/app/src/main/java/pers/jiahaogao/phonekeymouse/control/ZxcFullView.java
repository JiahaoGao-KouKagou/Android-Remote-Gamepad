package pers.jiahaogao.phonekeymouse.control;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public final class ZxcFullView extends KeyView {
    public ZxcFullView(Context context) {
        super(context);
    }

    public ZxcFullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        rangeActs = new RangeAct[3];
        rangeActs[0] = new RangeAct('Z');
        rangeActs[1] = new RangeAct('X');
        rangeActs[2] = new RangeAct('C');
    }
}
