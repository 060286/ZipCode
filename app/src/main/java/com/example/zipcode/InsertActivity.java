package com.example.examtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zipcode.model.Country;
import com.example.zipcode.R;

public class InsertActivity extends AppCompatActivity {

    private EditText edtName, edtAddress, edtZipCode;
    private Spinner spnCountry;
    private RadioGroup rdgCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        //ánh xạ
        Button btnInsert = findViewById(R.id.btnInsert);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtZipCode = findViewById(R.id.edtZipCode);
        spnCountry = findViewById(R.id.spnCountry);
        rdgCountry = findViewById(R.id.rdgCountry);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {

                if (validate()) {
                    Country country = new Country();
                    country.setName(edtName.getText().toString());
                    country.setAddress(edtAddress.getText().toString());
                    country.setCountry(spnCountry.getSelectedItem().toString());
                    country.setZipCode(edtZipCode.getText().toString());

                    switch (rdgCountry.getCheckedRadioButtonId()) {
                        case R.id.rdoDomestic:
                            country.setArea("Domestic");
                            break;
                        case R.id.rdoForeign:
                            country.setArea("Foreign");
                            break;
                    }

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Country", country); //truyền model Country sử dụng Serializable
                    //có thể truyền từng trường qua MainActivity đẻ xử lý
                    intent.putExtras(bundle); // gởi kèm dữ liệu
                    setResult(1, intent); // gởi kết quả về, truyền resultCode = 1 vì đã truyền requestCode = 1 từ MainActivity
                    finish();
                } else {
                    Toast.makeText(InsertActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validate() {
        return !edtName.getText().toString().matches("") &&
                !edtAddress.getText().toString().matches("") &&
                !edtZipCode.getText().toString().matches("");
    }
}