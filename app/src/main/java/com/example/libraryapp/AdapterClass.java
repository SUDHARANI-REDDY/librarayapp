package com.example.libraryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{
    ArrayList<Bookstodb> List;
    public AdapterClass(ArrayList<Bookstodb> List)
    {
        this.List=List;
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

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,author,quantity,edition;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.bookid);
            name=itemView.findViewById(R.id.bookname);
            author=itemView.findViewById(R.id.author);

            quantity=itemView.findViewById(R.id.bookquantity);
        }

    }


}
