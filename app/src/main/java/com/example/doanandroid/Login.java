package com.example.doanandroid;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity{
    private EditText editUserName;
    private EditText editPassWord;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        //ánh xạ
        editUserName = findViewById(R.id.editUser);
        editPassWord = findViewById(R.id.editPass);
        btnLogin = findViewById(R.id.btnLogin);
        //
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUserName.getText().toString();
                String password = editPassWord.getText().toString();
                //
                if(username.equals("admin")&& password.equals("admin1234")){
                    Intent MyIntent = new Intent(Login.this, MainActivity.class);
                    startActivity(MyIntent);

                }else{
                    Toast.makeText(Login.this,"Invalid Ussername or Password",Toast.LENGTH_SHORT).show();
                }



            }

        });
    }


}
