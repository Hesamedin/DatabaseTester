package com.kamalan.databasetester;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import android.app.Application;

/**
 * Created by hesam on 9/5/16.
 */
public class DatabaseTesterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Stetho must be used in DEBUG build only. However this is not production app.
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }
}
