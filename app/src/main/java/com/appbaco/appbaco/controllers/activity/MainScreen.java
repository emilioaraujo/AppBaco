package com.appbaco.appbaco.controllers.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbaco.appbaco.R;
import com.appbaco.appbaco.controllers.entity.TransactionHeaderController;
import com.appbaco.appbaco.controllers.fragment.DialogTransaction;
import com.appbaco.appbaco.models.entity.TransactionHeader;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainScreen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DialogTransaction createUpdateDialog;

    private TransactionHeader currentRecord;

    private final TransactionHeaderController entityController = new TransactionHeaderController(MainActivity.appbacoDatabase);
    private OnFragmentInteractionListener mListener;

    public MainScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static MainScreen newInstance(String param1, String param2) {
        MainScreen fragment = new MainScreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActivity().setTitle("Main Screen");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        final FloatingActionsMenu fabMenu = (FloatingActionsMenu) view.findViewById(R.id.fabAddTransactionMenu);

        FloatingActionButton fabActionIncome = (FloatingActionButton) view.findViewById(R.id.fabActionIncome);
        fabActionIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("fabActionIncome");
                currentRecord = null;
                showCreateUpdateDialog(currentRecord,2);
                fabMenu.collapse();
            }
        });
        FloatingActionButton fabActionExpense = (FloatingActionButton) view.findViewById(R.id.fabActionExpense);
        fabActionExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("fabActionExpense");
                currentRecord = null;
                showCreateUpdateDialog(currentRecord,3);
                fabMenu.collapse();
            }
        });
        FloatingActionButton fabActionTransfer= (FloatingActionButton) view.findViewById(R.id.fabActionTransfer);
        fabActionTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("fabActionTransfer");
                currentRecord = null;
                showCreateUpdateDialog(currentRecord,1);
                fabMenu.collapse();
            }
        });
        //Find the +1 button
        // mPlusOneButton = (PlusOneButton) view.findViewById(R.id.plus_one_button);
        MainActivity.toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void showCreateUpdateDialog(TransactionHeader entity, Integer transactionTypeId) {

        Bundle args = new Bundle();
        if (entity != null) {
            args.putInt("id", entity.getId());
            args.putInt("transaction_type_id", entity.getTransactionTypeId());
        } else {
            args.putInt("id", 0);
            args.putInt("transaction_type_id", transactionTypeId);
        }

        createUpdateDialog = new DialogTransaction();
        createUpdateDialog.setArguments(args);
        createUpdateDialog.show(getActivity().getSupportFragmentManager(), "Account");
        createUpdateDialog.setActionListener(new DialogTransaction.ActionListener() {

            @Override
            public void onSave(Integer id) {

                Snackbar snackbar = Snackbar.make(getView(), "Success: Record Saved!", Snackbar.LENGTH_LONG).setAction("Action", null);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorBackgrounSuccess));
                snackbar.show();
            }

            @Override
            public void onCancel() {
                // nada que hacer
            }
        });

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
