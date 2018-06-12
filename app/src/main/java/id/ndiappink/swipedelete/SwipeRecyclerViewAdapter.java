package id.ndiappink.swipedelete;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;


public class SwipeRecyclerViewAdapter extends RecyclerSwipeAdapter<SwipeRecyclerViewAdapter.SimpleViewHolder> {

    private Context mContext;
    int rotazione = 0;

    private ArrayList<Richiesta> richiestaList;


    public SwipeRecyclerViewAdapter(Context context, ArrayList<Richiesta> objects) {
        this.mContext = context;
        this.richiestaList = objects;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_layout, parent, false);
        return new SimpleViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {

        if(position %2 == 1)
        {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }
        else
        {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));

        }
        final Richiesta item = richiestaList.get(position);


        viewHolder.tv_matricola.setText(item.getMatricola());
        viewHolder.tv_nomeEcognome.setText(item.getNomeEcognome());
        viewHolder.tv_giornoInizio.setText(item.getGiornoInizio());
        viewHolder.tv_oraInizio.setText(item.getOraInizio());
        viewHolder.tv_giornoFine.setText(item.getGiornoFine());
        viewHolder.tv_oraFine.setText(item.getOraFine());
        viewHolder.tv_testo.setText(item.getTesto());
        viewHolder.tv_tipo.setText(item.getTipo() );


        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Bottom, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper1));

        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wraper));



        viewHolder.btn_testo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rotazione == 0) {
                    viewHolder.tv_testo.setVisibility(View.VISIBLE);
                    viewHolder.btn_testo.animate().rotation(180).start();
                    rotazione++;
                }else{
                    viewHolder.tv_testo.setVisibility(View.GONE);
                    viewHolder.btn_testo.animate().rotation(360).start();
                    rotazione--;
                }
            }

        });


        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {



            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });


        viewHolder.Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                richiestaList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, richiestaList.size());
                mItemManger.closeAllItems();
            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return richiestaList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public SwipeLayout swipeLayout;
        public TextView tv_matricola;
        public TextView tv_nomeEcognome;
        public TextView tv_giornoInizio;
        public TextView tv_oraInizio;
        public TextView tv_giornoFine;
        public TextView tv_oraFine;
        public ImageButton btn_testo;
        public TextView tv_testo;
        public TextView tv_tipo;


        public ImageView Delete;
        public ImageView Share;
        public SimpleViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            tv_matricola = (TextView) itemView.findViewById(R.id.tv_matricola);
            tv_nomeEcognome = (TextView) itemView.findViewById(R.id.tv_nomeCognome);
            tv_giornoInizio = (TextView) itemView.findViewById(R.id.tv_giornoInizio);
            tv_oraInizio = (TextView) itemView.findViewById(R.id.tv_oraInizio);
            tv_giornoFine = (TextView) itemView.findViewById(R.id.tv_giornoFine);
            tv_oraFine= (TextView) itemView.findViewById(R.id.tv_oraFine);
            btn_testo = (ImageButton) itemView.findViewById(R.id.btn_testo);
            tv_testo = (TextView) itemView.findViewById(R.id.tv_testo);
            tv_tipo = (TextView) itemView.findViewById(R.id.tv_tipoRichiesta);

            Delete = (ImageView) itemView.findViewById(R.id.Delete);

            Share = (ImageView) itemView.findViewById(R.id.Share);
        }
    }


}
