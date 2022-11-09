package pers.jiahaogao.phonekeymouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import pers.jiahaogao.phonekeymouse.lib.IpInfo;

public class MainActivity extends AppCompatActivity {

    // 用于页面跳转和传输数据
    private ActivityResultLauncher<Intent> activityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 显式：携带参数跳转
        activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        });

        ((Button) findViewById(R.id.btn_connect)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConnectActivity.class);
                activityLauncher.launch(intent);
            }
        });

        launchActivity(((Button) findViewById(R.id.btn_zxcFull)), ZxcFullActivity.class);
        launchActivity(((Button) findViewById(R.id.btn_zxcLeft)), ZxcLeftActivity.class);
        launchActivity(((Button) findViewById(R.id.btn_zxcRight)), ZxcRightActivity.class);
        launchActivity(((Button) findViewById(R.id.btn_wasdMain)), WasdMainActivity.class);
        launchActivity(((Button) findViewById(R.id.btn_wasdNum)), WasdNumActivity.class);
    }

    private void launchActivity(Button btn, Class<?> target) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != IpInfo.portStr) {
                    Intent intent = new Intent(MainActivity.this, target);
                    activityLauncher.launch(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Have NOT connected WLAN", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}