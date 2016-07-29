package com.survey.worklake.music;


import android.content.ComponentName;
import android.content.ContentResolver;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicListFragment extends Fragment implements View.OnClickListener  {
    ListView songView;
    MusicService musicSrv;

    boolean musicBound = false;
    TextView status;
    ProgressBar progressBar;
    Button startMedia;
    TextView stop;
    TextView start;
    Button stopMedia;
    Button shuffle;
    Button pre;
    Button forward;
    MediaPlayer mp;
    RelativeLayout toprelative;
    boolean expanded=false;
    View v;
ImageView searchicon;

    public MusicListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_music_list, container, false);

        getSongList();
        songView = (ListView) v.findViewById(R.id.song_list);

        SongAdapter songAdt = new SongAdapter(getActivity(), ((MainActivity)getActivity()).songList);
        songView.setAdapter(songAdt);

        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ((MainActivity)getActivity()).setSong(position);
                top();
                start.setText("\u23F8");

            }
        });
              progressBar = (ProgressBar) v.findViewById(R.id.seekBar);
        startMedia = (Button) v.findViewById(R.id.start);

        start = (TextView) v.findViewById(R.id.start1);

        pre=(Button)v.findViewById(R.id.pre1);
        forward=(Button)v.findViewById(R.id.next1);
        shuffle=(Button)v.findViewById(R.id.shuffle);
        toprelative=(RelativeLayout)v.findViewById(R.id.toprelative);
        startMedia.setOnClickListener(this);
searchicon=(ImageView)v.findViewById(R.id.imageView2) ;
        start.setOnClickListener(this);
        searchicon.setOnClickListener(this);

        shuffle.setOnClickListener(this);
        pre.setOnClickListener(this);
        forward.setOnClickListener(this);



        toprelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!expanded) {

                    toprelative.animate().translationYBy(-974).setDuration(1000);
                    start.setVisibility(View.INVISIBLE);

                    expanded = true;
                } else {
                    toprelative.animate().translationYBy(974).setDuration(1000);
                    start.setVisibility(View.VISIBLE);

                    expanded = false;

                }

            }
        });

        return v;
    }


    public void getSongList() {

        ContentResolver musicResolver = getActivity().getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, MediaStore.Audio.Media.TITLE);


        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                ((MainActivity)getActivity()).songList.add(new Song(thisId, thisTitle, thisArtist));
            }
            while (musicCursor.moveToNext());
        }

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                ((MainActivity) getActivity()).startPlayer();
                break;


            case R.id.start1:
                    if((((MainActivity) getActivity()).playing())){
                    start.setText("\u25B6");
                        ((MainActivity) getActivity()).pauseSong();

                    }
                else {
                        start.setText("\u23F8");
                        ((MainActivity) getActivity()).startPlayer();

                    }
                break;

            case R.id.pre1:
                ((MainActivity) getActivity()).previousSong();
                break;

            case R.id.next1:
                ((MainActivity) getActivity()).nextSong();
                break;
            case R.id.shuffle:
                ((MainActivity) getActivity()).shufflesong();
                break;
            case R.id.imageView2:
                setUpEditText();


        }}
    public void top(){
        TextView play=(TextView)v.findViewById(R.id.play);
        play.setText(((MainActivity)getActivity()).getSongList().get(((MainActivity)getActivity()).getSong()).getTitle());
    }

  /*  public void setUpEditText() {
        final EditText edittext1 = (EditText)v.findViewById(R.id.editText1);
        ViewGroup.LayoutParams searchTextParams = edittext1.getLayoutParams();
        if(searchTextParams.height > 0) {
            searchTextParams.height = 0;
            edittext1.setLayoutParams(searchTextParams);
            ((MainActivity)getActivity()).setSong()
        }
        else {
            searchTextParams.height = 130;
            edittext1.setLayoutParams(searchTextParams);
            edittext1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(edittext1.getText().length() > 3){
                        final List<Song> songs = new ArrayList<>();
                        final List<Integer> ids = new ArrayList<>();
                        for(int i = 0 ; i <((MainActivity)getActivity()).songList.size() ; i++) {
                            if(((MainActivity)getActivity()).songList.get(i).getTitle().toLowerCase().contains(edittext1.getText().toString().toLowerCase())) {
                                songs.add(((MainActivity)getActivity()).songList.get(i));
                                ids.add(i);
                            }
                        }

                        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ((MainActivity)getActivity()).setSong(ids.get(position));
                                setTop(ids.get(position));
                                seekUpdation();
                            }
                        });
                    }
                    else {
                        setSongList(view);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }*/

    public void setUpEditText() {
        final EditText searchText = (EditText) v.findViewById(R.id.editText1);
        ViewGroup.LayoutParams searchTextParams = searchText.getLayoutParams();
        if (searchTextParams.height > 0) {
            searchTextParams.height = 0;
            searchText.setLayoutParams(searchTextParams);
            setSongList(v);
        } else {
            searchTextParams.height = 130;
            searchText.setLayoutParams(searchTextParams);
            searchText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (searchText.getText().length() > 3) {
                        final List<Song> songs = new ArrayList<>();
                        final List<Integer> ids = new ArrayList<>();
                        for (int i = 0; i < ((MainActivity) getActivity()).getPlayList().size(); i++) {
                            if (((MainActivity) getActivity()).getPlayList().get(i).getTitle().toLowerCase().contains(searchText.getText().toString().toLowerCase())) {
                                songs.add(((MainActivity) getActivity()).getPlayList().get(i));
                                ids.add(i);
                            }
                        }
                        SongAdapter adapter = new SongAdapter(getContext(), (ArrayList<Song>) songs);
                        songView = (ListView) v.findViewById(R.id.song_list);
                        songView.setAdapter(adapter);
                        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ((MainActivity) getActivity()).setSong(ids.get(position));
                                //   setTop(ids.get(position));
                                //  seekUpdation();
                            }
                        });
                    } else {
                        SongAdapter songAdt = new SongAdapter(getActivity(), ((MainActivity)getActivity()).songList);
                        songView.setAdapter(songAdt);
                        setSongList(v);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
    public void setSongList(View view) {


        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ((MainActivity)getActivity()).setSong(position);
                top();
                start.setText("\u23F8");

            }
        });


    }
}
































  /* @Override
    public void onClick(View v) {
        if (v.equals(startMedia)) {
            if (mp != null && mp.isPlaying()) return;
            mp = MediaPlayer.create(Player.this, R.raw.exodus_piranha);
            mp.start();
            status.setText(R.string.PlayingMedia);
            progressBar.setVisibility(ProgressBar.VISIBLE);
            progressBar.setProgress(0);
            progressBar.setMax(mp.getDuration());
            new Thread(this).start();
        }

        if (v.equals(stop) && mp!=null) {
            mp.stop();
            mp = null;
            status.setText(R.string.Stopped);
            progressBar.setVisibility(ProgressBar.GONE);
        }
    }*/

    /*    public void run() {
        int currentPosition= 0;
        int total = mp.getDuration();

        while (mp!=null && currentPosition<total) {
            try {
                Thread.sleep(1000);
                currentPosition= mp.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            progressBar.setProgress(CurrentPosition);
        }
    }
}*/

