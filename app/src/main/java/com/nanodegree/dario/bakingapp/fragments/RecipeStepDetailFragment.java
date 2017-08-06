package com.nanodegree.dario.bakingapp.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.model.Step;
import com.squareup.picasso.Picasso;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipeStepDetailFragment extends Fragment {

    private static final String FRAGMENT_STEP = "FRAGMENT_STEP";
    private static final String IS_LAST = "IS_LAST";
    private static final java.lang.String IS_FIRST = "IS_FIRST";

    private Step step;
    private TextView description;
    private ImageView image;
    private Button nextStepButton;
    private StepDetailsListener callback;
    private RelativeLayout imageContainer;
    private ImageView playButton;
    private SimpleExoPlayer exoPlayer;
    private SimpleExoPlayerView playerView;
    private Button previousStepButton;

    public interface StepDetailsListener {
        void onPreviousStep();

        void onNextStep();
    }

    public RecipeStepDetailFragment() {

    }

    public static RecipeStepDetailFragment newInstance(Step step, boolean isFirst, boolean isLast) {
        RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FRAGMENT_STEP, step);
        bundle.putBoolean(IS_LAST, isLast);
        bundle.putBoolean(IS_FIRST, isFirst);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (StepDetailsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement StepDetailsListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);

        step = getArguments().getParcelable(FRAGMENT_STEP);

        boolean isLast = getArguments().getBoolean(IS_LAST);
        boolean isFirst = getArguments().getBoolean(IS_FIRST);

        description = (TextView) rootView.findViewById(R.id.description);
        description.setText(step.getDescription());

        imageContainer = (RelativeLayout) rootView.findViewById(R.id.media_container);
        image = (ImageView) rootView.findViewById(R.id.image_view);

        playerView = (SimpleExoPlayerView) rootView.findViewById(R.id.playerView);

        playButton = (ImageView) rootView.findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (step.getVideoURL() != null && !step.getVideoURL().isEmpty()) {
                    playButton.setVisibility(View.GONE);
                    exoPlayer.setPlayWhenReady(true);
                }
            }
        });

        setUpMediaContent();

        nextStepButton = (Button) rootView.findViewById(R.id.next_step_button);
        nextStepButton.setVisibility(isLast ? View.GONE : View.VISIBLE);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releasePlayer();
                callback.onNextStep();
            }
        });

        previousStepButton = (Button) rootView.findViewById(R.id.previous_step_button);
        previousStepButton.setVisibility(isFirst ? View.GONE : View.VISIBLE);
        previousStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releasePlayer();
                callback.onPreviousStep();
            }
        });

        return rootView;
    }

    private void setUpMediaContent() {
        if (hasVideoUrl()) {
            playerView.setVisibility(View.VISIBLE);
            playerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.video_artwork));
            playButton.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
            initializePlayer(Uri.parse(step.getVideoURL()));
        } else if (hasThumbnailImage()) {
            playerView.setVisibility(View.GONE);
            playButton.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
            Picasso.with(getContext()).load(step.getThumbnailURL()).into(this.image);
        } else {
            imageContainer.setVisibility(View.GONE);
        }
    }

    private boolean hasVideoUrl() {
        return step.getVideoURL() != null && !step.getVideoURL().isEmpty();
    }

    private boolean hasThumbnailImage() {
        return step.getThumbnailURL() != null && !step.getThumbnailURL().isEmpty();
    }

    private void initializePlayer(Uri mediaUri) {
        if (exoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            playerView.setPlayer(exoPlayer);

            String userAgent = Util.getUserAgent(getContext(), "RecipeVideo");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                    new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(), null, null);
            exoPlayer.prepare(mediaSource);
        }
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}
