package com.example.bai1sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btLuu, btLay, btXoaKey, btXoaAll;
    private final String Name = "name";
    private final String QueQuan = "home";
    private final String Tuoi = "age";
    private final String Single = "single";
    private final String Weight = "weight";
    private final  String SHARED_PREFRENCES_NAME = "thongTin";

    //trả về main , tên class hiện tại nó đang đứng
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLuu = findViewById(R.id.btLuu);
        btLay = findViewById(R.id.btLayData);
        btXoaKey = findViewById(R.id.btXoaData);
        btXoaAll = findViewById(R.id.btXoaDataAll);

        btLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

        btLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layData();
            }
        });

        btXoaKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaDataKey(Name);
                xoaDataKey(Single);
                layData();
            }
        });

        btXoaAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaDataAll();
            }
        });
    }

    public void addData(){

        //Khởi tạo 1 SHARED_PREFERENCES
        //ĐỐI số 1 tên mà bạn lưu dl , tất cả dl sẽ đưa vào file đó
        //Mode_private dl lưu xuống share file thongTin thì chỉ ứng dụng này đọc đc ứng dụng khác không truy suất và đọc được
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE);

        //ĐƯA dữ liệu vào file thongTin
        //đưa dl hoặc update dl khởi tạo 1 edittor ,dạng như 1 công cụ soạn thảo , công cụ đưa dl vào file
        //mỏ file ra
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //đưa dl vào
        editor.putString(Name, "Vi Ngọc Ánh");
        editor.putString(QueQuan, "Thái Nguyên");
        editor.putInt(Tuoi, 20);
        editor.putBoolean(Single, false);
        editor.putLong(Weight, 55);

        //khi mở file đưa dl vào nhưng chưa apply hoặc commit, dl ko đc đưa vào, ko có thay đổi, file trống,
        //khi gọi apply hoặc comit  dl đc đưa vào và lưu lại trạng thái đc đưa vào
        //dl đã đưa vào file xml, nhưng chưa lưu lại trạng thái
        //apply(), hđ theo cơ chế bất đồng bộ, không trả về true hoặc false
        editor.apply();
        Toast.makeText(MainActivity.this, "Lưu data thành công", Toast.LENGTH_SHORT).show();
        //hoặc // commit hđ theo cơ chế đồng bộ, khi bấm trả về true hoặc false
        //editor.commit();

        //khi bạn muốn sửa tên vs tuổi, commit hđ cơ chế đồng bộ, ai vào trc thực hiện trước rồi thực hiện tiếp
        //apply cơ chế bất đồng bộ, thực hiện đồng thời (song song), không cần phải đợi (NÊN SD APPLY)
    }

    public void layData(){

        //vào file lấy dl ra
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE);

        //editor dùng đến chỉnh sửa còn khi lấy ra thi không cần
        String ten = sharedPreferences.getString(Name, "vna");
        String queQuan = sharedPreferences.getString(QueQuan, "tn");
        int tuoi = sharedPreferences.getInt(Tuoi, 1);
        boolean honNhan = sharedPreferences.getBoolean(Single, false);
        long canNang = sharedPreferences.getLong(Weight, 60);
        String sdt = sharedPreferences.getString("sdt", "0347924941");

        Log.d(TAG, "Thông tin cá nhân: " + "Tên :" +ten+
                                                "\nTuổi :" +tuoi+
                                                "\nQuê Quán :" +queQuan+
                                                "\nTình Trạng Hôn Nhân :" +honNhan+
                                                "\nCân Nặng :" +canNang+
                                                "\nSố Điện Thoại :" +sdt);


    }

    public void xoaDataKey(String key){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE);

        //xóa thì phải tạo 1 editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(key);

        editor.apply();
    }

    public void xoaDataAll(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }

}
