package com.example.zipcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.zipcode.model.Country;
import com.example.zipcode.R;
import com.example.zipcode.model.Country;

public class EditActivity extends AppCompatActivity {

    private EditText edtNameEdit, edtAddressEdit, edtZipCodeEdit;
    private Spinner spnCountryEdit;
    private RadioGroup rdgCountryEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //ánh xạ
        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnDelete = findViewById(R.id.btnDelete);
        edtNameEdit = findViewById(R.id.edtNameEdit);
        edtAddressEdit = findViewById(R.id.edtAddressEdit);
        edtZipCodeEdit = findViewById(R.id.edtZipCodeEdit);
        spnCountryEdit = findViewById(R.id.spnCountryEdit);
        rdgCountryEdit = findViewById(R.id.rdgCountryEdit);

        Bundle bundle = getIntent().getExtras();
        Country country = (Country) bundle.getSerializable("CountryEdit");
        int position = bundle.getInt("Position");

        edtNameEdit.setText(country.getName());
        edtAddressEdit.setText(country.getAddress());
        edtZipCodeEdit.setText(country.getZipCode());

        if (country.getArea().equals("Foreign"))
            rdgCountryEdit.check(R.id.rdoForeignEdit);
        else
            rdgCountryEdit.check(R.id.rdoDomesticEdit);

        int pos = ((ArrayAdapter<String>) spnCountryEdit.getAdapter()).getPosition(country.getCountry());
        spnCountryEdit.setSelection(pos);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Country country = new Country();
                country.setName(edtNameEdit.getText().toString());
                country.setAddress(edtAddressEdit.getText().toString());
                country.setCountry(spnCountryEdit.getSelectedItem().toString());
                country.setZipCode(edtZipCodeEdit.getText().toString());

                switch (rdgCountryEdit.getCheckedRadioButtonId()) {
                    case R.id.rdoDomesticEdit:
                        country.setArea("Domestic");
                        break;
                    case R.id.rdoForeignEdit:
                        country.setArea("Foreign");
                        break;
                }

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("CountryEdit", country); //truyền model Country sử dụng Serializable
                //có thể truyền từng trường qua MainActivity đẻ xử lý
                bundle.putInt("Position", position); //truyền vị trí item cần sửa trong list
                bundle.putBoolean("isEdit", true); //truyền biên isEdit để phân biệt hành động đang thực hiện là Edit hay Delete
                intent.putExtras(bundle); // gởi kèm dữ liệu
                setResult(2, intent); // gởi kết quả về, truyền resultCode = 2 vì đã truyền requestCode = 2 từ MainActivity
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("Position", position); //truyền vị trí item cần xóa trong list
                bundle.putBoolean("isEdit", false); //truyền biên isEdit để phân biệt hành động đang thực hiện là Edit hay Delete
                intent.putExtras(bundle);
                setResult(2, intent); // gởi kết quả về, truyền resultCode = 2 vì đã truyền requestCode = 2 từ MainActivity
                finish();
            }
        });
    }
}