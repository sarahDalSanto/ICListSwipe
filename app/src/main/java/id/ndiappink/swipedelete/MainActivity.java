package id.ndiappink.swipedelete;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.view.Gravity.END;


public class MainActivity extends AppCompatActivity {
    private TextView tvEmptyTextView;
    private RecyclerView mRecyclerView;
    private ArrayList<Richiesta> mDataSet;

   JSONObject obj;
    JSONArray jsonArray;
    ArrayList<Richiesta> arrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvEmptyTextView = (TextView) findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       arrayList = new ArrayList<>();
        //loadData();

/*
        if (mDataSet.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            tvEmptyTextView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            tvEmptyTextView.setVisibility(View.GONE);
        }
*/

        String json = null;
        try {
            InputStream is = getAssets().open("giustificativi.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            obj = new JSONObject(json);
            jsonArray = obj.getJSONArray("giust");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonMarchi = jsonArray.getJSONObject(i);

                String [] hh_fine = jsonMarchi.getString("Durata").split("/");
                arrayList.add(new Richiesta(Integer.toString(jsonMarchi.getInt("Dipendente")), jsonMarchi.getString("Nome"), jsonMarchi.getString("Gginizio"),Integer.toString(jsonMarchi.getInt("hh_inizio")),
                        jsonMarchi.getString("Ggfine"), jsonMarchi.getString("Durata"), jsonMarchi.getString("Descrizione"), jsonMarchi.getString("Causale")));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final SwipeRecyclerViewAdapter mAdapter = new SwipeRecyclerViewAdapter(this, arrayList);

        ((SwipeRecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("RecyclerView", "onScrollStateChanged");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

/*
    public void loadData() {
        String testo = "Lorem ipsum dolor sit amet, consectetur adipiscing nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo";
        for (int i = 0; i <= 20; i++) {
            mDataSet.add(new Richiesta( i+"", "Matteo Marchi", "03 Giu 2018", "9:00", "03 Giu 2018", "18:00","FERIE", testo));
        }
    }
*/

}
