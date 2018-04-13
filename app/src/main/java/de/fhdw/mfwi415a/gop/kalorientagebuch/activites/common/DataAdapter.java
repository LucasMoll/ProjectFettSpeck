package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common;

/**
 * Created by pschoger on 21.03.2018.
 */
import java.io.IOException;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataAdapter {
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    //getter

    public Cursor getAllEinheiten() {
        String sql = "SELECT * FROM Einheit where delFlg = 0 order by Bezeichnung ";
        return getData(sql, "getAllEinheiten");
    }

    public Cursor getAllLebensmittel() {
            String sql = "SELECT * FROM Lebensmittel where delFlg = 0 order by Bezeichnung COLLATE NOCASE";
            return getData(sql, "getLebensmittel");
    }

    public Cursor getDailyMax() {
            String sql = "select Einstellungen.Tageslimit from Einstellungen";
            return getData(sql, "getDailyMax");
    }

    public Cursor getName() {
        String sql = "select Benutzername from Einstellungen";
        return getData(sql, "getName");
    }

    public Cursor getEMail() {
        String sql = "select EMail from Einstellungen";
        return getData(sql, "getEMail");
    }


    public Cursor getGerichteOfDay(String s) {
            String sql = "select KT_Bezeichnung, GerichtName,Portion*SUM as Summe, KT_ID from (select GerichtID, KT_Bezeichnung, KT_ID, Menge as Portion from KTEintrag_Gericht inner join (select ID as KT_ID, Bezeichnung as KT_Bezeichnung, ID from KTEintrag where KTEintrag.Zeitpunkt = \""+s+"\") where KT_ID = KTEintrag_Gericht.KTEintragID) q1 left join (select  Gericht_ID, GerichtName, sum(Lebensmittel_Einheit.Menge*Entsprechung) as SUM from Lebensmittel_Einheit inner join (select Lebensmittel_Einheit.LebensmittelID as subquer2_LebensmittelID, (subquery1_Menge*1.0/Lebensmittel_Einheit.Menge*1.0) as Entsprechung, Gericht.Bezeichnung as GerichtName, Gericht.ID as Gericht_ID from Lebensmittel_Einheit, Gericht inner join (select LebensmittelID as subquery1_LebensmittelID, EinheitID as subquer1_EinheitID, Menge as subquery1_Menge, GerichtID from Lebensmittel_Gericht) where subquery1_LebensmittelID = LebensmittelID AND (Lebensmittel_Einheit.EinheitID = subquer1_EinheitID) AND Gericht.ID= GerichtID) where subquer2_LebensmittelID = Lebensmittel_Einheit.LebensmittelID AND  Lebensmittel_Einheit.EinheitID = 1 group by Gericht_ID)q2 ON q1.GerichtID = q2.Gericht_ID order by KT_ID";
            return getData(sql, "getGerichteofDay");
    }
    public Cursor getLebensmittelOfDay(String s)
    {
        String sql = "select Bezeichnung, L_Bezeichnung, KTEintrag_Lebensmittel.KTEintragID as KTE_ID, sum(Menge * kcal) as SUMME from KTEintrag_Lebensmittel inner join (select KTEintrag.Bezeichnung, ID as KTE_ID from KTEintrag where Zeitpunkt = \""+s+"\") on  KTEintrag_Lebensmittel.KTEintragID  = KTE_ID left join (select ID as Lebensmittel_ID, Bezeichnung as L_Bezeichnung, kcal from Lebensmittel left join (select Lebensmittel_Einheit.LebensmittelID as L_ID, Lebensmittel_Einheit.Menge as kcal from Lebensmittel_Einheit where EinheitID=1 ) on  Lebensmittel_ID = L_ID ) where LebensmittelID = Lebensmittel_ID group by KTE_ID; Lebensmittel_ID";
        return getData(sql, "getLebensmittelOfDay");
    }

    public Cursor getAllGerichte()
    {
            String sql = "select * from Gericht where delFlg = 0 order by Gericht.Bezeichnung";
            return getData(sql,"getGerichte");
    }

    public Cursor getNameOfGericht( int i)
    {
        String sql = "select * from Gericht where ID =" + i;
        return getData(sql,"getnameOfGericht");
    }

    public Cursor getNameOfLebensmittel( int i)
    {
        String sql = "select * from Lebensmittel where ID =" + i;
        return getData(sql,"getnameOfLebensmittel");
    }
    public Cursor getMaxKTE_ID() {
        String sql = "select max(ID) from KTEintrag";
        return getData(sql,"getMaxKTE_ID");
    }

    public Cursor getMaxEinheit_ID(){
        String sql = "select max(ID) from Einheit";
        return getData(sql,"getMaxEintrag_ID");
    }

    public Cursor getMaxGericht_ID(){
        String sql = "select max(ID) from Gericht";
        return getData(sql, "getMaxGericht_ID");
    }

    public Cursor getMaxLebensmittelGericht_ID(){
        String sql = "select max(ID) from Lebensmittel_Gericht";
        return getData(sql, "getMaxLebensmittelGericht_ID");
    }

    public Cursor getEinheitenOfLebensmittelByLebensmittelId (int id)
    {
        String sql= "select Einheit.ID, Lebensmittel.Bezeichnung As Lebensmittelbezeichnung, Einheit.Bezeichnung as Einheitenbezeichnung, Einheit.Kurzbezeichnung, Lebensmittel_Einheit.Menge from Lebensmittel inner join Lebensmittel_Einheit on Lebensmittel.ID = Lebensmittel_Einheit.LebensmittelID  join Einheit on Lebensmittel_Einheit.EinheitID = Einheit.ID where Lebensmittel.delFlg = 0 and Lebensmittel.ID = "+id;
        return getData(sql, "getEinheitenOfLebensmittelByLebensmittelId");
    }

    public Cursor getUnusedEinheitenFromLebensmittelId(int id) {
        String sql = "select * From Einheit where Bezeichnung not in (select Einheit.Bezeichnung From Einheit join Lebensmittel_Einheit on Einheit.ID = Lebensmittel_Einheit.EinheitID where Einheit.delFlg = 0 And Lebensmittel_Einheit.LebensmittelID = " + id + ") and Einheit.delFlg = 0";
        return getData(sql, "getUnusedEinheitenFromLebensmittelId");
    }

    public Cursor getIDofLebensmittel(String s){
        String sql = "select ID from Lebensmittel where Bezeichnung=\""+s+"\"";
        return  getData(sql, "getIdzuBezeichnung");
    }

    public Cursor getIDofEinheit(String s){
        String sql = "select ID from Einheit where Bezeichnung=\""+s+"\"";
        return  getData(sql, "getIdzuEinheit");
    }

    public Cursor getNameofEinheit(int i){
        String sql = "select Bezeichnung from Einheit where ID=" +i ;
        return  getData(sql, "getNameofEinheit");
    }

    public Cursor getKurzBezofEinheit(int i){
        String sql = "select Kurzbezeichnung from Einheit where ID=" +i ;
        return  getData(sql, "getKurzBezofEinheit");
    }


    public Cursor getData(String sqlQuery, String logName) {
        try {

            Cursor mCur = mDb.rawQuery(sqlQuery, null);
            if (mCur != null) {
                mCur.moveToNext();
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, logName+" >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getData(String table, String[] columns, String selection, String having, String orderby, String limit) {
        try {
            Cursor mCur = mDb.query(table, columns, selection, null, "", having, orderby, limit);
            if (mCur != null) {
                mCur.moveToNext();
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public void writeData(String sqlQuery, String logName) {
        try {
            mDb.execSQL(sqlQuery);
        } catch (SQLException mSQLException) {
            Log.e(TAG, logName + " >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public void writeNewLebensmittel(String bezeichnung) {
        String sql = "insert into Lebensmittel (Bezeichnung, delflg) values (\"" + bezeichnung + "\", 0);";
        writeData(sql, "writeLebensmittel");
    }

    public void writeEinheitToLebensmittel(String lebensmittelBezeichnung, String einheitBezeichnung, Double menge) {
        String sql = "insert into Lebensmittel_Einheit (LebensmittelID, EinheitID, Menge) Values ((select ID From Lebensmittel Where Bezeichnung = \"" + lebensmittelBezeichnung + "\"), (select ID From Einheit Where Einheit.Bezeichnung = \"" + einheitBezeichnung + "\"), " + menge + ")";
        writeData(sql, "writeEinheitToLebensmittel");
    }

    public void deleteLebensmittel_Einheiten(int idLebensmittel, int idEinheit)
    {
        String sql = "delete from Lebensmittel_Einheit where EinheitID = "+idEinheit+" And LebensmittelID = "+idLebensmittel;
        writeData(sql, "setDeleteFlagLebensmittel_Einheiten");
    }

    public void setDeleteFlagLebensmittel(int id)
    {
        String sql = "update Lebensmittel set delFlg = 1 where Lebensmittel.ID ="+id;
        writeData(sql, "setDeleteFlagLebensmittel");
    }

    public Cursor getLebensmittelByID(int id) {
        String sql = "select * From Lebensmittel where ID ="+id;
         return getData(sql, "setDeleteFlagLebensmittel");
    }

    public void updateLebensmittelBezeichnung(String lebensmittelOld, String lebensmittelNew) {
        String sql = "update Lebensmittel set Bezeichnung = \""+lebensmittelNew+"\" where Bezeichnung =\""+lebensmittelOld+"\"";
        writeData(sql, "setDeleteFlagLebensmittel");
    }
}