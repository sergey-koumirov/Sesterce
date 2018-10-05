package tk.forest_tales.sesterce.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tk.forest_tales.sesterce.R;
import tk.forest_tales.sesterce.tables.Template;

public class TemplateListAdapter extends RecyclerView.Adapter<TemplateListAdapter.TemplateViewHolder> {

    class TemplateViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private TemplateViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Template> mTemplates;
    private Context mContext;
    private Fragment mFragment;

    public TemplateListAdapter(Fragment fragment, TemplateViewModel templateViewModel) {
        mFragment = fragment;
        mContext = fragment.getActivity();
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public TemplateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.template_item, parent, false);
        return new TemplateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TemplateViewHolder holder, int position) {
        if (mTemplates != null) {
            Template current = mTemplates.get(position);
            holder.wordItemView.setText(current.getId());
        } else {
            holder.wordItemView.setText("N/A");
        }
    }

    public void setTemplates(List<Template> words){
        mTemplates = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTemplates != null) {
            return mTemplates.size();
        }else{
            return 0;
        }
    }
}
