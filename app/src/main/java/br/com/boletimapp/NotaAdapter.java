package br.com.fasj.unibrasapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaViewHolder> {

    private Context Context;
    private ArrayList<Nota> NotaList;

    public NotaAdapter(Context context, ArrayList<Nota> notaList){
        Context = context;
        NotaList = notaList;
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(Context).inflate(R.layout.item, parent, false);
        return new NotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        Nota currentNota = NotaList.get(position);

        String disciplina = currentNota.getDisciplina();
        String turma = currentNota.getTurma();
        String a1 = currentNota.getA1();
        String a2 = currentNota.getA2();
        String sub = currentNota.getSub();
        String a3 = currentNota.getA3();
        int faltasA1 = currentNota.getFaltasA1();
        int faltasA2 = currentNota.getFaltasA2();

        holder.disciplinaView.setText("Disciplina: "+disciplina);
        holder.turmaView.setText("Turma: "+turma);
        holder.a1View.setText("Nota A1: "+a1);
        holder.a2View.setText("Nota A2: "+a2);
        holder.subView.setText("Substitutiva: "+sub);
        holder.a3View.setText("Exame Final: "+a3);
        holder.faltasA1View.setText("Faltas A1: "+faltasA1);
        holder.faltasA2View.setText("Faltas A2: "+faltasA2);
    }

    @Override
    public int getItemCount() {
        return NotaList.size();
    }

    public class NotaViewHolder extends RecyclerView.ViewHolder{

        public TextView disciplinaView;
        public TextView turmaView;
        public TextView a1View;
        public TextView a2View;
        public TextView subView;
        public TextView a3View;
        public TextView faltasA1View;
        public TextView faltasA2View;

        public NotaViewHolder(View itemView){
            super(itemView);
            disciplinaView = itemView.findViewById(R.id.txtDisciplina);
            turmaView = itemView.findViewById(R.id.txtTurma);
            a1View = itemView.findViewById(R.id.txtA1);
            a2View = itemView.findViewById(R.id.txtA2);
            subView = itemView.findViewById(R.id.txtsub);
            a3View = itemView.findViewById(R.id.txtA3);
            faltasA1View = itemView.findViewById(R.id.txtFaltasA1);
            faltasA2View = itemView.findViewById(R.id.txtFaltasA2);
        }
    }

}
