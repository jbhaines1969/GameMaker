package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import static java.net.Proxy.Type.HTTP;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragFilePicker.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragFilePicker extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String KEY_FILE_PICKER_TYPE = "selectType";
    private static final String KEY_FILE_EXTENSION = "fileType";

    private String externalDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
    private String currentDirectory = externalDirectory;
    private String selectType = "";
    private String fileType = "";
    private String selectedFile = "";
    private TextView label;
    private ListView lstFiles;
    private Button btnOk;
    private Button btnCancel;
    private ArrayList<String> fileList = null;
    private ArrayAdapter<String> fileListAdapter = null;
    private int selectedItem;
    private File chosenFile;
    boolean buttonIsActive = false;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            selectType = savedInstanceState.getString(KEY_FILE_PICKER_TYPE);
            fileType = savedInstanceState.getString(KEY_FILE_EXTENSION);
        } else {
            selectType = this.getArguments().getString(KEY_FILE_PICKER_TYPE);
            fileType = this.getArguments().getString(KEY_FILE_EXTENSION);
        }
        View rootView = inflater.inflate(R.layout.fragment_file_picker, container, false);

        btnOk = (Button) rootView.findViewById(R.id.btnOk);
        if (selectType.equals("Delete")) {
            btnOk.setText(getResources().getString(R.string.delete));
        }
        if (selectType.equals("Share")) {
            btnOk.setText(R.string.share);
        }
        btnOk.setOnClickListener(this);
        btnOk.setTextColor(Color.LTGRAY);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        label = (TextView) rootView.findViewById(R.id.lblFilePickerLabel);
        label.setText(getLabelText());
        lstFiles = (ListView) rootView.findViewById(R.id.lstFiles);
        fileList = getFileList(externalDirectory);
        fileListAdapter = makeFileListAdapter();
        lstFiles.setAdapter(fileListAdapter);
        lstFiles.setOnItemClickListener(this);

        return rootView;
    }

    private String getLabelText() {

        String label = getString(R.string.choose_file);

        switch (selectType) {
            case ("Delete"):
                label = getString(R.string.delete_file);
                break;
            case ("Share"):
                label = getString(R.string.share_file);
                break;
        }

        return label;

    }

    private ArrayList<String> getFileList(String currentDirectory) {
        ArrayList<String> files = new ArrayList<>();

        try {

            if (!(currentDirectory.equals(externalDirectory))) {
                files.add(".."); // add button to go up one directory if in subfolder
            }

            File dir = new File(currentDirectory);

            if (!dir.exists() || !dir.isDirectory()) {
                return files;
            }

            for (File file : dir.listFiles()) {
                //populate list with file names from current directory

                if (file.isDirectory()) {
                    // Add "/" to directory names to identify them in the list
                    files.add(file.getName() + "/");
                } else {
                    files.add(file.getName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return files;
    }

    private ArrayAdapter<String> makeFileListAdapter() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, fileList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);
                text.setGravity(Gravity.CENTER);
                return view;
            }
        };

        return adapter;
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
        if (activity instanceof FragFilePicker.OnFragmentInteractionListener) {
            mListener = (FragFilePicker.OnFragmentInteractionListener) activity;
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

        if (v == btnCancel) {
            getActivity().getFragmentManager().beginTransaction().remove(getFragmentManager().
                    findFragmentById(R.id.info_frame_open_menu)).commit();
        }

        if (v == btnOk) {

            if (buttonIsActive) {
                if (selectType.equals("Edit")) {
                    if (mListener != null) {
                        mListener.filePickerFileChosen(chosenFile);
                    }
                }

                if (selectType.equals("Delete")) {
                    showDeleteDialog(chosenFile);
                    updateFileList(currentDirectory);
                }

                if (selectType.equals("Share")) {
                    emailFile(chosenFile);
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        for (int i = 0; i < lstFiles.getChildCount(); i++) {
            if (position == i) {
                lstFiles.getChildAt(i).setBackgroundColor(Color.LTGRAY);
                buttonIsActive = true;
            } else {
                lstFiles.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
            }
        }

        String item = (String) parent.getItemAtPosition(position);

        if (item.equals("..")) {

            btnOk.setTextColor(Color.LTGRAY);
            buttonIsActive = false;

            File oldFile = new File(currentDirectory);
            updateFileList(oldFile.getParent());
        } else {

            String fileName = fileListAdapter.getItem(position);
            File file = new File(currentDirectory, fileName);
            if (file.exists()) {

                if (file.isDirectory()) {

                    btnOk.setTextColor(Color.LTGRAY);
                    buttonIsActive = false;
                    currentDirectory += file.getName();
                    updateFileList(file.getAbsolutePath());
                } else {
                    btnOk.setTextColor(Color.BLACK);
                    buttonIsActive = true;
                    chosenFile = file;
                }
            }
        }
    }


    public void updateFileList(String filePath) {
        currentDirectory = filePath;
        fileList = getFileList(filePath);
        fileListAdapter = makeFileListAdapter();
        lstFiles.setAdapter(fileListAdapter);

    }

    public void showDeleteDialog(final File file) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_delete_list_item)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        file.delete();
                        updateFileList(currentDirectory);

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

    public void emailFile(File file) {
        Uri fileURI = Uri.fromFile(file);
        Intent mailIntent = new Intent(android.content.Intent.ACTION_SEND);
        mailIntent.setType("messsage/vnd.com.boardmonkey.TABLETop.gamefile");
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "TABLETop game file: " + file.getName());
        mailIntent.putExtra(Intent.EXTRA_STREAM, fileURI);
        startActivity(Intent.createChooser(mailIntent, "Send Mail With..."));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void filePickerFileChosen(File file);
    }
}
