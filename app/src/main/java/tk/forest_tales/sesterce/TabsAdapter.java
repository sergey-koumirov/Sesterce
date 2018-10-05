package tk.forest_tales.sesterce;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tk.forest_tales.sesterce.fragments.Accounts;
import tk.forest_tales.sesterce.fragments.Info;
import tk.forest_tales.sesterce.fragments.Transactions;

public class TabsAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Info", "Transactions", "Accounts" };
    private Context context;

    public TabsAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 1: return Transactions.newInstance();
            case 2: return Accounts.newInstance();
        }
        return Info.newInstance();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
