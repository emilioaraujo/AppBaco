package com.appbaco.appbaco.controllers.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.appbaco.appbaco.R;
import com.appbaco.appbaco.controllers.activity.CustomCalculator;
import com.appbaco.appbaco.controllers.activity.MainActivity;
import com.appbaco.appbaco.controllers.entity.AccountController;
import com.appbaco.appbaco.controllers.entity.TransactionHeaderController;
import com.appbaco.appbaco.models.entity.Account;
import com.appbaco.appbaco.models.entity.TransactionHeader;
import com.appbaco.appbaco.utilities.ColorAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by MARAUJO on 12/25/2016.
 */

public class DialogTransaction extends DialogFragment implements
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {
    private ActionListener actionListener;
    private Spinner spColors;
    private Button btnCancel;
    private Button btnSave;
    private Integer id;
    private Integer transactionTypeId;
    private ArrayList<Account> fromAccountList;
    private ArrayList<Account> toAccountList;
    private TextView txtTransactionAmount;
    private TextView lblTransactionType;
    private Spinner spFromAccount;
    private Spinner spToAccount;
    private final TransactionHeaderController entityController = new TransactionHeaderController(MainActivity.appbacoDatabase);
    private AccountController accountController;

    public DialogTransaction() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = 0;
        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
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

        return true;
    }


    // TODO: agregar comentario de funcionalidad
    public AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_transaction, null);
        builder.setView(v);


        accountController= new AccountController(MainActivity.appbacoDatabase);
        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        txtTransactionAmount = (TextView) v.findViewById(R.id.txtTransactionAmount);
        lblTransactionType = (TextView) v.findViewById(R.id.lblTransactionType);
        spFromAccount = (Spinner) v.findViewById(R.id.spFromAccount);

        if (transactionTypeId == 1) {
            lblTransactionType.setText("Account Transfer");
        }
        if (transactionTypeId == 2) {
            lblTransactionType.setText("Income Transaction");
        }
        if (transactionTypeId == 3) {
            lblTransactionType.setText("Expense Transaction");
        }
        if(transactionTypeId==2 || transactionTypeId==3){
            try {
                fromAccountList = accountController.findByAccountType(transactionTypeId);
            }catch(Exception ex){
                Snackbar snackbar = Snackbar.make(getView(), "Error: "+ex.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorBackgroundError));
                snackbar.show();
            }
        }

        //ArrayAdapter<Account> adapterAccounts = ArrayAdapter.createFromResource(getActivity(), R.array.account_type, android.R.layout.simple_spinner_item);
        //adapterAccounts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spFromAccount.setAdapter(adapterAccounts);


        //--


        //listeners
        txtTransactionAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    CustomCalculator calc = new CustomCalculator();
                    calc.show(getActivity().getSupportFragmentManager(), "Calculator");
                    calc.setOnCalculatorResultListener(new CustomCalculator.OnCalculatorResultListener() {
                        @Override
                        public void onResult(Double result) {
                            txtTransactionAmount.setText(result.toString());
                        }
                    });
                }
            }
        });

        //--

        if (MainActivity.appbacoDatabase != null) {
            TransactionHeader entity = null;
            try {
                entity = entityController.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
            }
            if (entity != null) {
                //TODO cargar controles
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
                        //TODO: Saving
/*
                        TransactionHeader entity = new TransactionHeader();
                        entity.setId(id);
                        entity.setColor(Integer.parseInt(spColors.getSelectedItem().toString()));
                        entity.setAccountTypeId(spAccountType.getSelectedItemPosition());
                        entity.setName(txtAccountName.getText().toString());
                        entity.setDescription(txtAccountDescription.getText().toString());
                        entity.setInitialBalance(Double.parseDouble(txtInitialBalance.getText().toString()));
                        if (entity.getAccountTypeId() == 3 || entity.getAccountTypeId() == 4) {
                            entity.setDayPay(Integer.parseInt(spPayDay.getSelectedItem().toString()));
                            entity.setExpireMonth(Integer.parseInt(spMonthExpire.getSelectedItem().toString()));
                            entity.setExpireYear(Integer.parseInt(spYearExpire.getSelectedItem().toString()));
                            entity.setInitialBalance(Double.parseDouble(txtAmountLimit.getText().toString()));
                        }
                        try {
                            if (entity.getId() == 0) {
                                entityController.create(entity);
                            } else {
                                entityController.update(entity);
                            }
                            actionListener.onSave(entity.getId());
                            dismiss();

                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
*/
                    }
                }
        );
        return builder.create();
    }


    private boolean validate() {
        // TODO: Organizar de acuerdo al orden en el form

        return true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(getActivity(), "Fecha: " + year + "-" + month + "-" + dayOfMonth, Toast.LENGTH_LONG).show();
    }

    //@Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }

    private void getDatePicker() {
        // Obtener fecha actual
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Retornar en nueva instancia del dialogo selector de fecha
        DatePickerDialog test = new DatePickerDialog(getActivity(), this, year, month, day);
        test.show();
    }

}