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

import dev.chau.adsmodjava.R;

public class VerticalFragment extends Fragment {
    private static final String TAG = "LogVerticalFragment";

    TemplateView templateView;
    RecyclerView rcvVer;
    ArrayList<String> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vertical, container, false);
        return view;
    }

}