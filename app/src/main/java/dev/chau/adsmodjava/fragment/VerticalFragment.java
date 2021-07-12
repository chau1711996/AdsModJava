package dev.chau.adsmodjava.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.ads.nativetemplates.TemplateView;

import java.util.ArrayList;

import dev.chau.adsmodjava.AdsManager;
import dev.chau.adsmodjava.R;
import dev.chau.adsmodjava.adapter.ItemAdapter;

public class VerticalFragment extends Fragment {
    private static final String TAG = "LogVerticalFragment";

    TemplateView templateView;
    RecyclerView rcvVer;
    ArrayList<String> mList = new ArrayList<>();
    ItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vertical, container, false);

        templateView = view.findViewById(R.id.templateVer);
        rcvVer = view.findViewById(R.id.rcvVer);

        setUpAdsNative();
        setUpRecycleViewVer();

        return view;
    }

    private void setUpAdsNative() {
        AdsManager adsManager = AdsManager.getInstance(getContext(), TAG);
        adsManager.loadNative("ca-app-pub-3940256099942544/2247696110", templateView);
    }

    private void setUpRecycleViewVer(){
        mList = loadList();

        adapter = new ItemAdapter(mList, getContext(), randomNum());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcvVer.setLayoutManager(manager);
        rcvVer.setAdapter(adapter);
    }

    private int randomNum(){
        int min = 3;
        int max = 10;

        int result = -1;

        while (result % 2 != 0){
            result = (int)(Math.random() * (max - min + 1) + min);
        }

        return result;
    }

    private ArrayList<String> loadList(){
        ArrayList<String> list = new ArrayList<>();

        for(int i = 1; i <= 20; i++){
            list.add("Chau "+i);
        }

        return list;
    }

}