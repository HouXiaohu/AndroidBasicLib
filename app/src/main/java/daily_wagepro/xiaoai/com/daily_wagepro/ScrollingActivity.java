package daily_wagepro.xiaoai.com.daily_wagepro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ScrollingActivity extends AppCompatActivity {

    private ScrollZoomListView lv_1;
    private ImageView iv_zoomview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        lv_1 = (ScrollZoomListView) findViewById(R.id.lv_1);
        iv_zoomview = (ImageView) findViewById(R.id.iv_zoomview);
        ArrayAdapter<String> adapter=  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{
                "12312","123123","412312"
        });
        lv_1.setZoomImageView(iv_zoomview);
        lv_1.setAdapter(adapter);

    }
}
