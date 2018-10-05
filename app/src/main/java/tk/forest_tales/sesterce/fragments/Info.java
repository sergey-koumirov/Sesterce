package tk.forest_tales.sesterce.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tk.forest_tales.sesterce.R;

public class Info extends Fragment {

    public static Info newInstance() {
        Info fragment = new Info();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #INFO");
        return view;
    }
}
