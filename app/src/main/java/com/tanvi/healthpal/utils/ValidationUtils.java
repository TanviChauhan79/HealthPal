package com.tanvi.healthpal.utils;

import com.tanvi.healthpal.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class ValidationUtils {
    @NotNull
    public static final ValidationUtils INSTANCE;

    @JvmStatic
    @NotNull
    public static final Pair isMobileNumberValid(@Nullable String number) {
        if (number != null) {
            CharSequence var1 = (CharSequence) number;
            boolean var2 = false;
            if (var1.length() != 0) {
                if (number.length() >= 10 && number.length() <= 10) {
                    return new Pair(true, 0);
                }

                return new Pair(false, R.string.error_invalid_number);
            }
        }

        return new Pair(false, R.string.error_empty_number);
    }

    @JvmStatic
    @NotNull
    public static final Pair isFieldValid(@Nullable String text) {
        if (text != null) {
            CharSequence var1 = (CharSequence) text;
            boolean var2 = false;
            if (var1.length() != 0 && !StringsKt.isBlank((CharSequence) text)) {
                return new Pair(true, 0);
            }
        }

        return new Pair(false, R.string.error_empty_field);
    }

    @JvmStatic
    @NotNull
    public static final Pair isSameFieldValid(@Nullable String text, @Nullable String text2) {
        CharSequence var2 = (CharSequence) text;
        boolean var3 = false;
        boolean var4 = false;
        if (var2 != null && !StringsKt.isBlank(var2)) {
            var2 = (CharSequence) text2;
            var3 = false;
            var4 = false;
            if (var2 != null && !StringsKt.isBlank(var2)) {
                if (Intrinsics.areEqual(text, text2)) {
                    return new Pair(true, 0);
                }

                return new Pair(false, R.string.error_password_doesn_t_match);
            }
        }

        return new Pair(false, R.string.error_password_doesn_t_match);
    }

    private ValidationUtils() {
    }

    static {
        ValidationUtils var0 = new ValidationUtils();
        INSTANCE = var0;
    }

}