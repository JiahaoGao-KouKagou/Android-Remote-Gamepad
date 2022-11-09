package pers.jiahaogao.phonekeymouse.lib;


public class RangeAct {

    private int keyValue;
    private boolean isPressed;

    public RangeAct(int keyValue) {
        this.keyValue = keyValue;
        this.isPressed = false;
    }

    private String encode(String state) {
        String result = LibClass.KEY_ACT;
        if (keyValue < 100) {
            result = result + "0";
        }
        result = result + keyValue + state;
        return  result;
    }

    public void act(String state) {
        if (state == KeyState.ACT_DOWN) {
            isPressed = true;
        } else {
            isPressed = false;
        }
        // System.out.println(keyValue + " : " + isPressed);

        LibClass.send(IpInfo.ipv4, IpInfo.portStr, encode(state));
    }

    public boolean isPressed() {
        return isPressed;
    }
}
