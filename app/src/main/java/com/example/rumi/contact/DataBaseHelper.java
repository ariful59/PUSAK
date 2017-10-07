package com.example.rumi.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Rumi on 3/11/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contacts.db";
    private static final String TABLE_NAME="contacts";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_ADDRESS="address";
    private static final String COLUMN_PHONE="phone";
    private static final String COLUMN_BLOOD="blood";
    private static final String COLUMN_DEPT="dept";
    private static final String COLUMN_YEAR="year";
    private static final String COLUMN_UNI="university";

    private SQLiteDatabase db;
    private static final String TABLE_CREATE="create table contacts (email text primary key not null,"+
    "name text not null,address text not null,phone text not null,university text not null,dept text not null,year text not null,blood text not null)";
    public DataBaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query="DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TABLE_NAME, null );
        return res;
    }

    public String searchPass(String email)
    {
        db=this.getReadableDatabase();
        String qurey="select email from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(qurey,null);
        String a,b="not found";
        if(cursor.moveToFirst())
        {
            do{
                a=cursor.getString(0);
                if(a.equals(email)){
                    b="email Found";
                    break;
                }
            }while(cursor.moveToNext());
        }
        System.out.println(b);
        return b;
    }
    public String[] all_students()
    {
        db=this.getReadableDatabase();
        String qurey="select name from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(qurey,null);
        String a[]=new String[cursor.getCount()];
        int i=0;
        while(cursor.moveToNext()) {
            a[i++] = cursor.getString(0);
        }
        return a;
    }

    public String[] dept_search()
    {
        db=this.getReadableDatabase();
        String qurey="select dept from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(qurey,null);
        String a[]=new String[cursor.getCount()];
        int i=0;
        String b="";
            while(cursor.moveToNext()){
                b=cursor.getString(0);
                boolean falg=false;
                for(int j=0;j<i;j++)
                {
                    if(b.equalsIgnoreCase(a[j]))
                    {
                        falg=true;
                    }

                }
                if(!falg) {
                    a[i] = b;
                    i++;
                }
            }

        String res[]=new String[i];
        for(int l=0;l<i;l++) {
            res[l] = a[l];
        }
        System.out.println("Total Dept "+i);
        return res;
    }
    public String[] details_profile(String name)
    {
        db=this.getReadableDatabase();
        String result="\""+name+"\"";
        String qurey="select name,email,address,phone,blood,dept,year,university from "+TABLE_NAME+" where name="+result;
        Cursor cursor=db.rawQuery(qurey,null);
        String a[]=new String[cursor.getCount()];
        String res[]=new String[cursor.getCount()];
        int i=0;
        while(cursor.moveToNext()){
            a[i]=cursor.getString(0);
            a[i]+=",";
            a[i]+=cursor.getString(1);
            a[i]+=",";
            a[i]+=cursor.getString(2);
            a[i]+=",";
            a[i]+=cursor.getString(3);
            a[i]+=",";
            a[i]+=cursor.getString(4);
            a[i]+=",";
            a[i]+=cursor.getString(5);
            a[i]+=",";
            a[i]+=cursor.getString(6);
            a[i]+=",";
            a[i]+=cursor.getString(7);
            System.out.println("database Print"+a[i]);
        }

        return a;
    }
    public String[] districts_search()
    {
        db=this.getReadableDatabase();
        String qurey="select address from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(qurey,null);
        String a[]=new String[cursor.getCount()];
        int i=0;
        String b="";
            while(cursor.moveToNext()){
                b=cursor.getString(0);
                boolean falg=false;
                for(int j=0;j<i;j++)
                {
                    if(b.equals(a[j]))
                    {
                        falg=true;
                    }

                }
                if(!falg) {
                    a[i] = b;
                    i++;
                }
            }

        String res[]=new String[i];
        for(int l=0;l<i;l++) {
            res[l] = a[l];
        }
        System.out.println("Total Address"+i);
        return res;
    }
    public String[] university_search()
    {
        db=this.getReadableDatabase();
        String qurey="select university from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(qurey,null);
        String a[]=new String[cursor.getCount()];
        int i=0;
        String b="";
            while(cursor.moveToNext()){
                b=cursor.getString(0);
                boolean falg=false;
                for(int j=0;j<i;j++)
                {
                    if(b.equals(a[j]))
                    {
                        falg=true;
                    }

                }
                if(!falg) {
                    a[i] = b;
                    i++;
                }
            }
        String res[]=new String[i];
        for(int l=0;l<i;l++) {
            res[l] = a[l];
        }
        System.out.println("Total University "+i);
        return res;
    }
    public int total_donar(String blood)
    {
        db=this.getReadableDatabase();
        String result="\"%"+blood+"%\"";
        String qurey="select * from "+TABLE_NAME+" where blood like"+result;
        Cursor cursor=db.rawQuery(qurey,null);
        int a=cursor.getCount();
        return a;
    }
    public int total_dept(String dept)
    {
        db=this.getReadableDatabase();
        String result="\"%"+dept+"%\"";
        String qurey="select * from "+TABLE_NAME+" where dept like"+result;
        Cursor cursor=db.rawQuery(qurey,null);
        int a=cursor.getCount();
        return a;
    }
    public int total_districts(String uni)
    {
        db=this.getReadableDatabase();
        String result="\"%"+uni+"%\"";
        String qurey="select address from "+TABLE_NAME+" where address like"+result;
        Cursor cursor=db.rawQuery(qurey,null);
        int a=cursor.getCount();
        return a;
    }
    public int total_university(String uni)
    {
        db=this.getReadableDatabase();
        String result="\""+uni+"\"";
        String qurey="select name from "+TABLE_NAME+" where university="+result;
        Cursor cursor=db.rawQuery(qurey,null);
        int a=cursor.getCount();
        return a;
    }
    public String [] student_list(String uni)
    {
        db=this.getReadableDatabase();
        String result="\""+uni+"\"";
        String qurey="select name from "+TABLE_NAME+" where university="+result;
        Cursor cursor=db.rawQuery(qurey,null);
        int a=cursor.getCount();
        System.out.println("total students "+a);
        String ab[]=new String[a];
        int i=0;
        while(cursor.moveToNext()){
            ab[i++]=cursor.getString(0);
            System.out.println(ab[i-1]);
        }
        return ab;
    }
    public String [] student_blood_list(String uni)
    {
        db=this.getReadableDatabase();
        String result="\""+uni+"\"";
        String qurey="select name from "+TABLE_NAME+" where blood="+result;
        Cursor cursor=db.rawQuery(qurey,null);
        int a=cursor.getCount();
        System.out.println("total students "+a);
        String ab[]=new String[a];
        int i=0;
        while(cursor.moveToNext()){
            ab[i++]=cursor.getString(0);
            System.out.println(ab[i-1]);
        }
        return ab;
    }
    public String [] student_districts_list(String uni)
    {
        db=this.getReadableDatabase();
        String result="\""+uni+"\"";
        String qurey="select name from "+TABLE_NAME+" where address="+result;
        Cursor cursor=db.rawQuery(qurey,null);
        int a=cursor.getCount();
        System.out.println("total students "+a);
        String ab[]=new String[a];
        int i=0;
        while(cursor.moveToNext()){
            ab[i++]=cursor.getString(0);
            System.out.println(ab[i-1]);
        }
        return ab;
    }
    public String [] student_dept_list(String uni)
    {
        db=this.getReadableDatabase();
        String result="\""+uni+"\"";
        String qurey="select name from "+TABLE_NAME+" where dept="+result;
        Cursor cursor=db.rawQuery(qurey,null);
        int a=cursor.getCount();
        System.out.println("total students "+a);
        String ab[]=new String[a];
        int i=0;
        while(cursor.moveToNext()){
            ab[i++]=cursor.getString(0);
            System.out.println(ab[i-1]);
        }
        return ab;
    }
    public void insertConact(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String qurey = "select * from contacts";
        Cursor cursor = db.rawQuery(qurey, null);
        contentValues.put(COLUMN_EMAIL, c.getemail());
        contentValues.put(COLUMN_NAME, c.getname());
        contentValues.put(COLUMN_ADDRESS, c.getaddress());
        contentValues.put(COLUMN_PHONE, c.getphone());
        contentValues.put(COLUMN_UNI, c.getuniversity());
        contentValues.put(COLUMN_DEPT, c.getdept());
        contentValues.put(COLUMN_YEAR, c.getyear());
        contentValues.put(COLUMN_BLOOD, c.getblood());
        //db.insert(TABLE_NAME, null, contentValues);
        db.insertWithOnConflict(TABLE_NAME, null, contentValues ,SQLiteDatabase.CONFLICT_IGNORE);
        db.close();

    }
}
