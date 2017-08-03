package com.nanodegree.dario.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.model.Step;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipeStepDetailFragment extends Fragment {

    private static final String FRAGMENT_STEP = "FRAGMENT_STEP";
    private Step step;
    private TextView description;
    private VideoView videoView;

    public RecipeStepDetailFragment() {

    }

    public static RecipeStepDetailFragment newInstance(Step step) {
        RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FRAGMENT_STEP, step);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);

        step = getArguments().getParcelable(FRAGMENT_STEP);

        description = (TextView) rootView.findViewById(R.id.description);
        description.setText(step.getDescription());

        videoView = (VideoView) rootView.findViewById(R.id.video_view);


        return rootView;
    }
}
