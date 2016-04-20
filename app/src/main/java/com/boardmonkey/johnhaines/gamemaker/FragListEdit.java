package com.boardmonkey.johnhaines.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragListEdit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragListEdit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragListEdit extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener, AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LIST_TYPE_KEY = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private String listType;
    private TextView lblGroupName;
    private Button btnAddItem;
    private ListView lstItemList;
    private EditText txtAddItem;
    private ArrayAdapter listAdapter;

    public FragListEdit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param listType Parameter 1.
     *
     * @return A new instance of fragment FragListEdit.
     */
    // TODO: Rename and change types and number of parameters
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
        listAdapter.notifyDataSetChanged();

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

                // This section is for Characteristic Creation

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

                // This section if for Race Editing


                // This section is for Class Editing

                txtAddItem.setText("");
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Integer index = position;

        if (mListener != null) {
            mListener.onListEditSelectInList(listType, index);
        }
    }

    public ArrayAdapter makeListAdapter() {

        ArrayList<String> emptyList = new ArrayList<String>();

        ArrayAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, emptyList);

        ((GameApplication) getActivity().getApplication()).getGame().sortList(listType);

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
        // TODO: Update argument type and name
        void onListEditSelectInList(String message, Integer index);
    }
}
