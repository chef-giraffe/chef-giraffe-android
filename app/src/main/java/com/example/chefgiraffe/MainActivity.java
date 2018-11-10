package com.example.chefgiraffe;

import android.Manifest;
import android.os.Bundle;

import com.google.zxing.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler,
        EasyPermissions.PermissionCallbacks {
    @BindView(R.id.scanner)
    ZXingScannerView scanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EasyPermissions.requestPermissions(this, "", 1, Manifest.permission.CAMERA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
    }

    @Override
    public void handleResult(Result result) {
        scanner.resumeCameraPreview(this);
        // TODO
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        startCamera();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        finish();
    }

    private void startCamera() {
        scanner.setResultHandler(this);
        scanner.startCamera();
    }
}
