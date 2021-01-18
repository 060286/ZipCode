package com.example.zipcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zipcode.model.Country;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Country> countryList = new ArrayList<>();
    private ListView lvAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvAdress = findViewById(R.id.lvAddress);

        CountryAdapter adapter = new CountryAdapter();

        lvAdress.setAdapter(adapter);

        lvAdress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //sử dụng startActivityForResult để lấy kết quả từ Insert trả về, requestCode từ Main -> Edit là 2
                Country country = countryList.get(position);

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CountryEdit", country); //truyền model Country sử dụng Serializable
                bundle.putInt("Position", position); //truyền position để lấy được vị trí item đã được click để xử lý edit hoặc delete
                //có thể truyền từng trường qua MainActivity đẻ xử lý
                intent.putExtras(bundle); // gởi kèm dữ liệu
                startActivityForResult(intent, 2);
            }
        });
    }

    class CountryAdapter extends ArrayAdapter<Country> {
        public CountryAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public CountryAdapter() {
            super(MainActivity.this, android.R.layout.simple_list_item_1, countryList);
        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, null);
            }

            //cap nhat du lieu cho tung dong
            Country r = countryList.get(position);

            ((TextView) row.findViewById(R.id.title)).setText(r.getName());
            ((TextView) row.findViewById(R.id.content)).setText(r.getAddress() + " - " + r.getZipCode());
            ImageView icon = row.findViewById(R.id.icon);

            String type = r.getCountry();
            switch (type) {
                case "Viet Nam":
                    icon.setImageResource(R.mipmap.vietnam);
                    break;
                case "Japan":
                    icon.setImageResource(R.mipmap.japan);
                    break;
                case "South Korea":
                    icon.setImageResource(R.mipmap.south_korea);
                    break;
                case "United Kingdom":
                    icon.setImageResource(R.mipmap.united_kingdom);
                    break;
            }

            return row;
        }
    }
}