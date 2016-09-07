package com.kamalan.databasetester;

import com.kamalan.databasetester.model.Booking;
import com.kamalan.databasetester.realm.MyRealmDB;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MyRealmDB mRealm;

    private StringBuilder mSb;
    private EditText mConsole;

    private List<Booking> mBookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConsole = (EditText) findViewById(R.id.etConsole);
        mConsole.setKeyListener(null);

        mSb = new StringBuilder();

        mRealm = new MyRealmDB(this);

        List<Booking> bookings = mRealm.getAllBookings();
        Log.d(TAG, "Number of " + bookings.size() + " item(s) found in Booking Table.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRealm.closeDB();
    }

    private void printMessage(String message) {
        // the "- 1" should send it to the TOP of the last line, instead of the bottom of the last line
        int y = (mConsole.getLineCount() - 1) * mConsole.getLineHeight();

        mSb.append(message);
        mSb.append("\n");
        mConsole.setText(mSb.toString());
        mConsole.scrollTo(0, y);
    }

    public void onClearDbClicked(View view) {
        printMessage(">> CLR all tables");
        long startTime = System.currentTimeMillis();

        mRealm.deleteBookingTable();
        long processTime = System.currentTimeMillis();
        processTime -= startTime;
        printMessage("> Realm db cleared in " + processTime + "ms");
    }

    public void onGenerateBookingsClicked(View view) {
        printMessage(">> Try to create 10000 random Booking object...");
        long startTime = System.currentTimeMillis();

        mBookingList = getBookingList(10000);
        long processTime = System.currentTimeMillis();
        processTime -= startTime;
        printMessage("> Number of " + mBookingList.size() + " Booking obj created in " + processTime/1000 + "s");
    }

    private List<Booking> getBookingList(int size) {
        List<Booking> bookingList = new ArrayList<>(size);
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            Booking booking = new Booking();
            booking.setId(UUID.randomUUID().toString());
            booking.setCode(UUID.randomUUID().toString());
            booking.setFareLowerBound(rand.nextFloat());
            booking.setFareUpperBound(rand.nextFloat());
            booking.setPhoneNumber("1234567890");
            booking.setPickUpTime(rand.nextLong());
            booking.setRequestedTaxiTypeName("taxi"+UUID.randomUUID().toString());
            booking.setTaxiTypeId(String.valueOf(rand.nextInt(100)));

            bookingList.add(booking);
        }

        return bookingList;
    }

    public void onInsertBookingsClicked(View view) {
        if (mBookingList == null) {
            printMessage("> Booking list is empty. Generate Booking obj first.");
            return;
        }

        printMessage(">> Try to store 10000 random Booking object in Realm db...");
        long startTime = System.currentTimeMillis();
        for (Booking booking : mBookingList) {
            mRealm.storeBooking(booking);
        }

        long processTime = System.currentTimeMillis();
        processTime -= startTime;
        printMessage("> Number of " + mBookingList.size() + " Booking obj stored in Realm db in" + processTime/1000 + "s");
    }

    public void onRetrieveBookingsClicked(View view) {
        printMessage(">> Try to find numbers of Booking object in Realm db with id...");
        long startTime = System.currentTimeMillis();

        List<Booking> bookings = mRealm.findBookingById("1234");

        long processTime = System.currentTimeMillis();
        processTime -= startTime;
        printMessage("> Number of " + bookings.size() + " Booking(s) found in db with id in " + processTime + "ms");
    }
}
