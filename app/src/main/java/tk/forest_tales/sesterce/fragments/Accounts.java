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
import android.widget.Toast;

import java.util.List;

import tk.forest_tales.sesterce.ActivityAccountEditor;
import tk.forest_tales.sesterce.R;
import tk.forest_tales.sesterce.tables.Account;
import tk.forest_tales.sesterce.view.AccountListAdapter;
import tk.forest_tales.sesterce.view.AccountViewModel;

import static android.app.Activity.RESULT_OK;

public class Accounts extends Fragment {

    public static final int NEW_ACCOUNT = 1;
    public static final int EDIT_ACCOUNT = 2;

    private AccountViewModel mAccountViewModel;

    public static Accounts newInstance() {
        Accounts fragment = new Accounts();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);
        final Context context = this.getActivity();

        mAccountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);

        final AccountListAdapter adapter = new AccountListAdapter(this, mAccountViewModel);

        mAccountViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(@Nullable final List<Account> accounts) {
                adapter.setAccounts(accounts);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.account_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityAccountEditor.class);
                startActivityForResult(intent, NEW_ACCOUNT);
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if (requestCode == NEW_ACCOUNT) {
                Account account = new Account(
                        data.getStringExtra(ActivityAccountEditor.EXTRA_REPLY_NAME),
                        data.getStringExtra(ActivityAccountEditor.EXTRA_REPLY_CURRENCY),
                        data.getStringExtra(ActivityAccountEditor.EXTRA_REPLY_KIND)
                );
                mAccountViewModel.insert(account);
            }else if( requestCode == EDIT_ACCOUNT) {
                Account account = new Account(
                        data.getIntExtra(ActivityAccountEditor.EXTRA_REPLY_ID,-1),
                        data.getStringExtra(ActivityAccountEditor.EXTRA_REPLY_NAME),
                        data.getStringExtra(ActivityAccountEditor.EXTRA_REPLY_CURRENCY),
                        data.getStringExtra(ActivityAccountEditor.EXTRA_REPLY_KIND)
                );
                mAccountViewModel.update(account);
            }else{
                Toast.makeText(
                        this.getActivity().getApplicationContext(),
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
