package junk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;


public class DataNode {

    private  ObservableList<Songs> songsObservableList = null;
    private final static String db= "jdbc:sqlite:./data/dbs/chinook.db";


    public DataNode(){
        songsObservableList = FXCollections.observableArrayList();
    }

    public  ObservableList<Songs> getSongsObservableList() {
        return songsObservableList;
    }

    public void getdata(String source){
        readDB(source);
    }
    public static void deleteFromDB(String name, String album, String mediaType, String genre, String composer){
        final String sql = "delete from albums\n" +
                "where albums.AlbumId = (select tracks.AlbumId from tracks) and albums.Title = \"" + album + "\";\n" +
                "delete from media_types\n" +
                "where media_types.MediaTypeId = (select tracks.MediaTypeId from tracks) and media_types.Name = \"" + mediaType + "\";\n" +
                "delete from genres\n" +
                "where genres.GenreId = (select tracks.GenreId from tracks) and genres.Name = \"" + genre + "\";\n" +
                "delete from tracks\n" +
                "where tracks.Name = \"" + name + "\" and tracks.Composer = \"" + composer + "\";\n";
        query(sql);
    }
    public static void editDB(String name, String album, String mediaType, String genre, String composer, Float price, String  oldName, String  oldAlbum, String oldComposer, String oldMedia_type, String oldGenre, Float oldPrice){
        final String sql = "update albums\n" +
                "set Title  = \"" + album + "\"\n" +
                "where albums.AlbumId = (select tracks.AlbumId from tracks where tracks.Name = \"" + oldName + "\" limit 1);\n" +
                "update media_types\n" +
                "set Name = \"" + mediaType + "\"\n" +
                "where media_types.MediaTypeId = (select tracks.MediaTypeId from tracks where tracks.Name = \"" + oldName + "\" and tracks.Composer = \"" + oldComposer + "\" limit 1);\n" +
                "update genres\n" +
                "set Name = \"" + genre + "\"\n" +
                "where genres.GenreId = (select tracks.GenreId from tracks where tracks.Name = \"" + oldName + "\" and tracks.Composer = \"" + oldComposer + "\" limit 1);\n" +
                "update tracks\n" +
                "set Name = \"" + name + "\", Composer = \"" + composer + "\", UnitPrice = \"" + price + "\"\n" +
                "where tracks.Name = \"" + oldName + "\" and tracks.Composer = \"" + oldComposer + "\" and tracks.UnitPrice = " + oldPrice + "";
        query(sql);
    }
    public static void addDataToDB(String name, String album, String mediaType, String genre, String composer, float price){
        final String sql =
                "insert into albums ( Title, ArtistId)\n" +
                "values (\"" + album + "\",254);\n" +
                "insert into media_types (Name)\n" +
                "values (\"" + mediaType + "\");\n" +
                "insert into genres (Name)\n" +
                "values (\"" + genre + "\");\n" +
                "insert into tracks (Name, AlbumId, MediaTypeId, GenreId,Composer, Milliseconds, Bytes, UnitPrice)\n" +
                "values \n" +
                "(\"" + name + "\",\n" +
                "(select albums.AlbumId \n" +
                "\tfrom albums, tracks \n" +
                "\twhere albums.Title = \"" + album + "\" limit 1),\n" +
                "(select media_types.MediaTypeId\n" +
                "\tfrom media_types, tracks\n" +
                "\twhere media_types.Name = \"" + mediaType + "\" limit 1),\n" +
                "(select genres.GenreId\n" +
                "from genres, tracks\n" +
                "where genres.Name = \"" + genre + "\" limit 1),\n" +
                "\"" + composer + "\",\n" +
                "254,\n" +
                "254,\n" +
                "" + price + ");";
        query(sql);
       // dbShit(sql);
    }

    private static void dbShit(String sql) {
        try{
            Statement st =  DriverManager.getConnection(db).createStatement();
            st.execute(sql);

        }
        catch (SQLException e)
        {
           // e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    private static void query(String sql) {
        String[] sqlArray = sql.split(";");
        for (String a:sqlArray) {
            dbShit(a);
        }
    }
    private void readDB(String source) {

        final String sql = "select tracks.Name \"Song_name\", albums.Title \"Album\", media_types.Name \"Audi_type\",genres.Name \"Genre\", tracks.Composer \"Composer\", tracks.UnitPrice \"Price\" \n" +
                "from tracks, albums, media_types, genres\n" +
                "where tracks.AlbumId = albums.AlbumId and media_types.MediaTypeId = tracks.MediaTypeId and genres.GenreId = tracks.GenreId";
        //final String sql = "select * from  " + source + "";

        try (Connection conn =  DriverManager.getConnection(db);
             Statement stmt = conn.createStatement();
             ResultSet rset = stmt.executeQuery(sql)) {

            Songs record = null;
            while (rset.next())
            {
                record = new Songs ( rset.getString("Song_name"),
                        rset.getString("Album"),
                        rset.getString("Audi_type"),
                        rset.getString("Genre"),
                        rset.getString("Composer"),
                        rset.getFloat("Price")
                );
                songsObservableList.addAll(record);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
