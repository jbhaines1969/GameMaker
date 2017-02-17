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
public class FragRaceEditorClassesAllowed extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private Integer index = 0;

    private TextView lblRClassesRaceName;
    private ListView lstClassesAllowed;
    private ButtonNoClick btnSaveClassesAllowed;
    private ArrayAdapter listAdapter;


    public FragRaceEditorClassesAllowed() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt("index");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        index = getArguments().getInt("index");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_race_editor_classes_allowed, container, false);

        lblRClassesRaceName = (TextView) rootView.findViewById(R.id.lblPossessedTraitsItemName);
        lblRClassesRaceName.setText("Allowed Classes");
        lstClassesAllowed = (ListView) rootView.findViewById(R.id.lstClassesAllowed);
        btnSaveClassesAllowed = (ButtonNoClick) rootView.findViewById(R.id.btnSaveClassesAllowed);
        btnSaveClassesAllowed.setOnClickListener(this);
        getPrimaryButtonImage(btnSaveClassesAllowed);
        listAdapter = makeListAdapter();
        lstClassesAllowed.setAdapter(listAdapter);

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

    private void selectItems() {

        for (ClassCharClass item : ((GameApplication) getActivity().
                getApplication()).getGame().getRaces().get(index).getAllowedClasses()) {

            for (int i = 0; i < listAdapter.getCount(); i++) {
                if (item == listAdapter.getItem(i)) {
                    lstClassesAllowed.setItemChecked(i, true);
                }
            }
        }
    }

    private final ArrayAdapter<ClassCharClass> makeListAdapter() {

        ArrayAdapter listAdapter = new ArrayAdapter<ClassCharClass>(
                getActivity(), android.R.layout.simple_list_item_multiple_choice, ((GameApplication) getActivity().
                getApplication()).getGame().getClasses()) {
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

    private String getRaceName(Integer index) {

        String name = ((GameApplication) getActivity().
                getApplication()).getGame().getRaces().get(index).toString();

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

        if (v == btnSaveClassesAllowed) {

            ((GameApplication) getActivity().
                    getApplication()).getGame().getRaces().get(index).clearClassesAllowed();
            for (int i = 0; i < listAdapter.getCount(); i++) {
                if (lstClassesAllowed.isItemChecked(i) == true) {
                    ClassCharClass item = (ClassCharClass) lstClassesAllowed.getItemAtPosition(i);
                    ((GameApplication) getActivity().
                            getApplication()).getGame().getRaces().get(index).addClass(item);
                }
            }

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();
        }

    }
}
