<!--

Det må finnes get-metoder i klassen TvSerie som matcher dataene som forventes i denne filen.

TvSerie:
- tittel
- bildeUrl

 -->

<template id="tvserie-overview">
   <div>
        <h1>TvSerier</h1>
        <ul class="tvserie-overview-list">
            <li v-for="tvserie in tvserier">
                <a :href="`/tvserie/${tvserie.tittel}/sesong/1`" class="link-to-tvserie-details">
                    <div class="single-tvserie-container" >
                      <h2 v-if="tvserie.tittel">{{tvserie.tittel}}</h2>
                      <h2 v-else class="error">TvSerie må ha instansvariabelen "tittel"</h2>

                      <img v-if="tvserie?.bildeUrl" class="cover-image" v-bind:src="tvserie.bildeUrl">
                      <img v-else class="cover-image" src="https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn8AIqMGskD.jpg">
                    </div>
                </a>
            </li>
        </ul>
   </div>
</template>
<script>
    app.component("tvserie-overview", {
        template: "#tvserie-overview",
        data: () => ({
          tvserier: []
        }),
        created() {
            fetch("/api/tvserie")
                .then(res => res.json())
                .then(json => {
                   this.tvserier = json;
                  console.log(this.tvserier)
                })
                .catch(() => alert("Feil ved uthenting av TvSerier.\n" +
                    "Har du laget en get-path i formatet: /api/tvserie\n" +
                    "som gir en liste med TvSerier?"));
        }
    });
</script>
<style>
    li{
        list-style-type: none;
    }

    .tvserie-overview-list{
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
    }

    .tvserie-overview-list li{
        padding: 10px;
        border: 1px solid white;
        border-radius: 15px;
    }

    .link-to-tvserie-details{
        width: 400px;
        height:100px;
        text-decoration: none;
        color: white;
    }

    div.single-tvserie-container{
        overflow: hidden;
        width: 300px;
        background-color: #000000;
        margin: 0 auto;
        opacity: 0.96;
        color: #FFFFFF;
        text-align: center;
    }

    div.single-tvserie-container:hover{
        opacity: 1.0;
        overflow: hidden;
        -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        -moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
    }

    img.cover-image-frontpage {
        height: auto;
        width: 100%;
        padding-bottom: 20px;
        max-height: 280px;
    }

</style>