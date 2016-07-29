package com.survey.worklake.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

 public ArrayList<Song> songList;
    private MusicService musicSrv;
    private Intent playIntent;
    boolean musicBound=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songList=new ArrayList<>();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HomepageFragment())
                    .commit();


    }





    }

    public void replacehome(){
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.container)).commit();

            getSupportFragmentManager().beginTransaction().replace(R.id.container, new MusicListFragment()).commit();
        }




    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder)service;
            //get service
            musicSrv = binder.getService();
            //pass list
            musicSrv.setList(songList);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(MusicService.player != null) {
//            MusicService.player.release();
//            // Set the MediaPlayer to null to avoid IlLegalStateException
//            // when call mp.reset() after launching the app again
//            MusicService.player = null;
//        }
        unbindService(musicConnection);
        musicSrv.onUnbind(playIntent);
    }
    public void setSong(int position) {
        musicSrv.setSong(position);
        musicSrv.playSong();
    }
    public int getSong()
    {
       return  musicSrv.getIndex();
    }
    public void nextSong() {
        musicSrv.playNext();

    }
    public void previousSong() {
        musicSrv.playPrev();

    }
    public ArrayList<Song> getSongList(){
        return songList;
    }
    public void pauseSong() {

        musicSrv.pausePlayer();
    }
    public void shufflesong(){
        musicSrv.setShuffle();
    }
    public void startPlayer()
    {
        musicSrv.playSong();
    }
    public int getDuration() {        return musicSrv.getDur();
    }
    public boolean playing(){
       return musicSrv.isPng();
    }
    public ArrayList<Song> getPlayList(){
        return musicSrv.getSongs();
    }
}








