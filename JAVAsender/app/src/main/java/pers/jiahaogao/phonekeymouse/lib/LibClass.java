package pers.jiahaogao.phonekeymouse.lib;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class LibClass {

    public static final int LENGTH_UDP_PKG = 64;

    public static final String CONNECT_SUCCEED = "1";
    public static final String MOUSE_MOVE = "2";
    public static final String MOUSE_BUTTON = "4";
    public static final String KEY_ACT = "8";


    public static void send(String ipv4, String portStr, String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int port = Integer.parseInt(portStr);
                try {
                    DatagramSocket s = new DatagramSocket();
                    InetAddress destinationAddress = InetAddress.getByName(ipv4);
                    // System.out.println("destination:" + destinationAddress);
                    byte[] message = msg.getBytes();
                    DatagramPacket p = new DatagramPacket(message, message.length, destinationAddress, port);
                    s.send(p);
                    // System.out.println("successfully send" + p);
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @SuppressLint("ClickableViewAccessibility")
    public static void bindBtnOnTouch(Button btn, RangeAct rangeAct) {
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int iAction = motionEvent.getAction();
                if (iAction == MotionEvent.ACTION_DOWN && !rangeAct.isPressed()) {    // 按下
                    rangeAct.act(KeyState.ACT_DOWN);
                } else if (iAction == MotionEvent.ACTION_UP && rangeAct.isPressed()) {    // 弹起
                    rangeAct.act(KeyState.ACT_UP);
                }
                return false;
            }
        });
    }

    public static void bindBtnOnClick(Button btn, RangeAct rangeAct) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rangeAct.isPressed()) {
                    rangeAct.act(KeyState.ACT_UP);
                } else {
                    rangeAct.act(KeyState.ACT_DOWN);
                }
            }
        });
    }
}
