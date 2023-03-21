import controller.EpisodeController;
import controller.TvSerieController;
import data.TvSerieDataRepository;
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

        //create a GET response to port 8080 root or home page
        // home = /
        // get() takes annon class created from an interface as 2nd argument
        //we can override the handle() in Handler interface to send response to client
        //here we are sending simple "Hello World!" text as response to request to home page.
        app.get("/", new VueComponent("hello-world"));

        //oppgave 2.6 - vue component paths.
        //This connects front-end(vue) with the backend(javalin here).
        //this front end path is a response which returns list of TVseries
        app.get("/tvserie", new VueComponent("tvserie-overview"));

        //this path retuns specific season number in specific TVSerie
        //in oppgave 2.7 episodes path is working so Vue can fetch() episodes too correctly.
        app.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}",new VueComponent("tvserie-detail"));

        //api paths. Front-end takes data from these paths
        // resource for controller logic is from lecture and Lars's github repo for prog2
        TvSerieDataRepository data = new TvSerieDataRepository();

        TvSerieController controller = new TvSerieController(data);
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

        //oppgave 2.7

        //get all episodes API path
        //instance of TVserieDataRepo
        TvSerieDataRepository episodesData = new TvSerieDataRepository();

        //instance of EpisodeController which can use the TVSerieDatarepository object using the interface in controller class constructor
        EpisodeController episodeController = new EpisodeController(episodesData);
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




    }//end of main method
}
