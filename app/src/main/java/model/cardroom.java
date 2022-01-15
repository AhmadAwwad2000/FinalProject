package model;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

public class cardroom extends RecyclerView.Adapter<cardroom.ViewHolder> {

    public static String[] roomdb;
    private int[] imageIds;


    public cardroom(String[] roomdb, int[] imageIds){
        this.roomdb = roomdb;
        this.imageIds = imageIds;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardroom,
                parent,
                false);

        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.image);
        Drawable dr = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(dr);
        TextView txt = (TextView)cardView.findViewById(R.id.txtName);
        if (roomdb[position]==null){
            txt.setText("err");
        }
        else{
            txt.setText(roomdb[position]);
        }
        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //
            }
        });

    }


    @Override
    public int getItemCount() {
        return roomdb.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }


    }


}
