package com.johnhaines.boardmonkey.gamemaker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogDeleteListItem extends DialogFragment {

    private String listType;
    private int index;
    DeleteDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        listType = getArguments().getString("listType");
        index = getArguments().getInt("index");
        builder.setMessage(R.string.dialog_delete_list_item)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (listType == "Attributes") {
                            ClassCharAttribute charAt = ((GameApplication) getActivity().getApplication()).getGame().getAttributes().get(index);
                            ((GameApplication) getActivity().getApplication()).getGame().removeAttribute(index);
                            for (ClassCharRace race : ((GameApplication) getActivity().getApplication()).getGame().getRaces()) {
                                race.removeAttributeModMale(charAt);
                                race.removeAttributeModFemale(charAt);
                            }
                        }
                        if (listType == "Races") {
                            ((GameApplication) getActivity().getApplication()).getGame().removeRace(index);

                        }
                        if (listType == "Classes") {
                            ((GameApplication) getActivity().getApplication()).getGame().removeClass(index);
                        }
                        if (listType == "Skills") {
                            ((GameApplication) getActivity().getApplication()).getGame().removeSkill(index);
                        }
                        if (listType == "Traits") {
                            ((GameApplication) getActivity().getApplication()).getGame().removeTrait(index);
                        }
                        if (listType == "Features") {
                            ((GameApplication) getActivity().getApplication()).getGame().removeFeature(index);
                        }

                        mListener.deleteDialogDeleteClicked();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface DeleteDialogListener {
        public void deleteDialogDeleteClicked();
    }
}





