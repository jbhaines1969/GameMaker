package com.boardmonkey.johnhaines.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
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
public class FragListSelector extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button btnAttributes;
    Button btnRaces;
    Button btnClasses;
    Button btnSkills;
    Button btnTraits;
    Button btnFeatures;


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
    // TODO: Rename and change types and number of parameters
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

        btnAttributes = (Button) rootView.findViewById(R.id.btnAttributes);
        btnAttributes.setOnClickListener(this);
        btnRaces = (Button) rootView.findViewById(R.id.btnRaces);
        btnRaces.setOnClickListener(this);
        btnClasses = (Button) rootView.findViewById(R.id.btnClasses);
        btnClasses.setOnClickListener(this);
        btnSkills = (Button) rootView.findViewById(R.id.btnSkills);
        btnSkills.setOnClickListener(this);
        btnTraits = (Button) rootView.findViewById(R.id.btnTraits);
        btnTraits.setOnClickListener(this);
        btnFeatures = (Button) rootView.findViewById(R.id.btnFeatures);
        btnFeatures.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        String message = "";
        switch(v.getId()) {
            case(R.id.btnAttributes):
                message = "Attributes";
                break;
            case(R.id.btnRaces):
                message = "Races";
                break;
            case(R.id.btnSkills):
                message = "Skills";
                break;
            case(R.id.btnClasses):
                message = "Classes";
                break;
            case(R.id.btnTraits):
                message = "Traits";
                break;
            case(R.id.btnFeatures):
                message = "Features";
                break;
        }
        if (mListener != null) {
            mListener.onListSelectorInteraction(message);
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
        // TODO: Update argument type and name
        void onListSelectorInteraction(String message);
    }


}
