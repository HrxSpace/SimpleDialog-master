package com.kaka.simpledialog_master;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kaka.dialoglib.SimpleDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.open_dialog).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_dialog:
                final SimpleDialog.Builder sb= new SimpleDialog.Builder(MainActivity.this);
                        sb.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher))
                        .setTitleText("大标题")
                        .setSubTitleText("小标题")
                        .setContentText("正文\n内容")
                        .setRightBtnText("右按键")
                        .setMiddleBtnText("中按键")
                        .setLeftBtnText("左按键")
                        .setTitleGravity(SimpleDialog.PanelGravity.LEFT)
                        .setCanceledOnTouchOutside(true)
                        .setOnRightClicked(new SimpleDialog.OnRightClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                Toast.makeText(MainActivity.this, "右按键--"+sb.getInputText(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setOnLeftClicked(new SimpleDialog.OnLeftClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                Toast.makeText(MainActivity.this, "左按键", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setOnMiddleClicked(new SimpleDialog.OnMiddleClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                Toast.makeText(MainActivity.this, "中按键", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build().show();
                break;
        }
    }

}
