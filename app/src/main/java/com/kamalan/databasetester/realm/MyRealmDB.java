package com.kamalan.databasetester.realm;

import com.kamalan.databasetester.model.Booking;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by hesam on 9/6/16.
 */
public class MyRealmDB  {
    private Context mContext;
    private Realm mRealm;

    public MyRealmDB(Context context) {
        mContext = context;

        configRealm();

        mRealm = Realm.getDefaultInstance();
    }

    private void configRealm() {
        // Configure Realm for the application
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(mContext)
                .name("dbTester.realm")
                .build();

        // Make this config the default
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public void closeDB() {
        mRealm.close();
    }

    public void storeBooking(final Booking booking) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(booking);
            }
        });
    }

    public RealmResults<Booking> getAllBookings() {
        return mRealm.where(Booking.class).findAll();
    }

    public RealmResults<Booking> findBookingById(String id) {
        return mRealm.where(Booking.class)
                .contains("id", id)
                .findAll();
    }

    public void deleteBookingTable() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Booking.class);
            }
        });
    }
}
