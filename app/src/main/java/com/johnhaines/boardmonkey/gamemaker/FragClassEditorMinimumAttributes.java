package com.johnhaines.boardmonkey.gamemaker;


import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragClassEditorMinimumAttributes extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private Integer index = 0;

    private TextView lblAttributeModsRaceName;
    private ListView lstAttributeMods;
    private ButtonNoClick btnSaveAttributeMods;
    private AdapterTreeKeyAndEditTextList listAdapter;

    public FragClassEditorMinimumAttributes() {
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
        View rootView = inflater.inflate(R.layout.fragment_race_editor_attribute_mods, container, false);

        lblAttributeModsRaceName = (TextView) rootView.findViewById(R.id.lblAttributeModsRaceName);
        lblAttributeModsRaceName.setText("Minimum Attributes");
        lstAttributeMods = (ListView) rootView.findViewById(R.id.lstAttributeMods);
        btnSaveAttributeMods = (ButtonNoClick) rootView.findViewById(R.id.btnSaveAttributeMods);
        btnSaveAttributeMods.setOnClickListener(this);
        getPrimaryButtonImage(btnSaveAttributeMods);
        listAdapter = makeListAdapter();
        lstAttributeMods.setAdapter(listAdapter);

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

    private AdapterTreeKeyAndEditTextList makeListAdapter() {

        AdapterTreeKeyAndEditTextList listAdapter = new AdapterTreeKeyAndEditTextList(((GameApplication) getActivity().
                getApplication()).getGame().getClasses().get(index).getMinAttributes());

        return listAdapter;
    }

    private String getRaceName(Integer index) {

        String name = ((GameApplication) getActivity().
                getApplication()).getGame().getClasses().get(index).toString();

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

        if (v == btnSaveAttributeMods) {

            int mod = 0;

            for (int i = 0; i < listAdapter.getCount(); i++) {

                Map.Entry<ClassCharAttribute, Integer> item = listAdapter.getItem(i);

                View view = lstAttributeMods.getChildAt(i);
                EditText modField = (EditText) view.findViewById(R.id.txtModField);

                String string = modField.getText().toString();

                if (isNumeric(string)) {
                    mod = Integer.parseInt(string);

                    ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).
                            addMinAttribute(item.getKey(), mod);
                }
            }

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
