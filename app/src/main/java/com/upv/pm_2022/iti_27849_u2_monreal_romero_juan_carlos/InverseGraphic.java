package com.upv.pm_2022.iti_27849_u2_monreal_romero_juan_carlos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class InverseGraphic extends AppCompatActivity {

    private ArrayList<double[][]> list;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inverse_graphic);
        linearLayout = findViewById(R.id.surface);
        list = MatrixOperations.listOfInverse;
        System.out.println(list.toString());
        Button button = new Button(this);
        button.setText("Return");
        DragAndDropView dragAndDropView = new DragAndDropView(this, list);
        button.setOnClickListener( view -> {
            dragAndDropView.destroyDrawingCache();
            startActivity(new Intent(this, MainActivity.class));
        });
        linearLayout.addView(button, 0);
        linearLayout.addView(dragAndDropView, 1);

    }
}