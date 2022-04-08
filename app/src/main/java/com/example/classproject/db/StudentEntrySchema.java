package com.example.classproject.db;

import android.provider.BaseColumns;

public final class StudentEntrySchema {

        private StudentEntrySchema() {
        }

        // To prevent someone from accidentally instantiating the student class,
        // make the constructor private.

        /* Inner class that defines the table contents */
        public static class StudentEntry implements BaseColumns {
            public static final String TABLE_NAME = "students";
            public static final String COLUMN_FIRST_NAME = "firstname";
            public static final String COLUMN_LAST_NAME= "lastname";
            public static final String COLUMN_REG_NO = "regno";
            public static final String COLUMN_COURSE = "course";
            public static final String COLUMN_GENDER = "gender";
        }

        /* initialize student entries on student table */
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + StudentEntry.TABLE_NAME + " (" +
                        StudentEntry._ID + " INTEGER PRIMARY KEY," +
                        StudentEntry.COLUMN_FIRST_NAME + " TEXT," +
                        StudentEntry.COLUMN_LAST_NAME + " TEXT," +
                        StudentEntry.COLUMN_REG_NO + " TEXT," +
                        StudentEntry.COLUMN_COURSE + " TEXT," +
                        StudentEntry.COLUMN_GENDER + " TEXT)";

    /* initialize student entries on student table */
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME;
    /* Querying all data from the databse*/
        public static final String SQL_SELECT_ALL =
            "SELECT * FROM "+StudentEntry.TABLE_NAME;
}
