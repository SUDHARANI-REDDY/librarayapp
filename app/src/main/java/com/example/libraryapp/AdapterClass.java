package com.example.libraryapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{
    ArrayList<Bookstodb> List;
    Context c;
    public AdapterClass(Context c, ArrayList<Bookstodb> List)
    {
        this.List=List;
        this.c= c;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.id.setText("Book Id: "+List.get(i).getBookid());
        myViewHolder.name.setText(List.get(i).getBook_name());
        myViewHolder.author.setText("Book Author: "+List.get(i).getBook_author());
        myViewHolder.quantity.setText("Book Quantity: "+List.get(i).getBook_quantity());
        myViewHolder.edition.setText("Book Editition: "+List.get(i).getBook_edition());

        myViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int pos) {
                String bookn=List.get(pos).getBook_name();
                String booki=List.get(pos).getBookid();
                String booka=List.get(pos).getBook_author();
                String bookq=List.get(pos).getBook_quantity();
                String booke=List.get(pos).getBook_edition();

                Intent intent=new Intent(c,borrowbookactivity.class);
                intent.putExtra("book name",bookn);
                intent.putExtra("book id",booki);
                intent.putExtra("book author",booka);
                intent.putExtra("book edition",booke);

                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView id,name,author,quantity,edition;
        ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.bookid);
            name=itemView.findViewById(R.id.bookname);
            author=itemView.findViewById(R.id.author);
            quantity=itemView.findViewById(R.id.bookquantity);
            edition=itemView.findViewById(R.id.edition);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClickListener(view,getLayoutPosition());

        }
        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener=ic;
        }
    }


}
