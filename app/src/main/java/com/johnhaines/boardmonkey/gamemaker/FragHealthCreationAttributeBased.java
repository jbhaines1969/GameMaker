package com.johnhaines.boardmonkey.gamemaker;


import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragHealthCreationAttributeBased extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    ButtonNoClick btnSave;
    EditText edtAttTimes;
    EditText edtAttPlus;
    ArrayAdapter listAdapter;
    ListView lstAttList;

    public FragHealthCreationAttributeBased() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_health_creation_attribute, container, false);

        btnSave = (ButtonNoClick) rootView.findViewById(R.id.btnSaveHealthAttribute);
        btnSave.setOnClickListener(this);
        getPrimaryButtonImage(btnSave);
        edtAttPlus = (EditText) rootView.findViewById(R.id.edtHealthAdditive);
        edtAttPlus.setText(setAttPlusText());
        edtAttTimes = (EditText) rootView.findViewById(R.id.edtHealthMultiplier);
        edtAttTimes.setText(setAttTimesText());
        lstAttList = (ListView) rootView.findViewById(R.id.lstHealthCreationAttList);
        listAdapter = makeListAdapter();
        lstAttList.setAdapter(listAdapter);
        setSelectedAttributes();

        return rootView;
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

        if (view == btnSave) {

            int attPlus = Integer.parseInt(edtAttPlus.getText().toString());
            float attTimes = Float.parseFloat(edtAttTimes.getText().toString());

            ((GameApplication) getActivity().getApplication()).getGame().getHitsType().setAttPlus(attPlus);
            ((GameApplication) getActivity().getApplication()).getGame().getHitsType().setAttTimes(attTimes);

            ((GameApplication) getActivity().
                    getApplication()).getGame().getHitsType().getAttList().clear();

            for (int i = 0; i < listAdapter.getCount(); i++) {
                if (lstAttList.isItemChecked(i)) {
                    ClassCharAttribute att = (ClassCharAttribute) lstAttList.getItemAtPosition(i);
                    ((GameApplication) getActivity().
                            getApplication()).getGame().getHitsType().addAttribute(att);
                }
            }

            ((ActHealthCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();
        }
    }

    private String setAttPlusText() {
        String text = String.valueOf(((GameApplication) getActivity().
                getApplication()).getGame().getHitsType().getAttPlus());
        return text;
    }

    private String setAttTimesText() {
        String text = String.valueOf(((GameApplication) getActivity().
                getApplication()).getGame().getHitsType().getAttTimes());
        return text;
    }

    private void setSelectedAttributes() {

        for (ClassCharAttribute item : ((GameApplication) getActivity().
                getApplication()).getGame().getHitsType().getAttList()) {

            for (int i = 0; i < listAdapter.getCount(); i++) {
                if (item == listAdapter.getItem(i)) {
                    lstAttList.setItemChecked(i, true);
                }
            }
        }

    }

    private ArrayAdapter makeListAdapter() {
        listAdapter = new ArrayAdapter<ClassCharAttribute>(
                getActivity(), android.R.layout.simple_list_item_multiple_choice, ((GameApplication) getActivity().
                getApplication()).getGame().getAttributes()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);
                return view;
            }
        };

        return listAdapter;
    }

}
