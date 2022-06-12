package antijuan.beidanci;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import antijuan.beidanci.dao.UserDao;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void reg(View view){

        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

    }


    public void login(View view){

        EditText EditTextname = (EditText)findViewById(R.id.name);
        EditText EditTextpassword = (EditText)findViewById(R.id.password);

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = new UserDao();

                boolean aa = userDao.login(EditTextname.getText().toString(),EditTextpassword.getText().toString());
                int msg = 0;
                if(aa){
                    msg = 1;
                }

                hand1.sendEmptyMessage(msg);


            }
        }).start();


    }
    final Handler hand1 = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            else
            {
                Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_LONG).show();

            }
        }
    };

}
