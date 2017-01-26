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
import com.appbaco.appbaco.controllers.entity.TransactionCategoryController;
import com.appbaco.appbaco.models.activity.ListCategoryListAdapter;
import com.appbaco.appbaco.models.entity.TransactionCategory;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Integer transactionTypeId = 2;

    ListView list;
    View view;

    DialogCategory createUpdateDialog;

    private TransactionCategory currentRecord;
    AlertDialog transactionCategoryDialog;

    private ArrayList<String> stringArrayList;
    private final TransactionCategoryController entityController = new TransactionCategoryController(MainActivity.appbacoDatabase);

    TabLayout tabs;
    AppBarLayout appBarLayout;

    private CategoryList.OnFragmentInteractionListener mListener;
    public CategoryList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryList.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryList newInstance(String param1, String param2) {
        CategoryList fragment = new CategoryList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActivity().setTitle("Category List");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category_list, container, false);

        tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        transactionTypeId = tab.getPosition()+2;

                        if(tab.getPosition()==0) {
                            //toolbar color
                            //MainActivity.toolbar.setBackgroundColor(getResources().getColor(R.color.colorIncome));
                            //navbar color
                            //getActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.colorIncome));
                        }else{
                            //toolbar color
                            //MainActivity.toolbar.setBackgroundColor(getResources().getColor(R.color.colorExpense));
                            //navbar color
                            //getActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.colorExpense));
                        }
                        configureList();
                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        //Not implemented
                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        //Not implemented
                    }
                }
        );
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabAddCategory);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRecord = null;
                showCreateUpdateDialog(currentRecord);
            }
        });
        this.list = (ListView) view.findViewById(R.id.lvCategory);
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
            final ArrayList<TransactionCategory> entities;
            try {
                entities = entityController.findAll(transactionTypeId);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            if (entities != null) {
                ListCategoryListAdapter adapter = new ListCategoryListAdapter(getActivity(), this, R.layout.list_category_item, entities);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        currentRecord = entities.get(position);
                        showRecordDetail(entities.get(position));
                    }
                });

            }
        }
    }


    public void showRecordDetail(TransactionCategory entity) {
        // TODO: Pending to implementation
        Toast.makeText(getContext(), "Record Detail not Implemented", Toast.LENGTH_SHORT).show();
    }

    public void deleteRecord(final TransactionCategory entity) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Confirm")
                .setMessage("Do you really want delete: " + entity.getName() + " ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        try {
                            entityController.delete(entity);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        configureList();
                        Toast.makeText(getContext(), "Record Deleted!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void showCreateUpdateDialog(TransactionCategory entity) {
        Bundle args = new Bundle();
        if (entity != null) {
            args.putInt("id", entity.getId());
        } else {
            args.putInt("id", 0);
        }
        args.putInt("transaction_type_id", transactionTypeId);

        createUpdateDialog = new DialogCategory();
        createUpdateDialog.setArguments(args);
        createUpdateDialog.show(getActivity().getSupportFragmentManager(), "Category");
        createUpdateDialog.setActionListener(new DialogCategory.ActionListener() {

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
    }

    //--
}
