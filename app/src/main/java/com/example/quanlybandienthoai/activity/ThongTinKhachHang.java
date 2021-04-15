package com.example.quanlybandienthoai.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlybandienthoai.R;
import com.example.quanlybandienthoai.ultil.CheckConnection;

public class ThongTinKhachHang extends AppCompatActivity {

    static final String Database_Name = "db_qlbh.sqlite";

    SQLiteDatabase database;
    DatabaseConnection db;

    EditText editTenKH ,edtEmail , edtSoDienThoai , edtGhiChu;
    Button btnXacNhanDonHang , btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        AnhXa();
        CanCel();
        EventButtonXacNhan();

    }

    private void CanCel()
    {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void EventButtonXacNhan() {
        btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten = editTenKH.getText().toString().trim();
                String sdt = edtSoDienThoai.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String ghiChu = edtGhiChu.getText().toString();

                if(ten.length() > 0  && sdt.length() > 0 && email.length() > 0)
                {

                   Boolean checkInsertData = db.insertThongTinDonHang(ten,sdt,email,ghiChu);

                    if(checkInsertData == true)
                        Toast.makeText(getApplicationContext(),
                            "New Entry inserted" ,
                                Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),
                                "NO Entry inserted" ,
                                Toast.LENGTH_SHORT).show();

                    db.close();



                    database.close();






                }else
                {
                    CheckConnection.showToast_Short(getApplicationContext(),"Hãy điền thông tin đầy đủ !");
                }
            }
        });
    }

    private void AnhXa() {
        editTenKH = findViewById(R.id.edt_TenKH);
        edtEmail = findViewById(R.id.edt_Email);
        edtSoDienThoai = findViewById(R.id.edt_SoDienThoai);
        edtGhiChu = findViewById(R.id.edt_GhiChu);
        btnXacNhanDonHang = findViewById(R.id.buttonXacNhanDonhang);
        btnTroVe = findViewById(R.id.buttonTroVe);
        database = DatabaseConnection.initDatabase(this,Database_Name);
        db = new DatabaseConnection(this);
    }

}