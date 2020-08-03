package com.example.projekakhir.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.JENIS;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.TABLE_NAME;


public class MoviesHelper {
    private static String DATABASE_TABLE = TABLE_NAME;
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public MoviesHelper(Context context){
       this.context = context;
    }

    public void open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public long insertProvider(ContentValues contentValues){
        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public void close(){
        databaseHelper.close();
    }


    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null,_ID+"=?",new String[]{id},null,null,null,null);
    }
    public Cursor queryByNameProvider(String name){
        return database.query(DATABASE_TABLE,null,JENIS+"=?",new String[]{name},null,null,null);
    }

    public int updateProvider(String id, ContentValues contentValues){
        return database.update(DATABASE_TABLE, contentValues,_ID+"=?",new String[]{id});
    }

    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID+"=?",new String[]{id});
    }

    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE, null,null,null,null,null,null);
    }

//    private static MoviesHelper INSTANCE;
//
//    public MoviesHelper(Context context) {
//        dataBaseHelper = new DatabaseHelper(context);
//    }
//    public static MoviesHelper getInstance(Context context) {
//        if (INSTANCE == null) {
//            synchronized (SQLiteOpenHelper.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new MoviesHelper(context);
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//
//    public void open() throws SQLException {
//        database = dataBaseHelper.getWritableDatabase();
//    }
//    public void close() {
//        dataBaseHelper.close();
//        if (database.isOpen())
//            database.close();
//    }
//
//    public long insert(ContentValues values) {
//
//        return database.insert(TABLE_NAME, null, values);
//    }
//
//    public Cursor getDataByName(String nama) {
//        return database.query(
//                TABLE_NAME, null,
//                JENIS + " LIKE ?",
//                new String[]{nama},
//                null,
//                null,
//                null,
//                null
//        );
//    }
//    public int deleteById(String id) {
//        return database.delete(TABLE_NAME, _ID + " = ?", new String[]{id});
//    }
//
//    public int queryById( int id){
//        Cursor cursor = database.query(
//                TABLE_NAME, null,
//                _ID + " = ?",
//                new String[]{String.valueOf(id)},
//                null,
//                null,
//                null,
//                null
//        );
//
//        if (cursor.getCount() >0){
//            return 1;
//        }
//        else return 0;
//    }

}
