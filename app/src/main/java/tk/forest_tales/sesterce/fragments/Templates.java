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

import tk.forest_tales.sesterce.R;
import tk.forest_tales.sesterce.editors.TemplateEditor;
import tk.forest_tales.sesterce.tables.Template;
import tk.forest_tales.sesterce.view.TemplateListAdapter;
import tk.forest_tales.sesterce.view.TemplateViewModel;

import static android.app.Activity.RESULT_OK;

public class Templates extends Fragment {

    public static final int NEW_TEMPLATE = 3;
    public static final int EDIT_TEMPLATE = 4;

    private TemplateViewModel mTemplateViewModel;

    public static Templates newInstance() {
        Templates fragment = new Templates();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_templates, container, false);
        final Context context = this.getActivity();

        mTemplateViewModel = ViewModelProviders.of(this).get(TemplateViewModel.class);

        final TemplateListAdapter adapter = new TemplateListAdapter(this, mTemplateViewModel);

        mTemplateViewModel.getAllTemplates().observe(this, new Observer<List<Template>>() {
            @Override
            public void onChanged(@Nullable final List<Template> templates) {
                adapter.setTemplates(templates);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.template_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TemplateEditor.class);
                startActivityForResult(intent, NEW_TEMPLATE);
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if (requestCode == NEW_TEMPLATE) {
                Template template = new Template();
                mTemplateViewModel.insert(template);
            }else if( requestCode == EDIT_TEMPLATE) {
                Template template = new Template();
                mTemplateViewModel.update(template);
            }else{
                Toast.makeText(
                        this.getActivity().getApplicationContext(),
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
