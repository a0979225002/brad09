package tw.org.iii.brad.brad08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity {
private SharedPreferences sp;
private SharedPreferences.Editor editor;
private View test5,test6;
//不管事甚麼的父類都是view,所有螢幕都可被案,所以這裡的button也可以簡化為viw來呼叫
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test5 = findViewById(R.id.test5);
        test6 = findViewById(R.id.test6);

        //參數1:由自己命名的黨名,參數二MODE_PRIVATE
        //他負責調出資料
        sp = getSharedPreferences("config",MODE_PRIVATE);
        editor =sp.edit();
    }

    public void text1(View view) {
        //比如他玩到第4關儲存起來
        editor.putInt("stage",4);
        //關掉音樂儲存起來
        editor.putBoolean("sound",false);
        //遊戲id儲存起來
        editor.putString("username","brad");
        editor.commit();//全部儲存出去
    }

    public void text2(View view) {
        //給予預設值
        boolean sound = sp.getBoolean("sound",true);
        int stage = sp.getInt("stage",1);
        String username = sp.getString("username","nobodt");

        Toast.makeText(this,username+":"+stage+":"+sound,Toast.LENGTH_SHORT).show();
    }

    public void text3(View view) {
        try {
            //                                   MODE_PRIVATE改寫掉
            FileOutputStream fout = openFileOutput("brad.txt",MODE_PRIVATE);
            fout.write("hello,world\n1234567\n7654321".getBytes());
           fout.flush();
            fout.close();
            Toast.makeText(this,"Save ok",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.v("brad",e.toString());
            Toast.makeText(this,"Save failuer",Toast.LENGTH_SHORT).show();
        }

    }

    public void text4(View view) {
        FileInputStream fin = null;
        try {
            fin = openFileInput("brad.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine())!=null){
                sb.append(line+"\n");
            }
            fin.close();
            Toast.makeText(this,sb,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(this,sb,Toast.LENGTH_SHORT).show();
        }

    }

    public void text56(View view) {
        Button btn =  (Button)view;
        //使用這方法不用做if判斷,就能判斷是按哪顆按鈕
        Log.v("brad",btn.getText().toString());
        if (view == test5){
            Log.v("brad","text5");
        }else if (view ==test6){
            Log.v("brad","text6");
        }
    }
}
