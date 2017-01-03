package org.androidtown.batterybrex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        registerReceiver(broadcastReceiver, filter);
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        int receivedCount = 0;

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            receivedCount++;
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                onBatteryChanged(intent);
            } else if (action.equals(Intent.ACTION_BATTERY_LOW)) {
                Toast.makeText(MainActivity.this, "배터리가 없습니다", Toast.LENGTH_SHORT).show();
            } else if (action.equals(Intent.ACTION_BATTERY_OKAY)) {
                Toast.makeText(MainActivity.this, "배터리가 양호상태입니다", Toast.LENGTH_SHORT).show();
            } else if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
                Toast.makeText(MainActivity.this, "전원이 연결 되었습니다", Toast.LENGTH_SHORT).show();
            } else if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
                Toast.makeText(MainActivity.this, "전원이 분리 되었습니다", Toast.LENGTH_SHORT).show();
            }
        }


        public void onBatteryChanged(Intent intent) {
            int plug, status, scale, level, ratio;
            String splug = "";
            String sStatus = "";

            plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_HEALTH_UNKNOWN);
            scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            ratio = level * 100 / scale;

            switch (plug) {
                case BatteryManager.BATTERY_PLUGGED_AC:
                    splug = "AC";
                    break;
                case BatteryManager.BATTERY_PLUGGED_USB:
                    splug = "USB";
                    break;
                default:
                    splug = "Battery";
            }

            switch (status) {
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    sStatus = "충전중";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    sStatus = "충전중아님";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    sStatus = "방전중";
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    sStatus = "만땅 충전";
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    sStatus = "알수 업슨";
                    break;
            }
            String resultStr = String.format("수신 횟 수 :%d\n연결 : %s\n 상태 : s\nlevel : %d%%", receivedCount, splug, sStatus, ratio);
            textView.setText(resultStr);
        }
    };
}
