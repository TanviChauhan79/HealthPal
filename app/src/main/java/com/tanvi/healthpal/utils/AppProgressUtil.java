package com.tanvi.healthpal.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.tanvi.healthpal.R;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public final class AppProgressUtil {
    private static Dialog progressDialog;
    @NotNull
    public static final AppProgressUtil INSTANCE;

    public final boolean isShowing() {
        return progressDialog != null;
    }

    public final void showOldProgressDialog(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Dialog dialog;
        if (progressDialog != null) {
            dialog = progressDialog;
            Intrinsics.checkNotNull(dialog);
            if (dialog.isShowing()) {
                return;
            }
        }

        progressDialog = new Dialog(context, R.style.Theme_AppCompat_Dialog);
        dialog = progressDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.setCancelable(false);
        dialog = progressDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog = progressDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.show();
        dialog = progressDialog;
        Intrinsics.checkNotNull(dialog);
        Window var2 = dialog.getWindow();
        Intrinsics.checkNotNull(var2);
        var2.setBackgroundDrawableResource(android.R.color.transparent);
        dialog = progressDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.setContentView(R.layout.layout_animated_progress_bar);
    }

    public final void closeOldProgressDialog() {
        try {
            if (progressDialog != null) {
                Dialog dialog = progressDialog;
                Intrinsics.checkNotNull(dialog);
                if (dialog.isShowing()) {
                    dialog = progressDialog;
                    Intrinsics.checkNotNull(dialog);
                    dialog.dismiss();
                    progressDialog = (Dialog)null;
                }
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        } finally {
            progressDialog = (Dialog)null;
        }

    }

    private AppProgressUtil() {
    }

    static {
        AppProgressUtil var0 = new AppProgressUtil();
        INSTANCE = var0;
    }
}

