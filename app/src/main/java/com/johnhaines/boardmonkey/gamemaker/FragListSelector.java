package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragListSelector.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragListSelector#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragListSelector extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ButtonNoClick btnAttributes;
    private ButtonNoClick btnRaces;
    private ButtonNoClick btnClasses;
    private ButtonNoClick btnSkills;
    private ButtonNoClick btnTraits;
    private ButtonNoClick btnFeatures;

    private ClassGame game;

    private Activity activity;


    public FragListSelector() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragListSelector.
     */

    public static FragListSelector newInstance(String param1, String param2) {
        FragListSelector fragment = new FragListSelector();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = ((GameApplication) getActivity().getApplication()).getGame();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_select, container, false);

        btnAttributes = (ButtonNoClick) rootView.findViewById(R.id.btnAttributes);
        btnAttributes.setOnClickListener(this);
        getPrimaryButtonImage(btnAttributes);
        btnRaces = (ButtonNoClick) rootView.findViewById(R.id.btnRaces);
        btnRaces.setOnClickListener(this);
        getPrimaryButtonImage(btnRaces);
        btnClasses = (ButtonNoClick) rootView.findViewById(R.id.btnClasses);
        btnClasses.setOnClickListener(this);
        getPrimaryButtonImage(btnClasses);
        btnSkills = (ButtonNoClick) rootView.findViewById(R.id.btnSkills);
        btnSkills.setOnClickListener(this);
        getPrimaryButtonImage(btnSkills);
        btnTraits = (ButtonNoClick) rootView.findViewById(R.id.btnTraits);
        btnTraits.setOnClickListener(this);
        getPrimaryButtonImage(btnTraits);
        btnFeatures = (ButtonNoClick) rootView.findViewById(R.id.btnFeatures);
        btnFeatures.setOnClickListener(this);
        getPrimaryButtonImage(btnFeatures);

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void getPrimaryButtonImage(ButtonNoClick btn) {

        switch (game.getType()) {
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
            case ("Mixed"):
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
            default:
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
        }
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
    public void onClick(View v) {

        if (v instanceof ButtonNoClick) {
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

        String message = "";
        switch (v.getId()) {
            case (R.id.btnAttributes):
                message = "Attributes";
                break;
            case (R.id.btnRaces):
                message = "Races";
                break;
            case (R.id.btnSkills):
                message = "Skills";
                break;
            case (R.id.btnClasses):
                message = "Classes";
                break;
            case (R.id.btnTraits):
                message = "Traits";
                break;
            case (R.id.btnFeatures):
                message = "Features";
                break;
        }
        if (mListener != null) {
            mListener.onListSelectorInteraction(message, 0);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onListSelectorInteraction(String message, Integer index);
    }


}
