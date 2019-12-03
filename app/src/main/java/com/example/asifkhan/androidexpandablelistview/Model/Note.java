package com.example.asifkhan.androidexpandablelistview.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.support.constraint.solver.LinearSystem;

import java.io.Serializable;


@Entity(tableName = "note")
public class Note implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int NoteID;
    @ColumnInfo(name = "Title")
    private String Title;
    @ColumnInfo(name = "NoteItem")
    private String NoteItem;
    @ColumnInfo(name = "IsDone")
    private int IsDone;

    //Get
    public int getNoteID() { return NoteID; }
    public String getTitle() { return Title; }
    public String getNoteItem() { return NoteItem; }
    public int getIsDone() { return IsDone; }

    //Set
    public void setNoteID(int noteID) { NoteID = noteID; }
    public void setTitle(String title) { Title = title; }
    public void setNoteItem(String noteItem) { NoteItem = noteItem; }
    public void setIsDone(int isDone) { IsDone = isDone; }
}
