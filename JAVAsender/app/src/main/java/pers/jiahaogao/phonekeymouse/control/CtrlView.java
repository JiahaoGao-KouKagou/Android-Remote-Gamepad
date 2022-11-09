package pers.jiahaogao.phonekeymouse.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pers.jiahaogao.phonekeymouse.lib.KeyState;
import pers.jiahaogao.phonekeymouse.lib.LibClass;
import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public abstract class CtrlView extends SurfaceView implements SurfaceHolder.Callback {

    protected float[] focusAbsRect = new float[2];
    protected float focusRadius;

    protected RangeAct[] rangeActs;

    protected SurfaceHolder surfaceHolder = this.getHolder();
    protected Canvas canvas;
    protected final Paint paintBackground = new Paint();
    protected final Paint paintFocus = new Paint();


    // Constructor (used in java when new)
    public CtrlView(Context context) {
        super(context);
    }

    // Constructor (used in xml)
    public CtrlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
    }


    protected abstract void loadBasicPattern();

    protected abstract int[] inWhoseRange();

    protected void doAct(int[] inRange, RangeAct[] rangeActs) {
        boolean isInRange = false;
        for (int i = 0; i < rangeActs.length; i++) {
            // find in rangeActs

            for (int j = 0; j < inRange.length; j++) {
                if (inRange[j] == i) {
                    // if is in its range
                    isInRange = true;
                    if (!rangeActs[i].isPressed()) {
                        // if has not been pressed
                        rangeActs[i].act(KeyState.ACT_DOWN);
                    }
                    break;
                }
            }

            if (false == isInRange) {
                // if not in its range
                if (rangeActs[i].isPressed()) {
                    // if has been pressed
                    rangeActs[i].act(KeyState.ACT_UP);
                }
            }

            isInRange = false;
        }
    }

}
