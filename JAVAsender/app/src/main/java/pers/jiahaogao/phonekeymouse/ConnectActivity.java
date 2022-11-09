package pers.jiahaogao.phonekeymouse;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import pers.jiahaogao.phonekeymouse.lib.IpInfo;
import pers.jiahaogao.phonekeymouse.lib.LibClass;

public class ConnectActivity extends AppCompatActivity {

    // 缓存
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        // get preferences
        preferences = getPreferences(Activity.MODE_PRIVATE);
        if (null != preferences) {
            // put into EditText
            ((EditText) findViewById(R.id.edit_ip)).setText(preferences.getString("ipv4", ""));
            ((EditText) findViewById(R.id.edit_port)).setText(preferences.getString("portStr", ""));
        }

        // btn submit and connect
        ((Button) findViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipv4 = ((EditText) findViewById(R.id.edit_ip)).getText().toString().replaceAll(" ", "");
                String portStr = ((EditText) findViewById(R.id.edit_port)).getText().toString().replaceAll("\\s*", "");
                if (isValidIPv4AndPort(ipv4, portStr)) {
                    String msg = LibClass.CONNECT_SUCCEED;
                    LibClass.send(ipv4, portStr, msg);

                    // write into IpInfo class
                    IpInfo.ipv4 = ipv4;
                    IpInfo.portStr = portStr;

                    // write preferences
                    preferences.edit()
                            .putString("ipv4", ipv4)
                            .putString("portStr", portStr)
                            .commit();
                }
            }
        });

    }

    private boolean isValidIPv4AndPort(String ipv4, String portStr) {
        boolean result = false;
        if (ipv4.length() > 0 || portStr.length() > 0) {
            result = true;
        }
        return result;
    }

}


