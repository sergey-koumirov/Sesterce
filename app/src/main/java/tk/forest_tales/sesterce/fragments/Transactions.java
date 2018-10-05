package tk.forest_tales.sesterce.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tk.forest_tales.sesterce.ActivityNewTransaction;
import tk.forest_tales.sesterce.R;
import tk.forest_tales.sesterce.tables.Transaction;
import tk.forest_tales.sesterce.view.TransactionListAdapter;
import tk.forest_tales.sesterce.view.TransactionViewModel;

public class Transactions extends Fragment {

    public static final int NEW_TRANSACTION_ACTIVITY_REQUEST_CODE = 2;

    private TransactionViewModel mTransactionViewModel;

    public static Transactions newInstance() {
        Transactions fragment = new Transactions();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        final Context context = this.getActivity();

        final TransactionListAdapter adapter = new TransactionListAdapter(context);

        RecyclerView recyclerView = view.findViewById(R.id.transaction_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        mTransactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);

        mTransactionViewModel.getAllTransactions().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(@Nullable final List<Transaction> accounts) {
                // Update the cached copy of the words in the adapter.
                adapter.setTransactions(accounts);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityNewTransaction.class);
                startActivityForResult(intent, NEW_TRANSACTION_ACTIVITY_REQUEST_CODE);
            }
        });

        return view;
    }
}
