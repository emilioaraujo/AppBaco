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
import com.appbaco.appbaco.models.entity.Account;
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
    private EditText txtAccountName;
    private EditText txtAccountDescription;
    private Spinner spColors;
    private Button btnCancel;
    private Button btnSave;
    private Integer id;


    private EditText txtInitialBalance;
    private EditText txtAmountLimit;
    private TextView lblPayDay;
    private TextView lblExpireEnd;
    private TextInputLayout tilAmountLimit;
    private Spinner spAccountType;
    private Spinner spPayDay;
    private Spinner spYearExpire;
    private Spinner spMonthExpire;
    private Spinner spAccountCurrency;

    private String[] days = new String[]{"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
            "24", "25", "26", "27", "28", "29", "30"};

    private String[] months = new String[]{"Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    private ArrayList<String> currencyCodes = new ArrayList<>();
    private ArrayList<String> currencyCountry = new ArrayList<>();

    private final AccountController entityController = new AccountController(MainActivity.appbacoDatabase);

    public DialogTransaction() {

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

        //labels
        this.lblPayDay = (TextView) v.findViewById(R.id.lblPayDay);
        this.lblExpireEnd = (TextView) v.findViewById(R.id.lblExpireEnd);

        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        txtAccountName = (EditText) v.findViewById(R.id.txtAccountName);
        txtAccountDescription = (EditText) v.findViewById(R.id.txtAccountDescription);
        spColors = (Spinner) v.findViewById(R.id.spColors);

        this.txtInitialBalance = (EditText) v.findViewById(R.id.txtInitialBalance);
        this.txtAmountLimit = (EditText) v.findViewById(R.id.txtAmountLimit);
        this.spAccountType = (Spinner) v.findViewById(R.id.spAccountType);
        this.spPayDay = (Spinner) v.findViewById(R.id.spPayDay);
        this.spMonthExpire = (Spinner) v.findViewById(R.id.spMonthExpire);
        this.spYearExpire = (Spinner) v.findViewById(R.id.spYearExpire);


        this.showOrHideControls();

        ColorAdapter<Integer> spinnerArrayAdapter = new ColorAdapter<Integer>(getActivity(), MainActivity.getColors());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColors.setAdapter(spinnerArrayAdapter);
        spColors.setSelection(MainActivity.getColors().indexOf(0));

        ArrayAdapter<CharSequence> adapterAccountType = ArrayAdapter.createFromResource(getActivity(), R.array.account_type, android.R.layout.simple_spinner_item);
        adapterAccountType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAccountType.setAdapter(adapterAccountType);

        ArrayAdapter<String> spinnerPayDayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, days);
        spinnerPayDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spPayDay.setAdapter(spinnerPayDayAdapter);

        //month spinner
        ArrayAdapter<String> spinnerMonthAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, months);
        spinnerMonthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spMonthExpire.setAdapter(spinnerMonthAdapter);
        //--

        //year spinner
        Calendar calendar = Calendar.getInstance();
        Integer currentYear = calendar.get(Calendar.YEAR);
        String[] years = new String[20];

        years[0] = "Year";
        for (int i = 1; i < 20; i++) {
            years[i] = currentYear.toString();
            currentYear = currentYear + 1;
        }

        ArrayAdapter<String> spinnerYearAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, years);
        spinnerYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spYearExpire.setAdapter(spinnerYearAdapter);
        //--
        //--


        //listeners
        txtInitialBalance.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    CustomCalculator calc = new CustomCalculator();
                    calc.show(getActivity().getSupportFragmentManager(), "Calculator");
                    calc.setOnCalculatorResultListener(new CustomCalculator.OnCalculatorResultListener() {
                        @Override
                        public void onResult(Double result) {
                            txtInitialBalance.setText(result.toString());
                        }
                    });
                }
            }
        });
        //listeners
        txtAmountLimit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    CustomCalculator calc = new CustomCalculator();
                    calc.show(getActivity().getSupportFragmentManager(), "Calculator");
                    calc.setOnCalculatorResultListener(new CustomCalculator.OnCalculatorResultListener() {
                        @Override
                        public void onResult(Double result) {
                            txtAmountLimit.setText(result.toString());
                        }
                    });
                }
            }
        });
        spAccountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                showOrHideControls();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

                showOrHideControls();
            }
        });
        //--

        if (MainActivity.appbacoDatabase != null) {
            Account entity = null;
            try {
                entity = entityController.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
            }
            if (entity != null) {
                if (MainActivity.getColors().indexOf(entity.getColor()) > 0) {
                    spColors.setSelection(MainActivity.getColors().indexOf(entity.getColor()));
                }

                spAccountType.setSelection(entity.getAccountTypeId());
                txtAccountName.setText(entity.getName());
                txtAccountDescription.setText(entity.getDescription());
                txtInitialBalance.setText(entity.getInitialBalance().toString());
                spPayDay.setSelection(entity.getDayPay());
                spMonthExpire.setSelection(entity.getExpireMonth());

                for (int i = 1; i < 20; i++) {
                    if (Integer.parseInt(years[i]) == entity.getExpireYear()) {
                        spYearExpire.setSelection(i);
                        break;
                    }
                    //txtAmountLimit.setText(entity.getAmountLimit().toString());
                }
                //txtAmountLimit.setText(entity.getAmountLimit().toString());

                showOrHideControls();

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
                        //if (!validate(v)) {
                        //    return;
                        //}

                        Account entity = new Account();
                        entity.setId(id);
                        entity.setColor(Integer.parseInt(spColors.getSelectedItem().toString()));
                        entity.setAccountTypeId(spAccountType.getSelectedItemPosition());
                        entity.setName(txtAccountName.getText().toString());
                        entity.setDescription(txtAccountDescription.getText().toString());
                        entity.setInitialBalance(Double.parseDouble(txtInitialBalance.getText().toString()));
                        if(entity.getAccountTypeId()==3 || entity.getAccountTypeId()==4) {
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

                    }
                }
        );
        return builder.create();
    }

    private void showOrHideControls() {

        if (this.spAccountType.getSelectedItemPosition() == 3 || this.spAccountType.getSelectedItemPosition() == 4) {
            this.lblPayDay.setVisibility(View.VISIBLE);
            this.spPayDay.setVisibility(View.VISIBLE);
            this.lblExpireEnd.setVisibility(View.VISIBLE);
            this.spMonthExpire.setVisibility(View.VISIBLE);
            this.spYearExpire.setVisibility(View.VISIBLE);
            this.txtAmountLimit.setVisibility(View.VISIBLE);

        } else {
            this.lblPayDay.setVisibility(View.GONE);
            this.spPayDay.setVisibility(View.GONE);
            this.lblExpireEnd.setVisibility(View.GONE);
            this.spMonthExpire.setVisibility(View.GONE);
            this.spYearExpire.setVisibility(View.GONE);
            this.txtAmountLimit.setVisibility(View.GONE);

        }

    }

    private boolean validate() {
        // TODO: Organizar de acuerdo al orden en el form
        if (this.spAccountType.getSelectedItemPosition() == 0) {
            //TextView errorText = (TextView) this.spAccountType.getSelectedView();
            //errorText.setError("");
            //errorText.setTextColor(Color.RED);
            //errorText.setText("Mandatory");
            Snackbar.make(null, "Welcome to AndroidHive", Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (this.txtAccountName.getText().toString().isEmpty()) {
            this.txtAccountName.setError("Mandatory");
            return false;
        }

        if (this.spAccountType.getSelectedItemPosition() == 3 || this.spAccountType.getSelectedItemPosition() == 4) {
            if (this.spPayDay.getSelectedItemPosition() == 0) {
                TextView errorText = (TextView) this.spPayDay.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);
                errorText.setText("Mandatory");
                return false;
            }
            if (this.spMonthExpire.getSelectedItemPosition() == 0) {
                TextView errorText = (TextView) this.spMonthExpire.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);
                errorText.setText("Mandatory");
                return false;
            }
            if (this.spYearExpire.getSelectedItemPosition() == 0) {
                TextView errorText = (TextView) this.spYearExpire.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);
                errorText.setText("Mandatory");
                return false;
            }
        }
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