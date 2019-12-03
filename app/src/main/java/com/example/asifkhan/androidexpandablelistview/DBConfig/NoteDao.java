package com.example.asifkhan.androidexpandablelistview.DBConfig;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asifkhan.androidexpandablelistview.Model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    long  AddNote(Note note);

    @Update
    int UpdateNote(Note... objNote);

    @Query("UPDATE note SET IsDone = 1 WHERE NoteItem = :NoteItem")
    int UpdateDoneStatus(String NoteItem);

    @Query("Select * from note order by NoteID DESC")
    List<Note> GetAllNotes();


    @Query("Select DISTINCT Title from note ")
    List<String> GetAllNoteTitles();

    @Query("Select * from note WHERE Title= :Title ")
    List<Note> GetAllByTitle(String Title);

    @Query("Select * from note where IsDone=1")
    List<Note> isDoneList ();







}
