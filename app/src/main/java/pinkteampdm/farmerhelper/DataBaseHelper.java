package pinkteampdm.farmerhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "AgriculturaProj.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CULTUREREGISTRY = "CultureRegistry" ;
    private static final String COLUMN1_CULTUREREGISTRY_ID = "RegistryID";
    private static final String COLUMN2_CULTUREREGISTRY_CULTUREID = "CultureID";
    private static final String COLUMN3_CULTUREREGISTRY_DATE = "SeedDate";
    private static final String COLUMN4_CULTUREREGISTRY_GPS = "GpsCoordinates";

    private static final String TABLE_CULTURE = "Culture";
    private static final String COLUMN1_CULTURE_ID = "CultureID";
    private static final String COLUMN2_CULTURE_NAME = "CultureName";

    private static final String TABLE_CULTUREINFO = "CultureInfo";
    private static final String COLUMN1_CULTUREINFO_ID = "CultureInfoID";
    private static final String COLUMN2_CULTUREINFO_CULTUREID = "CultureID";
    private static final String COLUMN3_CULTUREINFO_MONTH = "Month";
    private static final String COLUMN4_CULTUREINFO_MOON = "MoonFase";
    private static final String COLUMN5_CULTUREINFO_ZONE = "CountryZone";
    private static final String COLUMN6_CULTUREINFO_WEEK = "Week";

    private final static String TABLE_ACTIVITY = "Activity";
    private final static String COLUMN1_ACTIVITY_ID = "ActivityID";
    private final static String COLUMN2_ACTIVITY_NAME = "Name";
    private final static String COLUMN3_ACTIVITY_DESCRIPTION = "Description";

    private final static String TABLE_PLAGUE = "Plague";
    private final static String COLUMN1_PLAGUE_ID = "PlagueID";
    private final static String COLUMN2_PLAGUE_NAME = "Name";
    private final static String COLUMN3_PLAGUE_MONTH = "Month";

    private final static String TABLE_ZONE = "Zone";
    private final static String COLUMN1_ZONE_ID = "ZoneID";
    private final static String COLUMN2_ZONE_NAME = "Name";
    private final static String COLUMN3_ZONE_DESCRIPTION = "Description";


    private final static String TABLE_CULTUREINFOZONE = "CultureInfoZone";
    private final static String COLUMN1_CULTUREINFOZONE_ZONEID = "ZoneID";
    private final static String COLUMN2_CULTUREINFOZONE_CULTUREINFOID = "CultureInfoID";

    private final static String TABLE_PLAGUECULTURE = "PlagueCulture";
    private final static String COLUMN1_PLAGUECULTURE_PLAGUEID = "PlagueID";
    private final static String COLUMN2_PLAGUECULTURE_CULTUREID = "CultureID";

    private final static String TABLE_CULTUREINFOACTIVITY = "CultureInfoActivity";
    private final static String COLUMN1_CULTUREINFOACTIVITY_ACTIVITYID = "ActivityID";
    private final static String COLUMN2_CULTUREINFOACTIVITY_CULTUREINFOID = "CultureInfoID";

    private final String nothing = "Qualquer";
    private final String []zone = new String[]{"Normal", "Estufa", "Horta","Jardim"};
    private final String []place = new String[]{"Sul", "Norte/Centro"};
    private final String []moons = new String[]{"Minguante", "Crescente"};
    private final String []months = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};


    /*              Queries related to CultureRegistry table                */
    private static final String CULTUREREGISTRY_TABLE_CREATE =
        "CREATE TABLE " +TABLE_CULTUREREGISTRY+ " ("
                        +COLUMN1_CULTUREREGISTRY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        +COLUMN2_CULTUREINFO_CULTUREID+ " INTEGER, "
                        +COLUMN3_CULTUREREGISTRY_DATE+ " DATE, "
                        +COLUMN4_CULTUREREGISTRY_GPS+ " VARCHAR(10), "
                        + " FOREIGN KEY(" +COLUMN2_CULTUREREGISTRY_CULTUREID+ ") REFERENCES " +TABLE_CULTURE+ " (" +COLUMN1_CULTURE_ID+ ")); ";

    private static final String CULTUREREGISTRY_TABLE_DROP =
        "DROP TABLE " +TABLE_CULTUREREGISTRY+ ";";

    private static final String CULTUREREGISTRY_TABLE_TEMP =
        "CREATE TEMP TABLE CultureRegistryAux AS SELECT * FROM " +TABLE_CULTUREREGISTRY+ ";";

    private static final String CULTUREREGISTRY_TABLE_INSERT =
        "INSERT INTO " +TABLE_CULTUREREGISTRY+ " ("
                       +COLUMN1_CULTUREREGISTRY_ID+ ", "
                       +COLUMN2_CULTUREREGISTRY_CULTUREID+ ", "
                       +COLUMN4_CULTUREREGISTRY_GPS+ ", "
                       +COLUMN3_CULTUREREGISTRY_DATE+ ") SELECT* FROM CultureRegistryAux;";


    /*              Queries related to Culture table                */
    private static final String CULTURE_TABLE_CREATE =
        "CREATE TABLE " +TABLE_CULTURE+ "("
                        +COLUMN1_CULTURE_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +COLUMN2_CULTURE_NAME+ " VARCHAR(30));";

    private static final String CULTURE_TABLE_DROP =
        "DROP TABLE " +TABLE_CULTURE+ ";";

    private static final String CULTURE_TABLE_TEMP =
        "CREATE TEMP TABLE CultureAux AS SELECT * FROM " +TABLE_CULTURE+ ";";

    private static final String CULTURE_TABLE_INSERT =
        "INSERT INTO " +TABLE_CULTURE+ " ("
                       +COLUMN1_CULTURE_ID+ ", "
                       +COLUMN2_CULTURE_NAME+ ") SELECT* FROM CultureAux;";


    /*              Queries related to CultureInfo table                */
    private static final String CULTUREINFO_TABLE_CREATE =
        "CREATE TABLE " +TABLE_CULTUREINFO+ " ("
                        +COLUMN1_CULTUREINFO_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +COLUMN1_CULTURE_ID+ " INTEGER, "
                        +COLUMN3_CULTUREINFO_MONTH+ " VARCHAR(15), "
                        +COLUMN4_CULTUREINFO_MOON+ " VARCHAR(30), "
                        +COLUMN5_CULTUREINFO_ZONE+ " VARCHAR(30), "
                        +COLUMN6_CULTUREINFO_WEEK+ " INT, "
                        + " FOREIGN KEY(" +COLUMN2_CULTUREINFO_CULTUREID+ ") REFERENCES " +TABLE_CULTURE+ " (" +COLUMN1_CULTURE_ID+ "));";

    private static final String CULTUREINFO_TABLE_DROP =
        "DROP TABLE " +TABLE_CULTUREINFO+ ";";

    private static final String CULTUREINFO_TABLE_TEMP =
        "CREATE TEMP TABLE CultureInfoAux AS SELECT * FROM " +TABLE_CULTUREINFO+ ";";

    private static final String CULTUREINFO_TABLE_INSERT =
        "INSERT INTO " +TABLE_CULTUREINFO+ " ("
                       +COLUMN1_CULTUREINFO_ID+ ", "
                       +COLUMN2_CULTUREINFO_CULTUREID+ ", "
                       +COLUMN3_CULTUREINFO_MONTH+ ", "
                       +COLUMN4_CULTUREINFO_MOON+ ", "
                       +COLUMN6_CULTUREINFO_WEEK+ ", "
                       +COLUMN5_CULTUREINFO_ZONE+ ") SELECT* FROM CultureInfoAux;";


    /*              Queries related to Activity table                */
    private static final String ACTIVITY_TABLE_CREATE =
        "CREATE TABLE " +TABLE_ACTIVITY+ "("
                        +COLUMN1_ACTIVITY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +COLUMN2_ACTIVITY_NAME+ " VARCHAR(30),"
                        +COLUMN3_ACTIVITY_DESCRIPTION+" VARCHAR(200));";

    private static final String ACTIVITY_TABLE_DROP =
        "DROP TABLE " +TABLE_ACTIVITY+ ";";

    private static final String ACTIVITY_TABLE_TEMP =
        "CREATE TEMP TABLE ActivityAux AS SELECT * FROM " +TABLE_ACTIVITY+ ";";

    private static final String ACTIVITY_TABLE_INSERT =
        "INSERT INTO " +TABLE_ACTIVITY+ " ("
                       +COLUMN1_ACTIVITY_ID+ ", "
                       +COLUMN2_ACTIVITY_NAME+ ", "
                       +COLUMN3_ACTIVITY_DESCRIPTION+ ") SELECT* FROM ActivityAux;";


    /*              Queries related to Plague table                */
    private static final String PLAGUE_TABLE_CREATE =
        "CREATE TABLE " +TABLE_PLAGUE+ "("
                        +COLUMN1_PLAGUE_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +COLUMN2_PLAGUE_NAME+ " VARCHAR(30), "
                        +COLUMN3_PLAGUE_MONTH+ " VARCHAR(15));";

    private static final String PLAGUE_TABLE_DROP =
        "DROP TABLE " +TABLE_PLAGUE+ ";";

    private static final String PLAGUE_TABLE_TEMP =
        "CREATE TEMP TABLE PlagueAux AS SELECT * FROM " +TABLE_PLAGUE+ ";";

    private static final String PLAGUE_TABLE_INSERT =
        "INSERT INTO " +TABLE_PLAGUE+ " ("
                       +COLUMN1_PLAGUE_ID+ ", "
                       +COLUMN2_PLAGUE_NAME+ ", "
                       +COLUMN3_PLAGUE_MONTH+ ") SELECT* FROM PlagueAux;";


    /*              Queries related to PlagueCulture table                */
    private static final String PLAGUECULTURE_TABLE_CREATE =
        "CREATE TABLE " +TABLE_PLAGUECULTURE+ "("
                        +COLUMN1_PLAGUECULTURE_PLAGUEID+ " INTEGER,"
                        +COLUMN2_PLAGUECULTURE_CULTUREID+ " INTEGER,"
                        + " FOREIGN KEY (" +COLUMN1_PLAGUECULTURE_PLAGUEID+ ") REFERENCES " +TABLE_PLAGUE+ " (" +COLUMN1_PLAGUE_ID+ "),"
                        + " FOREIGN KEY (" +COLUMN2_PLAGUECULTURE_CULTUREID+ ") REFERENCES " +TABLE_CULTURE+ " (" +COLUMN1_CULTURE_ID+ ")); ";


    private static final String PLAGUECULTURE_TABLE_DROP =
        "DROP TABLE " +TABLE_PLAGUECULTURE+ ";";

    private static final String PLAGUECULTURE_TABLE_TEMP =
        "CREATE TEMP TABLE PlagueCultureAux AS SELECT * FROM " +TABLE_PLAGUECULTURE+ ";";

    private static final String PLAGUECULTURE_TABLE_INSERT =
        "INSERT INTO " +TABLE_PLAGUECULTURE+ " ("
                       +COLUMN1_PLAGUECULTURE_PLAGUEID+ ", "
                       +COLUMN2_PLAGUECULTURE_CULTUREID+ ") SELECT* FROM PlagueCultureAux;";


    /*              Queries related to CultureInfoActivity table                */
    private static final String CULTUREINFOACTIVITY_TABLE_CREATE =
        "CREATE TABLE " +TABLE_CULTUREINFOACTIVITY+ "("
                        +COLUMN1_CULTUREINFOACTIVITY_ACTIVITYID+ " INTEGER,"
                        +COLUMN2_CULTUREINFOACTIVITY_CULTUREINFOID+ " INTEGER,"
                        + " FOREIGN KEY (" +COLUMN1_CULTUREINFOACTIVITY_ACTIVITYID+ ") REFERENCES " +TABLE_ACTIVITY+ "(" +COLUMN1_ACTIVITY_ID+ "),"
                        + " FOREIGN KEY (" +COLUMN2_CULTUREINFOACTIVITY_CULTUREINFOID+ ") REFERENCES " +TABLE_CULTUREINFO+ "(" +COLUMN1_CULTUREINFO_ID+ ")); ";

    private static final String CULTUREINFOACTIVITY_TABLE_DROP =
        "DROP TABLE " +TABLE_CULTUREINFOACTIVITY+ ";";

    private static final String CULTUREINFOACTIVITY_TABLE_TEMP =
        "CREATE TEMP TABLE CultureInfoActivityAux AS SELECT * FROM " +TABLE_CULTUREINFOACTIVITY+ ";";

    private static final String CULTUREINFOACTIVITY_TABLE_INSERT =
        "INSERT INTO " +TABLE_CULTUREINFOACTIVITY+ " ("
                       +COLUMN1_CULTUREINFOACTIVITY_ACTIVITYID+ ", "
                       +COLUMN2_CULTUREINFOACTIVITY_CULTUREINFOID+ ") SELECT* FROM CultureInfoActivityAux;";


     /*              Queries related to CultureInfoZone table                */
    private static final String CULTUREINFOZONE_TABLE_CREATE =
         "CREATE TABLE " +TABLE_CULTUREINFOZONE+ "("
                         +COLUMN1_CULTUREINFOZONE_ZONEID+ " INTEGER,"
                         +COLUMN2_CULTUREINFOZONE_CULTUREINFOID+ " INTEGER,"
                         + " FOREIGN KEY (" +COLUMN1_CULTUREINFOZONE_ZONEID+ ") REFERENCES " +TABLE_ZONE+ "(" +COLUMN1_ZONE_ID+ "),"
                         + " FOREIGN KEY (" +COLUMN2_CULTUREINFOZONE_CULTUREINFOID+ ") REFERENCES " +TABLE_CULTUREINFO+ "(" +COLUMN1_CULTUREINFO_ID+ ")); ";

    private static final String CULTUREINFOZONE_TABLE_DROP =
        "DROP TABLE " +TABLE_CULTUREINFOZONE+ ";";

    private static final String CULTUREINFOZONE_TABLE_TEMP =
            "CREATE TEMP TABLE CultureInfoZoneAux AS SELECT * FROM " +TABLE_CULTUREINFOZONE+ ";";

    private static final String CULTUREINFOZONE_TABLE_INSERT =
        "INSERT INTO " +TABLE_CULTUREINFOZONE+ " ("
                       +COLUMN1_CULTUREINFOZONE_ZONEID+ ", "
                       +COLUMN2_CULTUREINFOZONE_CULTUREINFOID+ ") SELECT* FROM CultureInfoZoneAux;";

    /*              Queries related to Zone table                */
    private static final String ZONE_TABLE_CREATE =
        "CREATE TABLE " +TABLE_ZONE+ "("
                        +COLUMN1_ZONE_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +COLUMN2_ZONE_NAME+ " VARCHAR(30),"
                        +COLUMN3_ZONE_DESCRIPTION+" VARCHAR(200));";

    private static final String ZONE_TABLE_DROP =
        "DROP TABLE " +TABLE_ZONE+ ";";

    private static final String ZONE_TABLE_TEMP =
        "CREATE TEMP TABLE ZoneAux AS SELECT * FROM " +TABLE_ZONE+ ";";

    private static final String ZONE_TABLE_INSERT =
        "INSERT INTO " +TABLE_ZONE+ " ("
                       +COLUMN1_ZONE_ID+ ", "
                       +COLUMN2_ZONE_NAME+ ", "
                       +COLUMN3_ZONE_DESCRIPTION+ ") SELECT* FROM ZoneAux;";

    DataBaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(CULTURE_TABLE_CREATE);
        db.execSQL(PLAGUE_TABLE_CREATE);
        db.execSQL(ACTIVITY_TABLE_CREATE);
        db.execSQL(CULTUREINFO_TABLE_CREATE);
        db.execSQL(CULTUREREGISTRY_TABLE_CREATE);
        db.execSQL(PLAGUECULTURE_TABLE_CREATE);
        db.execSQL(CULTUREINFOACTIVITY_TABLE_CREATE);
        db.execSQL(ZONE_TABLE_CREATE);
        db.execSQL(CULTUREINFOZONE_TABLE_CREATE);

        insertAllCultures(db);
        insertAllZones(db);
        insertAllActivities(db);
        insertAllInfoCultureActivities(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL(CULTURE_TABLE_TEMP);
        db.execSQL(CULTURE_TABLE_DROP);
        db.execSQL(CULTURE_TABLE_CREATE);
        db.execSQL(CULTURE_TABLE_INSERT);

        db.execSQL(CULTUREREGISTRY_TABLE_TEMP);
        db.execSQL(CULTUREREGISTRY_TABLE_DROP);
        db.execSQL(CULTUREREGISTRY_TABLE_CREATE);
        db.execSQL(CULTUREREGISTRY_TABLE_INSERT);

        db.execSQL(CULTUREINFO_TABLE_TEMP);
        db.execSQL(CULTUREINFO_TABLE_DROP);
        db.execSQL(CULTUREINFO_TABLE_CREATE);
        db.execSQL(CULTUREINFO_TABLE_INSERT);

        db.execSQL(ACTIVITY_TABLE_TEMP);
        db.execSQL(ACTIVITY_TABLE_DROP);
        db.execSQL(ACTIVITY_TABLE_CREATE);
        db.execSQL(ACTIVITY_TABLE_INSERT);

        db.execSQL(PLAGUE_TABLE_TEMP);
        db.execSQL(PLAGUE_TABLE_DROP);
        db.execSQL(PLAGUE_TABLE_CREATE);
        db.execSQL(PLAGUE_TABLE_INSERT);

        db.execSQL(PLAGUECULTURE_TABLE_TEMP);
        db.execSQL(PLAGUECULTURE_TABLE_DROP);
        db.execSQL(PLAGUECULTURE_TABLE_CREATE);
        db.execSQL(PLAGUECULTURE_TABLE_INSERT);

        db.execSQL(CULTUREINFOACTIVITY_TABLE_TEMP);
        db.execSQL(CULTUREINFOACTIVITY_TABLE_DROP);
        db.execSQL(CULTUREINFOACTIVITY_TABLE_CREATE);
        db.execSQL(CULTUREINFOACTIVITY_TABLE_INSERT);

        db.execSQL(ZONE_TABLE_TEMP);
        db.execSQL(ZONE_TABLE_DROP);
        db.execSQL(ZONE_TABLE_CREATE);
        db.execSQL(ZONE_TABLE_INSERT);

        db.execSQL(CULTUREINFOZONE_TABLE_TEMP);
        db.execSQL(CULTUREINFOZONE_TABLE_DROP);
        db.execSQL(CULTUREINFOZONE_TABLE_CREATE);
        db.execSQL(CULTUREINFOZONE_TABLE_INSERT);
    }

    private void insertAllZones(SQLiteDatabase db){
        //private final String []zone = new String[]{"Normal", "Estufa", "Horta","Jardim"};
        insertZone(db, zone[0], "Qualquer zona");
        insertZone(db, zone[1], "Plantar em estufa");
        insertZone(db, zone[2], "Plantar na horta");
        insertZone(db, zone[3], "Plantar no Jardim");
    }

    private void insertZone(SQLiteDatabase db, String name, String description){
        db.execSQL("INSERT INTO " + TABLE_ZONE + "(" + COLUMN2_ZONE_NAME + "," + COLUMN3_ZONE_DESCRIPTION + ") VALUES (\"" + name + "\", \"" + description + "\");");
    }

    private void insertAllCultures(SQLiteDatabase db){

        insertCulture(db, "Arroz");
        insertCulture(db, "Soja");
        insertCulture(db, "Agriões");
        insertCulture(db, "Melão de Inverno");
        insertCulture(db, "Cebola Verde");
        insertCulture(db, "Aipo");
        insertCulture(db, "Couve Rábano");
        insertCulture(db, "Chicória");
        insertCulture(db, "Feijão Verde");
        insertCulture(db, "Beterraba Roxa");
        insertCulture(db, "Beldroegas");
        insertCulture(db, "Feijão de Trepar");
        insertCulture(db, "Feijão Anão");
        insertCulture(db, "Hortaliça");
        insertCulture(db, "Acelgas");
        insertCulture(db, "Alface Romana");
        insertCulture(db, "Chicória Amarga");
        insertCulture(db, "Repolho de Inverno");
        insertCulture(db, "Oliveira");
        insertCulture(db, "Cereais Praganosos");
        insertCulture(db, "Pessegueiro");
        insertCulture(db, "Laranjeira");
        insertCulture(db, "Amendoeira");
        insertCulture(db, "Medronheiro");
        insertCulture(db, "Castanheiro");
        insertCulture(db, "Noz");
        insertCulture(db, "Aveleira");
        insertCulture(db, "Cerejeiro");
        insertCulture(db, "Pereiro");
        insertCulture(db, "Macieira");
        insertCulture(db, "Couve Temporã");
        insertCulture(db, "Tremoçeiro");
        insertCulture(db, "Batata Temporã");
        insertCulture(db, "Figueira");
        insertCulture(db, "Batata");
        insertCulture(db, "Favas");
        insertCulture(db, "Ervilhas");
        insertCulture(db, "Centeio");
        insertCulture(db, "Couve Galega");
        insertCulture(db, "Nabos");
        insertCulture(db, "Nabiças");
        insertCulture(db, "Rabanetes");
        insertCulture(db, "Salsa");
        insertCulture(db, "Tomate");
        insertCulture(db, "Abóbora");
        insertCulture(db, "Cenoura");
        insertCulture(db, "Feijão");
        insertCulture(db, "Pepino");
        insertCulture(db, "Melão");
        insertCulture(db, "Pimento");
        insertCulture(db, "Alho");
        insertCulture(db, "Alface");
        insertCulture(db, "Alho Porro");
        insertCulture(db, "Couve Flor");
        insertCulture(db, "Couve");
        insertCulture(db, "Repolho");
        insertCulture(db, "Melancia");
        insertCulture(db, "Milho de Sequeiro");
        insertCulture(db, "Cebola");
        insertCulture(db, "Espinafres");
        insertCulture(db, "Bróculos");
        insertCulture(db, "Damasqueiro");
        insertCulture(db, "Morangueiro");
        insertCulture(db, "Pinheiro Bravo");
        insertCulture(db, "Alho Francês");
        insertCulture(db, "Beterraba");
        insertCulture(db, "Coentros");
        insertCulture(db, "Serôdia");
        insertCulture(db, "Couve de Grelos");
        insertCulture(db, "Couve Nabo");
        insertCulture(db, "Espargos");
        insertCulture(db, "Nabos Serôdios");
        insertCulture(db, "Segurelha");
        insertCulture(db, "Couve de Bruxelas");
        insertCulture(db, "Milho");
        insertCulture(db, "Batata de Regadio");
        insertCulture(db, "Trigo");
        insertCulture(db, "Aveia");
        insertCulture(db, "Cevada");
        insertCulture(db, "Cebolinho");
        insertCulture(db, "Azeda");
        insertCulture(db, "Abóbora Trepadeira");
        insertCulture(db, "Alcachofra");
        insertCulture(db, "Rosas");
        insertCulture(db, "Malmequeres");
        insertCulture(db, "Margaridas");
        insertCulture(db, "Lilases");
        insertCulture(db, "Girassóis");
        insertCulture(db, "Goivo");
        insertCulture(db, "Narciso");
        insertCulture(db, "Campainhas Brancas");
        insertCulture(db, "Chagas");
        insertCulture(db, "Bocas de Lobo");
        insertCulture(db, "Cravos");
        insertCulture(db, "Begónias");
        insertCulture(db, "Ervilhas de Cheiro");
        insertCulture(db, "Gipsófilas");
        insertCulture(db, "Lírios");
        insertCulture(db, "Paciências");
        insertCulture(db, "Flor de Lis");
        insertCulture(db, "Violetas");
        insertCulture(db, "Amores Perfeitos");
        insertCulture(db, "Camélias");
        insertCulture(db, "Jacintos");
        insertCulture(db, "Tulipas");
        insertCulture(db, "Manjericos");
        insertCulture(db, "Cidreira");
        insertCulture(db, "Trepadeiras");
        insertCulture(db, "Calêndolas");
        insertCulture(db, "Cinerárias");
        insertCulture(db, "Dálias");
        insertCulture(db, "Gladiolos");
        insertCulture(db, "Iris");
        insertCulture(db, "Ciclames");
        insertCulture(db, "Grão de Bico");
    }

    private void insertCulture(SQLiteDatabase db, String name){
        db.execSQL("INSERT INTO " +TABLE_CULTURE+ "(" +COLUMN2_CULTURE_NAME+ ") VALUES (\"" +name+"\");");
    }

    private void insertAllActivities(SQLiteDatabase db){

        insertActivity(db, "Podar", "Cortar ramos, rama ou braços inúteis.");
        insertActivity(db, "Lavrar", "Lavrar a terra");
        insertActivity(db, "Enxertar", "Unir tecidos de duas plantas.");
        insertActivity(db, "Semear", "Colocar semente debaixo da terra.");
        insertActivity(db, "Transplantar", "Mudar a planta de local.");
        insertActivity(db, "Colheita", "Colher a cultura.");
        insertActivity(db, "Proteger", "Proteger a cultura das condições atmosféricas.");
        insertActivity(db, "Resinar", "Retirar a resina das árvores.");
        insertActivity(db, "Colocar Estacas", "Preparar as estacas para culturas.");
        insertActivity(db, "Mondar", "Arrancar ervas nocivas.");
        insertActivity(db, "Regar", "Deitar água nas culturas.");
        insertActivity(db, "Estrumar", "Colocar estrume no terreno.");
        insertActivity(db, "Ceifa", "Retirar ervas utilizando um instrumento adequado.");
        insertActivity(db, "Cuidar das culturas", "Tirar folhas secas, limpar, etc.");
        insertActivity(db, "Debulha ", "Tirar os grãos.");
        insertActivity(db, "Secar", "Colocar cultura ao sol a secar.");
        insertActivity(db, "Arejar", "Dar espaço.");
        insertActivity(db, "Arrotear", "Limpar/Cortar o mato para plantar.");
        insertActivity(db, "Cortar", "Cortar Madeira.");
        insertActivity(db, "Limpeza", "Limpar lagar.");
        insertActivity(db, "Mergulhia das vinhas", "Tipo de multiplicação vegetativa que consiste em dobrar um ramo da planta-mãe até enterrá-lo no solo. Quando está enraizada pode separar-se da planta-mãe.");

    }

    private void insertActivity(SQLiteDatabase db, String name, String description){
        db.execSQL("INSERT INTO " +TABLE_ACTIVITY+ "(" +COLUMN2_ACTIVITY_NAME+ ", " +COLUMN3_ACTIVITY_DESCRIPTION+") VALUES (\"" +name+ "\", \""  +description+ "\");");
    }

    private void insertAllInfoCultureActivities(SQLiteDatabase db){
        long cultureInfoID;
    /*  String nothing = "Qualquer";
        String []zone = new String[]{"Normal", "Estufa", "Horta","Jardim"};
        String []place = new String[]{"Sul", "Norte/Centro"};
        String []moons = new String[]{"Minguante", "Crescente"};
        String []months = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    */
        cultureInfoID = insertInfoCulture(db, "Nabiças", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Melancia", months[1], nothing, nothing ,0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Favas", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Espinafres", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Ervilhas", months[1], nothing, nothing, 0);
        insertZoneInfo(db, zone[2], cultureInfoID);
        insertActivityInfo(db, "Semear", cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Espargos", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Couve Nabo", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Couve de Grelos", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Couve Flor", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Coentros", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Cebola", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Beterraba", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Alho Francês", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Pinheiro Bravo", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Melancia", months[1], nothing, place[0], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Pepino", months[1], nothing, place[0], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Ervilhas", months[1], nothing, place[0], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Cenoura", months[1], nothing, place[0], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Abóbora", months[1], nothing, place[0], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Tomate", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Feijão", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Repolho", months[1], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Alface", months[1], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Alho Porro", months[1], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Pimento", months[1], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Nabiças", months[1], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Nabos", months[1], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Couve", months[1], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Alface", months[1], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Tulipas", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Colheita", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Jacintos", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Colheita", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Camélias", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Colheita", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Amores Perfeitos", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Colheita", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Violetas", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Colheita", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Flor de Lis", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Paciências", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Lírios", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Girassóis", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Gipsófilas", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Ervilhas de Cheiro", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Begónias", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[3], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Grão de Bico", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Ervilhas", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Favas", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Rabanetes", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Couve", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Alface Romana", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[2], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Abóbora", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[1], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Pimento", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[1], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Pepino", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[1], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Melão", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Tomate", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[1], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Feijão", months[0], nothing, place[0], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertActivityInfo(db, "Transplantar", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Cenoura", months[0], nothing, place[0], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Abóbora", months[0], nothing, place[0], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Tomate", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Salsa", months[0], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Rabanetes", months[0], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Nabiças", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Nabos", months[0], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Couve Galega", months[0], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Centeio", months[0], nothing, place[1], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Ervilhas", months[0], nothing, place[0], 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Batata", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertActivityInfo(db, "Lavrar", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Figueira", months[0], moons[0], nothing, 0);
        insertActivityInfo(db, "Podar", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Laranjeira", months[0], moons[0], nothing, 0);
        insertActivityInfo(db, "Podar", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Macieira", months[0], moons[0], nothing, 0);
        insertActivityInfo(db, "Podar", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Figueira", months[0], moons[1], nothing, 0);
        insertActivityInfo(db, "Enxertar", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Laranjeira", months[0], moons[1], nothing, 0);
        insertActivityInfo(db, "Enxertar", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Macieira", months[0], moons[1], nothing, 0);
        insertActivityInfo(db, "Enxertar", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

        cultureInfoID = insertInfoCulture(db, "Favas", months[0], nothing, nothing, 0);
        insertActivityInfo(db, "Semear", cultureInfoID);
        insertActivityInfo(db, "Transplantar", cultureInfoID);
        insertZoneInfo(db, zone[0], cultureInfoID);

    }

    private long insertInfoCulture(SQLiteDatabase db, String cultureName, String month, String moonFase, String countryZone, int week){
        ContentValues newCV = new ContentValues();
        newCV.put(COLUMN2_CULTUREINFO_CULTUREID,getCultureID(db,cultureName));
        newCV.put(COLUMN3_CULTUREINFO_MONTH, month);
        newCV.put(COLUMN4_CULTUREINFO_MOON, moonFase);
        newCV.put(COLUMN5_CULTUREINFO_ZONE, countryZone);
        newCV.put(COLUMN6_CULTUREINFO_WEEK, week);
        return  db.insert(TABLE_CULTUREINFO, null, newCV );
    }

    private void insertActivityInfo(SQLiteDatabase db, String name, long infoCultureID){
        db.execSQL("INSERT INTO " + TABLE_CULTUREINFOACTIVITY + "(" + COLUMN1_CULTUREINFOACTIVITY_ACTIVITYID + ", " + COLUMN2_CULTUREINFOACTIVITY_CULTUREINFOID + ") VALUES (\"" + getActivityID(db, name) + "\", \"" + infoCultureID + "\");");
    }

    private void insertZoneInfo(SQLiteDatabase db, String name, long infoCultureID){
        db.execSQL("INSERT INTO " + TABLE_CULTUREINFOZONE + "(" + COLUMN1_CULTUREINFOZONE_ZONEID + ", " + COLUMN2_CULTUREINFOZONE_CULTUREINFOID + ") VALUES (\"" + getZoneID(db, name) + "\", \"" + infoCultureID + "\");");
    }

    public String[] getListCultureNames(SQLiteDatabase db){
        Cursor newCursor = db.query(TABLE_CULTURE, new String[]{COLUMN2_CULTURE_NAME},null,null,null,null,null);
        if(newCursor.getCount() > 0){
            String[] list = new String[newCursor.getCount()];
            //  newCursor.moveToFirst();
            int i = 0;
            while(newCursor.moveToNext()){
                list[i] = newCursor.getString(0);
                i++;
            }

            newCursor.close();
            return list;
        }
        return new String[]{};
    }

    public int getCultureID(SQLiteDatabase db, String name){
        Cursor newCursor = db.rawQuery("SELECT " + COLUMN1_CULTURE_ID + " FROM " + TABLE_CULTURE + " WHERE " + COLUMN2_CULTURE_NAME + "=?;", new String[]{name}, null);
        newCursor.moveToFirst();
        return newCursor.getInt(newCursor.getColumnIndex(COLUMN1_CULTURE_ID));
    }

    public int getActivityID(SQLiteDatabase db, String name){
        Cursor newCursor = db.rawQuery("SELECT " + COLUMN1_ACTIVITY_ID + " FROM " + TABLE_ACTIVITY + " WHERE " + COLUMN2_ACTIVITY_NAME + "=?;", new String[]{name}, null);
        newCursor.moveToFirst();
        return newCursor.getInt(newCursor.getColumnIndex(COLUMN1_ACTIVITY_ID));
    }

    public int getZoneID(SQLiteDatabase db, String name){
        Cursor newCursor = db.rawQuery("SELECT " + COLUMN1_ZONE_ID + " FROM " + TABLE_ZONE + " WHERE " + COLUMN2_ZONE_NAME + "=?;", new String[]{name}, null);
        newCursor.moveToFirst();
        return newCursor.getInt(newCursor.getColumnIndex(COLUMN1_ZONE_ID));
    }

    public  boolean insertCultureRegistry(SQLiteDatabase db, String name,String seedDate, String gps){
        Long resultado;

        ContentValues newCV = new ContentValues();
        newCV.put(COLUMN2_CULTUREREGISTRY_CULTUREID, getCultureID(db,name));
        newCV.put(COLUMN3_CULTUREREGISTRY_DATE, seedDate);
        newCV.put(COLUMN4_CULTUREREGISTRY_GPS, gps);
        resultado=db.insert(TABLE_CULTUREREGISTRY, null, newCV);
        if ( resultado==-1)
            return false;
        else
            return true;

    }

    public void listCultureRegistry(SQLiteDatabase db){
        Cursor newCursor = db.query(TABLE_CULTUREREGISTRY, new String[]{COLUMN1_CULTUREREGISTRY_ID, COLUMN2_CULTUREREGISTRY_CULTUREID, COLUMN3_CULTUREREGISTRY_DATE, COLUMN4_CULTUREREGISTRY_GPS },null,null,null,null,null);
        while(newCursor.moveToNext()){
            Log.d("Table CultReistry", "\n\n---------Nova Linha-------");
            Log.d("Table CultRegistry", "ID: " + newCursor.getString(0) );
            Log.d("Table CultRegistry", "CultID: " + newCursor.getString(1) );
            Log.d("Table CultReistry", "Date : " + newCursor.getString(3) );
            Log.d("Table CultReistry", "GPS: " + newCursor.getString(4) );
        }

    }
}
