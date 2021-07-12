package dev.chau.adsmodjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.ads.nativetemplates.TemplateView;

import java.util.ArrayList;

import dev.chau.adsmodjava.AdsManager;
import dev.chau.adsmodjava.R;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<String> list;
    Context context;
    int ITEM_PER_AD;


    public static final int DEFAULT_VIEW_TYPE = 0;
    public static final int NATIVE_AD_VIEW_TYPE = 1;

    public ItemAdapter(ArrayList<String> list, Context context, int item){
        this.list = list;
        this.context = context;
        ITEM_PER_AD = item;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (viewType == DEFAULT_VIEW_TYPE) {
            view = layoutInflater
                    .inflate(R.layout.item_product, parent, false);
            return new ItemHolder(view);
        }
        view = layoutInflater.inflate(R.layout.item_native, parent, false);
        return new NativeAdHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof ItemHolder)) {
            return;
        }
        int itemPosition = position - position / ITEM_PER_AD ;

        ((ItemHolder) holder).txt.setText(list.get(itemPosition));
    }

    @Override
    public int getItemViewType(int position) {
        if (position % ITEM_PER_AD == 0 && position > 0) {
            return NATIVE_AD_VIEW_TYPE;
        }
        return DEFAULT_VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        int itemCount = list.size();
        itemCount += itemCount / ITEM_PER_AD ;
        return itemCount;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        TextView txt;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_View);
        }
    }
    static class NativeAdHolder extends RecyclerView.ViewHolder {
        TemplateView templateView;
        public NativeAdHolder(@NonNull View itemView) {
            super(itemView);
            templateView = itemView.findViewById(R.id.templateItem);
            AdsManager adsManager = AdsManager.getInstance(itemView.getContext(), "LogItemAdapter");
            adsManager.loadNative("ca-app-pub-3940256099942544/2247696110", templateView);
        }
    }
}
