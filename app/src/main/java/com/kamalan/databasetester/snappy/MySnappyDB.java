package com.kamalan.databasetester.snappy;

import com.kamalan.databasetester.model.Booking;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesam on 9/7/16.
 */
public class MySnappyDB {

    private Context mContext;
    private DB mSnappyDb;

    public MySnappyDB(Context context) {
        mContext = context;

        try {
            mSnappyDb = DBFactory.open(mContext);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            mSnappyDb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public void storeBooking(String key, Booking booking) {
        try {
            mSnappyDb.put(key, booking);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> findBookingByKey(String key) {
        List<Booking> list = new ArrayList<>();

        try {
            String[] bookings = mSnappyDb.findKeys(key);

            for (String bookingKey: bookings) {
                list.add(mSnappyDb.getObject(bookingKey, Booking.class));
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void deleteBookingTable() {
        try {
            mSnappyDb.destroy();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }
}
