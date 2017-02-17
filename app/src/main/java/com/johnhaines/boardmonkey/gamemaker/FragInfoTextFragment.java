package com.johnhaines.boardmonkey.gamemaker;


import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragInfoTextFragment extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private TextView txtInfoText;
    private String text = "this didn't pull the bundle";
    private ButtonNoClick btnDone;
    private LinearLayout backgroundLayout;

    private OnFragmentInteractionListener mListener;

    public FragInfoTextFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            text = savedInstanceState.getString("text");
        } else {
            text = getArguments().getString("text");
        }
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info_text, container, false);

        backgroundLayout = (LinearLayout) rootView.findViewById(R.id.infoBoxBackground);

        txtInfoText = (TextView) rootView.findViewById(R.id.txtInfoFragmentText);
        txtInfoText.setText(text);
        txtInfoText.setMovementMethod(new ScrollingMovementMethod());
        btnDone = (ButtonNoClick) rootView.findViewById(R.id.btnCloseInfo);
        btnDone.setOnClickListener(this);
        getPrimaryButtonImage(btnDone);

        setBackgroundImage();

        return rootView;
    }

    private void setBackgroundImage() {

        switch (((GameApplication) getActivity().getApplication()).getGame().getType()) {
            case ("Fantasy"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_info_background));
                break;
            case ("Sci-Fi"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_info_background));
                break;
            case ("Military"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_info_background));
                break;
            case ("Mixed"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_info_background));
                break;
            default:
                backgroundLayout.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_info_background));
                break;
        }

    }

    public void getPrimaryButtonImage(ButtonNoClick btn) {

        switch (((GameApplication) getActivity().getApplication()).getGame().getType()) {
            case ("Fantasy"):
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
            case ("Sci-Fi"):
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
            case ("Military"):
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
            default:
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragInfoTextFragment.OnFragmentInteractionListener) {
            mListener = (FragInfoTextFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof FragInfoTextFragment.OnFragmentInteractionListener) {
            mListener = (FragInfoTextFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public void playSound(int currenSoundID) {

        MediaPlayer mPlayer = MediaPlayer.create(getActivity(), currenSoundID);

        mPlayer.setVolume(1, 1);
        mPlayer.setLooping(false);
        mPlayer.setOnCompletionListener(this);
        mPlayer.start();

    }

    @Override
    public void onCompletion(MediaPlayer mPlayer) {
        mPlayer.reset();
        mPlayer.release();
    }

    @Override
    public void onClick(View view) {

        if (view instanceof ButtonNoClick) {
            String gameType = ((GameApplication) getActivity().getApplication()).getGame().getType();

            int soundID = 0;
            switch (gameType) {
                case ("Sci-Fi"):
                    soundID = R.raw.syfi_hit;
                    break;
                case ("Fantasy"):
                    soundID = R.raw.fan_hit;
                    break;
                case ("Military"):
                    soundID = R.raw.mil_hit;
                    break;
                case ("Mixed"):
                    soundID = R.raw.fan_hit;
                    break;
            }
            playSound(soundID);
        }

        if (view == btnDone) {

            if (mListener != null) {
                mListener.onDoneButtonClicked();
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onDoneButtonClicked();
    }
}
