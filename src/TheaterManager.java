import java.util.List;

public class TheaterManager {
    private DatabaseManager dbManager;

    public TheaterManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<String> fetchAllTheaters() {
        return dbManager.getAllTheaters();
    }
}
