package pers.jiahaogao.phonekeymouse.control;

// References
//作者：[kqw攻城狮](http://kongqw.github.io/about/index.html)
//出处：[个人站](http://kongqw.com/2016/09/01/2016-09-01-Android%E8%87%AA%E5%AE%9A%E4%B9%89%E6%91%87%E6%9D%86/) | [CSDN](http://blog.csdn.net/q4878802/article/details/52402529)
// https://www.cnblogs.com/huansky/p/11808234.html

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pers.jiahaogao.phonekeymouse.lib.Coordinate;
import pers.jiahaogao.phonekeymouse.lib.KeyState;

public abstract class RockerView extends CtrlView {

    protected float viewRadius;

    protected float[] center = new float[2];
    protected float[] focusPolar = new float[2];
    protected float[] nullAbsRectDiag = new float[2];

    private final Paint paintRocker = new Paint();
    private final Paint paintDirectLine = new Paint();
    private final Paint paintNull = new Paint();


    public RockerView(Context context) {
        super(context);
    }

    public RockerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);

        center[0] = (float) getWidth() / 2;
        center[1] = (float) getHeight() / 2;

        viewRadius = center[0];
        focusRadius = viewRadius / 6;
        nullAbsRectDiag[0] = (float) (center[0] - 1.5 * focusRadius);
        nullAbsRectDiag[1] = (float) (center[0] + 1.5 * focusRadius);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paintBackground.setColor(Color.BLACK);
        paintRocker.setColor(Color.LTGRAY);
        paintDirectLine.setColor(Color.BLACK);
        paintNull.setColor(Color.BLACK);
        paintFocus.setColor(Color.RED);

        this.canvas = surfaceHolder.lockCanvas();
        loadBasicPattern();
        this.canvas.drawCircle(center[0], center[1], focusRadius, paintFocus);
        surfaceHolder.unlockCanvasAndPost(this.canvas);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        canvas = surfaceHolder.lockCanvas();
        loadBasicPattern();

        float[] nowAbsRect = {event.getX(), event.getY()};
        setFocusPolarAndAbsRect(nowAbsRect);

        doAct(inWhoseRange(), rangeActs);

        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            for (int i = 0; i < rangeActs.length; i++) {
                if (rangeActs[i].isPressed()) {
                    rangeActs[i].act(KeyState.ACT_UP);
                }
            }
            canvas.drawCircle(center[0], center[1], focusRadius, paintFocus);
        } else {
            canvas.drawCircle(focusAbsRect[0], focusAbsRect[1], focusRadius, paintFocus);
        }

        this.surfaceHolder.unlockCanvasAndPost(canvas);
        return true;
    }

    @Override
    protected void loadBasicPattern() {
        canvas.drawRect(0, 0, 2 * viewRadius, 2 * viewRadius, paintBackground);
        canvas.drawCircle(center[0], center[1], viewRadius, paintRocker);
        canvas.drawRect(nullAbsRectDiag[0], nullAbsRectDiag[0], nullAbsRectDiag[1], nullAbsRectDiag[1], paintNull);
        canvas.drawLine(0, 0, 2 * viewRadius, 2 * viewRadius, paintDirectLine);
        canvas.drawLine(2 * viewRadius, 0, 0, 2 * viewRadius, paintDirectLine);
    }


    private void setFocusPolarAndAbsRect(float[] nowAbsRect) {
        focusPolar = Coordinate.toPolar(Coordinate.toRlaRect(nowAbsRect, center));
        if (focusPolar[0] >= (viewRadius - focusRadius)) {
            focusPolar[0] = (viewRadius - focusRadius);
        }
        focusAbsRect = Coordinate.toAbsolut(Coordinate.toRect(focusPolar), center);
    }

    @Override
    protected int[] inWhoseRange() {

        int[] result = {-1, -1};
        final int IN_LEFT = 0;
        final int IN_UP = 1;
        final int IN_RIGHT = 2;
        final int IN_DOWN = 3;

        float r = focusPolar[0];
        float theta = focusPolar[1];

        float pi = (float) Math.PI;
        float pi_2 = pi / 2;
        float pi_4 = pi_2 / 2;


        if ((focusAbsRect[0] - focusRadius) <= nullAbsRectDiag[0]
                || (focusAbsRect[1] - focusRadius) <= nullAbsRectDiag[0]
                || (focusAbsRect[0] + focusRadius) >= nullAbsRectDiag[1]
                || (focusAbsRect[1] + focusRadius) >= nullAbsRectDiag[1]) {

            float beta = (float) Math.asin((focusRadius / r));

            if (-pi < theta && theta < -(3 * pi_4)) {
                result[0] = IN_LEFT;
                if (Math.abs(theta + (3 * pi_4)) < beta) {
                    result[1] = IN_DOWN;
                }
            } else if (-(3 * pi_4) < theta && theta < -pi_2) {
                result[0] = IN_DOWN;
                if (Math.abs(theta + (3 * pi_4)) < beta) {
                    result[1] = IN_LEFT;
                }
            } else if (-pi_2 < theta && theta < -pi_4) {
                result[0] = IN_DOWN;
                if (Math.abs(theta + pi_4) < beta) {
                    result[1] = IN_RIGHT;
                }
            } else if (-pi_4 < theta && theta < 0) {
                result[0] = IN_RIGHT;
                if (Math.abs(theta + pi_4) < beta) {
                    result[1] = IN_DOWN;
                }
            } else if (0 < theta && theta < pi_4) {
                result[0] = IN_RIGHT;
                if (Math.abs(theta - pi_4) < beta) {
                    result[1] = IN_UP;
                }
            } else if (pi_4 < theta && theta < pi_2) {
                result[0] = IN_UP;
                if (Math.abs(theta - pi_4) < beta) {
                    result[1] = IN_RIGHT;
                }
            } else if (pi_2 < theta && theta < (3 * pi_4)) {
                result[0] = IN_UP;
                if (Math.abs(theta - (3 * pi_4)) < beta) {
                    result[1] = IN_LEFT;
                }
            } else {
                result[0] = IN_LEFT;
                if (Math.abs(theta - (3 * pi_4)) < beta) {
                    result[1] = IN_UP;
                }
            }

        }

        return result;
    }

}
