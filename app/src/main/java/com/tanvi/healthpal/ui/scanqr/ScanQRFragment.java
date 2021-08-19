package com.tanvi.healthpal.ui.scanqr;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.afollestad.materialdialogs.DialogBehavior;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.main.DialogLayout;
import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentQrScannerBinding;
import com.tanvi.healthpal.ui.qrscanner.model.ResGetNutritionalInfoFromBarcodeModel;
import com.tanvi.healthpal.utils.AppProgressUtil;
import com.tanvi.healthpal.utils.Constants;
import com.tanvi.healthpal.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ScanQRFragment extends Fragment {

    private FragmentQrScannerBinding binding = null;

    private ScanQRViewModel viewModel = null;

    private CodeScanner codeScanner = null;
    private boolean isPermissionsGranted = false;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentQrScannerBinding.inflate(inflater);

        viewModel = new ViewModelProvider(this).get(ScanQRViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        codeScanner = new CodeScanner(requireContext(), binding.scannerView);

        // Parameters (default values)
        codeScanner.setCamera(CodeScanner.CAMERA_BACK); // or CAMERA_FRONT or specific camera id
        codeScanner.setFormats(CodeScanner.ALL_FORMATS); // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.setAutoFocusMode(AutoFocusMode.SAFE); // or CONTINUOUS
        codeScanner.setScanMode(ScanMode.SINGLE); // or CONTINUOUS or PREVIEW
        codeScanner.setAutoFocusEnabled(true); // Whether to enable auto focus or not
        codeScanner.setFlashEnabled(false); // Whether to enable flash or not

        // Callbacks
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull @NotNull Result result) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewModel.callApi(result.getText());
                    }
                });
            }
        });

        codeScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull @NotNull Exception error) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customToast("Camera initialization error: " + error.getMessage());
                    }
                });
            }
        });

        binding.scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermissions();
            }
        });

        viewModel.showProgressBar.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
                } else {
                    AppProgressUtil.INSTANCE.closeOldProgressDialog();
                }
            }
        });

        viewModel.showToast.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty()) {
                    customToast(s);
                }
            }
        });

        viewModel.data.observe(this, new Observer<ResGetNutritionalInfoFromBarcodeModel>() {
            @Override
            public void onChanged(ResGetNutritionalInfoFromBarcodeModel resGetNutritionalInfoFromBarcodeModel) {
                Bundle bundle = new Bundle();
                if(resGetNutritionalInfoFromBarcodeModel.getProduct() != null
                        && resGetNutritionalInfoFromBarcodeModel.getProduct().getNutriments() != null) {
                    resGetNutritionalInfoFromBarcodeModel.getProduct().getNutriments().setProduct_image(resGetNutritionalInfoFromBarcodeModel.getProduct().getImage_front_url());
                    resGetNutritionalInfoFromBarcodeModel.getProduct().getNutriments().setProduct_name(resGetNutritionalInfoFromBarcodeModel.getProduct().getProduct_name());
                    bundle.putParcelable(Constants.NUTRIMENTS_INFO, resGetNutritionalInfoFromBarcodeModel.getProduct().getNutriments());
//                    bundle.putString(Constants.PRODUCT_IMAGE, resGetNutritionalInfoFromBarcodeModel.getProduct().getImage_front_url());
//                    bundle.putString(Constants.PRODUCT_NAME, resGetNutritionalInfoFromBarcodeModel.getProduct().getProduct_name());
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_QRScannerFragment_to_nutritionDetailsFragment, bundle);
//                    customToast("Data fetched successfully.")
                } else {
                    customToast("Product details are not available.");
                }
            }
        });

    }

    private void customToast(String string){
        ToastUtils.INSTANCE.longCustomToast(getLayoutInflater(), requireView(), 0, string);
    }

    private void askPermissions(){
        Dexter.withContext(requireContext())
                .withPermissions(
                        Manifest.permission.CAMERA
                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if(multiplePermissionsReport.areAllPermissionsGranted()){
                            isPermissionsGranted = true;
                            if(!codeScanner.isPreviewActive()){
                                codeScanner.startPreview();
                            }
                        }
                        if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
        }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError dexterError) {

            }
        }).onSameThread().check();
    }

    private void showSettingsDialog() {

        MaterialDialog materialDialog = new MaterialDialog(requireContext(), new DialogBehavior() {
            @Override
            public int getThemeRes(boolean b) {
                return 0;
            }

            @NotNull
            @Override
            public ViewGroup createView(@NotNull Context context, @NotNull Window window, @NotNull LayoutInflater layoutInflater, @NotNull MaterialDialog materialDialog) {
                return null;
            }

            @NotNull
            @Override
            public DialogLayout getDialogLayout(@NotNull ViewGroup viewGroup) {
                return null;
            }

            @Override
            public void setWindowConstraints(@NotNull Context context, @NotNull Window window, @NotNull DialogLayout dialogLayout, @org.jetbrains.annotations.Nullable Integer integer) {

            }

            @Override
            public void setBackgroundColor(@NotNull DialogLayout dialogLayout, int i, float v) {

            }

            @Override
            public void onPreShow(@NotNull MaterialDialog materialDialog) {

            }

            @Override
            public void onPostShow(@NotNull MaterialDialog materialDialog) {

            }

            @Override
            public boolean onDismiss() {
                return false;
            }
        });

        materialDialog.setTitle(R.string.app_name);
        materialDialog.message(R.string.warn_permission_denied_camera, null, null);
        materialDialog.cancelOnTouchOutside(false);
        materialDialog.cancelable(false);
        materialDialog.positiveButton(R.string.settings, null, new Function1<MaterialDialog, Unit>() {
            @Override
            public Unit invoke(MaterialDialog materialDialog) {
                openSettings();
                return null;
            }
        });
        materialDialog.negativeButton(android.R.string.cancel, null, new Function1<MaterialDialog, Unit>() {
            @Override
            public Unit invoke(MaterialDialog materialDialog) {
                materialDialog.cancel();
                return null;
            }
        });
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", requireActivity().getPackageName()
                , null);
        intent.setData(uri);
        requireActivity().startActivityForResult(intent, 101);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!codeScanner.isPreviewActive() && isPermissionsGranted){
            codeScanner.startPreview();
        } else if(!isPermissionsGranted){
            askPermissions();
        }
    }

    @Override
    public void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }

}
