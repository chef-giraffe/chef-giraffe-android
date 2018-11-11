package com.example.chefgiraffe;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.chefgiraffe.domains.DataRequest;
import com.example.chefgiraffe.domains.Table;
import com.google.zxing.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements ZXingScannerView.ResultHandler,
        EasyPermissions.PermissionCallbacks {
    @BindView(R.id.scanner)
    ZXingScannerView scanner;
    private MainViewModel viewModel;

    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getTable().observe(this, new Observer<DataRequest<Table>>() {
            @Override
            public void onChanged(DataRequest<Table> value) {
                if (value.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, TableActivity.class);
                    intent.putExtra(TableActivity.VAR_TABLE_ID, value.getData().getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, value.getMessage(), Toast.LENGTH_SHORT).show();
                    scanner.resumeCameraPreview(MainActivity.this);
                }
            }
        });

        EasyPermissions.requestPermissions(this, "", 1, Manifest.permission.CAMERA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
    }

    @Override
    public void handleResult(Result result) {
        viewModel.selectedTableId(result.getText());
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
