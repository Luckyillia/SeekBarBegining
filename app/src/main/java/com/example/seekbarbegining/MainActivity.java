package com.example.seekbarbegining;

// MainActivity.java
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast; // Dodajemy import dla Toast
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBarAmount;
    TextView valueTextView;

    TextView resizableTextView;
    TextView fontSizeLabelTextView;
    SeekBar fontSizeSeekBar;

    final int MIN_FONT_SIZE = 10;

    TextView redValueTextView, greenValueTextView, blueValueTextView;
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    View colorPreviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ładujemy nasz layout XML

        // Łączymy zmienne z elementami z XML używając ich ID
        seekBarAmount = findViewById(R.id.seekBarAmount);
        valueTextView = findViewById(R.id.valueTextView);

        // Ustawiamy początkową wartość TextView na podstawie progressu SeekBar
        // (na wypadek gdyby progress był inny niż 0, a my chcemy to odzwierciedlić od razu)
        valueTextView.setText("Wartość: " + seekBarAmount.getProgress());

        // Ustawiamy "słuchacza" dla naszego suwaka
        seekBarAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                // Ta metoda jest wywoływana ZA KAŻDYM RAZEM, gdy wartość suwaka się zmienia.
                // seekBar: to nasz suwak (seekBarAmount)
                // progressValue: aktualna wartość suwaka (od 0 do wartości 'max')
                // fromUser: true, jeśli zmiana była dokonana przez użytkownika,
                //           false, jeśli programowo (np. przez seekBarAmount.setProgress(X))

                // Aktualizujemy tekst w TextView, aby pokazywał nową wartość
                valueTextView.setText("Wartość: " + progressValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Ta metoda jest wywoływana, GDY UŻYTKOWNIK ZACZYNA PRZESUWAĆ suwak.
                // Możemy tu np. wyświetlić krótki komunikat.
                Toast.makeText(MainActivity.this, "Zaczynasz regulację!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Ta metoda jest wywoływana, GDY UŻYTKOWNIK KOŃCZY PRZESUWAĆ suwak (puszcza palec).
                // Możemy tu np. zapisać ostatecznie wybraną wartość.
                int finalValue = seekBar.getProgress(); // Pobieramy ostateczną wartość
                Toast.makeText(MainActivity.this, "Ustawiono wartość: " + finalValue, Toast.LENGTH_SHORT).show();
            }
        });
        // Inicjalizacja elementów dla Przykładu 2
        resizableTextView = findViewById(R.id.resizableTextView);
        fontSizeLabelTextView = findViewById(R.id.fontSizeLabelTextView);
        fontSizeSeekBar = findViewById(R.id.fontSizeSeekBar);

        // Ustawienie początkowego rozmiaru tekstu i etykiety
        // (dla API < 26, gdzie min jest zawsze 0, dodajemy nasz offset MIN_FONT_SIZE)
        int initialFontSize = fontSizeSeekBar.getProgress() + MIN_FONT_SIZE;
        resizableTextView.setTextSize(initialFontSize); // setTextSize domyślnie przyjmuje wartość w 'sp'
        fontSizeLabelTextView.setText("Rozmiar czcionki: " + initialFontSize + "sp");

        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    // progress tutaj to wartość od 0 do (max_ustawione_w_xml)
                    // Aby uzyskać rzeczywisty rozmiar czcionki, dodajemy MIN_FONT_SIZE
                    int currentFontSize = progress + MIN_FONT_SIZE;
                    resizableTextView.setTextSize(currentFontSize);
                    fontSizeLabelTextView.setText("Rozmiar czcionki: " + currentFontSize + "sp");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Zmieniasz rozmiar tekstu", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int finalSize = seekBar.getProgress() + MIN_FONT_SIZE;
                Toast.makeText(MainActivity.this, "Ustawiono rozmiar: " + finalSize + "sp", Toast.LENGTH_SHORT).show();
            }
        });
        redValueTextView = findViewById(R.id.redValueTextView);
        redSeekBar = findViewById(R.id.redSeekBar);
        colorPreviewView = findViewById(R.id.colorPreviewView);

        blueValueTextView = findViewById(R.id.blueValueTextView);
        blueSeekBar = findViewById(R.id.blueSeekBar);

        greenValueTextView = findViewById(R.id.greenValueTextView);
        greenSeekBar = findViewById(R.id.greenSeekBar);

        // Ustawienie początkowej wartości
        int initialRed = redSeekBar.getProgress();
        redValueTextView.setText("Czerwony: " + initialRed);

        int initialGreen = redSeekBar.getProgress();
        greenValueTextView.setText("Zielony: " + initialGreen);

        int initialBlue = redSeekBar.getProgress();
        blueValueTextView.setText("Niebieski: " + initialBlue);

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    redValueTextView.setText("Czerwony: " + progress);
                    colorPreviewView.setBackgroundColor(Color.rgb(progress, greenSeekBar.getProgress(), blueSeekBar.getProgress()));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Opcjonalnie: np. Toast.makeText(MainActivity.this, "Zmieniasz czerwień", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Opcjonalnie: np. Toast.makeText(MainActivity.this, "Ustalono czerwień", Toast.LENGTH_SHORT).show();
            }
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    greenValueTextView.setText("Zielony: " + progress);
                    colorPreviewView.setBackgroundColor(Color.rgb(redSeekBar.getProgress(), progress, blueSeekBar.getProgress()));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Opcjonalnie: np. Toast.makeText(MainActivity.this, "Zmieniasz czerwień", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Opcjonalnie: np. Toast.makeText(MainActivity.this, "Ustalono czerwień", Toast.LENGTH_SHORT).show();
            }
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    blueValueTextView.setText("Niebieski: " + progress);
                    colorPreviewView.setBackgroundColor(Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Opcjonalnie: np. Toast.makeText(MainActivity.this, "Zmieniasz czerwień", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Opcjonalnie: np. Toast.makeText(MainActivity.this, "Ustalono czerwień", Toast.LENGTH_SHORT).show();
            }
        });
        colorPreviewView.setBackgroundColor(Color.rgb(initialRed, initialGreen, initialBlue));
    }
}