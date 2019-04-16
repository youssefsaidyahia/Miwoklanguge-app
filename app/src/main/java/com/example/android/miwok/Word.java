package com.example.android.miwok;

/**
 * Created by YOUSSEF on 24/06/2018.
 */

public class Word {
    private   String  eng_word;
    private   String miw_word ;
    private int R_drawa_num_1=NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int R_audio_num_1;
    public Word(){
        eng_word="";
        miw_word="";
        R_drawa_num_1=0;
    }
    public Word(String s1 , String s2 , int au1) {
        eng_word = s1;
        miw_word = s2;
        R_audio_num_1=au1;
    }
    public Word(String s1 , String s2 , int i1 , int au1){
        eng_word=s1;
        miw_word=s2;
        R_drawa_num_1=i1;
        R_audio_num_1=au1;
    }
    public String getTranslation(){
        return eng_word;
    }
    public String getmiw_translation(){
        return miw_word;
    }
    public int getImageResourceId(){return R_drawa_num_1;}
    public int getaudioResourceId(){return R_audio_num_1;}
    public boolean hasImage() {
        return R_drawa_num_1 != NO_IMAGE_PROVIDED;
         }
}
