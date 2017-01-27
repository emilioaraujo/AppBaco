package com.appbaco.appbaco.controllers.fragment;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appbaco.appbaco.R;
import com.appbaco.appbaco.controllers.activity.MainActivity;
import com.appbaco.appbaco.controllers.entity.TransactionCategoryController;
import com.appbaco.appbaco.models.activity.ListCategoryListAdapter;
import com.appbaco.appbaco.models.entity.TransactionCategory;
import com.appbaco.appbaco.utilities.ColorAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by MARAUJO on 12/25/2016.
 */

public class DialogCategory extends DialogFragment {
    private ActionListener actionListener;
    private EditText txtCategoryName;
    private EditText txtCategoryDescription;
    private Spinner spColors;
    private Button btnCancel;
    private Button btnSave;
    private Integer id;
    private Integer transactionTypeId;
    private final TransactionCategoryController entityController = new TransactionCategoryController(MainActivity.appbacoDatabase);

    public DialogCategory() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = 0;
        this.transactionTypeId=0;
        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
        }
        if (getArguments() != null) {
            this.transactionTypeId = getArguments().getInt("transaction_type_id");
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
       String message ="";
        boolean error = false;
        if (this.txtCategoryName.getText().toString().isEmpty()) {
            message="Name does not be blank";
            error=true;
        }
        if(error){
            Snackbar snackbar = Snackbar.make(view, "Error: "+message, Snackbar.LENGTH_LONG).setAction("Action", null);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorBackgroundError));
            snackbar.show();
            return false;
        }else {
            return true;
        }
    }

    // TODO: agregar comentario de funcionalidad
    public AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_category, null);
        builder.setView(v);

        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        txtCategoryName = (EditText) v.findViewById(R.id.txtCategoryName);
        txtCategoryDescription = (EditText) v.findViewById(R.id.txtCategoryDescription);
        spColors = (Spinner) v.findViewById(R.id.spColors);

        ColorAdapter<Integer> spinnerArrayAdapter = new ColorAdapter<Integer>(getActivity(), MainActivity.getColors());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColors.setAdapter(spinnerArrayAdapter);
        spColors.setSelection(MainActivity.getColors().indexOf(0));

        if (MainActivity.appbacoDatabase != null) {
            TransactionCategory entity = null;
            try {
                entity = entityController.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
            }
            if (entity != null) {
                txtCategoryName.setText(entity.getName());
                txtCategoryDescription.setText(entity.getDescription());
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
                            return;
                        }


                        TransactionCategory entity = new TransactionCategory();
                        entity.setId(id);
                        entity.setName(txtCategoryName.getText().toString());
                        entity.setTransactionTypeId(transactionTypeId);
                        entity.setDescription(txtCategoryDescription.getText().toString());
                        entity.setColor(Integer.parseInt(spColors.getSelectedItem().toString()));

                        try {
                            if (entity.getId() == 0) {
                                entityController.create(entity);
                            } else {
                                entityController.update(entity);
                            }
                            actionListener.onSave(entity.getId());
                            dismiss();
                        } catch (Exception e) {
                            Snackbar snackbar = Snackbar.make(v, "Error: "+e.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null);
                            View sbView = snackbar.getView();
                            sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorBackgroundError));
                            snackbar.show();
                            e.printStackTrace();
                        }

                    }
                }
        );
        return builder.create();
    }
}