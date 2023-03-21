package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.TVSerie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TvSerieJSONRepository {
    ArrayList<TVSerie> tvSeries;
    public TvSerieJSONRepository(String filePath) {

        //oppgave 2.1 a

        //object mapper
        ObjectMapper mapper = new ObjectMapper();

        //filePath
        File path = new File(filePath);

        //module to be able to read LocalDate object correctly. This is needed in order to be able get LocalDate
        mapper.registerModule(new JavaTimeModule());

        //read tvseries from json file
        try {
            TVSerie[] seriesFromJson = mapper.readValue(path,TVSerie[].class);

          //  ArrayList<TVSerie> serieArrayList = new ArrayList<>(Arrays.asList(seriesFromJson));
            tvSeries = new ArrayList<>(Arrays.asList(seriesFromJson));
            //System.out.println(tvSeries);
            for(TVSerie serie : tvSeries){
                System.out.println(serie);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //oppgave 2.1 read json again
    public ArrayList<TVSerie> readjson(String filePath){
        ObjectMapper mapper = new ObjectMapper();

        //filePath
        File path = new File(filePath);

        //module to be able to read LocalDate object correctly. This is needed in order to be able get LocalDate
        mapper.registerModule(new JavaTimeModule());

        //read tvseries from json filej
        try {
            TVSerie[] seriesFromJson = mapper.readValue(path,TVSerie[].class);

            //  ArrayList<TVSerie> serieArrayList = new ArrayList<>(Arrays.asList(seriesFromJson));
            return new ArrayList<>(Arrays.asList(seriesFromJson));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}// end of class
