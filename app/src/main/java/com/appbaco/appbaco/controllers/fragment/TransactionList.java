package com.appbaco.appbaco.controllers.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.appbaco.appbaco.R;
import com.appbaco.appbaco.controllers.activity.MainActivity;
import com.appbaco.appbaco.controllers.entity.AccountController;
import com.appbaco.appbaco.controllers.entity.TransactionHeaderController;
import com.appbaco.appbaco.models.activity.ListAccountAdapter;
import com.appbaco.appbaco.models.activity.ListTransactionAdapter;
import com.appbaco.appbaco.models.entity.Account;
import com.appbaco.appbaco.models.entity.TransactionDetail;
import com.appbaco.appbaco.models.entity.TransactionHeader;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransactionList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransactionList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView list;
    View view;

    DialogTransaction createUpdateDialog;

    private TransactionHeader currentHeader;
    private ArrayList<TransactionDetail> currentDetail;
    AlertDialog accountDialog;

    private ArrayList<String> stringArrayList;
    private final TransactionHeaderController entityController = new TransactionHeaderController(MainActivity.appbacoDatabase);

    TabLayout tabs;
    AppBarLayout appBarLayout;

    private OnFragmentInteractionListener mListener;

    public TransactionList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountList.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountList newInstance(String param1, String param2) {
        AccountList fragment = new AccountList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActivity().setTitle("Transaction List");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_transaction_list, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabAddTransaction);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentHeader = null;
                currentDetail = null;
                showCreateUpdateDialog(currentHeader);
            }
        });
        this.list = (ListView) view.findViewById(R.id.lvTransactions);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);
        this.configureList();

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

    //METODOS AGREGADOS
    // Metodo para configurar la lista de categorias
    public void configureList() {
        if (MainActivity.appbacoDatabase != null) {
            //Declaring arrays list to store the data
            final ArrayList<TransactionHeader> entities;
            try {
                entities = entityController.findAll();
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar snackbar = Snackbar.make(view, "Error: "+e.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorBackgroundError));
                snackbar.show();
                return;
            }
            if (entities != null) {

                ListTransactionAdapter adapter = new ListTransactionAdapter(getActivity(), this, R.layout.list_transaction_item, entities);

                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        currentHeader = entities.get(position);
                        showRecordDetail(entities.get(position));
                    }
                });

            }
        }
    }


    public void showRecordDetail(TransactionHeader entity) {
        // TODO: Pending to implementation
        Toast.makeText(getContext(), "Record Detail not Implemented", Toast.LENGTH_SHORT).show();
    }

    public void deleteRecord(final TransactionHeader entity) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Confirm")
                .setMessage("Do you really want delete de transaction ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        try {
                            entityController.delete(entity);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Snackbar snackbar = Snackbar.make(view,  "Error: " + e.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null);
                            View sbView = snackbar.getView();
                            sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorBackgroundError));
                            snackbar.show();
                        }
                        configureList();
                        Snackbar snackbar = Snackbar.make(view, "Success: Record Deleted!", Snackbar.LENGTH_LONG).setAction("Action", null);
                        View sbView = snackbar.getView();
                        sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorBackgrounSuccess));
                        snackbar.show();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void showCreateUpdateDialog(TransactionHeader entity) {
/*
        Bundle args = new Bundle();
        if (entity != null) {
            args.putInt("id", entity.getId());
        } else {
            args.putInt("id", 0);
        }

        createUpdateDialog = new DialogAccount();
        createUpdateDialog.setArguments(args);
        createUpdateDialog.show(getActivity().getSupportFragmentManager(), "Account");
        createUpdateDialog.setActionListener(new DialogAccount.ActionListener() {

            @Override
            public void onSave(Integer id) {
                configureList();
                Snackbar snackbar = Snackbar.make(view, "Success: Record Saved!", Snackbar.LENGTH_LONG).setAction("Action", null);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorBackgrounSuccess));
                snackbar.show();
            }

            @Override
            public void onCancel() {
                // nada que hacer
            }
        });
*/
    }

    //--
}
