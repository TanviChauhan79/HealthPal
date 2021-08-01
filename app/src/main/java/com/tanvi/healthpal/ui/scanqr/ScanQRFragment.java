package com.tanvi.healthpal.ui.scanqr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.google.zxing.Result;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentScanQRBinding;
import com.tanvi.healthpal.ui.scanqr.apis.entity.QRScannerViewModel;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class ScanQRFragment extends Fragment {
    private FragmentScanQRBinding binding;
    private QRScannerViewModel viewModel;

    private CodeScanner codeScanner;

    private boolean isPermissionsGranted = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentScanQRBinding.inflate(inflater);
        viewModel= new ViewModelProvider(this).get(QRScannerViewModel.class);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        codeScanner= new CodeScanner(getContext(),binding.scannerView);

        codeScannerPermissions();

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bundle bundle=new Bundle();
                        bundle= viewModel.ApiCall(result.toString());
                        Navigation.findNavController(getView())
                                .navigate(R.id.action_scanQRFragment_to_nutritionsDetailFragment,bundle);
                        Toast.makeText(getContext(),"sdas"+result,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        codeScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull Exception error) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),""+error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }
    private void codeScannerPermissions(){
        codeScanner.setCamera(CodeScanner.CAMERA_BACK);
        codeScanner.setFormats(CodeScanner.ALL_FORMATS);
        codeScanner.setAutoFocusMode(AutoFocusMode.SAFE);
        codeScanner.setScanMode(ScanMode.SINGLE);
        codeScanner.setAutoFocusEnabled(true); // Whether to enable auto focus or not
        codeScanner.setFlashEnabled(false); // Whether to enable flash or not
    }
}