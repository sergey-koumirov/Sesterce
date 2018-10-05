package tk.forest_tales.sesterce.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tk.forest_tales.sesterce.ActivityAccountEditor;
import tk.forest_tales.sesterce.R;
import tk.forest_tales.sesterce.tables.Account;

import static tk.forest_tales.sesterce.ActivityAccountEditor.EXTRA_REPLY_ID;
import static tk.forest_tales.sesterce.ActivityAccountEditor.EXTRA_REPLY_NAME;
import static tk.forest_tales.sesterce.ActivityAccountEditor.EXTRA_REPLY_CURRENCY;
import static tk.forest_tales.sesterce.ActivityAccountEditor.EXTRA_REPLY_KIND;
import static tk.forest_tales.sesterce.fragments.Accounts.EDIT_ACCOUNT_ACTIVITY_REQUEST_CODE;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountViewHolder>{

    class AccountViewHolder extends RecyclerView.ViewHolder {
        private final TextView accountKindView;
        private final TextView accountCurrencyView;
        private final TextView accountNameView;

        private AccountViewHolder(View itemView) {
            super(itemView);
            accountKindView = itemView.findViewById(R.id.kind);
            accountCurrencyView = itemView.findViewById(R.id.currency);
            accountNameView = itemView.findViewById(R.id.name);

            accountNameView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int p=getLayoutPosition();
                    final Account current = mAccounts.get(p);

                    new AlertDialog.Builder(mContext)
                            .setTitle("Delete Account")
                            .setMessage("Sure?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    mAccountViewModel.delete(current);
                                }})
                            .setNegativeButton(android.R.string.no, null).show();

                    return true;
                }
            });

            accountNameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int p=getLayoutPosition();
                    final Account current = mAccounts.get(p);

                    Intent intent = new Intent(mContext, ActivityAccountEditor.class);
                    intent.putExtra(EXTRA_REPLY_ID, current.getId());
                    intent.putExtra(EXTRA_REPLY_NAME, current.getName());
                    intent.putExtra(EXTRA_REPLY_CURRENCY, current.getCurrency());
                    intent.putExtra(EXTRA_REPLY_KIND, current.getKind());
                    mFragment.startActivityForResult(intent, EDIT_ACCOUNT_ACTIVITY_REQUEST_CODE);
                }
            });

        }
    }

    private final LayoutInflater mInflater;
    private List<Account> mAccounts;
    private Context mContext;
    private Fragment mFragment;
    private AccountViewModel mAccountViewModel;

    public AccountListAdapter(Fragment fragment, AccountViewModel accountViewModel) {
        mFragment = fragment;
        mContext = fragment.getActivity();
        mInflater = LayoutInflater.from(mContext);
        mAccountViewModel = accountViewModel;
    }


    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.account_item, parent, false);
        return new AccountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        if (mAccounts != null) {
            Account current = mAccounts.get(position);

            holder.accountKindView.setText(current.getKind());

            holder.accountCurrencyView.setText(current.getCurrency());
            holder.accountCurrencyView.setTextColor( currencyToColor(current.getCurrency()) );

            holder.accountNameView.setText(current.getName());
        } else {
            holder.accountKindView.setText("N/A");
            holder.accountCurrencyView.setText("N/A");
            holder.accountNameView.setText("N/A");
        }
    }

    public void setAccounts(List<Account> accounts){
        mAccounts = accounts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAccounts != null){
            return mAccounts.size();
        }else {
            return 0;
        }
    }

    private int currencyToColor(String currency){
        switch(currency){
            case "RUR": return ContextCompat.getColor(mContext, R.color.currencyRUR);
            case "USD": return ContextCompat.getColor(mContext, R.color.currencyUSD);
            case "EUR": return ContextCompat.getColor(mContext, R.color.currencyEUR);
        }
        return Color.GRAY;
    }
}
