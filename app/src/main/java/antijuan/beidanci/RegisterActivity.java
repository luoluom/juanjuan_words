package antijuan.beidanci;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import antijuan.beidanci.dao.UserDao;
import antijuan.beidanci.entity.User;

public class RegisterActivity extends AppCompatActivity {
    EditText name = null;
    EditText username = null;
    EditText password = null;
    EditText passwordAgain = null;
    EditText phone = null;
    EditText age = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordAgain = findViewById(R.id.password_again);

        phone = findViewById(R.id.phone);
        age = findViewById(R.id.age);
    }

    public void reLogin(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }


    public void register(View view){
        String cname = name.getText().toString();
        String cusername = username.getText().toString();
        String cpassword = password.getText().toString();
        String cpasswordAgain = passwordAgain.getText().toString();

        System.out.println(phone.getText().toString());

        String cphone = phone.getText().toString();
        int cgae = Integer.parseInt(age.getText().toString());

        if(!cpassword.equals(cpasswordAgain)){
            Toast.makeText(getApplicationContext(),"两次密码输入不相同",Toast.LENGTH_LONG).show();
            return;
        }
        if(cname.length() < 2 || cusername.length() < 2 || cpassword.length() < 2 ){
            Toast.makeText(getApplicationContext(),"输入信息不符合要求请重新输入",Toast.LENGTH_LONG).show();
            return;
        }


        User user = new User();

        user.setName(cname);
        user.setUsername(cusername);
        user.setPassword(cpassword);
        user.setAge(cgae);
        user.setPhone(cphone);

        new Thread(){
            @Override
            public void run() {

                int msg = 0;

                UserDao userDao = new UserDao();

                User uu = userDao.findUser(user.getName());

                if(uu != null){
                    msg = 1;
                }

                boolean flag = userDao.register(user);
                if(flag){
                    msg = 2;
                }
                hand.sendEmptyMessage(msg);

            }
        }.start();


    }
    final Handler hand = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0)
            {
                Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();

            }
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"该账号已经存在，请换一个账号",Toast.LENGTH_LONG).show();

            }
            if(msg.what == 2)
            {
                //startActivity(new Intent(getApplication(),MainActivity.class));

                Intent intent = new Intent();
                //将想要传递的数据用putExtra封装在intent中
                intent.putExtra("a","註冊");
                setResult(RESULT_CANCELED,intent);
                finish();
            }

        }
    };
}
