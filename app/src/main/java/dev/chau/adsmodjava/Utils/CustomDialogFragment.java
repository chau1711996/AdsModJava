package dev.chau.adsmodjava.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {

    public static String TAG = "CustomDialogFragment";

    private final OnClickDialogListener listener;

    private final boolean isActive;

    public CustomDialogFragment(Boolean isActive, OnClickDialogListener listener) {
        this.listener = listener;
        this.isActive = isActive;
    }

    public static CustomDialogFragment newInstance(String title, Boolean isActive, OnClickDialogListener listener) {
        CustomDialogFragment frag = new CustomDialogFragment(isActive, listener);
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getArguments() != null;
        String title = getArguments().getString("title");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setPositiveButton("OK", (dialog, which) -> {
            listener.onClickDialogListener("Click OKE");
            dialog.dismiss();
        });
        if(isActive){
            alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> {
                if (dialog != null) {
                    dialog.dismiss();
                }
            });
        }
        return alertDialogBuilder.create();
    }
}
