package com.example.akash_raj.myfilemanager;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Files_Subfolders> arrayList=new ArrayList<>();
    Files_Subfolders yo_bc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread thread=new Thread(){
            @Override
            public synchronized void start() {
                setContentView(R.layout.start_screen);
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    Log.d("Sleeping ",e.toString());
                }
                finally {
                    setContentView(R.layout.activity_main);
                }
            }
        };
     //   thread.start();
        setContentView(R.layout.start_screen);
        final File f=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        yo_bc=new Files_Subfolders(f);
        yo_bc.getSubFiles(arrayList);
        setContentView(R.layout.activity_main);
        TextView cdir=(TextView)findViewById(R.id.curr_dir);
        cdir.setText(yo_bc.getname());
        final MyAdapter myAdapter=new MyAdapter(this,arrayList);
        final ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Files_Subfolders abc=arrayList.get(position);
                if(!abc.is_file()) {
                    abc.getSubFiles(arrayList);
                    myAdapter.notifyDataSetChanged();
                    TextView cdir = (TextView) findViewById(R.id.curr_dir);
                    cdir.setText(abc.getname());
                }
                else
                {
                    Toast.makeText(MainActivity.this,"No Action Defined for files",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
