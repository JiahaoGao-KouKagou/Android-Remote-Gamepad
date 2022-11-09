package pers.jiahaogao.phonekeymouse.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pers.jiahaogao.phonekeymouse.lib.KeyState;
import pers.jiahaogao.phonekeymouse.lib.RangeAct;

public abstract class KeyView extends CtrlView {

    protected final Paint paintSplit = new Paint();
    protected float viewWidth;
    protected float viewHeight;


    public KeyView(Context context) {
        super(context);
    }

    public KeyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @SuppressLint("DrawAllocation")
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);

        viewWidth = (float) getWidth();
        viewHeight = (float) getHeight();

        focusRadius = viewWidth / 12;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paintBackground.setColor(Color.LTGRAY);
        paintSplit.setColor(Color.DKGRAY);
        paintFocus.setColor(Color.BLUE);

        this.canvas = surfaceHolder.lockCanvas();
        loadBasicPattern();
        surfaceHolder.unlockCanvasAndPost(this.canvas);
    }


    @Override
    protected void loadBasicPattern() {
        canvas.drawRect(0, 0, viewWidth, viewHeight, paintBackground);
        canvas.drawLine(viewWidth / 3, 0, viewWidth / 3, viewHeight, paintSplit);
        canvas.drawLine(viewWidth * 2 / 3, 0, viewWidth * 2 / 3, viewHeight, paintSplit);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        canvas = surfaceHolder.lockCanvas();
        loadBasicPattern();

        focusAbsRect[0] = event.getX();
        focusAbsRect[1] = event.getY();

        doAct(inWhoseRange(), rangeActs);

        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            for (int i = 0; i < rangeActs.length; i++) {
                if (rangeActs[i].isPressed()) {
                    rangeActs[i].act(KeyState.ACT_UP);
                }
            }
        } else {
            canvas.drawCircle(focusAbsRect[0], focusAbsRect[1], focusRadius, paintFocus);
        }

        this.surfaceHolder.unlockCanvasAndPost(canvas);
        return true;
    }

    @Override
    protected int[] inWhoseRange() {
        int[] result = {-1, -1};

        final int IN_Z = 0;
        final int IN_X = 1;
        final int IN_C = 2;

        if (focusAbsRect[0] - focusRadius < viewWidth / 3) {
            result[0] = IN_Z;
            if (focusAbsRect[0] + focusRadius > viewWidth / 3) {
                result[1] = IN_X;
            }
        } else if (focusAbsRect[0] - focusRadius < viewWidth * 2 / 3) {
            result[0] = IN_X;
            if (focusAbsRect[0] + focusRadius > viewWidth * 2 / 3) {
                result[1] = IN_C;
            }
        } else {
            result[0] = IN_C;
        }

        return result;
    }

}
