package com.tanvi.healthpal.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tanvi.healthpal.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ToastUtils {

    @NotNull
    public static final ToastUtils INSTANCE;

    public static final void longCustomToast(@NotNull LayoutInflater layoutInflater, @NotNull View view, int stringCode, @Nullable String stringText){

        View layout = layoutInflater.inflate(
                R.layout.layout_remainder_sent_custom_toast, view.findViewById(R.id.llToast)
        );
        TextView toastText = (TextView) layout.findViewById(R.id.tvRemainderText);
        toastText.setText(stringText);
        Toast toast = new Toast(view.getContext());
        toast.setGravity(Gravity.BOTTOM, 0, 80);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    private ToastUtils() {
    }

    static {
        ToastUtils var0 = new ToastUtils();
        INSTANCE = var0;
    }

}
