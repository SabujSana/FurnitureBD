package com.example.sabuj.furniturebd.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.sabuj.furniturebd.ItemDetailsActivity;
import com.example.sabuj.furniturebd.fragments.Fragment_chair;
import com.example.sabuj.furniturebd.R;
import com.example.sabuj.furniturebd.models.ModelData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdapterData extends RecyclerView.Adapter<AdapterData.MyViewHolder> {
    private RecyclerView mRecyclerView;
    private ArrayList<ModelData> dataArrayList;
    Context mContext;

    public AdapterData(Context context, ArrayList<ModelData> arrayList) {
        mContext = context;
        dataArrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_data, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final ModelData chairData = dataArrayList.get(position);

        holder.model.setText(chairData.getModel());
        holder.price.setText(chairData.getPrice());
//        Glide.with(mContext).load(chairData.getImage()).into(holder.image);

        Picasso.with(getContext())
                .load(chairData.getImage())
                .transform(Fragment_chair.PaletteTransformation.instance())
                .into(holder.image);

        holder.itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ItemDetailsActivity.class);
                intent.putExtra("item", chairData);
                getContext().startActivity(intent);
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ItemDetailsActivity.class);
                intent.putExtra("item", chairData);
                getContext().startActivity(intent);
            }
        });
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView model, price;
        public View mView;
        public LinearLayout itemLinearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            image = (ImageView) mView.findViewById(R.id.iv_image);
            model = (TextView) mView.findViewById(R.id.tv_model);
            price = (TextView) mView.findViewById(R.id.tv_price);
            itemLinearLayout = (LinearLayout) mView.findViewById(R.id.layout_item);
        }
    }
}
