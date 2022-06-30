package com.upv.pm_2022.iti_27849_u2_monreal_romero_juan_carlos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GraphicFragment extends Fragment {

    private ArrayList<double[][]> arrayList;
    public GraphicFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View newView = inflater.inflate(R.layout.fragment_graphic, container, false);
        if (MatrixOperations.listOfInverse == null) {
            Toast.makeText(getContext(), "Please first calculate the inverse of any matrix", Toast.LENGTH_SHORT).show();
            return null;
        }else {
            arrayList = MatrixOperations.listOfInverse;
            ArrayList<String> steps = MatrixOperations.steps;
            return new DragAndDropView(newView.getContext(), arrayList, steps);
        }
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }
}