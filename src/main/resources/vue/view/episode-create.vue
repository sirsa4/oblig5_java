<template id="episode-create" @episode-submitted="episodeCreate">
    <div class="form-style">
        <h2>Oppretter S{{sesongNummer}}E{{episodeNummer}} i {{tvserieTittel}}</h2>
        <form class="create" @submit="checkForm" :action=`/api/tvserie/${tvserieTittel}/createepisode` method="post">
            <div v-if="errors.length">
                <b>Vennligst rett følgende feil:</b>
                <ul>
                    <li v-for="error in errors">{{ error }}</li>
                </ul>
            </div>

            <p>
                <label for="tittel">Tittel</label>
                <input type="text" name="tittel" id="tittel" v-model="tittel" />
            </p>
            <p>
              <label for="sesongNummer">Sesongnummer</label>
              <input type="number" name="sesongNummer" id="sesongNummer" v-model="sesongNummer" min="1" />
            </p>
          <p>
            <label for="episodeNummer">Episodenummer</label>
            <input type="number" name="episodeNummer" id="episodeNummer" v-model="episodeNummer" min="1" />
          </p>

          <p>
            <label for="beskrivelse">Beskrivelse</label>
            <input type="text" name="beskrivelse" id="beskrivelse" v-model="beskrivelse" />
          </p>

            <p>
                <label for="spilletid">Spilletid</label>
                <input type="number" step="1" name="spilletid" id="spilletid" v-model="spilletid" min="0" />
            </p>
            <p>
              <label for="utgivelsesdato">Utgivelsesdato</label>
              <input type="date" name="utgivelsesdato" id="utgivelsesdato" v-model="utgivelsesdato" />
            </p>
            <p>
                <label for="bildeUrl">Bilde Url</label>
                    <input type="url" name="bildeUrl" id="bildeUrl" v-model="bildeUrl" />
            </p>

            <p>
                <input type="submit" value="Opprett Episode">
            </p>

        </form>
    </div>

</template>
<script>

    app.component("episode-create", {
        template: "#episode-create",
        data: () => ({
            tvserieTittel: null,
            tittel: null,
            beskrivelse: null,
            episodeNummer: null,
            sesongNummer: null,
            spilletid: null,
            utgivelsesdato: null,
            bildeUrl: null,
            errors: []
        }),
        created() {
            this.tvserieTittel = this.$javalin.pathParams["tvserie-id"];
        },
        methods:{
            checkForm:function(e) {
                //const urlRegex = "/^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\\.(?:[a-z\u00a1-\uffff]{2,})))(?::\\d{2,5})?(?:[/?#]\\S*)?$/i";
                //const dateRegex = "/^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
                if(this.tittel && this.sesongNummer && this.episodeNummer) return true;
                this.errors = [];
                if (!this.tittel) this.errors.push("Tittel påkrevd");
                if (!this.sesongNummer) this.errors.push("Sesongnummer påkrevd");
                if (!this.episodeNummer) this.errors.push("Episodenummer påkrevd");
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
