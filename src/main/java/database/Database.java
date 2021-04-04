package database;

public class Database {
    static boolean testing = false;
    private static IDatabase db;

    public static void setTesting() {
        testing = true;
    }

    public static IDatabase getDatabase() {
        if (db == null) {
            if (testing) {
                db = new TestDatabase();
            } else {
                db = new RealDatabase();
            }
        }

        return db;
    }
}
