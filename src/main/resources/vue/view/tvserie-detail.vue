<!--

Det må finnes get-metoder i klassene TvSerie, Produksjon og Episode som matcher dataene som forventes i denne filen.

TvSerie:
- tittel
- beskrivelse
- antallSesonger
- bildeUrl

Produksjon:
- tittel
- bildeUrl

Episode:
- episodeNummer
- sesongNummer

 -->

<template id="tvserie-detail">
  <div class="content-wrapper">
    <div v-if="tvserie" class="detail-tvserie-container">
      <img v-if="tvserie.bildeUrl" class="cover-image" v-bind:src="tvserie.bildeUrl">
      <img v-else class="cover-image" src="https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn8AIqMGskD.jpg">

      <h1 v-if="tvserie.tittel">{{tvserie.tittel}}</h1>
      <h1 v-else class="error">TvSerie må ha get-metode for "tittel"</h1>
      <p v-if="tvserie.beskrivelse">{{tvserie.beskrivelse}}.</p>
      <p v-else>TvSerie må ha get-metode for "beskrivelse".</p>
      <p>{{tvserie.tittel}} har {{episoder.length}} episoder i sesong {{sesongNr}}.</p>
      <p><a class="button" class="add" :href="`/tvserie/${tvserie.tittel}/createepisode`">Opprett episode i sesong {{sesongNr}}</a></p>
    </div>

    <div v-if="tvserie" class="filter-menu">
      <ul class="buttonList">

        <p v-if="!tvserie.antallSesonger" class="error">TvSerie må ha get-metode for "antallSesonger"</p>
        <div v-for="nr in tvserie.antallSesonger" :key="sesongNr">
            <a v-if="nr == sesongNr" :href="`/tvserie/${tvserie.tittel}/sesong/${nr}`" class="active">
              <li>Sesong {{nr}}</li>
            </a>
            <a v-else :href="`/tvserie/${tvserie.tittel}/sesong/${nr}`">
              <li>Sesong {{nr}}</li>
            </a>

        </div>

      </ul>

      <ul class="buttonList">
        <div v-if="sortering=='episodenr'" class="active">   <li><a v-if="sesongNr" :href="`/tvserie/${tvserie.tittel}/sesong/${sesongNr}?sortering=episodenr`">Sorter episodenummer</a></li> </div>
        <div v-else>   <li><a v-if="sesongNr" :href="`/tvserie/${tvserie.tittel}/sesong/${sesongNr}?sortering=episodenr`">Sorter episodenummer</a></li></div>
        <div v-if="sortering=='tittel'" class="active">    <li><a v-if="sesongNr" :href="`/tvserie/${tvserie.tittel}/sesong/${sesongNr}?sortering=tittel`">Sorter alfabetisk</a></li> </div>
        <div v-else>    <li><a v-if="sesongNr" :href="`/tvserie/${tvserie.tittel}/sesong/${sesongNr}?sortering=tittel`">Sorter alfabetisk</a></li> </div>
        <div v-if="sortering=='spilletid'" class="active">   <li><a v-if="sesongNr" :href="`/tvserie/${tvserie.tittel}/sesong/${sesongNr}?sortering=spilletid`">Sorter på spilletid</a></li> </div>
        <div v-else>   <li><a v-if="sesongNr" :href="`/tvserie/${tvserie.tittel}/sesong/${sesongNr}?sortering=spilletid`">Sorter på spilletid</a></li> </div>
      </ul>
    </div>

    <h2>Episoder i sesong {{sesongNr}}</h2>

    <div v-if="!episoder[0]?.tittel" class="error">Produksjon må ha get-metode for "tittel"</div>
    <div v-if="!episoder[0]?.sesongNummer" class="error">Episode må ha get-metode for "sesongNummer"</div>
    <div v-if="!episoder[0]?.episodeNummer" class="error">Episode må ha get-metode for "episodeNummer"</div>

    <ul class="episode-overview-list">
      <li v-for="episode in episoder">
        <a v-if="tvserie" class="link-to-episode-details" :href="`/tvserie/${tvserie.tittel}/sesong/${episode.sesongNummer}/episode/${episode.episodeNummer}`">
          <div class="single-episode-container">
            <h1>E{{episode.episodeNummer}} - {{episode.tittel}}</h1>
            <img v-if="episode.bildeUrl" class="list-image" v-bind:src="episode.bildeUrl">
            <img v-else class="list-image" src="https://image.tmdb.org/t/p/w500/exT4NW9EdXG1qLZHKJnRpq3gh1H.jpg">
          </div>
        </a>
      </li>
    </ul>
  </div>
</template>
<script>
app.component("tvserie-detail", {
  template: "#tvserie-detail",
  data: () => ({
    tvserie: null,
    episoder: [],
    sesongNr: "1",
    sortering: "episodenr",
  }),
  created() {
    const tvserieId = this.$javalin.pathParams["tvserie-id"];
    const sesong = this.$javalin.pathParams["sesong-nr"];
    let sort = new URL(location.href).searchParams.get('sortering');
    if (sesong)
      this.sesongNr = sesong;
    if (sort)
      this.sortering = sort
    fetch(`/api/tvserie/${tvserieId}`)
        .then(res => res.json())
        .then(json => {
          this.tvserie = json
        })
        .catch(() => alert("Feil ved uthenting av TvSerie.\n" +
            "Har du laget en get-path i formatet: /api/tvserie/{tvserie-id}\n" +
            "som gir data om en TvSerie"))
    fetch(`/api/tvserie/${tvserieId}/sesong/${this.sesongNr}?sortering=${this.sortering}`)
        .then(res => res.json())
        .then(json => {this.episoder = json})
        .catch(() => alert("Feil ved uthenting av episoder.\n" +
            "Har du laget en get-path i formatet: /api/tvserie/{tvserie-id}/sesong/{sesong-nr}\n" +
            "som gir en liste med episoder?"));

  }
});
</script>
<style>

.episode-overview-list{
  color:black;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.buttonList{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin-top: 30px;
  margin-bottom: 30px;

}

.buttonList li{
  padding: 10px;
  margin: 10px;
  border: 1px solid white;
  color: white;
  border-radius: 15px;
}

.buttonList li a{
  color: white;
  text-decoration: none;
  font-weight: bold;
}

.buttonList li:hover{
  border: 2px solid white;
}


.filter-menu ul {
  list-style-type: none;
}

div.detail-tvserie-container{
  padding: 10px;
  overflow: hidden;
  width: 750px;
  margin: 0 auto;
  background-color: rgba(20, 20, 20, 0.98);
  opacity: 0.96;
  color: white;
  -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
  -moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
  box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
}

div.single-episode-container{
  overflow: hidden;
  color: white;
  background-color: rgba(0, 0, 0, 0.98);
  margin: 0 auto;
  opacity: 0.96;
  text-align: center;
}

img.cover-image {
  height: auto;
  width: 50%;
  margin: 5px;
  float: left;
}

img.list-image {
  width: 140px;
  padding-bottom: 20px;
  transition: transform .2s;
}

.list-image:hover{
  transform: scale(1.1);
}

.active li{
  background-color: darkslategrey;
  font-size: 18px;
}

h1{
  font-size:18px;
  padding-top: 10px;
  color: white;
}

h2{
  font-size:28px;
  padding: 10px;
  color: white;
  text-align: center;
}

.link-to-episode-details{
  width: 400px;
  height:100px;
  text-decoration: none;
  color:black;
}

.button {
  padding: 10px;
  margin: 10px;
  border: 1px solid white;
  color: white;
  border-radius: 15px;
}

button a{
  color: white;
  text-decoration: none;
  font-weight: bold;
}

.error {
  color: firebrick;
  font-size: 24px;
  text-align: center;
}

.button:hover{
  border: 2px solid white;
}

</style>
