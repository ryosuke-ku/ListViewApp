package com.example.listviewapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * アクティビティ生成時に呼ばれる
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // レイアウトからリストビューを取得
        ListView listView = (ListView)findViewById(R.id.sample_listview);

        // リストビューに表示する要素を設定
        ArrayList<SampleListItem> listItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
            SampleListItem item = new SampleListItem(bmp, "sample text No. " + String.valueOf(i));
            listItems.add(item);
        }

        // 出力結果をリストビューに表示
        SampleListAdapter adapter = new SampleListAdapter(this, R.layout.samplelist_item, listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
    }

    /**
     * リストビューのタップイベント
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // タップしたアイテムの取得
            ListView listView = (ListView)parent;
            SampleListItem item = (SampleListItem)listView.getItemAtPosition(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Tap No. " + String.valueOf(position));
            builder.setMessage(item.getTitle());
            builder.show();
        }
    };
}