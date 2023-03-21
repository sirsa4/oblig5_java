<template id="episode-update" @episode-submitted="episodeUpdate">
    <div class="form-style">
        <h2>Oppdaterer S{{gammeltSesongNummer}}E{{gammeltEpisodeNummer}} i {{tvserieTittel}}</h2>
        <form class="create" @submit="checkForm" :action=`/api/tvserie/${tvserieTittel}/sesong/${gammeltSesongNummer}/episode/${gammeltEpisodeNummer}/updateepisode` method="post">
            <div v-if="errors.length">
                <b>Vennligst rett følgende feil:</b>
                <ul>
                    <li v-for="error in errors">{{ error }}</li>
                </ul>
            </div>

          <input type="hidden" id="gammeltSesongNummer" name="gammeltSesongNummer" v-model="gammeltSesongNummer">
          <input type="hidden" id="gammeltEpisodeNummer" name="gammeltEpisodeNummer" v-model="gammeltEpisodeNummer">

            <p>
                <label for="episode.tittel">Tittel</label>
                <input type="text" name="tittel" id="tittel" v-model="episode.tittel" />
            </p>
            <p>
              <label for="sesongNummer">Sesongnummer</label>
              <input type="number" name="sesongNummer" id="sesongNummer" v-model="episode.sesongNummer" min="1" />
            </p>
            <p>
              <label for="episodeNummer">Episodenummer</label>
              <input type="number" name="episodeNummer" id="episodeNummer" v-model="episode.episodeNummer" min="1" />
            </p>
            <p>
              <label for="episode.beskrivelse">Beskrivelse</label>
              <input type="text" name="beskrivelse" id="beskrivelse" v-model="episode.beskrivelse" />
            </p>

            <p>
                <label for="episode.spilletid">Spilletid</label>
                <input type="number" step="1" name="spilletid" id="spilletid" v-model="episode.spilletid" min="0" />
            </p>
            <p>
              <label for="utgivelsesdato">Utgivelsesdato</label>
              <input type="date" name="utgivelsesdato" id="utgivelsesdato" v-model="utgivelsesdato" />
            </p>
            <p>
                <label for="episode.bildeUrl">Bilde Url</label>
                    <input type="url" name="bildeUrl" id="bildeUrl" v-model="episode.bildeUrl" />
            </p>
            <p>
                <input type="submit" value="Oppdater Episode">
            </p>

        </form>
    </div>

</template>
<script>
    app.component("episode-update", {
        template: "#episode-update",
        data: () => ({
            tvserieTittel: null,
            gammeltEpisodeNummer: null,
            gammeltSesongNummer: null,
            utgivelsesdato: "2017-3-2",
            episode: null,
            errors: []
        }),
        created() {
            const tvserieId = this.$javalin.pathParams["tvserie-id"];
            const sesongNr = this.$javalin.pathParams["sesong-nr"];
            const episodeNr = this.$javalin.pathParams["episode-nr"];

            this.tvserieTittel = tvserieId;
            this.gammeltEpisodeNummer = episodeNr;
            this.gammeltSesongNummer = sesongNr;

            fetch(`/api/tvserie/${tvserieId}/sesong/${sesongNr}/episode/${episodeNr}`)
                .then(res => res.json())
                .then(res => this.episode = res)
                .then(res => this.utgivelsesdato = res.utgivelsesdato[0] + "-" + (('00'+res.utgivelsesdato[1]).slice(-2)) + "-" + (('00'+res.utgivelsesdato[2]).slice(-2)))
                .catch(() => alert("Feil ved uthenting av episode.\n" +
                    "Har du laget en get-path i formatet: \napi/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}\n" +
                    "som gir data om en Episode?"));
        },
        methods:{
            checkForm:function(e) {
                const urlRegex = "/^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\\.(?:[a-z\u00a1-\uffff]{2,})))(?::\\d{2,5})?(?:[/?#]\\S*)?$/i";
                if(this.episode.tittel && this.episode.sesongNummer && this.episode.episodeNummer) return true;
                this.errors = [];
                if (!this.episode.tittel) this.errors.push("Tittel påkrevd");
                if (!this.episode.sesongNummer) this.errors.push("Sesongnummer påkrevd");
                if (!this.episode.episodeNummer) this.errors.push("Episodenummer påkrevd");
                e.preventDefault();
            }
        }
    });
</script>
<style>
    .form-style{
        font-family: 'Open Sans Condensed', arial, sans;
        width: 500px;
        padding: 30px;
        background: #191919;
        margin: 50px auto;
        box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.22);
        -moz-box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.22);
        -webkit-box-shadow:  0px 0px 15px rgba(0, 0, 0, 0.22);
        border: #dddddd;
    }
    .form-style h2{
        background: #4D4D4D;
        font-family: 'Open Sans Condensed', sans-serif;
        color: #FFFFFF;
        font-size: 18px;
        font-weight: 100;
        padding: 20px;
        margin: -30px -30px 30px -30px;
    }
    .form-style input[type="text"],
    .form-style input[type="date"],
    .form-style input[type="datetime"],
    .form-style input[type="email"],
    .form-style input[type="number"],
    .form-style input[type="search"],
    .form-style input[type="time"],
    .form-style input[type="url"],
    .form-style input[type="password"],
    .form-style textarea,
    .form-style select
    {
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        outline: none;
        display: block;
        width: 100%;
        padding: 7px;
        border: none;
        color: white;
        border-bottom: 1px solid #ddd;
        background: transparent;
        margin-bottom: 10px;
        font: 16px Arial, Helvetica, sans-serif;
        height: 45px;
    }
    .form-style textarea{
        resize:none;
        overflow: hidden;
    }
    .form-style input[type="button"],
    .form-style input[type="submit"]{
        background: none;
        display: inline-block;
        cursor: pointer;
        font-family: 'Open Sans Condensed', sans-serif;
        font-size: 14px;
        text-decoration: none;
        text-transform: uppercase;
        padding: 10px;
        margin: 10px;
        border: 1px solid white;
        color: white;
        border-radius: 15px;
    }
    .form-style input[type="button"]:hover,
    .form-style input[type="submit"]:hover {
        border: 2px solid white;
    }

    .create {
        color: white;
    }
</style>
