package valeriamoscoso.ioc.hanguldaebak.presentation.studyLetters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import valeriamoscoso.ioc.hanguldaebak.R;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;

public class LettersAdapter extends RecyclerView.Adapter<LettersViewHolder> {

    private List<Letter> letters;
    private LayoutInflater inflater;


    public LettersAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setData (List<Letter> letters){
        this.letters = letters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LettersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.letter_item, parent, false);

        return new LettersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LettersViewHolder holder, int position) {

        Letter letter = letters.get(position);
        holder.bindViewHolder(letter);

    }

    @Override
    public int getItemCount() {
        return letters.size();
    }



}
