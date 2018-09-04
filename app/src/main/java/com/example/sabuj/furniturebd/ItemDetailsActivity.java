package com.example.sabuj.furniturebd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabuj.furniturebd.fragments.Fragment_chair;
import com.example.sabuj.furniturebd.models.ModelData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemDetailsActivity extends AppCompatActivity {
    public ModelData chairData;
    private ArrayList<ModelData> chairArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        ImageView image = (ImageView) findViewById(R.id.image1);
        TextView model = (TextView) findViewById(R.id.tv_model_details);
        TextView price = (TextView) findViewById(R.id.tv_price_details);
        TextView addToCart = (TextView) findViewById(R.id.text_action_bottom1);
        TextView buyNow = (TextView) findViewById(R.id.text_action_bottom2);

        Intent intent=getIntent();
        chairData=(ModelData) intent.getSerializableExtra("item");
        model.setText(chairData.getModel());
        price.setText(chairData.getPrice());
        Picasso.with(getApplication())
                .load(chairData.getImage())
                .transform(Fragment_chair.PaletteTransformation.instance())
                .into(image);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Item added to cart",Toast.LENGTH_LONG).show();
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Check for buying Item",Toast.LENGTH_LONG).show();

            }
        });
    }
}
