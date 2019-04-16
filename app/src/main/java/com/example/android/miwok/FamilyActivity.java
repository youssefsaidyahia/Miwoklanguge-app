package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    MediaPlayer m;
    AudioManager am;
    AudioManager.OnAudioFocusChangeListener al=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i==AudioManager.AUDIOFOCUS_GAIN){
                m.start();
            }
            else if(i==AudioManager.AUDIOFOCUS_LOSS){
                m.stop();
                releaseMediaPlayer();
            }
            else if(i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                m.pause();
                m.seekTo(0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        // Create a list of words
       final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother", "әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son", "angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter", "tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother", "taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa",R.drawable.family_grandfather,R.raw.family_grandfather));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_listyout file.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word word = words.get(position);
                int result=am.requestAudioFocus(al,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    m = MediaPlayer.create(FamilyActivity.this, word.getaudioResourceId());
                    m.start();
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer m) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });
        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
    }
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (m != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            m.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            m= null;
            am.abandonAudioFocus(al);
        }
    }
}