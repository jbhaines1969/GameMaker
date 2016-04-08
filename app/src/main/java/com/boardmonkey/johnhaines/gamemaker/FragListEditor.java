package com.boardmonkey.johnhaines.gamemaker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class FragListEditor extends Fragment implements OnItemSelectedListener, View.OnClickListener, AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LIST_TYPE_KEY = "param1";


    // TODO: Rename and change types of parameters
    private String listType;
    private TextView lblGroupName;
    private Button btnAddItem;
    private ListView lstItemList;
    private EditText txtAddItem;
    private ArrayAdapter listAdapter;

    public FragListEditor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param listType Parameter 1.
     *
     * @return A new instance of fragment FragListEditor.
     */
    // TODO: Rename and change types and number of parameters
    public static FragListEditor newInstance(String listType) {
        FragListEditor fragment = new FragListEditor();
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
        listType = this.getArguments().getString("listType");
        View rootView = inflater.inflate(R.layout.fragment_list_editor, container, false);

        lblGroupName = (TextView) rootView.findViewById(R.id.lblGroupName);
        lblGroupName.setText(listType);
        btnAddItem = (Button) rootView.findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(this);
        lstItemList = (ListView) rootView.findViewById(R.id.lstItemList);
        lstItemList.setOnItemClickListener(this);
        txtAddItem = (EditText) rootView.findViewById(R.id.txtAddItem);
        listAdapter = makeListAdapter();
        lstItemList.setAdapter(listAdapter);


        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if (v == btnAddItem) {

            if (txtAddItem.getText().toString() != null) {

                String newItem = txtAddItem.getText().toString();

                if (listType == "Attributes") {
                    CharAttribute charAt = new CharAttribute(newItem);
                    ((GameApplication) getActivity().getApplication()).getGame().addAttribute(charAt);
                    listAdapter.notifyDataSetChanged();
                }
                if (listType == "Races") {
                    CharRace charAt = new CharRace(newItem);
                    ((GameApplication) getActivity().getApplication()).getGame().addRace(charAt);
                    listAdapter.notifyDataSetChanged();
                }
                if (listType == "Classes") {
                    CharClass charAt = new CharClass(newItem);
                    ((GameApplication) getActivity().getApplication()).getGame().addClass(charAt);
                    listAdapter.notifyDataSetChanged();
                }
                if (listType == "Skills") {
                    CharSkill charAt = new CharSkill(newItem);
                    ((GameApplication) getActivity().getApplication()).getGame().addSkill(charAt);
                    listAdapter.notifyDataSetChanged();
                }
                if (listType == "Traits") {
                    CharTrait charAt = new CharTrait(newItem);
                    ((GameApplication) getActivity().getApplication()).getGame().addTrait(charAt);
                    listAdapter.notifyDataSetChanged();
                }
                if (listType == "Features") {
                    CharFeature charAt = new CharFeature(newItem);
                    ((GameApplication) getActivity().getApplication()).getGame().addFeature(charAt);
                    listAdapter.notifyDataSetChanged();
                }

                txtAddItem.setText("");
            }
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (parent == lstItemList) {
            if (listType == "Attributes") {

            }
        }

    }

    public ArrayAdapter makeListAdapter() {

        ArrayList<String> emptyList = new ArrayList<String>();

        ArrayAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, emptyList);

        if (listType == "Attributes") {
            listAdapter = new ArrayAdapter<>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getAttributes());
        }
        if (listType == "Races") {
            listAdapter = new ArrayAdapter<>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getRaces());
        }
        if (listType == "Classes") {
            listAdapter = new ArrayAdapter<>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getClasses());
        }
        if (listType == "Skills") {
            listAdapter = new ArrayAdapter<>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getSkills());
        }
        if (listType == "Traits") {
            listAdapter = new ArrayAdapter<>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getTraits());
        }
        if (listType == "Features") {
            listAdapter = new ArrayAdapter<>(
                    getActivity(), android.R.layout.simple_list_item_1, ((GameApplication) getActivity().
                    getApplication()).getGame().getFeatures());
        }

        return listAdapter;

    }


}
