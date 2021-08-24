package pl.softr.ocr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;

import java.io.IOException;
import java.util.List;

import pl.softr.ocr.invoices.InvoiceAdapter;

public class InvoiceElementsPreview extends AppCompatActivity implements TextRecognition {

    private TextRecognizerCustom textRecognizer;
    private String uriString;
    private ImageView photoView;
    private RecyclerView elementsRV;
    private List<Text.TextBlock> textBlocks;
    private InvoiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inovice_elements_preview);
        elementsRV = findViewById(R.id.rv_invoice_elements);
        Intent i = getIntent();
        uriString = i.getStringExtra("uri");
        if (uriString != null) {
            Uri uri = Uri.parse(uriString);
            createInputImage(uri);
        }
    }

    public void createInputImage(Uri imageUri) {
        InputImage image;
        try {
            image = InputImage.fromFilePath(this, imageUri);
            textRecognizer = new TextRecognizerCustom(image, this);
            textRecognizer.readText();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTextReceived() {
        createListView(textRecognizer.getTextBlocks());
    }

    private void createListView(List<Text.TextBlock> elements) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new InvoiceAdapter(elements);
                elementsRV.setAdapter(adapter);
                elementsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                //adapter.setDataSet(elements);
                //adapter.notifyDataSetChanged();

            }
        });

    }

}