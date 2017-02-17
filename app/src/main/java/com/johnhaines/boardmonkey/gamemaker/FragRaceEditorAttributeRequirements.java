package com.johnhaines.boardmonkey.gamemaker;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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
public class FragRaceEditorAttributeRequirements extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private Integer index = 0;

    private TextView lblAttributeModsRaceName;
    private TextView lblAttribute;
    private TextView lblMinMax;
    private ListView lstAttributeMods;
    private ButtonNoClick btnSaveAttributeMods;
    private AdapterTreeKeyAndTwoEditTextList listAdapter;

    public FragRaceEditorAttributeRequirements() {
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
        View rootView = inflater.inflate(R.layout.fragment_race_editor_attribute_requirements, container, false);

        lblAttributeModsRaceName = (TextView) rootView.findViewById(R.id.lblAttributeModsRaceName);
        lblAttributeModsRaceName.setText("Attribute Limits");
        lblAttribute = (TextView) rootView.findViewById(R.id.lblAttribute);
        lblMinMax = (TextView) rootView.findViewById(R.id.lblMinMax);
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

    private AdapterTreeKeyAndTwoEditTextList makeListAdapter() {

        AdapterTreeKeyAndTwoEditTextList listAdapter = new AdapterTreeKeyAndTwoEditTextList(((GameApplication) getActivity().
                getApplication()).getGame().getRaces().get(index).getMinAttributes(), ((GameApplication) getActivity().
                getApplication()).getGame().getRaces().get(index).getMaxAttributes());


        return listAdapter;
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

            int minMod = 0;
            int maxMod = 0;

            for (int i = 0; i < listAdapter.getCount(); i++) {

                Map.Entry<ClassCharAttribute, Integer> itemMin = listAdapter.getItemMin(i);
                Map.Entry<ClassCharAttribute, Integer> itemMax = listAdapter.getItemMax(i);

                View view = lstAttributeMods.getChildAt(i);
                EditText minField = (EditText) view.findViewById(R.id.txtMinField);
                EditText maxField = (EditText) view.findViewById(R.id.txtMaxField);

                String stringMin = minField.getText().toString();
                String stringMax = maxField.getText().toString();

                if (isNumeric(stringMin)) {
                    minMod = Integer.parseInt(stringMin);

                    ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).
                            addMinAttribute(itemMin.getKey(), minMod);
                }

                if (isNumeric(stringMax)) {

                    maxMod = Integer.parseInt(stringMax);

                    if (maxMod < minMod) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Invalid Values: Maximum cannot be lower than minimum.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                        break;
                    } else {
                        ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).
                                addMaxAttribute(itemMax.getKey(), maxMod);
                    }
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
