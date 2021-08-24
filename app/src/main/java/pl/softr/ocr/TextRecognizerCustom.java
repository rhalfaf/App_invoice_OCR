package pl.softr.ocr;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TextRecognizerCustom {
    private TextRecognizer textRecognizer;
    private InputImage image;
    private ExecutorService executor;
    private List<Text.TextBlock> textBlocks = new ArrayList();
    private TextRecognition callback;

    public TextRecognizerCustom(InputImage image, TextRecognition callback) {
        this.image = image;
        this.textRecognizer = com.google.mlkit.vision.text.TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        this.callback = callback;
        executor = Executors.newSingleThreadExecutor();
    }

    public void readText() {
        Task<Text> result = textRecognizer.process(image).addOnSuccessListener(executor, new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(@NonNull @NotNull Text text) {
                textBlocks.addAll(text.getTextBlocks());
                callback.onTextReceived();
                textRecognizer.close();
                executor.shutdown();
            }
        });
    }

    public List<Text.TextBlock> getTextBlocks() {
        return this.textBlocks;
    }
}
