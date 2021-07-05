package com.example.abc.itmcollegealigarh.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc.itmcollegealigarh.R;

public class MyDialogFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container);
        // This shows the title, replace My Dialog Title. You can use strings too.
        getDialog().setTitle("My Dialog Title");
        // If you want no title, use this code
        // getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }
}
