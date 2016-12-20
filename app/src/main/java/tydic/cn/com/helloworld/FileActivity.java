package tydic.cn.com.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileActivity extends Activity {
    ListView listView;
    TextView textView;
    File currentParent;
    File[] currentFiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        listView = (ListView)findViewById(R.id.list);
        textView = (TextView)findViewById(R.id.path);
        File root = new File("/mbt/sdcard");
        if(root.exists()){
            currentParent=root;
            currentFiles=root.listFiles();
            inflateListView(currentFiles);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentFiles[position].isFile())
                    return;
                File[] temp = currentFiles[position].listFiles();
                if(temp.length==0||temp==null){
                    Toast.makeText(FileActivity.this,"当前路径没有文件",Toast.LENGTH_SHORT).show();
                }else{
                    currentParent = currentFiles[position];
                    currentFiles = temp;
                    inflateListView(currentFiles);
                }
            }
        });
        Button button = (Button)findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (!currentParent.getCanonicalPath().equals("/mnt/sdcard")){
                        currentParent=currentParent.getParentFile();
                        currentFiles=currentParent.listFiles();
                        inflateListView(currentFiles);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void inflateListView(File[] files){
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i= 0;i<files.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            if(files[i].isFile()){
                map.put("icon",R.drawable.file);
            }else{
                map.put("icon",R.drawable.folder);
            }
            map.put("filename",files[i].getName());
            listItems.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listItems,R.layout.main2,new String[]{"icon","fileName"},new int[]{R.id.icon,R.id.fileName});
        listView.setAdapter(adapter);
        try{
            textView.setText("当前路径为："+currentParent.getCanonicalPath());
        }catch (Exception e){
            e.printStackTrace();
        }
     }
}
