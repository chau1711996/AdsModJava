package dev.chau.adsmodjava.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.ads.nativetemplates.TemplateView;

import java.util.ArrayList;

import dev.chau.adsmodjava.AdsManager;
import dev.chau.adsmodjava.R;
import dev.chau.adsmodjava.adapter.ItemAdapter;


public class GridFragment extends Fragment {

    private static final String TAG = "LogGridFragment";

    TemplateView templateView;
    RecyclerView rcvGrid;
    ArrayList<String> mList = new ArrayList<>();
    ItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        templateView = view.findViewById(R.id.templateGrid);
        rcvGrid = view.findViewById(R.id.rcvGrid);

        setUpAdsNative();
        setUpRecycleViewGrid();

        return view;
    }

    private void setUpAdsNative() {
        AdsManager adsManager = AdsManager.getInstance(getContext(), TAG);
        adsManager.loadNative("ca-app-pub-3940256099942544/2247696110", templateView);
    }

    private int randomNum(){
        int min = 3;
        int max = 9;

        int result = 0;

        while (result % 2 == 0){
            result = (int)(Math.random() * (max - min + 1) + min);
        }

        return result;
    }

    private void setUpRecycleViewGrid() {
        mList = loadList();
        adapter = new ItemAdapter(mList, getContext(), randomNum());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(adapter.getItemViewType(position) == ItemAdapter.NATIVE_AD_VIEW_TYPE){
                    return 2;
                }
                return 1;
            }
        });
        rcvGrid.setLayoutManager(manager);
        rcvGrid.setAdapter(adapter);
    }

    private ArrayList<String> loadList() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            list.add("Chau " + i);
        }

        return list;
    }
}