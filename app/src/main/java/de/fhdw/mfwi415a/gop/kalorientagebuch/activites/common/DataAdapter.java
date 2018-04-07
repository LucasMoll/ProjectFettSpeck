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
        try {
            String sql = "SELECT * FROM Einheit";

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getEinheiten >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getAllLebensmittel() {
        try {
            String sql = "SELECT * FROM Lebensmittel";

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getLebensmittel >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getDailyMax() {
        try {
            String sql = "select Einstellungen.Tageslimit from Einstellungen";

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getDailyMax >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getGerichteOfDay(String s) {
        try {
            String sql = "select KT_Bezeichnung, GerichtName,(Portion*SUM) as SUM , KT_ID from (select GerichtID, KT_Bezeichnung, KT_ID, Menge as Portion from KTEintrag_Gericht inner join (select ID as KT_ID, Bezeichnung as KT_Bezeichnung, ID from KTEintrag where KTEintrag.Zeitpunkt = \"" + s + "\") where KT_ID = KTEintrag_Gericht.KTEintragID) q1 left join (select  Gericht_ID, GerichtName, sum(Lebensmittel_Einheit.Menge*Entsprechung) as SUM from Lebensmittel_Einheit , (select Lebensmittel_Einheit.LebensmittelID as subquer2_LebensmittelID, (subquery1_Menge*1.0/Lebensmittel_Einheit.Menge*1.0) as Entsprechung, Gericht.Bezeichnung as GerichtName, Gericht.ID as Gericht_ID from Lebensmittel_Einheit, Gericht inner join (select LebensmittelID as subquery1_LebensmittelID, EinheitID as subquer1_EinheitID, Menge as subquery1_Menge, GerichtID from Lebensmittel_Gericht) where subquery1_LebensmittelID = LebensmittelID AND (Lebensmittel_Einheit.EinheitID = subquer1_EinheitID) AND Gericht.ID= GerichtID) where subquer2_LebensmittelID = Lebensmittel_Einheit.LebensmittelID AND  Lebensmittel_Einheit.EinheitID = 1)q2 ON q1.GerichtID = q2.Gericht_ID order by KT_ID\n";

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getGerichteOfDay >>" + mSQLException.toString());
            throw mSQLException;
        }
    }


    public Cursor getData(String table, String[] columns, String selection, String having, String orderby, String limit) {
        try {
            Cursor mCur = mDb.query(table,columns,selection,null,"",having, orderby, limit);
            if (mCur != null) {
                mCur.moveToNext();
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }
}