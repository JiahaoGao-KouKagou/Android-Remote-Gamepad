package pers.jiahaogao.phonekeymouse.lib;

public class Coordinate {
    public static float[] toPolar(float[] rect) {
        float r = (float) Math.sqrt(Math.pow(rect[0], 2) + Math.pow(rect[1], 2));
        float theta = (float) Math.atan2(rect[1], rect[0]);
        float[] result = {r, theta};
        return result;
    }

    public static float[] toRect(float[] polar) {
        float[] result = {(float) (polar[0] * Math.cos(polar[1])), (float) (polar[0] * Math.sin(polar[1]))};
        return result;
    }

    public static float[] toRlaRect(float[] absRect, float[] center) {
        float[] result = {absRect[0] - center[0], center[1] - absRect[1]};
        return result;
    }

    public static float[] toAbsolut(float[] rect, float[] center) {
        float[] result = {rect[0] + center[0], center[1] - rect[1]};
        return result;
    }
}
