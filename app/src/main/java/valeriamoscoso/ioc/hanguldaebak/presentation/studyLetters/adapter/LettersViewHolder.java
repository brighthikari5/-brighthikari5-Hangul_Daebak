package valeriamoscoso.ioc.hanguldaebak.presentation.studyLetters.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import valeriamoscoso.ioc.hanguldaebak.R;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;

public class LettersViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewTransInfo;
    private final TextView textViewDescriptionInfo;
    private final TextView textViewLetter;


    public LettersViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTransInfo = itemView.findViewById(R.id.textViewTransInfo);
        textViewDescriptionInfo = itemView.findViewById(R.id.textViewDescriptionInfo);
        textViewLetter = itemView.findViewById(R.id.textViewLetterhangul);
    }


    public void bindViewHolder(Letter letter) {
        textViewTransInfo.setText(letter.getTranslation());
        textViewDescriptionInfo.setText(letter.getDescription());
        textViewLetter.setText(letter.getLetter());
    }


}