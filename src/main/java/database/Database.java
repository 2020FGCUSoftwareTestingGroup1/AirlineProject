package database;

public class Database {
    private static IDatabase db;

    public static void setDatabase(IDatabase db) {
        Database.db = db;
    }

    public static IDatabase getDatabase() {
        if (db == null) {
            db = new RealDatabase();
        }

        return db;
    }
}
