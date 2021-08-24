package pl.softr.ocr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.ListenableFuture;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CameraPreview extends AppCompatActivity {

    private static String[] REQUIRED_PERMISSIONS = new String[]{Manifest.permission.CAMERA};
    private static final int REQUEST_CODE_PERMISSION = 101;

    private PreviewView viewFinder;
    private FloatingActionButton takePhoto;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private CameraSelector cameraSelector;
    private ProcessCameraProvider cameraProvider;
    private Preview preview;
    private File outputDirectory;
    private ImageCapture imageCapture;
    private ImageButton flashControl;
    private int flashMode = 0;
    private ImageCapture.OutputFileOptions outputFileOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);
        viewFinder = findViewById(R.id.viewFinder);
        takePhoto = findViewById(R.id.takePhoto);
        flashControl = findViewById(R.id.flashControl);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
        preview = new Preview.Builder().build();
        preview.setSurfaceProvider(viewFinder.getSurfaceProvider());
        outputDirectory = getOutputDirectory();
        try {
            cameraProvider = cameraProviderFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        imageCapture = new ImageCapture.Builder()
                .setTargetResolution(new Size(3264, 2448))
                .setTargetRotation(Surface.ROTATION_0)
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .setIoExecutor(Executors.newSingleThreadExecutor())
                .build();

        setOnClickListeners();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (allPermissionsGranted()) {
            cameraProviderFuture.addListener(() -> {
                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(CameraPreview.this, cameraSelector, preview, imageCapture);
            }, ContextCompat.getMainExecutor(this));
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSION);
        }

    }

    private void setOnClickListeners() {
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        flashControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flashMode == 0) {
                    imageCapture.setFlashMode(ImageCapture.FLASH_MODE_AUTO);
                    flashControl.setImageResource(R.drawable.outline_flash_auto_white_24dp);
                    flashMode++;
                } else if (flashMode == 1) {
                    imageCapture.setFlashMode(ImageCapture.FLASH_MODE_ON);
                    flashControl.setImageResource(R.drawable.outline_flash_on_white_24dp);
                    flashMode++;
                } else if (flashMode == 2) {
                    imageCapture.setFlashMode(ImageCapture.FLASH_MODE_OFF);
                    flashControl.setImageResource(R.drawable.outline_flash_off_white_24dp);
                    flashMode = 0;
                }
            }
        });
    }

    private void startCamera() {


    }

    private void takePhoto() {
        File photoFile = new File(outputDirectory, System.currentTimeMillis() + ".jpg");
        outputFileOptions = new ImageCapture.OutputFileOptions.Builder(photoFile).build();
        imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull @NotNull ImageCapture.OutputFileResults outputFileResults) {
                Uri uri = Uri.fromFile(photoFile);
                Intent i = new Intent(CameraPreview.this, InvoiceElementsPreview.class);
                i.putExtra("uri", String.valueOf(uri));
                startActivity(i);
            }

            @Override
            public void onError(@NonNull @NotNull ImageCaptureException exception) {
                Log.e(getClass().toString(), exception.getMessage());
            }
        });
    }


    private File getOutputDirectory() {
        return Arrays.stream(getExternalMediaDirs()).findFirst().get();
    }


    private boolean allPermissionsGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(this,
                        "Permissions not granted by the user.",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        cameraProvider.unbindAll();
    }


}