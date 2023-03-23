import controller.EpisodeController;
import controller.TvSerieController;
import data.TvSerieCSVRepository;
import data.TvSerieDataRepository;
import data.TvSerieJSONRepository;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.vue.VueComponent;
import org.jetbrains.annotations.NotNull;

public class Application {

    public static void main(String[] args) {

        //create Javalin server object
        //By default server runs on localhost port 8080. Jeg bruker port 3001
        Javalin app = Javalin.create(config -> {
            config.staticFiles.enableWebjars();
            config.vue.vueAppName = "app";
        }).start(3001);


        app.get("/", new VueComponent("hello-world"));


        app.get("/tvserie", new VueComponent("tvserie-overview"));


        app.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}",new VueComponent("tvserie-detail"));


        //oppgave 2.1 C
        //tvserie controller uses now the json data repository to get data from from
        TvSerieJSONRepository jsonData = new TvSerieJSONRepository("tvshows_10_with_roles.json");
        TvSerieCSVRepository csvData = new TvSerieCSVRepository("tvshows_10.csv",";");

        TvSerieController controller = new TvSerieController(csvData);
        app.get("/api/tvserie", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {

                controller.getTVSeries(context);

            }
        });

        //get specific TVSerie
        app.get("api/tvserie/{tvserie-id}", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {

                controller.getTVSerie(context);
            }
        });



        //Episode controller also using the json data repository to get data for episodes
        EpisodeController episodeController = new EpisodeController(csvData);

        app.get("/api/tvserie/{tvserie-id}/sesong/{sesong-id}", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                episodeController.getEpisodes(context);
            }
        });

        //Path to get single specific episode
        app.get("api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {

                episodeController.getSingleEpisode(context);


            }
        });

        //Vue path for single specific episode
        //This path uses the API path above this.
        app.get("tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}", new VueComponent("episode-detail"));

        //create episode vue front-end path
        app.get("create", new VueComponent("episode-create"));

        //create episode API path
        app.get("/api/tvserie/{tvserie-id}/createepisode", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                String serie = context.pathParam("tvserie-id");
                System.out.println(context.body());
            }
        });



    }//end of main method
}
