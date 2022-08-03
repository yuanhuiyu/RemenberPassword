package com.example.remenberpassword;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class LoginActivity extends BaseActivity{
    private SharedPreferences  preference;
    private SharedPreferences.Editor editor;

    private EditText nameEdit;
    private EditText passwordEdit;
    private Button login;

    private CheckBox rememberPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preference= PreferenceManager.getDefaultSharedPreferences(this);
        nameEdit=(EditText) findViewById(R.id.editTextTextPersonName);
        passwordEdit=(EditText)findViewById(R.id.editTextTextPassword);
        rememberPass=(CheckBox) findViewById(R.id.checkBox);
        login=(Button) findViewById(R.id.login);

        boolean isRemember=preference.getBoolean("remember_password",false);

        if(isRemember){//设置保存好的名字和密码
            String name=preference.getString("name","");
            String password=preference.getString("password","");
            nameEdit.setText(name);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LoginActivity","Clicked");
                String name=nameEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                //具有空异常


                if(name.equals("admin")&&password.equals("123456")){
                    //写入持久层
                    editor= preference.edit();
                    if(rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("name",name);
                        editor.putString("password",password);
                    }
                    else {
                        editor.clear();
                    }
                    editor.apply();;
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this,"name or password is invalid!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
