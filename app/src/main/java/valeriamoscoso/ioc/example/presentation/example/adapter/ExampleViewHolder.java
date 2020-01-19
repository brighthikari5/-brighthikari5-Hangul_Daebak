package valeriamoscoso.ioc.example.presentation.example.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import valeriamoscoso.ioc.example.domain.entity.Example;
import valeriamoscoso.ioc.hanguldaebak.R;

//se encarga de pintar los datos de un item, dado por el adaptador
class ExampleViewHolder extends RecyclerView.ViewHolder {

    private final TextView exampleTextViewName;
    private final TextView exampleTextViewSurname;
    private final TextView exampleTextViewNickname;

    ExampleViewHolder(@NonNull View itemView) {
        super(itemView);
        exampleTextViewName = itemView.findViewById(R.id.exampleTextViewName);
        exampleTextViewSurname = itemView.findViewById(R.id.exampleTextViewSurName);
        exampleTextViewNickname = itemView.findViewById(R.id.exampleTextViewNickName);
    }

    void bind(Example example) {
        exampleTextViewName.setText(example.getName());
        exampleTextViewSurname.setText(example.getSurName());
        exampleTextViewNickname.setText(example.getNickName());
    }
}
