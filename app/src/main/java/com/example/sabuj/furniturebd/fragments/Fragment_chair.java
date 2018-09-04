package com.example.sabuj.furniturebd.fragments;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sabuj.furniturebd.R;
import com.example.sabuj.furniturebd.adapters.AdapterData;
import com.example.sabuj.furniturebd.models.ModelData;
import com.example.sabuj.furniturebd.util.AppController;

import com.github.ybq.android.spinkit.style.Wave;
import com.squareup.picasso.Transformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

public class Fragment_chair extends Fragment {
    private static final String URL = "https://furniturebd.000webhostapp.com/view_chair.php";
    RecyclerView recyclerview;

    AdapterData mAdapterData;
    ArrayList<ModelData> mItems;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.spin_progress);
        setupRecyclerView(recyclerview);

        return view;
    }

    private void setupRecyclerView(RecyclerView recyclerview) {
        Wave wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);
        progressBar.setVisibility(View.VISIBLE);

        mItems = new ArrayList<ModelData>();
        loadJson();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        mAdapterData = new AdapterData(getActivity(), mItems);
        recyclerview.setAdapter(mAdapterData);
    }


    private int dpToPx(int dp) {
        Resources resources = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));

    }


    private void loadJson() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData modelData = new ModelData();
                                modelData.setImage(data.getString("image"));
                                modelData.setModel(data.getString("model"));
                                modelData.setPrice(data.getString("price"));
                                mItems.add(modelData);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapterData.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }


    public static class PaletteTransformation implements Transformation {

        private static final PaletteTransformation INSTANCE = new PaletteTransformation();
        private static final Map<Bitmap, Palette> CACHE = new WeakHashMap<Bitmap, Palette>();

        public static PaletteTransformation instance() {
            return INSTANCE;
        }

        public static Palette getPalette(Bitmap bitmap) {
            return CACHE.get(bitmap);
        }

        private PaletteTransformation() {
        }

        @Override
        public Bitmap transform(Bitmap source) {
            Palette palette = Palette.generate(source);
            CACHE.put(source, palette);
            return source;
        }

        @Override
        public String key() {
            return "";
        }

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }
}
