package com.appbaco.appbaco.controllers.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.appbaco.appbaco.R;
import com.appbaco.appbaco.controllers.entity.AccountController;
import com.appbaco.appbaco.controllers.entity.TransactionCategoryController;
import com.appbaco.appbaco.models.entity.Account;
import com.appbaco.appbaco.models.entity.TransactionCategory;
import com.appbaco.appbaco.utilities.ColorAdapter;

/**
 * Created by MARAUJO on 12/25/2016.
 */

public class DialogAccount extends DialogFragment {
    private ActionListener actionListener;
    private EditText txtAccountName;
    private EditText txtAccountDescription;
    private Spinner spColors;
    private Button btnCancel;
    private Button btnSave;
    private Integer id;
    private final AccountController entityController = new AccountController(MainActivity.appbacoDatabase);

    public DialogAccount() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = 0;
        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
        }
        return createDialog();
    }

    // TODO: agregar comentario de funcionalidad
    public interface ActionListener {
        public abstract void onSave(Integer id);
        public abstract void onCancel();
    }

    // TODO: agregar comentario de funcionalidad
    public void setActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    // TODO: agregar comentario de funcionalidad
    private boolean validate(View view) {
        if (this.txtAccountName.getText().toString().isEmpty()) {
            this.txtAccountDescription.setError("Name does not be blank");
            return false;
        }
        return true;
    }

    // TODO: agregar comentario de funcionalidad
    public AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_account, null);
        builder.setView(v);

        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        txtAccountName = (EditText) v.findViewById(R.id.txtAccountName);
        txtAccountDescription = (EditText) v.findViewById(R.id.txtAccountDescription);
        spColors = (Spinner) v.findViewById(R.id.spColors);

        ColorAdapter<Integer> spinnerArrayAdapter = new ColorAdapter<Integer>(getActivity(), MainActivity.getColors());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColors.setAdapter(spinnerArrayAdapter);
        spColors.setSelection(MainActivity.getColors().indexOf(0));

        if (MainActivity.appbacoDatabase != null) {
            Account entity = null;
            try {
                entity = entityController.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
            }
            if (entity != null) {
                txtAccountName.setText(entity.getName());
                txtAccountDescription.setText(entity.getDescription());
                if (MainActivity.getColors().indexOf(entity.getColor()) > 0) {
                    spColors.setSelection(MainActivity.getColors().indexOf(entity.getColor()));
                }
            }
        }

        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );

        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!validate(v)) {
                            actionListener.onCancel();
                            return;
                        }

                        Account entity = new Account();
                        entity.setId(id);
                        entity.setName(txtAccountName.getText().toString());
                        entity.setAccountTypeId(1);
                        entity.setDescription(txtAccountDescription.getText().toString());
                        entity.setColor(Integer.parseInt(spColors.getSelectedItem().toString()));

                        try {
                            if (entity.getId() == 0) {
                                entityController.create(entity);
                            } else {
                                entityController.update(entity);
                            }
                            actionListener.onSave(2);
                            dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }
        );
        return builder.create();
    }
}