package com.kamalan.databasetester;

import com.kamalan.databasetester.model.Booking;
import com.kamalan.databasetester.realm.MyRealmDB;
import com.kamalan.databasetester.snappy.MySnappyDB;

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
    private MySnappyDB mSnappy;

    private StringBuilder mSb;
    private EditText mConsole;
    private EditText mEditText;

    private List<Booking> mBookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.editText);
        mConsole = (EditText) findViewById(R.id.etConsole);
        mConsole.setKeyListener(null);

        mSb = new StringBuilder();

        mRealm = new MyRealmDB(this);

        mSnappy = new MySnappyDB(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRealm.closeDB();
        mSnappy.closeDB();
    }

    private void printMessage(String message) {
        // the "- 1" should send it to the TOP of the last line, instead of the bottom of the last line
        int y = (mConsole.getLineCount() - 1) * mConsole.getLineHeight();

        mSb.append(message);
        mSb.append("\n");
        mConsole.setText(mSb.toString());
        mConsole.scrollTo(0, y);
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
        mSb.setLength(0);
        if (mBookingList == null) {
            printMessage("> Booking list is empty. Generate Booking obj first.");
            return;
        }

        printMessage(">> Try to store 10000 random Booking object in db...");

        insertIntoRealmDb();
        insertIntoSnappyDb();
    }

    public void onRetrieveBookingsClicked(View view) {
        mSb.setLength(0);
        printMessage(">> Try to find numbers of Booking object in db with id...");

        realmFindById();
        snappyFindById();
    }

    public void onClearDbClicked(View view) {
        mSb.setLength(0);
        printMessage(">> CLR all tables");

        realmDelete();
        snappyDelete();
    }

    private void insertIntoRealmDb() {
        long startTime = System.currentTimeMillis();
        for (Booking booking : mBookingList) {
            mRealm.storeBooking(booking);
        }

        long processTime = System.currentTimeMillis();
        processTime -= startTime;
        printMessage("> Insert to Realm took " + processTime + "ms");
    }

    private void insertIntoSnappyDb() {
        long startTime = System.currentTimeMillis();
        for (Booking booking : mBookingList) {
            mSnappy.storeBooking(booking.getId(), booking);
        }

        long processTime = System.currentTimeMillis();
        processTime -= startTime;
        printMessage("> Insert to Snappy took " + processTime + "ms");
    }

    private void realmFindById() {
        long startTime = System.currentTimeMillis();

        List<Booking> bookings = mRealm.findBookingById(mEditText.getText().toString().trim());

        long processTime = System.currentTimeMillis();
        processTime -= startTime;
        printMessage("> Number of " + bookings.size() + " Booking(s) found in Realm db in " + processTime + "ms");
    }

    private void snappyFindById() {
        long startTime = System.currentTimeMillis();

        List<Booking> bookings = mSnappy.findBookingByKey(mEditText.getText().toString().trim());

        long processTime = System.currentTimeMillis();
        processTime -= startTime;
        printMessage("> Number of " + bookings.size() + " Booking(s) found in Snappy db in " + processTime + "ms");
    }

    private void realmDelete() {
        long startTime = System.currentTimeMillis();

        mRealm.deleteBookingTable();

        long processTime = System.currentTimeMillis();
        processTime -= startTime;
        printMessage("> Realm cleared in " + processTime + "ms");
    }

    private void snappyDelete() {
        long startTime = System.currentTimeMillis();

        mSnappy.deleteBookingTable();

        long processTime = System.currentTimeMillis();
        processTime -= startTime;
        printMessage("> Snappy cleared in " + processTime + "ms");
    }
}
