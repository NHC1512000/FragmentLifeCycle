package com.example.fragmentlifecycle2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentA extends Fragment {

    private String name =" Fragment A";
    private MediaPlayer mediaPlayer;
    private Button startFragmentB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayer = MediaPlayer.create(getContext(),R.raw.duongtoi);
        mediaPlayer.start();

        Log.d("Fragment A","On Create");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("Fragment A","Start on view create");

        startFragmentB = view.findViewById(R.id.btA);
        startFragmentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.remove(getParentFragmentManager().findFragmentByTag("fragA"));
                    transaction.add(R.id.frame,new FragmentB(),"fragB");
                    transaction.addToBackStack("fragB");
                    transaction.commit();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment A","On start");
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment A","On resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment A","On pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment A","On stop");
        mediaPlayer.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment A","On destroy view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment A","On destroy");
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
