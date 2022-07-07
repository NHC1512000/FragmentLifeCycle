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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentB extends Fragment {

    private String name =" Fragment B";
    private MediaPlayer mediaPlayer;
    private Button startFragmentA;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayer = MediaPlayer.create(getContext(),R.raw.nguoiemcodo);
        mediaPlayer.start();

        Log.d("Fragment A","On Create");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("Fragment B","Start on view create");

        startFragmentA = view.findViewById(R.id.btB);
        startFragmentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getParentFragmentManager();
                manager.popBackStack();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment B","On start");
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment B","On resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment B","On pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment B","On stop");
        mediaPlayer.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment B","On destroy view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment B","On destroy");
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
