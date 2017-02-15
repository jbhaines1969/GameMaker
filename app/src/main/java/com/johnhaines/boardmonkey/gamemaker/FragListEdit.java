package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragListEdit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragListEdit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragListEdit extends Fragment implements AdapterView.OnItemLongClickListener,
        View.OnClickListener, AdapterView.OnItemClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LIST_TYPE_KEY = "listType";
    private static final String INDEX_KEY = "index";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private String listType;
    private int index;
    private int levelNumber;
    private TextView lblGroupName;
    private Button btnAddItem;
    private ListView lstItemList;
    private EditText txtAddItem;
    private BaseAdapter listAdapter;
    private AdapterTreeIntegerKeyAndStringValue listAdapterLevelNames;
    private EditText edtLevelNumber;
    private boolean isLevelNames = false;

    public FragListEdit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param listType Parameter 1.
     * @return A new instance of fragment FragListEdit.
     */

    public static FragListEdit newInstance(String listType) {
        FragListEdit fragment = new FragListEdit();
        Bundle args = new Bundle();
        args.putString(LIST_TYPE_KEY, listType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //listType = getArguments().getString(LIST_TYPE_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            listType = savedInstanceState.getString(LIST_TYPE_KEY);
            index = savedInstanceState.getInt(INDEX_KEY);
        } else {
            listType = this.getArguments().getString(LIST_TYPE_KEY);
            index = this.getArguments().getInt(INDEX_KEY);
        }

        View rootView = null;
        if (listType.equals("Level Names")) {
            rootView = inflater.inflate(R.layout.fragment_list_editor_level_names, container, false);
            isLevelNames = true;
        } else {
            rootView = inflater.inflate(R.layout.fragment_list_editor, container, false);
            isLevelNames = false;
        }

        lblGroupName = (TextView) rootView.findViewById(R.id.lblGroupName);
        lblGroupName.setText(listType);
        btnAddItem = (Button) rootView.findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(this);
        lstItemList = (ListView) rootView.findViewById(R.id.lstItemList);
        lstItemList.setOnItemClickListener(this);
        lstItemList.setOnItemLongClickListener(this);
        txtAddItem = (EditText) rootView.findViewById(R.id.txtAddItem);
        listAdapter = makeListAdapter();
        lstItemList.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        if (isLevelNames) {
            edtLevelNumber = (EditText) rootView.findViewById(R.id.edtLevelNumber);
        }

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((GameApplication) getActivity().getApplication()).getGame().sortList(listType);
        listAdapter.notifyDataSetChanged();
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LIST_TYPE_KEY, listType);
        outState.putInt(INDEX_KEY, index);

    }

    @Override
    public void onClick(View v) {

        if (v == btnAddItem) {

            if (!txtAddItem.getText().toString().trim().isEmpty()) {

                String newItem = txtAddItem.getText().toString();

                // This section is for ClassCharacteristic Creation

                if (listType.equals(getString(R.string.attributes))) {
                    ClassCharAttribute item = new ClassCharAttribute(newItem);
                    if (!((GameApplication) getActivity().getApplication()).getGame().getAttributes().contains(item)) {
                        ((GameApplication) getActivity().getApplication()).getGame().addAttribute(item);
                        for (ClassCharRace race : ((GameApplication) getActivity().getApplication()).getGame().getRaces()) {
                            race.addAttributeModMale(item, 0);
                            race.addAttributeModFemale(item, 0);
                            race.addMinAttribute(item, 0);
                            race.addMaxAttribute(item, 0);
                        }
                        for (ClassCharClass thisClass : ((GameApplication) getActivity().getApplication()).getGame().
                                getClasses()) {
                            thisClass.addMinAttribute(item, 0);
                        }
                    }
                }
                if (listType.equals(getString(R.string.races))) {
                    ClassCharRace item = new ClassCharRace(newItem);
                    if (!((GameApplication) getActivity().getApplication()).getGame().getRaces().contains(item)) {
                        ((GameApplication) getActivity().getApplication()).getGame().addRace(item);
                        for (ClassCharAttribute attribute : ((GameApplication) getActivity().getApplication()).getGame().
                                getAttributes()) {
                            item.addAttributeModMale(attribute, 0);
                            item.addAttributeModFemale(attribute, 0);
                            item.addMinAttribute(attribute, 0);
                            item.addMaxAttribute(attribute, 0);
                        }
                    }
                }
                if (listType.equals(getString(R.string.classes))) {
                    ClassCharClass charAt = new ClassCharClass(newItem);
                    if (!((GameApplication) getActivity().getApplication()).getGame().getClasses().contains(charAt)) {
                        ((GameApplication) getActivity().getApplication()).getGame().addClass(charAt);
                        for (ClassCharAttribute attribute : ((GameApplication) getActivity().getApplication()).getGame().
                                getAttributes()) {
                            charAt.addMinAttribute(attribute, 0);
                            charAt.addMaxAttribute(attribute, 0);
                        }
                    }
                }
                if (listType.equals(getString(R.string.skills))) {
                    ClassCharSkill charAt = new ClassCharSkill(newItem);
                    int maxPoints = ((GameApplication) getActivity().getApplication()).getGame().getMaxSkillPoints();
                    charAt.setMaxPoints(maxPoints);
                    if (!((GameApplication) getActivity().getApplication()).getGame().getSkills().contains(charAt)) {
                        ((GameApplication) getActivity().getApplication()).getGame().addSkill(charAt);
                    }
                }
                if (listType.equals(getString(R.string.traits))) {
                    ClassCharTrait charAt = new ClassCharTrait(newItem);
                    int maxPoints = ((GameApplication) getActivity().getApplication()).getGame().getMaxTraitPoints();
                    charAt.setMaxPoints(maxPoints);
                    if (!((GameApplication) getActivity().getApplication()).getGame().getTraits().contains(charAt)) {
                        ((GameApplication) getActivity().getApplication()).getGame().addTrait(charAt);
                    }
                }
                if (listType.equals(getString(R.string.features))) {
                    ClassCharFeature charAt = new ClassCharFeature(newItem);
                    int maxPoints = ((GameApplication) getActivity().getApplication()).getGame().getMaxFeatPoints();
                    charAt.setMaxPoints(maxPoints);
                    if (!((GameApplication) getActivity().getApplication()).getGame().getFeatures().contains(charAt)) {
                        ((GameApplication) getActivity().getApplication()).getGame().addFeature(charAt);
                    }
                }

                if (listType.equals(getString(R.string.level_names))) {
                    if (!(edtLevelNumber.getText().toString().trim().isEmpty())) {
                        int nameListIndex = Integer.parseInt(edtLevelNumber.getText().toString());
                        ((GameApplication) getActivity().getApplication()).getGame().
                                getClasses().get(index).addLevelName(nameListIndex, newItem);
                        edtLevelNumber.setText("");
                    } else {
                        //TODO make toast telling them to put in a level number
                    }
                    lstItemList.setAdapter(makeListAdapter());
                }

                listAdapter.notifyDataSetChanged();

                ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();

                txtAddItem.setText("");
            }
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (mListener != null) {
            mListener.onListEditSelectInList(listType, position);
        }
    }

    private BaseAdapter makeListAdapter() {

        ArrayList<String> emptyList = new ArrayList<String>();

        BaseAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, emptyList);

        ((GameApplication) getActivity().getApplication()).getGame().sortList(listType);

        if (listType.equals(getString(R.string.attributes))) {
            listAdapter = new ArrayAdapter<ClassCharAttribute>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getAttributes()) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(Color.BLACK);
                    return view;
                }
            };
        }
        if (listType.equals(getString(R.string.races))) {
            listAdapter = new ArrayAdapter<ClassCharRace>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getRaces()) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(Color.BLACK);
                    return view;
                }
            };
        }
        if (listType.equals(getString(R.string.classes))) {
            listAdapter = new ArrayAdapter<ClassCharClass>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getClasses()) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(Color.BLACK);
                    return view;
                }
            };
        }
        if (listType.equals(getString(R.string.skills))) {
            listAdapter = new ArrayAdapter<ClassCharSkill>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getSkills()) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(Color.BLACK);
                    return view;
                }
            };
        }
        if (listType.equals(getString(R.string.traits))) {
            listAdapter = new ArrayAdapter<ClassCharTrait>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getTraits()) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(Color.BLACK);
                    return view;
                }
            };

        }
        if (listType.equals(getString(R.string.features))) {
            listAdapter = new ArrayAdapter<ClassCharFeature>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getFeatures()) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(Color.BLACK);
                    return view;
                }
            };
        }
        if (listType.equals(getString(R.string.level_names))) {
            listAdapter = new AdapterTreeIntegerKeyAndStringValue(((GameApplication) getActivity().
                    getApplication()).getGame().getClasses().get(index).getLevelNameList());
        }

        return listAdapter;

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        //TODO set position to key if levelNamesList

        showDeleteDialog(position);

        return true;
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

    public void showDeleteDialog(Integer position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final int index = position;
        builder.setMessage(R.string.dialog_delete_list_item)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (listType == "Attributes") {
                            ClassCharAttribute charAt = ((GameApplication) getActivity().getApplication()).getGame().
                                    getAttributes().get(index);
                            ((GameApplication) getActivity().getApplication()).getGame().removeAttribute(index);
                            for (ClassCharRace race : ((GameApplication) getActivity().getApplication()).getGame().getRaces()) {
                                race.removeAttributeModMale(charAt);
                                race.removeAttributeModFemale(charAt);
                                race.removeMinAttribute(charAt);
                                race.removeMaxAttribute(charAt);
                            }
                            for (ClassCharClass eachClass : ((GameApplication) getActivity().getApplication()).getGame().
                                    getClasses()) {
                                eachClass.removeMinAttribute(charAt);
                            }
                        }
                        if (listType.equals("Races")) {
                            ((GameApplication) getActivity().getApplication()).getGame().removeRace(index);
                        }
                        if (listType.equals("Classes")) {
                            ((GameApplication) getActivity().getApplication()).getGame().removeClass(index);
                        }
                        if (listType.equals("Skills")) {
                            ((GameApplication) getActivity().getApplication()).getGame().removeSkill(index);
                        }
                        if (listType.equals("Traits")) {
                            ((GameApplication) getActivity().getApplication()).getGame().removeTrait(index);
                        }
                        if (listType.equals("Features")) {
                            ((GameApplication) getActivity().getApplication()).getGame().removeFeature(index);
                        }
                        if (listType.equals("Level Names")) {
                            ((GameApplication) getActivity().getApplication()).getGame().getClasses().
                                    get(index).removeLevelName(index);
                        }

                        listAdapter.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();

                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public interface OnFragmentInteractionListener {
        void onListEditSelectInList(String message, Integer index);
    }

}
