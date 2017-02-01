package com.johnhaines.boardmonkey.gamemaker;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragInfoTextFragment extends Fragment implements View.OnClickListener {

    private TextView txtInfoText;
    private String text = "this didn't pull the bundle";
    private Button btnDone;

    private OnFragmentInteractionListener mListener;

    public FragInfoTextFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            text = savedInstanceState.getString("text");
        } else {
            text = "try it this way, see what happens";
        }
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info_text, container, false);

        txtInfoText = (TextView) rootView.findViewById(R.id.txtInfoFragmentText);
        txtInfoText.setText(text);
        btnDone = (Button) rootView.findViewById(R.id.btnCloseInfo);
        btnDone.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragInfoTextFragment.OnFragmentInteractionListener) {
            mListener = (FragInfoTextFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof FragInfoTextFragment.OnFragmentInteractionListener) {
            mListener = (FragInfoTextFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View view) {

        if (view == btnDone) {

            getActivity().getFragmentManager().beginTransaction().
                    remove(getActivity().getFragmentManager().findFragmentById(R.id.frmAdvancementMethodInfo)).commit();
        }
    }

    public interface OnFragmentInteractionListener {
        void onDoneButtonClicked();
    }
}
