package com.tanvi.healthpal.utils;

import androidx.databinding.BindingAdapter;

import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class AppBinding {
    @NotNull
    public static final AppBinding INSTANCE;

    @BindingAdapter({"app:errorText"})
    public static final void setErrorMessage(@Nullable TextInputLayout view, @Nullable String errorMessage) {
        if (view != null) {
            view.setError((CharSequence)errorMessage);
        }

        if (view != null) {
            CharSequence var2 = (CharSequence)errorMessage;
            boolean var3 = false;
            boolean var4 = false;
            view.setErrorEnabled(var2 != null && var2.length() != 0);
        }

    }

    private AppBinding() {
    }

    static {
        AppBinding var0 = new AppBinding();
        INSTANCE = var0;
    }
}
