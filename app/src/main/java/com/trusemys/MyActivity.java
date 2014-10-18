package com.trusemys;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyActivity extends ActionBarActivity {
    EditText ed_1;
    TextView tv;
    TextView tv_2;
    TextView tv_3;
    GridView gvMain;
    char [] resul;
    ArrayAdapter<String> adapter;
    String[] data = {"й","м","о","в","і","р","н",
                     "а","б","г","д","е","ё","ж",
                     "з","и","к","л","п","с","т",
                     "у","ф","х","ц","ч","ш","щ",
                     "ъ","ы","ь","э","ю","я","ґ",
                     "є","ї","!","?",",","."," "};

    char table [][] = new char[][] {{'й','м','о','в','і','р','н'},
                                    {'а','б','г','д','е','ё','ж'},
                                    {'з','и','к','л','п','с','т'},
                                    {'у','ф','х','ц','ч','ш','щ'},
                                    {'ъ','ы','ь','э','ю','я','ґ'},
                                    {'є','ї','!','?',',','.',' '}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my);
        ed_1 = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        tv_2 = (TextView) findViewById(R.id.textView2);




        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, data);
        gvMain = (GridView) findViewById(R.id.gridView);
        gvMain.setAdapter(adapter);
        adjustGridView();
    }

    private void adjustGridView() {
        gvMain.setNumColumns(7);
        gvMain.setVerticalSpacing(5);
        gvMain.setHorizontalSpacing(5);
    }

    public void encrypt(View v)
    {
        String str = ed_1.getText().toString();
        if(!str.isEmpty())
        {
            resul = new char[str.length()];
            for (int i = 0; i < str.length(); i++ )
            {
                resul[i] = encode(str.charAt(i));
            }
            String result = new String(resul);
            tv.setText(result);
        }
    }

    public void decrypt(View v)
    {
        String str = tv.getText().toString();
        if(!str.isEmpty())
        {
            resul = new char[str.length()];
            for (int i = 0; i < str.length(); i++ )
            {
                resul[i] = decode(str.charAt(i));
            }
            String result = new String(resul);
            tv_2.setText(result);
        }
    }

    public char encode (char c)
    {
        char temp = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 7; j++)
            {
                if(c == table[i][j])
                {
                    if(i == 5 )
                      temp = table[0][j];
                    else
                        temp = table[i+1][j];
                }
            }
        return temp;
    }
    public  char decode (char c)
    {
        char temp = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 7; j++)
            {
                if(c == table[i][j])
                {
                    if(i == 0 )
                        temp = table[5][j];
                    else
                        temp = table[i-1][j];
                }
            }
        return temp;
    }

    public  void clear (View v)
    {
        tv.setText("");
        tv_2.setText("");
        ed_1.setText("");
    }
}
