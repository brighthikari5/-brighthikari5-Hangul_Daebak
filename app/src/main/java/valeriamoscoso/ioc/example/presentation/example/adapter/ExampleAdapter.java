package valeriamoscoso.ioc.example.presentation.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import valeriamoscoso.ioc.example.domain.entity.ExampleContainer;
import valeriamoscoso.ioc.hanguldaebak.R;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleViewHolder> {

    private LayoutInflater mInflater;
    private ExampleContainer exampleContainer;

    public void setData(ExampleContainer exampleContainer) {
        this.exampleContainer = exampleContainer;
        notifyDataSetChanged();
    }

    public ExampleAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.exampleContainer = new ExampleContainer();
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_example, parent, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        holder.bind(exampleContainer.getExamples().get(position));
    }

    @Override
    public int getItemCount() {
        return exampleContainer.getExamples().size();
    }
}
