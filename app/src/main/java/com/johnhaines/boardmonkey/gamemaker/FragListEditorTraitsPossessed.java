package com.johnhaines.boardmonkey.gamemaker;


import android.app.Fragment;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragListEditorTraitsPossessed extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private Integer index = 0;
    private String listType = "";

    private TextView lblPossessedTraitsItemName;
    private ListView lstPossessedTraits;
    private ButtonNoClick btnSavePossessedTraits;
    private ArrayAdapter listAdapter;

    public FragListEditorTraitsPossessed() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt("index");
            listType = getArguments().getString("listType");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        index = getArguments().getInt("index");
        listType = getArguments().getString("listType");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_race_editor_race_traits, container, false);

        lblPossessedTraitsItemName = (TextView) rootView.findViewById(R.id.lblPossessedTraitsItemName);
        lblPossessedTraitsItemName.setText("Traits Possessed");
        lstPossessedTraits = (ListView) rootView.findViewById(R.id.lstRaceTraits);
        btnSavePossessedTraits = (ButtonNoClick) rootView.findViewById(R.id.btnSaveRaceTraits);
        btnSavePossessedTraits.setOnClickListener(this);
        getPrimaryButtonImage(btnSavePossessedTraits);
        listAdapter = makeListAdapter();
        lstPossessedTraits.setAdapter(listAdapter);

        selectItems();

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

    private void selectItems() {

        if (listType.equals("Races")) {
            for (ClassCharTrait item : ((GameApplication) getActivity().
                    getApplication()).getGame().getRaces().get(index).getTraits()) {

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (item == listAdapter.getItem(i)) {
                        lstPossessedTraits.setItemChecked(i, true);
                    }
                }
            }
        }

        if (listType.equals("Classes")) {
            for (ClassCharTrait item : ((GameApplication) getActivity().
                    getApplication()).getGame().getClasses().get(index).getTraits()) {

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (item == listAdapter.getItem(i)) {
                        lstPossessedTraits.setItemChecked(i, true);
                    }
                }
            }
        }
    }

    private ArrayAdapter makeListAdapter() {

        ArrayAdapter listAdapter = new ArrayAdapter<ClassCharTrait>(
                getActivity(), android.R.layout.simple_list_item_multiple_choice, ((GameApplication) getActivity().
                getApplication()).getGame().getTraits()) {
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

    private String getItemName(Integer index) {

        String name = "";

        if (listType.equals("Races")) {
            name = ((GameApplication) getActivity().
                    getApplication()).getGame().getRaces().get(index).toString();
        }

        if (listType.equals("Classes")) {
            name = ((GameApplication) getActivity().
                    getApplication()).getGame().getClasses().get(index).toString();
        }

        return name;
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

        if (v == btnSavePossessedTraits) {

            if (listType.equals("Races")) {
                ((GameApplication) getActivity().
                        getApplication()).getGame().getRaces().get(index).clearRaceTraits();

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (lstPossessedTraits.isItemChecked(i) == true) {
                        ClassCharTrait item = (ClassCharTrait) lstPossessedTraits.getItemAtPosition(i);
                        ((GameApplication) getActivity().
                                getApplication()).getGame().getRaces().get(index).addTrait(item);
                    }
                }
            }

            if (listType.equals("Classes")) {
                ((GameApplication) getActivity().
                        getApplication()).getGame().getClasses().get(index).clearClassTraits();

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (lstPossessedTraits.isItemChecked(i) == true) {
                        ClassCharTrait item = (ClassCharTrait) lstPossessedTraits.getItemAtPosition(i);
                        ((GameApplication) getActivity().
                                getApplication()).getGame().getClasses().get(index).addTrait(item);
                    }
                }
            }

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();
        }

    }
}
