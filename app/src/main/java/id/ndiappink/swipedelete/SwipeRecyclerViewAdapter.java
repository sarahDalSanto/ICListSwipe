package id.ndiappink.swipedelete;

import android.annotation.TargetApi;
import android.content.Context;
import android.drm.DrmStore;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.transition.TransitionValues;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.END;
import static android.view.Gravity.HORIZONTAL_GRAVITY_MASK;
import static android.view.Gravity.RIGHT;
import static android.view.Gravity.getAbsoluteGravity;
import static android.view.Gravity.isHorizontal;


public class SwipeRecyclerViewAdapter extends RecyclerSwipeAdapter<SwipeRecyclerViewAdapter.SimpleViewHolder> {

    private Context mContext;
    int rotazione = 0;
    View v;
    private ArrayList<Richiesta> richiestaList;



    public SwipeRecyclerViewAdapter(Context context, ArrayList<Richiesta> objects) {
        this.mContext = context;
        this.richiestaList = objects;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_layout, parent, false);
        v = view;
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

        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params1.gravity = Gravity.CENTER;
        viewHolder.ll.setLayoutParams(params1);
        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {



            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                params2.gravity = Gravity.START;

                viewHolder.ll.setGravity(END);
                viewHolder.ll.setPadding(0,0,15,0);
                viewHolder.ll.setLayoutParams(params2);

                viewHolder.btn_testo.setEnabled(false);
                viewHolder.btn_testo.setVisibility(View.INVISIBLE);
                viewHolder.tv_testo.setVisibility(View.GONE);
                viewHolder.btn_testo.animate().rotation(360).start();
                rotazione =0;

                Animation animation1 = AnimationUtils.loadAnimation(mContext, R.anim.right_to_left);
                    viewHolder.ll.startAnimation(animation1);

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }


            @Override
            public void onClose(SwipeLayout layout) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                viewHolder.ll.setLayoutParams(params);
                viewHolder.ll.setGravity(CENTER);

                viewHolder.btn_testo.setVisibility(View.VISIBLE);
                viewHolder.btn_testo.setEnabled(true);

                Animation animation1 = AnimationUtils.loadAnimation(mContext, R.anim.left_to_right);
                viewHolder.ll.startAnimation(animation1);
                /*
                //animazione
                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
                fadeIn.setDuration(500);

                AnimationSet animation = new AnimationSet(false); //change to false
                animation.addAnimation(fadeIn);

                viewHolder.ll.setAnimation(animation);
                */
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
        public LinearLayout ll;
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

            ll =(LinearLayout) itemView.findViewById(R.id.info_layout);
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
