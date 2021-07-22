package dev.chau.adsmodjava.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import java.util.ArrayList;

import dev.chau.adsmodjava.R;
import dev.chau.adsmodjava.Utils.OnClickDialogListener;

public class PopupManager {

    public static void ShowPopupWindow(Context context, OnClickDialogListener listener) {
        // inflate the layout of the popup window
        LayoutInflater inflater = LayoutInflater.from(context);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        Button btnClick = popupView.findViewById(R.id.btnClick);
        btnClick.setOnClickListener(v -> {
            listener.onClickDialogListener("Click Popup");
            popupWindow.dismiss();
        });
    }

    public static void ShowPopUpMenu(Context context, View anchor, ArrayList<String> list, OnClickDialogListener listener) {
        PopupMenu popup = new PopupMenu(context, anchor);
        //Inflating the Popup using xml file
//        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        //use list for menu
        for (String s : list) {
            popup.getMenu().add(s);
        }

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                listener.onClickDialogListener("You Clicked : " + item.getTitle());
                return true;
            }
        });

        popup.show(); //showing popup menu
    }

}
